-- V5: Añade la columna `role` con valor por defecto.
ALTER TABLE users
  ADD COLUMN role VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER';

-- Asegurar que filas existentes tengan un valor por defecto (por si la columna se añadió sin DEFAULT)
UPDATE users SET role = 'ROLE_USER' WHERE role IS NULL OR role = '';
