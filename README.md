# LifeSync API Documentation

## 📘 Introducción

### 🚀 Nombre de la API
*LifeSync*

### 📌 Versión
*1.0*

### 🎯 Propósito
La API LifeSync proporciona una base estructurada y segura para gestionar la aplicación de frontend.  
Actúa como punto centralizado para la administración de entidades y la lógica de negocio, asegurando una clara separación de responsabilidades, facilitando la escalabilidad y mantenibilidad del sistema.

### 🛠 Arquitectura utilizada
- *MVC (Modelo‑Vista‑Controlador)*
- *Service Layer* para encapsular la lógica de negocio

### 🌐 URL base
Aún no definida

### ⚙ Tecnologías empleadas
- Spring Boot
- Java
- JPA
- JWT (Autenticación y seguridad)

---

## 🔐 Seguridad y Autenticación

### 🗝 Tipo de autenticación
La API utiliza autenticación mediante *JWT (JSON Web Tokens)*, permitiendo validar y autorizar las solicitudes mediante un token enviado en cada petición protegida.

### 📥 Cómo obtener un token
http
POST /api/auth/login
Content-Type: application/json

{
  "correo": "usuario@correo.com",
  "contrasenia": "claveSegura123"
}


Respuesta:
json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}


### 🚨 Cómo usar el token
http
Authorization: Bearer <token>


### 📂 Componentes clave de la seguridad
- *SecurityConfig.java* – Configuraciones y filtros de seguridad.  
- *JwtAuthFilter.java* – Valida tokens JWT en las peticiones.  
- *AuthenticationSuccessListener.java* – Gestiona eventos después de autenticaciones exitosas.  
- *CorsConfig.java* – Políticas CORS.  
- *MapperConfig.java* – Mapeo entre entidades y DTOs.  

---

## 🌟 Entidades principales

### 📌 Entidad Usuario
| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| idUsuario | UUID | Identificador único del usuario | No |
| nombre | String | Nombre del usuario | Sí |
| edad | Integer | Edad del usuario | No |
| peso | Float | Peso (kg) | No |
| altura | Float | Altura (m) | No |
| objetivoPeso | Float | Peso objetivo (kg) | No |
| genero | String | Género | Sí |
| correo | String | Correo electrónico | Sí |
| contrasenia | String | Contraseña | Sí |
| racha | Racha | Racha del usuario | No |

### 📌 Entidad Hidratacion
| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| idHidratacion | Long | Identificador | No |
| estado | boolean | ¿Meta cumplida? | No |
| fecha | LocalDate | Fecha del registro | Sí |
| progreso | double | Progreso actual (ml) | No |
| meta | double | Meta diaria (ml) | No |
| usuario | Usuario | Usuario asociado | No |

### 📌 Entidad DailyDataArchive
| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| idArchive | Long | Identificador | No |
| fecha | LocalDate | Fecha del snapshot | Sí |
| usuario | Usuario | Usuario asociado | No |
| datosJson | String | Datos archivados (JSON) | Sí |

---

## 📁 DTOs

### 🎯 Core DTOs
- *HidratacionDTO* – Datos de hidratación.  
- *PlatilloDTO* – Información de platillo.  
- *ProgresoDTO* – Seguimiento de progreso.  
- *RachaDTO* – Datos de racha.  
- *RecetaAprobacionDTO* – Estado de aprobación.  
- *RecetaDTO* – Receta completa.  
- *StatusDTO* – Estado genérico.  
- *UsuarioDTO* – Datos esenciales de usuario.  

### 🚀 Response DTOs
- DailyDataArchiveResponseDTO – idUsuario, nombreUsuario, fecha, hidratacion, platillos, racha, recetas  
- GenericResponse – message, data, status  
- HidratacionResponseDTO – estado, progreso, idUsuario, fecha, meta  
- LoginResponseDTO – token, idUsuario, nombre, correo  
- PlatilloResponseDTO – proteina, carbohidrato, vegetal, caloriasTotales, idUsuario, fecha  
- RachaResponseDTO – idRacha, puntos, privilegio, idUsuario, fecha  
- RecetaResponseDTO – Detalles completos de receta  
- UsuarioResponseDTO – Datos básicos de usuario y rol  

### ✏ Create DTOs
- CreateDailyDataArchiveDTO  
- CreateHidratacionDTO  
- CreateLoginDTO  
- CreatePlatilloDTO  
- CreateRachaDTO  
- CreateRecetaDTO  
- CreateUsuarioDTO  

---

## 📑 Endpoints

> Completa las columnas *Descripción, **Request Body* y *Códigos* al detallar cada ruta.

### ✨ AuthController (/api/auth)
| Método | Ruta | Método Java | Respuesta |
|--------|------|-------------|-----------|
| POST | /api/auth/login | login | ResponseEntity<?> |

### ✨ HidratacionController (/api/hidratacion)
| Método | Ruta | Método Java | Respuesta |
|--------|------|-------------|-----------|
| POST | /api/hidratacion | createHidratacion | HidratacionResponseDTO |
| GET  | /api/hidratacion/usuario/{userId} | getHidratacionByUserId | HidratacionResponseDTO |
| PUT  | /api/hidratacion/{id} | updateHidratacion | HidratacionResponseDTO |
| DELETE | /api/hidratacion/{id} | deleteHidratacion | HidratacionResponseDTO |
| … | … | … | … |

(Continúa con los demás controladores de forma similar…)

---

## 🏗 Servicios (Service Layer)

| Servicio | Responsabilidad | Métodos clave |
|----------|-----------------|---------------|
| UsuarioService | Gestión de usuarios (registro, consulta, peso, etc.) | createUsuario, updateUsuario, … |
| HidratacionService | Progreso y meta diaria de hidratación | getHidratacionByUserId, updateProgresoHidratacion |
| PlatilloService | CRUD de platillos | createPlatillo, updatePlatillo |
| RecetaService | CRUD + aprobación de recetas | createReceta, aprobarReceta |
| RachaService | Sistema de rachas y puntos | createRacha, check |
| DailyDataArchiveService | Snapshots diarios | archiveDaily, archiveOrUpdate |

---

## ✅ Ejemplos de uso

### cURL — Crear usuario
bash
curl -X POST https://<url-base>/api/usuarios   -H "Authorization: Bearer <token>"   -H "Content-Type: application/json"   -d '{
        "nombre": "Luis",
        "correo": "luis@example.com",
        "contrasenia": "123456",
        "genero": "M"
      }'


### JavaScript — Login
javascript
fetch("https://<url-base>/api/auth/login", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({
    correo: "usuario@correo.com",
    contrasenia: "claveSegura123"
  })
})
  .then(res => res.json())
  .then(data => console.log(data.token));


---

## 🚨 Manejo de errores comunes
| Código | Significado | Causa habitual | Ejemplo |
|--------|-------------|----------------|---------|
| 400 | Bad Request | Datos faltantes/formato inválido | Faltar correo al crear usuario |
| 401 | Unauthorized | Token ausente o expirado | Acceso sin Authorization |
| 403 | Forbidden | Rol sin permisos | Usuario normal → endpoint admin |
| 404 | Not Found | ID o ruta inexistente | Usuario no hallado |
| 409 | Conflict | Duplicados/estado inconsistente | Correo ya registrado |
| 422 | Unprocessable Entity | Regla de negocio fallida | Meta negativa |
| 500 | Internal Server Error | Excepción no controlada | Fallo BD inesperado |

Formato de error:
json
{
  "timestamp": "2025-06-29T12:34:56",
  "status": 400,
  "error": "Bad Request",
  "message": "El campo correo es obligatorio",
  "path": "/api/usuarios"
}


---

## 🔧 Utilidades (Utils)
| Clase | Propósito | Detalles |
|-------|-----------|----------|
| HidroCalc | Cálculo meta diaria (peso × 35 ml) | Factor configurable (hidro.factor-ml) |
| JwtUtil | Generar/validar JWT (HS256, 1 h) | generateToken, isTokenValid, etc. |
| Constants | Literales comunes | Rutas, prefijos, etc. |

---

## ⏰ Tareas programadas (Schedulers)
| Tarea | Propósito | Cron | Comportamiento |
|-------|-----------|------|----------------|
| HidratacionResetTask | Reinicia progreso diario | 0 0 0 * * * | Estado=false, progreso=0 |
| DailyArchiveScheduler | Snapshots diarios | 0 5 0 * * * | archiveOrUpdate, limpieza >365 d |

---

## 🛠 Notas técnicas

### 📐 Convenciones
- *camelCase* → propiedades JSON (objetivoPeso)
- *PascalCase* → clases (HidratacionServiceImpl)
- Fechas ISO‑8601 (yyyy-MM-dd) zona America/El_Salvador
- Puertos: *8080* en dev

### 🔄 Versionado
- Header X-API-Version o /v1 en ruta  
- Cambios mayores = breaking; menores = campos opcionales

### ♻ Paginación
- Query params: page, size, sort  
- Respuesta envuelta en GenericResponse con totalItems, totalPages

---

© 2025 LifeSync – Todos los derechos reservados.




# 📘 Documentación de Base de Datos

## 📌 Introducción

Esta documentación detalla claramente las estructuras y relaciones de las tablas utilizadas en el sistema. Se enfoca en proporcionar información precisa y ordenada que facilite el entendimiento y mantenimiento de la base de datos.

---

## 🗃 Tablas

### 1️⃣ Usuario

*Propósito:* Almacenar información personal y credenciales de acceso para usuarios.

| Columna           | Tipo         | Restricciones        | Descripción                     |
| ----------------- | ------------ | -------------------- | ------------------------------- |
| id_usuario_uuid | UUID         | PK, NOT NULL         | Identificador único del usuario |
| nombre          | VARCHAR(255) | NOT NULL             | Nombre completo del usuario     |
| correo          | VARCHAR(255) | UNIQUE, NOT NULL     | Correo electrónico para acceso  |
| contrasenia     | VARCHAR(255) | NOT NULL             | Contraseña cifrada              |
| edad            | INTEGER      |                      | Edad del usuario                |
| genero          | VARCHAR(255) |                      | Género del usuario              |
| altura          | REAL         |                      | Altura en metros                |
| peso            | REAL         |                      | Peso en kilogramos              |
| objetivo_peso   | REAL         |                      | Meta de peso                    |
| rol             | VARCHAR(20)  |                      | Rol del usuario                 |
| id_racha_fk     | BIGINT       | FK → racha.id\_racha | Relación con tabla rachas       |

### 2️⃣ Racha

*Propósito:* Seguimiento continuo de actividad diaria de usuarios.

| Columna            | Tipo    | Restricciones                            | Descripción             |
| ------------------ | ------- | ---------------------------------------- | ----------------------- |
| id_racha         | BIGINT  | PK, IDENTITY                             | Identificador de racha  |
| id_usuario_fk    | UUID    | FK → usuario.id\_usuario\_uuid, NOT NULL | Usuario relacionado     |
| fecha            | DATE    | NOT NULL                                 | Fecha inicial de racha  |
| last_streak_date | DATE    |                                          | Última fecha registrada |
| privilegio       | BOOLEAN |                                          | Privilegio especial     |
| puntos           | INTEGER |                                          | Puntos acumulados       |

### 3️⃣ Daily Data Archive

*Propósito:* Guardar snapshots diarios en JSON para auditoría.

| Columna         | Tipo   | Restricciones                            | Descripción               |
| --------------- | ------ | ---------------------------------------- | ------------------------- |
| id_archive    | BIGINT | PK, IDENTITY                             | Identificador del archivo |
| id_usuario_fk | UUID   | FK → usuario.id\_usuario\_uuid, NOT NULL | Usuario relacionado       |
| fecha         | DATE   | NOT NULL                                 | Fecha del snapshot        |
| datos_json    | TEXT   | NOT NULL                                 | Datos en formato JSON     |

### 4️⃣ Hidratación

*Propósito:* Registrar consumo diario de agua de usuarios.

| Columna          | Tipo             | Restricciones                            | Descripción                |
| ---------------- | ---------------- | ---------------------------------------- | -------------------------- |
| id_hidratacion | BIGINT           | PK, IDENTITY                             | Identificador del registro |
| id_usuario_fk  | UUID             | FK → usuario.id\_usuario\_uuid, NOT NULL | Usuario relacionado        |
| fecha          | DATE             | NOT NULL                                 | Fecha de registro          |
| progreso       | DOUBLE PRECISION |                                          | Volumen consumido (ml)     |
| estado         | BOOLEAN          |                                          | Objetivo diario completado |

### 5️⃣ Platillo

*Propósito:* Registrar platillos consumidos por usuarios.

| Columna            | Tipo         | Restricciones                            | Descripción                |
| ------------------ | ------------ | ---------------------------------------- | -------------------------- |
| id_platillo      | BIGINT       | PK, IDENTITY                             | Identificador del platillo |
| id_usuario_fk    | UUID         | FK → usuario.id\_usuario\_uuid, NOT NULL | Usuario relacionado        |
| fecha            | DATE         | NOT NULL                                 | Fecha de consumo           |
| meal             | VARCHAR(255) |                                          | Tipo de comida             |
| calorias_totales | INTEGER      |                                          | Calorías totales           |
| carbohidrato     | VARCHAR(255) |                                          | Carbohidratos              |
| proteina         | VARCHAR(255) |                                          | Proteínas                  |
| vegetal          | VARCHAR(255) |                                          | Verduras                   |

### 6️⃣ Receta

*Propósito:* Almacenar recetas creadas por usuarios.

| Columna              | Tipo         | Restricciones                            | Descripción                                   |
| -------------------- | ------------ | ---------------------------------------- | --------------------------------------------- |
| id_receta          | BIGINT       | PK                                       | ID de receta                                  |
| id_usuario         | UUID         | FK → usuario.id\_usuario\_uuid, NOT NULL | Autor                                         |
| nombre             | VARCHAR(255) | NOT NULL                                 | Nombre                                        |
| descripcion        | VARCHAR(255) |                                          | Descripción breve                             |
| ingredientes_lista | VARCHAR(255) |                                          | Ingredientes                                  |
| procedimiento      | VARCHAR(255) |                                          | Preparación                                   |
| imagen             | VARCHAR(255) |                                          | Imagen asociada                               |
| fecha              | DATE         |                                          | Fecha de creación                             |
| aprobada           | BOOLEAN      |                                          | Estado aprobación                             |
| Otros campos         | VARCHAR(255) |                                          | Macronutrientes, porciones, cocciones, cortes |

---

## 🔗 Relaciones

* *Usuario → Racha* (1–N)
* *Usuario → Daily Data Archive* (1–N)
* *Usuario → Hidratación* (1–N)
* *Usuario → Platillo* (1–N)
* *Usuario → Receta* (1–N)

---

📌 *Nota:* Mantén esta documentación actualizada conforme realices cambios en la base de datos.
