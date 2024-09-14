FROM eclipse-temurin:17-jre-alpine
ENV TZ=Asia/Seoul
ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
