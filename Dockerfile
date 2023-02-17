FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/shelper-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8081 80 443 22
ENTRYPOINT ["java", "-jar", "shelper-0.0.1-SNAPSHOT.jar.jar"]

