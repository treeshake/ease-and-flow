FROM openjdk:15-jdk
COPY ["build/libs/*.jar", "app.jar"]
EXPOSE 8510
ENV JAVA_OPTS=""
ENV SPRING_PROFILE=""

RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$SPRING_PROFILE -jar /app.jar" ]
