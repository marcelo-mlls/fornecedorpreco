#
# Build stage
#
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY --from=build /home/app/target/sgutp_api-*.jar /usr/local/lib/sgutp_api.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/sgutp_api.jar"]
