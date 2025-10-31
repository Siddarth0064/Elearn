# Stage 1: Build with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
COPY portal /build
WORKDIR /build
RUN mvn -DskipTests package

# Stage 2: Run the JAR
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar

ENV PORT=8081
EXPOSE 8081
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
