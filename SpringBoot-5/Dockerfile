FROM openjdk:8
ADD target/SpringBoot-5-0.0.1-SNAPSHOT.jar docker-spring-boot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]