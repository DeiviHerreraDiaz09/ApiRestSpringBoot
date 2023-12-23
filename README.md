# Manga Project API REST con Spring Boot
## Descripción General
Este proyecto consiste en una API REST construida con Spring Boot para gestionar información de manga. Proporciona puntos finales para registrar y recuperar detalles de manga, manejar autenticación de usuario y gestionar detalles de alquiler de manga. La API está asegurada mediante JSON Web Tokens (JWT) para garantizar un control de acceso adecuado.

## Inicio Rápido
Para ejecutar el proyecto localmente, sigue estos pasos:

### 1. Clona el repositorio:
```git clone https://github.com/DeiviHerreraDiaz09/ApiRestSpringBoot.git```
### 2. Abre el proyecto en tu IDE preferido.
### 3. Configura la conexión a la base de datos en el archivo application.properties.
### 4. Construye y ejecuta la aplicación Spring Boot.
```./mvnw spring-boot:run```

# Puntos finales de la API
## Operaciones de Manga
### 1. Añadir Manga

- Punto final: POST /api/mangas/add
- Descripción: Registra un nuevo manga.
- Parámetros de solicitud:
  -  file (MultipartFile): Archivo de imagen del manga.
  -  title (String): Título del manga.
  -  description (String): Descripción del manga.
  - amount (Integer): Cantidad de manga.
  - price (Integer): Precio del manga.
- Encabezado de autorización: Token con el rol "administrador" requerido.
- Ejemplo de solicitud usando Postman:
-jemplo de respuesta:

```
"Manga creado exitosamente"
```

### 2. Obtener todos los Mangas

- Punto final: GET /api/mangas/list
- Descripción: Recupera una lista de todos los mangas.
- Ejemplo de solicitud usando Postman:

Ejemplo de respuesta:

```
[
  {
    "id": 1,
    "title": "Mi Manga",
    "description": "Historia emocionante",
    "amount": 10,
    "price": 5,
    "image": "manga_image.jpg"
  },
  ...
]
```
### 3. Obtener Imagen de Manga
- Punto final: GET /api/mangas/image/{imageName}
- Descripción: Recupera la imagen de un manga específico.
- Variable de ruta:
  - imageName (String): Nombre del archivo de imagen del manga.
- Ejemplo de solicitud usando Postman:

Respuesta:
Descarga del archivo de imagen.

## Operaciones de Usuario
### 1. Añadir Usuario

- Punto final: POST /api/users/add
- Descripción: Registra un nuevo usuario.
- Cuerpo de la solicitud: Objeto de usuario con correo electrónico, contraseña, etc.
- Ejemplo de solicitud usando Postman:
Ejemplo de respuesta:
```
{
  "idUsuario": 1,
  "email": "user@example.com",
  ...
}
```

### 2. Obtener todos los Usuarios

- Punto final: GET /api/users/list
- Descripción: Recupera una lista de todos los usuarios.
- Ejemplo de solicitud usando Postman:
Ejemplo de respuesta:

```

[
  {
    "idUsuario": 1,
    "email": "user@example.com",
    ...
  },
  ...
]
```

### 3. Inicio de Sesión de Usuario

- Punto final: POST /api/users/login
- Descripción: Autentica a un usuario y genera un token JWT.
- Cuerpo de la solicitud: Objeto de usuario con correo electrónico y contraseña.
- Ejemplo de solicitud usando Postman:
Ejemplo de respuesta:

```

[
  1,
  "eyJhbGciOiJIUzI1NiJ9...",
  "user"
]

```

### 4. Verificar Token

- Punto final: GET /api/users/verificar
- Descripción: Verifica la validez de un token JWT.
- Encabezado de autorización: Token JWT.
- Ejemplo de solicitud usando Postman:
Ejemplo de respuesta:

```
{
  "status": "El token es válido"
}
```

## Operaciones de Detalles de Manga
### 1. Añadir Detalles de Manga

- Punto final: POST /api/details/add
- Descripción: Añade detalles para el alquiler de manga.
- Cuerpo de la solicitud: Objeto de detalles de manga.
- Ejemplo de solicitud usando Postman:
Ejemplo de respuesta:
```

{
  "id": 1,
  "fechaAlquiler": "2023-01-01",
  ...
}

```

### 2. Obtener Todos los Detalles de Manga

- Punto final: GET /api/details/list
- Descripción: Recupera una lista de todos los detalles de manga.
- Ejemplo de solicitud usando Postman:
Ejemplo de respuesta:
```

[
  {
    "id": 1,
    "fechaAlquiler": "2023-01-01",
    "idUserFK": 1,
    "idMangaFK": 1,
    "idUserFKName": "John Doe",
    "idMangaFKName": "Mi Manga"
  },
  ...
]
```

### 3. Actualizar Detalles de Manga

- Punto final: PUT /api/details/{id}
- Descripción: Actualiza detalles para un alquiler específico de manga.
- Variable de ruta:
  - id (Integer): ID de detalles de manga.
- Cuerpo de la solicitud: Objeto actualizado de detalles de manga.
- Ejemplo de solicitud usando Postman:
Ejemplo de respuesta:

```

{
  "id": 1,
  "fechaAlquiler": "2023-01-01",
  "fecha_devolucion": "2023-01-10",
  ...
}
```

## Triggers Incrementales y Decrementales
Hemos implementado triggers para gestionar el inventario de mangas de manera incremental y decremental. Estos triggers se encargan de actualizar automáticamente la cantidad de mangas disponibles al agregar nuevos detalles de alquiler o actualizar los detalles existentes.

### Trigger Decremental
```

DELIMITER $$

CREATE TRIGGER after_insert_detail_manga
AFTER INSERT ON detail_manga
FOR EACH ROW
BEGIN
   UPDATE manga
   SET amount = amount - 1
   WHERE id_manga = NEW.id_mangafk;
END;

$$

DELIMITER ;

```

 
### Trigger Incremental

```

DELIMITER $$

CREATE TRIGGER after_update_detail_manga
AFTER UPDATE ON detail_manga
FOR EACH ROW
BEGIN
   IF NEW.fecha_devolucion IS NOT NULL THEN
      UPDATE manga
      SET amount = amount + 1
      WHERE id_manga = NEW.id_mangafk;
   END IF;
END;

$$

DELIMITER ; 

```

Estos triggers aseguran que la cantidad de mangas disponibles se actualice automáticamente según las transacciones de alquiler.
