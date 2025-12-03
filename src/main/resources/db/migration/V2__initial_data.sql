-- =========================
-- CONDICIONES
-- =========================
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (1, 'Diabetes tipo 2', 'DISEASE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (2, 'Hipertensión', 'DISEASE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (3, 'Enfermedad celíaca', 'DISEASE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (4, 'Insuficiencia renal crónica', 'DISEASE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (5, 'Hipercolesterolemia', 'DISEASE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();

INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (6, 'Alergia a la leche de vaca', 'ALLERGY', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (7, 'Alergia al huevo', 'ALLERGY', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (8, 'Alergia al maní', 'ALLERGY', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (9, 'Alergia a frutos secos', 'ALLERGY', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (10, 'Alergia al marisco', 'ALLERGY', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();

INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (11, 'Intolerancia a la lactosa', 'INTOLERANCE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (12, 'Intolerancia al gluten', 'INTOLERANCE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (13, 'Intolerancia a la fructosa', 'INTOLERANCE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (14, 'Intolerancia a la histamina', 'INTOLERANCE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (15, 'Intolerancia al sorbitol', 'INTOLERANCE', NOW(), NOW())
	ON DUPLICATE KEY UPDATE name=VALUES(name), condition_type=VALUES(condition_type), updated_at=NOW();

-- =========================
-- ALIMENTOS
-- =========================
-- Vegetales (100+)
INSERT INTO foods (id, name, category) VALUES (101, 'Zanahoria', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (102, 'Tomate', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (103, 'Lechuga', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (104, 'Espinaca', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (105, 'Brócoli', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (106, 'Cebolla', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (107, 'Ajo', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (108, 'Papa', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (109, 'Batata', 'VEGETAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Frutas (200+)
INSERT INTO foods (id, name, category) VALUES (201, 'Manzana', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (202, 'Banana', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (203, 'Naranja', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (204, 'Pera', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (205, 'Uva', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (206, 'Sandía', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (207, 'Melón', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (208, 'Frutilla', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (209, 'Mango', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (210, 'Kiwi', 'FRUTA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Carnes (300+)
INSERT INTO foods (id, name, category) VALUES (301, 'Pollo', 'CARNE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (302, 'Carne vacuna', 'CARNE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (303, 'Cerdo', 'CARNE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (304, 'Pescado blanco', 'CARNE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (305, 'Atún', 'CARNE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (306, 'Salmón', 'CARNE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (307, 'Hígado', 'CARNE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Lácteos (400+)
INSERT INTO foods (id, name, category) VALUES (401, 'Leche', 'LACTEO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (402, 'Queso', 'LACTEO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (403, 'Yogur', 'LACTEO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (404, 'Manteca', 'LACTEO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (405, 'Crema', 'LACTEO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (406, 'Ricota', 'LACTEO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Legumbres (500+)
INSERT INTO foods (id, name, category) VALUES (501, 'Lentejas', 'LEGUMBRE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (502, 'Porotos', 'LEGUMBRE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (503, 'Garbanzos', 'LEGUMBRE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (504, 'Arvejas', 'LEGUMBRE')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Cereales (600+)
INSERT INTO foods (id, name, category) VALUES (601, 'Arroz', 'CEREAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (602, 'Trigo', 'CEREAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (603, 'Avena', 'CEREAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (604, 'Maíz', 'CEREAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (605, 'Cebada', 'CEREAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (606, 'Centeno', 'CEREAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (607, 'Pan', 'CEREAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (608, 'Fideos', 'CEREAL')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Frutos secos (700+)
INSERT INTO foods (id, name, category) VALUES (701, 'Almendras', 'FRUTO_SECO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (702, 'Nueces', 'FRUTO_SECO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (703, 'Avellanas', 'FRUTO_SECO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (704, 'Castañas', 'FRUTO_SECO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (705, 'Pistachos', 'FRUTO_SECO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Snacks (800+)
INSERT INTO foods (id, name, category) VALUES (801, 'Papas fritas', 'SNACK')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (802, 'Galletitas', 'SNACK')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (803, 'Chocolates', 'SNACK')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (804, 'Caramelos', 'SNACK')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (805, 'Chicles', 'SNACK')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Bebidas (900+)
INSERT INTO foods (id, name, category) VALUES (901, 'Agua', 'BEBIDA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (902, 'Jugo natural', 'BEBIDA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (903, 'Gaseosa', 'BEBIDA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (904, 'Café', 'BEBIDA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (905, 'Té', 'BEBIDA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (906, 'Leche vegetal', 'BEBIDA')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- Condimentos (1000+)
INSERT INTO foods (id, name, category) VALUES (1001, 'Sal', 'CONDIMENTO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (1002, 'Azúcar', 'CONDIMENTO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (1003, 'Pimienta', 'CONDIMENTO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (1004, 'Orégano', 'CONDIMENTO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (1005, 'Comino', 'CONDIMENTO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (1006, 'Curry', 'CONDIMENTO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (1007, 'Vinagre', 'CONDIMENTO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);
INSERT INTO foods (id, name, category) VALUES (1008, 'Salsa de soja', 'CONDIMENTO')
	ON DUPLICATE KEY UPDATE name=VALUES(name), category=VALUES(category);

-- =========================
-- ASOCIACIÓN CONDICIÓN-ALIMENTO (prohibidos)
-- =========================
-- Diabetes tipo 2: azúcar, miel, gaseosa, pan blanco, fideos blancos, dulces
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (1, 1002); -- azúcar
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (1, 902);  -- jugo natural (referencia)
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (1, 903);  -- gaseosa
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (1, 607);  -- pan
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (1, 608);  -- fideos
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (1, 804);  -- caramelos

-- Hipertensión: sal, embutidos, papas fritas, salsa de soja, quesos curados
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (2, 1001); -- sal
-- embutidos (pendiente)
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (2, 801);  -- papas fritas
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (2, 1008); -- salsa de soja
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (2, 402);  -- queso

-- Enfermedad celíaca: trigo, cebada, centeno, pan común, fideos comunes, cerveza
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (3, 602); -- trigo
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (3, 605); -- cebada
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (3, 606); -- centeno
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (3, 607); -- pan
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (3, 608); -- fideos

-- Alergia a la leche de vaca: leche, queso, yogur, manteca, crema
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (6, 401); -- leche
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (6, 402); -- queso
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (6, 403); -- yogur
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (6, 404); -- manteca
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (6, 405); -- crema

-- Intolerancia a la lactosa: leche, yogur, queso, manteca, crema
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (11, 401); -- leche
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (11, 402); -- queso
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (11, 403); -- yogur
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (11, 404); -- manteca
INSERT IGNORE INTO condition_foods (condition_id, food_id) VALUES (11, 405); -- crema
