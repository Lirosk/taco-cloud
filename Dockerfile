FROM openjdk:23-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:23-jdk
WORKDIR /app
COPY --from=build /app/main/target/main-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
