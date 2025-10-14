# Etapa 1: Compilación
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar archivos necesarios
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

# Compilar sin tests
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el JAR desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto (Render lo usa dinámicamente)
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
