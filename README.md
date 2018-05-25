# SampleProject
Starter project with spring boot and jdbc

### Features:
- Spring boot
- Embebed maven
- REST API with Swagger documentation
- In-memory or remote JDBC database (H2)
  - /src/main/resources/schema.sql -> schema definition for embebed database, will be executed on boot
  - /src/main/resources/data.sql -> data definition for embebed database, will be executed on boot
- DDD modeling

### Configuration:
- /src/main/resources/application.properties -> main configuration file, check comments there

### Basic instructions:
- Deploy -> ./mvnw spring-boot:run
  - Swagger will be loaded on http://localhost:8080/swagger-ui.html
  - H2 embebed database console will be loaded on http://localhost:8080/h2-console

### TODO
- Fix context on tests
- Add field validators https://spring.io/guides/gs/validating-form-input/
- Implement profiles http://www.baeldung.com/spring-profiles