FROM eclipse-temurin:21
LABEL maintainer="matheuspichler94@gmail.com"
WORKDIR /app
COPY target/DockerAulaJava10x.jar /app/aula-docker.jar
ENTRYPOINT ["java", "-jar", "aula-docker.jar"]
