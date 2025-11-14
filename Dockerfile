
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build

COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

COPY src ./src

RUN mvn -q -e -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /build/target/*-runner.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]