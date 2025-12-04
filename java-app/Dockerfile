# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (better caching)
COPY pom.xml .
RUN mvn -q -B dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn -q -B package -DskipTests

# ---- Run stage ----
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

