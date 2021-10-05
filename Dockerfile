FROM openjdk:11
EXPOSE 8082
ADD target/amaresh-initial-docker.jar amaresh-initial-docker.jar
ENTRYPOINT ["java","-jar","/amaresh-initial-docker.jar"]