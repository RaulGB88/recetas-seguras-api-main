package com.recetas;

import com.recetas.model.User;
import com.recetas.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void registerLoginAndAccessProtectedEndpoint() throws Exception {
        // Clean users
        userRepository.deleteAll();

        // Register
        Map<String, String> registerReq = Map.of(
            "email", "test@example.com",
            "username", "testuser",
            "password", "Password123"
        );

        ResponseEntity<Map<String, Object>> regResp = postForMap("/api/auth/register", registerReq);
        Assertions.assertEquals(HttpStatus.CREATED, regResp.getStatusCode());
        Map<String, Object> regBody = regResp.getBody();
        Assertions.assertNotNull(regBody);
        String accessToken = (String) regBody.get("accessToken");
        String refreshToken = (String) regBody.get("refreshToken");
        Assertions.assertNotNull(accessToken);
        Assertions.assertNotNull(refreshToken);

        // Access protected endpoint
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<User[]> usersResp = restTemplate.exchange("/api/users", HttpMethod.GET, entity, User[].class);
        Assertions.assertEquals(HttpStatus.OK, usersResp.getStatusCode());
        Assertions.assertNotNull(usersResp.getBody());
        Assertions.assertTrue(usersResp.getBody().length >= 1);

        // Login
        Map<String, String> loginReq = Map.of(
                "email", "test@example.com",
                "password", "Password123"
        );
        ResponseEntity<Map<String, Object>> loginResp = postForMap("/api/auth/login", loginReq);
        Assertions.assertEquals(HttpStatus.OK, loginResp.getStatusCode());
        Map<String, Object> loginBody = loginResp.getBody();
        Assertions.assertNotNull(loginBody);
        Assertions.assertNotNull(loginBody.get("accessToken"));

        // Refresh
        Map<String, String> refreshReq = Map.of("refreshToken", refreshToken);
        ResponseEntity<Map<String, Object>> refreshResp = postForMap("/api/auth/refresh", refreshReq);
        Assertions.assertEquals(HttpStatus.OK, refreshResp.getStatusCode());
        Map<String, Object> refreshBody = refreshResp.getBody();
        Assertions.assertNotNull(refreshBody);
        Assertions.assertNotNull(refreshBody.get("accessToken"));
    }

    private ResponseEntity<Map<String, Object>> postForMap(String url, Object body) {
        HttpEntity<Object> entity = new HttpEntity<>(body);
        return restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, Object>>() {
        });
    }

}
