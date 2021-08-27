FROM openjdk:11
ADD target/user-docker.jar user-docker.jar
ENTRYPOINT ["java","-jar","user-docker.jar"]
EXPOSE 8081