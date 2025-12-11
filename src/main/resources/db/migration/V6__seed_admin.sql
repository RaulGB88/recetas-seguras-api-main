-- V6: Inserción de usuario admin.
-- Inserta un usuario admin con role ROLE_ADMIN, generando un hash BCrypt para la contraseña.

INSERT INTO users (id, username, email, password, role, created_at, updated_at)
VALUES (1, 'admin', 'admin@recetaseguras.es', '$2a$10$XOE3LEwrU7GYGwn2HrzKouB0BLj9Ic64O61YiiFyANdawMB4nENLC', 'ROLE_ADMIN', NOW(), NOW())
ON DUPLICATE KEY UPDATE username=VALUES(username), email=VALUES(email), role=VALUES(role), updated_at=NOW();
