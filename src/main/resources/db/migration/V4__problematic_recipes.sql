-- =========================
-- V4: RECETAS CON ALIMENTOS PROBLEMÁTICOS
-- =========================
-- 6. Tarta de queso
INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (6, 'Tarta de queso', 'Postre clásico con base de galletitas y relleno de queso.', '1. Triturar galletitas y mezclarlas con manteca.\n2. Batir queso, yogur y azúcar.\n3. Hornear hasta que cuaje.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

-- 7. Brownie con nueces
INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (7, 'Brownie con nueces', 'Postre de chocolate con nueces.', '1. Derretir chocolate con manteca.\n2. Mezclar con azúcar y harina.\n3. Agregar nueces y hornear.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

-- 8. Ensalada con queso
INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (8, 'Ensalada con queso', 'Fresca y simple, con vegetales y queso.', '1. Cortar lechuga, tomate y cebolla.\n2. Agregar cubos de queso.\n3. Aliñar con vinagre y orégano.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

-- 9. Galletas con almendras
INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (9, 'Galletas con almendras', 'Galletas dulces con trozos de almendras.', '1. Mezclar galletitas trituradas con manteca y azúcar.\n2. Agregar almendras picadas.\n3. Hornear.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

-- 10. Arroz con salsa de soja y queso
INSERT INTO recipes (id, title, description, steps, created_at, updated_at) VALUES (10, 'Arroz con salsa de soja y queso', 'Plato simple con arroz, vegetales y queso.', '1. Hervir arroz.\n2. Saltear brócoli y cebolla.\n3. Agregar salsa de soja y queso rallado.', NOW(), NOW())
  ON DUPLICATE KEY UPDATE title=VALUES(title), description=VALUES(description), steps=VALUES(steps), updated_at=NOW();

-- =========================
-- INGREDIENTES DE RECETAS (recipe_foods)
-- =========================

-- Tarta de queso (6)
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (6, 402, '200g'); -- Queso
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (6, 403, '150g'); -- Yogur
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (6, 1002, '100g'); -- Azúcar
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (6, 802, '150g'); -- Galletitas
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (6, 404, '100g'); -- Manteca

-- Brownie con nueces (7)
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (7, 803, '200g'); -- Chocolates
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (7, 1002, '150g'); -- Azúcar
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (7, 404, '120g'); -- Manteca
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (7, 702, '80g'); -- Nueces

-- Ensalada con queso (8)
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (8, 103, '1 unidad'); -- Lechuga
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (8, 102, '2 unidades'); -- Tomate
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (8, 106, '1/2 unidad'); -- Cebolla
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (8, 402, '100g'); -- Queso
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (8, 1007, 'A gusto'); -- Vinagre
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (8, 1004, 'A gusto'); -- Orégano

-- Galletas con almendras (9)
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (9, 802, '200g'); -- Galletitas
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (9, 404, '100g'); -- Manteca
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (9, 1002, '120g'); -- Azúcar
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (9, 701, '90g'); -- Almendras

-- Arroz con salsa de soja y queso (10)
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (10, 601, '200g'); -- Arroz
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (10, 105, '150g'); -- Brócoli
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (10, 106, '1 unidad'); -- Cebolla
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (10, 1008, 'A gusto'); -- Salsa de soja
INSERT IGNORE INTO recipe_foods (recipe_id, food_id, quantity) VALUES (10, 402, '50g'); -- Queso
