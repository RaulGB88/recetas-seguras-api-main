-- =========================
-- RECETAS
-- =========================
INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (1, 'Ensalada fresca de vegetales', 'Ligera y nutritiva, apta para celíacos y personas con colesterol alto.', '1. Lavar y cortar tomate, lechuga, zanahoria y cebolla.\n2. Mezclar en un bowl.\n3. Aliñar con aceite de oliva, sal moderada y orégano.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (2, 'Pollo al horno con papas', 'Receta simple y baja en grasas.', '1. Colocar pollo y papas en una bandeja.\n2. Condimentar con pimienta y comino.\n3. Hornear 45 minutos.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (3, 'Sopa de lentejas', 'Rica en fibra y proteínas vegetales.', '1. Hervir lentejas con zanahoria, cebolla y ajo.\n2. Agregar sal y curry al gusto.\n3. Cocinar hasta que espese.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (4, 'Smoothie de frutas con leche vegetal', 'Opción refrescante y apta para intolerancia a la lactosa.', '1. Licuar banana, frutilla y mango.\n2. Agregar leche vegetal y hielo.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (5, 'Arroz con vegetales salteados', 'Plato vegano y libre de gluten.', '1. Hervir arroz.\n2. Saltear brócoli, zanahoria y cebolla en aceite de oliva.\n3. Mezclar todo y condimentar con salsa de soja (opcional).', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

-- =========================
-- INGREDIENTES DE RECETAS (recipe_foods)
-- =========================
-- Ensalada fresca de vegetales
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (1, 101, '1 unidad'); -- Zanahoria
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (1, 102, '2 unidades'); -- Tomate
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (1, 103, '1/2 unidad'); -- Lechuga
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (1, 106, '1/2 unidad'); -- Cebolla
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (1, 1004, 'A gusto'); -- Orégano

-- Pollo al horno con papas
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (2, 301, '500g'); -- Pollo
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (2, 108, '4 unidades'); -- Papa
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (2, 1003, 'A gusto'); -- Pimienta
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (2, 1005, 'A gusto'); -- Comino

-- Sopa de lentejas
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (3, 501, '200g'); -- Lentejas
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (3, 101, '1 unidad'); -- Zanahoria
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (3, 106, '1 unidad'); -- Cebolla
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (3, 107, '2 dientes'); -- Ajo
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (3, 1006, 'A gusto'); -- Curry

-- Smoothie de frutas con leche vegetal
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (4, 202, '1 unidad'); -- Banana
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (4, 208, '100g'); -- Frutilla
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (4, 209, '1/2 unidad'); -- Mango
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (4, 906, '200ml'); -- Leche vegetal

-- Arroz con vegetales salteados
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (5, 601, '200g'); -- Arroz
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (5, 105, '150g'); -- Brócoli
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (5, 101, '1 unidad'); -- Zanahoria
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (5, 106, '1 unidad'); -- Cebolla
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (5, 1008, 'A gusto'); -- Salsa de soja
