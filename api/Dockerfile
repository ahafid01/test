FROM gradle:6.2.0-jdk13  AS cache
RUN mkdir /home/gradle/cache
ENV GRADLE_USER_HOME /home/gradle/cache
WORKDIR /home/gradle/api/
COPY build.gradle gradle.properties settings.gradle swagger.yaml ./
RUN gradle --no-daemon build --stacktrace

FROM gradle:6.2.0-jdk13 AS builder
COPY --from=cache /home/gradle/cache /home/gradle/.gradle
WORKDIR /usr/src/api
COPY src src
COPY build.gradle gradle.properties settings.gradle swagger.yaml ./
RUN gradle --no-daemon build --stacktrace

FROM adoptopenjdk/openjdk13:jre-13_33-alpine
WORKDIR /usr/src/api
COPY --from=builder /usr/src/api/build/libs/*.jar ./pfd-api.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar pfd-api.jar" ]
HEALTHCHECK --start-period=90s --interval=90s --retries=3 --timeout=90s CMD curl -f http://localhost:8080/ping || exit 1
