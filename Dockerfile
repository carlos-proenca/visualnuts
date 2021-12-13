FROM adoptopenjdk/openjdk11:alpine

MAINTAINER carlos proença <carlos_proenca@live.com>

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/dist/app.jar

ENTRYPOINT ["java", "-jar", "/usr/dist/app.jar"]