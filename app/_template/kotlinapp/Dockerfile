FROM curlimages/curl:8.1.1 AS download
ARG OTEL_AGENT_VERSION="1.27.0"
RUN curl --silent --fail -L "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_AGENT_VERSION}/opentelemetry-javaagent.jar" \
    -o "$HOME/opentelemetry-javaagent.jar"

FROM gradle:8.3.0-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:21-ea-17-jdk-slim
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/product-service-0.0.1-SNAPSHOT.jar /app/product-service.jar
COPY --from=download /home/curl_user/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
EXPOSE 5555
ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/product-service.jar"]