# Recetas Seguras API

API REST para gestión de recetas seguras teniendo en cuenta condiciones de salud (enfermedades, alergias e intolerancias).

Este README explica cómo configurar, ejecutar y probar la API localmente.

---

## Resumen

- **Backend:** Spring Boot (Java 21)
- **Autenticación:** JWT (access + refresh tokens) con rotación automática y duración configurable (por defecto 10 años)
- **Persistencia:** Spring Data JPA + Flyway para migraciones
- **Migraciones:** Flyway con datos iniciales
- **Documentación:** OpenAPI/Swagger UI disponible en `/swagger-ui.html`
- **Filtrado inteligente:** Recetas y alimentos seguros según condiciones del usuario

---

## Requisitos

- JDK 21
- Maven 3.x (Usado Maven 3.8.1)
- MySQL 8.x o superior (se usa MySQL 8.0.35 con Flyway en desarrollo)

---

## Configuración rápida (desarrollo)

1. Copia `.env-example` a `.env` en la raíz y completar las variables.

2. Generar un `JWT_SECRET` fuerte (32 bytes → Base64). En PowerShell:

````powershell
$bytes = New-Object byte[] 32
[System.Security.Cryptography.RandomNumberGenerator]::Create().GetBytes($bytes)
[Convert]::ToBase64String($bytes)
````

3. Pega el valor generado en `.env` como `JWT_SECRET`.

> El proyecto carga `.env` automáticamente (ver `com.recetas.config.DotenvEnvironmentPostProcessor`). El arranque validará la existencia de `JWT_SECRET` (ver `StartupValidator`).

4. Comprobar los datos de conexión de la base de datos en el `application.properties`.


### Variables de entorno clave

- `JWT_SECRET` (obligatorio): clave Base64 para firmar JWT.
- `JWT_ACCESS_EXP_MS` (opcional): expiración access token en ms (default **315360000000** = 10 años).
- `JWT_REFRESH_EXP_MS` (opcional): expiración refresh token en ms (default **315360000000** = 10 años).
- `APP_CORS_ALLOWED_ORIGINS` (opcional): orígenes permitidos (comma-separated). Default `*`.
- `APP_TOKEN_CLEANUP_MS` (opcional): frecuencia de limpieza de refresh tokens en ms (default 3600000).

> **Nota:** Los tokens tienen duración de 10 años por defecto (prácticamente permanentes) y solo expiran cuando el usuario hace logout. Esto simplifica la experiencia del usuario manteniendo la sesión activa.


## Credenciales de desarrollo (admin)

- **Email:** `admin@recetaseguras.es`
- **Contraseña:** `Adm1n!2025_#X7`

Estas credenciales se incluyen únicamente para facilitar pruebas en entornos de desarrollo local.

---

## Ejecutar la aplicación

En PowerShell desde la raíz del proyecto:

````powershell

## Arrancar en el propio IDE (IntelliJ IDEA)

# Arrancar en modo desarrollo (usa variables del .env)
mvn spring-boot:run

# O construir y ejecutar el jar
mvn package
java -jar target/recetas-seguras-api-1.0.0.jar
````

La app escucha por defecto en `http://localhost:8080`. Para el emulador Android usa `http://10.0.2.2:8080`.

### Migraciones Flyway

Las migraciones se ejecutan automáticamente al iniciar la aplicación:
- **V1__schema.sql:** Crea todas las tablas (users, conditions, foods, recipes, etc.)
- **V2__initial_data.sql:** Inserta 15 condiciones y 50+ alimentos con categorías en español
- **V3__recipes_data.sql:** Inserta 5 recetas de ejemplo con ingredientes

Todas las migraciones son idempotentes (pueden re-ejecutarse sin errores).

**Comandos útiles de Flyway:**
````powershell
# Reparar checksums después de modificar migraciones (solo desarrollo)
mvn flyway:repair

# Ver información de migraciones
mvn flyway:info
````
En caso de error al volver a ejecutar y de que no funcione sería necesario restaurar la base de datos.
