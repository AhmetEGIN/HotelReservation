FROM maven:3.8.1-openjdk-17-slim AS build

COPY pom.xml ./
COPY .mvn .mvn
COPY src src
RUN  mvn clean install -DskipTests
FROM mcr.microsoft.com/java/jre:17-zulu-alpine
WORKDIR hotel_reservation
ARG JAR_FILE=target/hotelReservation-0.0.1-SNAPSHOT.jar
COPY --from=build target/*.jar /application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/application.jar"]