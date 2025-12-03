package com.recetas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.recetas.dto.ConditionDto;
import com.recetas.dto.FoodDto;
import com.recetas.dto.RecipeDto;
import com.recetas.model.User;
import com.recetas.repository.ConditionRepository;
import com.recetas.repository.FoodRepository;
import com.recetas.repository.RecipeRepository;
import com.recetas.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConditionRepository conditionRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private com.recetas.repository.RecipeFoodRepository recipeFoodRepository;

    @BeforeEach
    public void setup() {
        recipeRepository.deleteAll();
        foodRepository.deleteAll();
        conditionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testSafeFoodsAndRecipesEndpoints() {
        String email = "safe@example.com";
        String username = "safeuser";
        String password = "Password123";

        FoodDto food1 = new FoodDto(); food1.name = "Manzana"; food1.category = "OTHER";
        FoodDto food2 = new FoodDto(); food2.name = "Leche"; food2.category = "LACTEO";
        FoodDto food3 = new FoodDto(); food3.name = "Carne"; food3.category = "CARNE";
        var foodEntity1 = new com.recetas.model.Food();
        foodEntity1.setName(food1.name);
        foodEntity1.setCategory(com.recetas.model.Food.FoodCategory.OTHER);
        var f1 = foodRepository.save(foodEntity1);

        var foodEntity2 = new com.recetas.model.Food();
        foodEntity2.setName(food2.name);
        foodEntity2.setCategory(com.recetas.model.Food.FoodCategory.LACTEO);
        var f2 = foodRepository.save(foodEntity2);

        var foodEntity3 = new com.recetas.model.Food();
        foodEntity3.setName(food3.name);
        foodEntity3.setCategory(com.recetas.model.Food.FoodCategory.CARNE);
        var f3 = foodRepository.save(foodEntity3);

        ConditionDto condDto = new ConditionDto(); condDto.name = "Intolerancia a la lactosa"; condDto.conditionType = "INTOLERANCE";
        var cond = new com.recetas.model.Condition(); cond.setName(condDto.name); cond.setConditionType(com.recetas.model.Condition.ConditionType.INTOLERANCE);
        cond.setFoods(Set.of(f2));
        cond = conditionRepository.save(cond);

        Map<String, String> registerReq = Map.of(
            "email", email,
            "username", username,
            "password", password
        );
        org.springframework.core.ParameterizedTypeReference<Map<String, Object>> typeRef = new org.springframework.core.ParameterizedTypeReference<>() {};
        org.springframework.http.RequestEntity<Map<String, String>> regRequest = org.springframework.http.RequestEntity
            .post(java.net.URI.create("/api/auth/register"))
            .accept(org.springframework.http.MediaType.APPLICATION_JSON)
            .body(registerReq);
        ResponseEntity<Map<String, Object>> regResp = restTemplate.exchange(regRequest, typeRef);
        Assertions.assertEquals(HttpStatus.CREATED, regResp.getStatusCode());
        Map<String, Object> regBody = regResp.getBody();
        Assertions.assertNotNull(regBody);
        Object tokenObj = regBody.get("accessToken");
        Assertions.assertNotNull(tokenObj);
        String accessToken = tokenObj.toString();
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<User[]> usersResp = restTemplate.exchange("/api/users", HttpMethod.GET, entity, User[].class);
        Assertions.assertEquals(HttpStatus.OK, usersResp.getStatusCode());
        Assertions.assertNotNull(usersResp.getBody());
        User user = Arrays.stream(usersResp.getBody())
            .filter(u -> email.equals(u.getEmail()))
            .findFirst()
            .orElseThrow();
        Map<String, Object> condBody = Map.of("conditionIds", List.of(cond.getId()));
        HttpEntity<Map<String, Object>> condEntity = new HttpEntity<>(condBody, headers);
        ResponseEntity<ConditionDto[]> condResp = restTemplate.exchange(
            "/api/users/" + user.getId() + "/conditions",
            HttpMethod.POST,
            condEntity,
            ConditionDto[].class
        );
        Assertions.assertEquals(HttpStatus.OK, condResp.getStatusCode());

        user = userRepository.findById(user.getId()).orElseThrow();

        var receta1 = new com.recetas.model.Recipe(); receta1.setTitle("Ensalada de manzana"); receta1.setDescription("Solo manzana"); receta1.setSteps("Cortar y servir");
        receta1 = recipeRepository.save(receta1);

        var receta2 = new com.recetas.model.Recipe(); receta2.setTitle("Leche con carne"); receta2.setDescription("Leche y carne"); receta2.setSteps("Mezclar y servir");
        receta2 = recipeRepository.save(receta2);

        var rf1 = new com.recetas.model.RecipeFood(); rf1.setRecipe(receta1); rf1.setFood(f1);
        var rf2 = new com.recetas.model.RecipeFood(); rf2.setRecipe(receta2); rf2.setFood(f2);
        var rf3 = new com.recetas.model.RecipeFood(); rf3.setRecipe(receta2); rf3.setFood(f3);
        rf1 = recipeFoodRepository.save(rf1);
        rf2 = recipeFoodRepository.save(rf2);
        rf3 = recipeFoodRepository.save(rf3);

        receta1.setRecipeFoods(Set.of(rf1));
        receta2.setRecipeFoods(Set.of(rf2, rf3));
        recipeRepository.save(receta1);
        recipeRepository.save(receta2);

        HttpEntity<Void> entityFoods = new HttpEntity<>(headers);
        ResponseEntity<FoodDto[]> respFoods = restTemplate.exchange("/api/users/" + user.getId() + "/safe-foods", HttpMethod.GET, entityFoods, FoodDto[].class);
        Assertions.assertEquals(HttpStatus.OK, respFoods.getStatusCode());
        List<String> safeFoodNames = Arrays.stream(respFoods.getBody()).map(f -> f.name).toList();
        Assertions.assertTrue(safeFoodNames.contains("Manzana"));
        Assertions.assertTrue(safeFoodNames.contains("Carne"));
        Assertions.assertFalse(safeFoodNames.contains("Leche"));

        HttpEntity<Void> entityRecipes = new HttpEntity<>(headers);
        ResponseEntity<RecipeDto[]> respRecipes = restTemplate.exchange("/api/users/" + user.getId() + "/safe-recipes", HttpMethod.GET, entityRecipes, RecipeDto[].class);
        Assertions.assertEquals(HttpStatus.OK, respRecipes.getStatusCode());
        List<String> safeRecipeTitles = Arrays.stream(respRecipes.getBody()).map(r -> r.title).toList();
        Assertions.assertTrue(safeRecipeTitles.contains("Ensalada de manzana"));
        Assertions.assertFalse(safeRecipeTitles.contains("Leche con carne"));
    }
}
