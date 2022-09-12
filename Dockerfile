FROM maven:3.8.6-amazoncorretto-8 as BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/

RUN mvn clean package
COPY target/spring-boot-banking-demo-${VERSION}.jar target/application.jar

FROM amazoncorretto:8-alpine3.13-jre
WORKDIR /app/

COPY --from=BUILDER /build/target/application.jar /app/
CMD java -jar /app/application.jar
