# CarZone API

This is a Spring Boot application for managing cars and engines.

## Technologies Used

*   Java 21
*   Spring Boot 3.5.4
*   Spring Data JPA
*   Spring Web
*   MySQL
*   Lombok
*   Maven

## Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Falasefemi2/carZone.git
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd carZone
    ```
3.  **Install dependencies:**
    ```bash
    ./mvnw install
    ```

## Configuration

The application uses a MySQL database. You need to configure the database connection in the `src/main/resources/application.yml` file or by setting environment variables.

**Environment Variables:**

Create a `.env` file in the root of the project with the following content:

```
SPRING_APPLICATION_NAME=carZone
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/carzone
SPRING_DATASOURCE_USERNAME=your-username
SPRING_DATASOURCE_PASSWORD=your-password
JWT_SECRET=your-jwt-secret
JWT_EXPIRATION=86400000
JWT_HEADER=Authorization
JWT_PREFIX=Bearer
```

**`application.yml`:**

```yaml
spring:
  application:
    name: ${SPRING_APPLICATION_NAME}

  config:
    import: optional:file:.env[.properties]

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}

    hikari:
      maximum-pool-size: 10

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.MySQL8Dialect

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION}
  header: ${JWT_HEADER}
  prefix: ${JWT_PREFIX}

server:
  port: ${PORT:8080}

logging:
  level:
    org.springframework.security: DEBUG
```

## API Endpoints

### Car Controller

*   `POST /api/v1/cars`: Create a new car.
*   `GET /api/v1/cars`: Get all cars.
*   `GET /api/v1/cars/{id}`: Get a car by ID.
*   `PUT /api/v1/cars/{id}`: Update a car by ID.
*   `DELETE /api/v1/cars/{id}`: Delete a car by ID.

### Engine Controller

*   `POST /api/v1/engines`: Create a new engine.
*   `GET /api/v1/engines`: Get all engines.
*   `GET /api/v1/engines/{id}`: Get an engine by ID.
*   `PUT /api/v1/engines/{id}`: Update an engine by ID.
*   `DELETE /api/v1/engines/{id}`: Delete an engine by ID.

## Running the Application

You can run the application using the following command:

```bash
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`.
