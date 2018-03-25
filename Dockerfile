FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 8089
RUN mkdir -p /app/
ADD build/libs/app.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]