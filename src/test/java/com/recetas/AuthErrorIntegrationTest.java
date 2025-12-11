package com.recetas;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthErrorIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void login_withShortPassword_returnsPasswordError() {
        Map<String, String> req = Map.of(
                "email", "noone@example.com",
                "password", "123"
        );
        ResponseEntity<String> resp = restTemplate.postForEntity("/api/auth/login", req, String.class);
        // Esperar respuesta de validación (422 Unprocessable Entity)
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resp.getStatusCode());
        String raw = resp.getBody();
        System.out.println("Response raw (short password): " + raw);
        assertNotNull(raw);
        Map<String, Object> body = null;
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            body = mapper.readValue(raw, new com.fasterxml.jackson.core.type.TypeReference<Map<String,Object>>(){});
            assertEquals("PASSWORD_ERROR", body.get("code"));
        } catch (Exception e) {
            fail("Failed to parse JSON response: " + e.getMessage());
        }
        assertNotNull(body);
        // Message should be present and errors list must exist
        assertTrue(body.get("message").toString().length() > 0);
        assertTrue(body.containsKey("errors"));
    }

    @Test
    public void login_withInvalidCredentials_returnsInvalidPassword() {
        Map<String, String> req = Map.of(
                "email", "doesnotexist@example.com",
                "password", "Password123"
        );
        ResponseEntity<String> resp = restTemplate.postForEntity("/api/auth/login", req, String.class);
        // Esperar 401 Unauthorized y código INVALID_PASSWORD
        assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
        String raw = resp.getBody();
        System.out.println("Response raw (invalid creds): " + raw);
        assertNotNull(raw);
        Map<String, Object> body = null;
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            body = mapper.readValue(raw, new com.fasterxml.jackson.core.type.TypeReference<Map<String,Object>>(){});
            // Cuando el email no está registrado, esperamos USER_NOT_FOUND
            assertEquals("USER_NOT_FOUND", body.get("code"));
        } catch (Exception e) {
            fail("Failed to parse JSON response: " + e.getMessage());
        }
        assertNotNull(body);
        // Preferir asserciones sobre `code` y `status`; el mensaje puede estar localizado, evitar comprobar su texto
        assertEquals(401, (int) body.get("status"));
    }

    private ResponseEntity<Map<String, Object>> postForMap(String url, Object body) {
        HttpEntity<Object> entity = new HttpEntity<>(body);
        return restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, Object>>() {
        });
    }
}
