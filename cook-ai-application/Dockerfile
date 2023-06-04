FROM openjdk:17-jdk-buster

WORKDIR /usr/src/app

COPY gradlew .
RUN chmod +x ./gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src ./src

RUN ./gradlew clean build --exclude-task test

CMD ["java", "-jar", "build/libs/CookAiApplicationApplication-0.0.1-SNAPSHOT.jar"]
