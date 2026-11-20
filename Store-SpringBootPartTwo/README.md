# Store-SpringBootPartTwo

A learning-oriented Spring Boot REST API for managing users, products, and shopping carts. The repository demonstrates a layered Java backend with authentication, persistence, request validation, and database migrations.

> This project was developed as a learning exercise using a course starter project as a foundation, then adapted and extended. It should not be presented as a fully independent implementation of every component.

## Tech stack

- Java 21, Spring Boot 3.4.1, Maven
- Spring Web, Spring Security, Spring Data JPA, Bean Validation
- MySQL and Flyway migrations
- JSON Web Tokens (JWT), BCrypt password hashing
- Lombok, MapStruct, JUnit 5, Mockito

## Features

- User registration, login, JWT validation, and current user profile
- User listing, profile management, and password changes
- Product listing (optional category filter) and product creation
- Shopping cart creation, item management, and total price calculation
- Bean Validation with centralized HTTP error handling
- SQL schema migrations (`src/main/resources/db/migration`)
- Unit tests for cart operations

## Requirements

- JDK 21
- MySQL 8+ running locally or accessible over the network
- Maven, or use the provided Maven wrapper

## Local setup

1. Create a MySQL user and grant it permission to create/use a database named `store_api`, or create the database yourself.
2. Copy `.env.example` to `.env` in the project root.
3. Set `DB_USERNAME`, `DB_PASSWORD`, and `JWT_SECRET`. Use a randomly generated JWT secret of at least 32 bytes. Adjust `DB_URL` if MySQL is not on localhost.
4. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows: `mvnw.cmd spring-boot:run`.

Spring Boot reads environment values using the configured dotenv dependency. Flyway migrations run when the application starts; the JPA schema is validated against the database.

## API overview

| Method | Path | Description |
| --- | --- | --- |
| `POST` | `/users` | Register a user |
| `POST` | `/auth/login` | Log in and obtain a JWT |
| `POST` | `/auth/validate` | Validate a bearer token |
| `GET` | `/auth/me` | Get the current user |
| `GET` | `/users` | List users (optional `sort`) |
| `GET` | `/users/{id}` | Get a user |
| `PUT` | `/users/{id}` | Update a user |
| `DELETE` | `/users/{id}` | Delete a user |
| `POST` | `/users/{id}/change-password` | Change a password |
| `GET` | `/products` | List products (optional `categoryId`) |
| `POST` | `/products` | Create a product |
| `POST` | `/carts` | Create a cart |
| `GET` | `/carts/{cartId}` | Get cart details |
| `POST` | `/carts/{cartId}/items` | Add an item |
| `PUT` | `/carts/{cartId}/items/{productId}` | Set item quantity |
| `DELETE` | `/carts/{cartId}/items/{productId}` | Remove an item |
| `DELETE` | `/carts/{cartId}/items` | Empty a cart |

The registration, login, and cart endpoints are publicly accessible under the current security rules. Other endpoints require an `Authorization: Bearer <token>` header. Cart IDs act as access tokens for carts; do not use this access model for sensitive production data.

### Example requests

Login (replace the example credentials with a registered user):

```bash
curl -X POST http://localhost:8080/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"you@example.com","password":"your-password"}'
```

Create a cart:

```bash
curl -X POST http://localhost:8080/carts
```

List products with a JWT:

```bash
curl http://localhost:8080/products -H 'Authorization: Bearer YOUR_JWT'
```

## Tests

```bash
./mvnw test
```

## Project layout

```text
src/main/java/com/storeapi/
├── config/        # Spring Security configuration
├── controllers/   # REST endpoints and error handling
├── dtos/          # API request/response models
├── entities/      # JPA domain entities
├── filters/       # JWT and HTTP filters
├── mappers/       # Entity/DTO conversion
├── repositories/  # Persistence interfaces
├── services/      # Application logic
└── validation/    # Custom bean validation
src/main/resources/db/migration/  # Flyway migrations
src/test/                       # Tests
```

## Security and deployment notes

This repository is a learning project and has **not** been security audited. Current endpoints do not enforce per-user ownership for all user-management operations, and carts are accessible to anyone with their IDs. Implement authorization policies, HTTPS, production-specific CORS/CSRF settings, stronger configuration validation, and integration tests before deploying it publicly. Never commit a real `.env` file or credentials. If credentials in an earlier copy were real, rotate them before publishing.

## Reuse and attribution

Some original code was derived from a course starter project. Verify the original starter's license or obtain permission before redistributing derived source code publicly. A new repository name and package name do not themselves change the underlying rights.
