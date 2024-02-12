FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . .

ENV DB_HOST=postgres

RUN chmod +x mvnw
RUN ./mvnw install -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "target/super-fancy-stock-app-0.0.1-SNAPSHOT.jar"]