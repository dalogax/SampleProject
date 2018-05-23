# SampleProject
Starter project with spring boot and jdbc

### Features:
- Spring boot
- Embebed maven
- REST API with Swagger documentation
- In-memory or remote JDBC database(H2)
  - /src/main/resources/schema.sql -> schema definition for embebed database, will be executed on boot
  - /src/main/resources/data.sql -> data definition for embebed database, will be executed on boot
- DDD modeling

### Configuration:
- /src/main/resources/application.properties -> main configuration file, check comments there

### Basic instructions:
- Deploy -> ./mvnw spring-boot:run