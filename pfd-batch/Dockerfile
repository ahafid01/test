FROM gradle:6.2.0-jdk13  AS cache
RUN mkdir /home/gradle/cache
ENV GRADLE_USER_HOME /home/gradle/cache
COPY build.gradle gradle.properties settings.gradle /home/gradle/api/
WORKDIR /home/gradle/api/
RUN gradle --no-daemon build --stacktrace

FROM gradle:6.2.0-jdk13 AS builder
COPY --from=cache /home/gradle/cache /home/gradle/.gradle
WORKDIR /usr/src/api
COPY src src
COPY build.gradle gradle.properties settings.gradle ./
RUN gradle --no-daemon build --stacktrace

FROM adoptopenjdk/openjdk13:jre-13_33-alpine
WORKDIR /usr/src/api
COPY --from=builder /usr/src/api/build/libs/*.jar ./pfd-batch.jar
ENTRYPOINT [ "sh", "-c", "java -jar pfd-batch.jar" ]