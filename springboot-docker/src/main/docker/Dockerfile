#FROM openjdk:8-jdk-alpine
#FROM adoptopenjdk/openjdk16
FROM mdsol/java17-jdk
VOLUME /tmp
ADD springboot-docker-1.0.jar spring-boot-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/spring-boot-docker.jar"]