# Franchise API — Spring Boot

API REST para administrar una lista de **franquicias**, sus **sucursales (branches)** y los **productos** disponibles en cada sucursal, incluyendo **stock** y consulta del **producto con mayor stock por sucursal** para una franquicia específica.

---

## Requisitos cubiertos (según enunciado)

✅ Spring Boot.  
✅ Endpoint para agregar una nueva franquicia.  
✅ Endpoint para agregar una nueva sucursal a una franquicia.  
✅ Endpoint para agregar un nuevo producto a una sucursal.  
✅ Endpoint para eliminar un producto de una sucursal.  
✅ Endpoint para modificar el stock de un producto.  
✅ Endpoint para mostrar el producto con más stock por sucursal para una franquicia (retorna lista e indica a qué sucursal pertenece).  
✅ Persistencia con base de datos (JPA + PostgreSQL/MySQL según configuración).  

**Extras (si están implementados en tu proyecto):**
- ✅ Docker / Docker Compose.
- ✅ Actualizar nombre de franquicia.
- ✅ Actualizar nombre de sucursal.
- ✅ Actualizar nombre de producto.

---

## Tecnologías

- Java 17+ (o 21)
- Spring Boot 3.x
- Spring Web
- Spring Validation
- Spring Data JPA
- Lombok
- Base de datos: PostgreSQL o MySQL (según tu configuración)
- Docker + Docker Compose (opcional)

---

## Modelo de datos

- **Franchise**
  - `id`, `name`
  - relación: `Franchise 1..N Branch`

- **Branch**
  - `id`, `name`, `franchise`
  - relación: `Branch 1..N Product`

- **Product**
  - `id`, `name`, `stock`, `branch`

---

## Ejecución del proyecto

### Opción A) Con Docker (recomendado si tienes docker-compose)
1. Construir y levantar servicios:

docker compose up --build

  API disponible en:

    http://localhost:8080

  Para detener:

    docker compose down

  Para resetear DB (borra volúmenes):

    docker compose down -v

### Opción B) Local (sin Docker)

  Crea la base de datos (ejemplo PostgreSQL):

    CREATE DATABASE franchise_db;

  Configura application.properties (o application.yml) con tus credenciales:

    spring.datasource.url=jdbc:postgresql://localhost:5432/franchise_db
    spring.datasource.username=postgres
    spring.datasource.password=TU_PASSWORD
    spring.jpa.hibernate.ddl-auto=update

  Ejecuta:

    mvn clean spring-boot:run

Base URL

Por defecto:

    http://localhost:8080

Endpoints obligatorios
1) Crear franquicia

        POST /api/franchises

Body:

{
  "name": "Franquicia Pepe"
}

2) Agregar sucursal a una franquicia

       POST /api/franchises/{franchiseId}/branches

Body:

{
  "name": "Sucursal Centro"
}

3) Agregar producto a una sucursal

        POST /api/branches/{branchId}/products

Body:

{
  "name": "Coca Cola",
  "stock": 120
}

4) Eliminar un producto

        DELETE /api/products/{productId}
5) Modificar stock de un producto

       PUT /api/products/{productId}/stock

Body:

{
  "stock": 300
}

6) Producto con mayor stock por sucursal para una franquicia

       GET /api/franchises/{franchiseId}/top-products

Ejemplo de respuesta:

[
  {
    "branchId": 1,
    "branchName": "Sucursal Centro",
    "productId": 10,
    "productName": "Agua",
    "stock": 300
  },
  {
    "branchId": 2,
    "branchName": "Sucursal Norte",
    "productId": 12,
    "productName": "Papas",
    "stock": 90
  }
]

  Nota: Si una sucursal no tiene productos, no aparecerá en el resultado.

Endpoints extra (bonus)
Actualizar nombre de franquicia

    PUT /api/franchises/{franchiseId}/name

Body:

{
  "name": "Franquicia Pepe PRO"
}

Actualizar nombre de sucursal

PUT /api/branches/{branchId}/name

Body:

{
  "name": "Sucursal Centro Renovada"
}

Actualizar nombre de producto

PUT /api/products/{productId}/name

Body:

{
  "name": "Coca Cola Zero"
}

Flujo recomendado para pruebas (Postman)

    Crear franquicia.

    Crear 2 sucursales para esa franquicia.

    Crear 2–3 productos por sucursal, con stocks diferentes.

    Actualizar el stock de un producto.

    Consultar top-products para validar el producto con mayor stock por sucursal.

    Eliminar un producto y repetir la consulta.
##  Author

**Breyner José Pertuz Castro**  
GitHub: https://github.com/beyner62838  
