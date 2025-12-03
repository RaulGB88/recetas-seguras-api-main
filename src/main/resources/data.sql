-- =========================
-- CONDICIONES
-- =========================
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (1, 'Diabetes tipo 2', 'DISEASE', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (2, 'Hipertensión', 'DISEASE', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (3, 'Enfermedad celíaca', 'DISEASE', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (4, 'Insuficiencia renal crónica', 'DISEASE', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (5, 'Hipercolesterolemia', 'DISEASE', NOW(), NOW());

INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (6, 'Alergia a la leche de vaca', 'ALLERGY', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (7, 'Alergia al huevo', 'ALLERGY', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (8, 'Alergia al maní', 'ALLERGY', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (9, 'Alergia a frutos secos', 'ALLERGY', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (10, 'Alergia al marisco', 'ALLERGY', NOW(), NOW());

INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (11, 'Intolerancia a la lactosa', 'INTOLERANCE', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (12, 'Intolerancia al gluten', 'INTOLERANCE', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (13, 'Intolerancia a la fructosa', 'INTOLERANCE', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (14, 'Intolerancia a la histamina', 'INTOLERANCE', NOW(), NOW());
INSERT INTO conditions (id, name, condition_type, created_at, updated_at) VALUES (15, 'Intolerancia al sorbitol', 'INTOLERANCE', NOW(), NOW());

-- =========================
-- ALIMENTOS
-- =========================
-- Vegetales (100+)
INSERT INTO foods (id, name, category) VALUES (101, 'Zanahoria', 'VEGETABLE');
INSERT INTO foods (id, name, category) VALUES (102, 'Tomate', 'VEGETABLE');
INSERT INTO foods (id, name, category) VALUES (103, 'Lechuga', 'VEGETABLE');
INSERT INTO foods (id, name, category) VALUES (104, 'Espinaca', 'VEGETABLE');
INSERT INTO foods (id, name, category) VALUES (105, 'Brócoli', 'VEGETABLE');
INSERT INTO foods (id, name, category) VALUES (106, 'Cebolla', 'VEGETABLE');
INSERT INTO foods (id, name, category) VALUES (107, 'Ajo', 'VEGETABLE');
INSERT INTO foods (id, name, category) VALUES (108, 'Papa', 'VEGETABLE');
INSERT INTO foods (id, name, category) VALUES (109, 'Batata', 'VEGETABLE');

-- Frutas (200+)
INSERT INTO foods (id, name, category) VALUES (201, 'Manzana', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (202, 'Banana', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (203, 'Naranja', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (204, 'Pera', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (205, 'Uva', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (206, 'Sandía', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (207, 'Melón', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (208, 'Frutilla', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (209, 'Mango', 'FRUIT');
INSERT INTO foods (id, name, category) VALUES (210, 'Kiwi', 'FRUIT');

-- Carnes (300+)
INSERT INTO foods (id, name, category) VALUES (301, 'Pollo', 'MEAT');
INSERT INTO foods (id, name, category) VALUES (302, 'Carne vacuna', 'MEAT');
INSERT INTO foods (id, name, category) VALUES (303, 'Cerdo', 'MEAT');
INSERT INTO foods (id, name, category) VALUES (304, 'Pescado blanco', 'MEAT');
INSERT INTO foods (id, name, category) VALUES (305, 'Atún', 'MEAT');
INSERT INTO foods (id, name, category) VALUES (306, 'Salmón', 'MEAT');
INSERT INTO foods (id, name, category) VALUES (307, 'Hígado', 'MEAT');

-- Lácteos (400+)
INSERT INTO foods (id, name, category) VALUES (401, 'Leche', 'DAIRY');
INSERT INTO foods (id, name, category) VALUES (402, 'Queso', 'DAIRY');
INSERT INTO foods (id, name, category) VALUES (403, 'Yogur', 'DAIRY');
INSERT INTO foods (id, name, category) VALUES (404, 'Manteca', 'DAIRY');
INSERT INTO foods (id, name, category) VALUES (405, 'Crema', 'DAIRY');
INSERT INTO foods (id, name, category) VALUES (406, 'Ricota', 'DAIRY');

-- Legumbres (500+)
INSERT INTO foods (id, name, category) VALUES (501, 'Lentejas', 'LEGUME');
INSERT INTO foods (id, name, category) VALUES (502, 'Porotos', 'LEGUME');
INSERT INTO foods (id, name, category) VALUES (503, 'Garbanzos', 'LEGUME');
INSERT INTO foods (id, name, category) VALUES (504, 'Arvejas', 'LEGUME');

-- Cereales (600+)
INSERT INTO foods (id, name, category) VALUES (601, 'Arroz', 'CEREAL');
INSERT INTO foods (id, name, category) VALUES (602, 'Trigo', 'CEREAL');
INSERT INTO foods (id, name, category) VALUES (603, 'Avena', 'CEREAL');
INSERT INTO foods (id, name, category) VALUES (604, 'Maíz', 'CEREAL');
INSERT INTO foods (id, name, category) VALUES (605, 'Cebada', 'CEREAL');
INSERT INTO foods (id, name, category) VALUES (606, 'Centeno', 'CEREAL');
INSERT INTO foods (id, name, category) VALUES (607, 'Pan', 'CEREAL');
INSERT INTO foods (id, name, category) VALUES (608, 'Fideos', 'CEREAL');

-- Frutos secos (700+)
INSERT INTO foods (id, name, category) VALUES (701, 'Almendras', 'NUT');
INSERT INTO foods (id, name, category) VALUES (702, 'Nueces', 'NUT');
INSERT INTO foods (id, name, category) VALUES (703, 'Avellanas', 'NUT');
INSERT INTO foods (id, name, category) VALUES (704, 'Castañas', 'NUT');
INSERT INTO foods (id, name, category) VALUES (705, 'Pistachos', 'NUT');

-- Snacks (800+)
INSERT INTO foods (id, name, category) VALUES (801, 'Papas fritas', 'SNACK');
INSERT INTO foods (id, name, category) VALUES (802, 'Galletitas', 'SNACK');
INSERT INTO foods (id, name, category) VALUES (803, 'Chocolates', 'SNACK');
INSERT INTO foods (id, name, category) VALUES (804, 'Caramelos', 'SNACK');
INSERT INTO foods (id, name, category) VALUES (805, 'Chicles', 'SNACK');

-- Bebidas (900+)
INSERT INTO foods (id, name, category) VALUES (901, 'Agua', 'DRINK');
INSERT INTO foods (id, name, category) VALUES (902, 'Jugo natural', 'DRINK');
INSERT INTO foods (id, name, category) VALUES (903, 'Gaseosa', 'DRINK');
INSERT INTO foods (id, name, category) VALUES (904, 'Café', 'DRINK');
INSERT INTO foods (id, name, category) VALUES (905, 'Té', 'DRINK');
INSERT INTO foods (id, name, category) VALUES (906, 'Leche vegetal', 'DRINK');

-- Condimentos (1000+)
INSERT INTO foods (id, name, category) VALUES (1001, 'Sal', 'CONDIMENT');
INSERT INTO foods (id, name, category) VALUES (1002, 'Azúcar', 'CONDIMENT');
INSERT INTO foods (id, name, category) VALUES (1003, 'Pimienta', 'CONDIMENT');
INSERT INTO foods (id, name, category) VALUES (1004, 'Orégano', 'CONDIMENT');
INSERT INTO foods (id, name, category) VALUES (1005, 'Comino', 'CONDIMENT');
INSERT INTO foods (id, name, category) VALUES (1006, 'Curry', 'CONDIMENT');
INSERT INTO foods (id, name, category) VALUES (1007, 'Vinagre', 'CONDIMENT');
INSERT INTO foods (id, name, category) VALUES (1008, 'Salsa de soja', 'CONDIMENT');

-- =========================
-- ASOCIACIÓN CONDICIÓN-ALIMENTO (prohibidos)
-- =========================
-- Diabetes tipo 2: azúcar, miel, gaseosa, pan blanco, fideos blancos, dulces
INSERT INTO condition_foods (condition_id, food_id) VALUES (1, 1002); -- azúcar
INSERT INTO condition_foods (condition_id, food_id) VALUES (1, 902);  -- miel (no está en la lista, puedes agregarlo si lo necesitas)
INSERT INTO condition_foods (condition_id, food_id) VALUES (1, 903);  -- gaseosa
INSERT INTO condition_foods (condition_id, food_id) VALUES (1, 607);  -- pan
INSERT INTO condition_foods (condition_id, food_id) VALUES (1, 608);  -- fideos
INSERT INTO condition_foods (condition_id, food_id) VALUES (1, 804);  -- caramelos

-- Hipertensión: sal, embutidos, papas fritas, salsa de soja, quesos curados
INSERT INTO condition_foods (condition_id, food_id) VALUES (2, 1001); -- sal
-- embutidos (no está en la lista, puedes agregarlo si lo necesitas)
INSERT INTO condition_foods (condition_id, food_id) VALUES (2, 801);  -- papas fritas
INSERT INTO condition_foods (condition_id, food_id) VALUES (2, 1008); -- salsa de soja
INSERT INTO condition_foods (condition_id, food_id) VALUES (2, 402);  -- queso

-- Enfermedad celíaca: trigo, cebada, centeno, pan común, fideos comunes, cerveza
INSERT INTO condition_foods (condition_id, food_id) VALUES (3, 602); -- trigo
INSERT INTO condition_foods (condition_id, food_id) VALUES (3, 605); -- cebada
INSERT INTO condition_foods (condition_id, food_id) VALUES (3, 606); -- centeno
INSERT INTO condition_foods (condition_id, food_id) VALUES (3, 607); -- pan
INSERT INTO condition_foods (condition_id, food_id) VALUES (3, 608); -- fideos

-- Alergia a la leche de vaca: leche, queso, yogur, manteca, crema
INSERT INTO condition_foods (condition_id, food_id) VALUES (6, 401); -- leche
INSERT INTO condition_foods (condition_id, food_id) VALUES (6, 402); -- queso
INSERT INTO condition_foods (condition_id, food_id) VALUES (6, 403); -- yogur
INSERT INTO condition_foods (condition_id, food_id) VALUES (6, 404); -- manteca
INSERT INTO condition_foods (condition_id, food_id) VALUES (6, 405); -- crema

-- Alergia al huevo: huevo, mayonesa, pasteles, panqueques, flan (no están en la lista, se pueden agregar)

-- Intolerancia a la lactosa: leche, yogur, queso, manteca, crema
INSERT INTO condition_foods (condition_id, food_id) VALUES (11, 401); -- leche
INSERT INTO condition_foods (condition_id, food_id) VALUES (11, 402); -- queso
INSERT INTO condition_foods (condition_id, food_id) VALUES (11, 403); -- yogur
INSERT INTO condition_foods (condition_id, food_id) VALUES (11, 404); -- manteca
INSERT INTO condition_foods (condition_id, food_id) VALUES (11, 405); -- crema

-- Se pueden continuar agregando asociaciones según la lista y el modelo.