-- =========================
-- SCHEMA: Tables creation
-- Compatible with H2 (tests) and MySQL (runtime)
-- =========================

CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE conditions (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  description TEXT,
  condition_type VARCHAR(100) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE foods (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(150) NOT NULL,
  category VARCHAR(100),
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE recipes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(150) NOT NULL,
  description TEXT,
  steps TEXT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE recipe_foods (
  id INT PRIMARY KEY AUTO_INCREMENT,
  recipe_id INT NOT NULL,
  food_id INT NOT NULL,
  quantity VARCHAR(50),
  CONSTRAINT fk_recipe_foods_recipe FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE,
  CONSTRAINT fk_recipe_foods_food FOREIGN KEY (food_id) REFERENCES foods(id) ON DELETE CASCADE
);

CREATE TABLE condition_foods (
  condition_id INT NOT NULL,
  food_id INT NOT NULL,
  CONSTRAINT pk_condition_foods PRIMARY KEY (condition_id, food_id),
  CONSTRAINT fk_condition_foods_condition FOREIGN KEY (condition_id) REFERENCES conditions(id) ON DELETE CASCADE,
  CONSTRAINT fk_condition_foods_food FOREIGN KEY (food_id) REFERENCES foods(id) ON DELETE CASCADE
);

CREATE TABLE user_conditions (
  user_id INT NOT NULL,
  condition_id INT NOT NULL,
  CONSTRAINT pk_user_conditions PRIMARY KEY (user_id, condition_id),
  CONSTRAINT fk_user_conditions_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_user_conditions_condition FOREIGN KEY (condition_id) REFERENCES conditions(id) ON DELETE CASCADE
);

CREATE TABLE refresh_tokens (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  token VARCHAR(255),
  expiry_date TIMESTAMP,
  user_id INT NOT NULL,
  revoked BOOLEAN DEFAULT FALSE,
  CONSTRAINT fk_refresh_tokens_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
