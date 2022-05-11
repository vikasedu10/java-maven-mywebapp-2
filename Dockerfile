FROM openjdk:8-jre-alpine

EXPOSE 8080

RUN mkdir -p /usr/app
COPY ./target/mywebapp-*.jar /usr/app/
WORKDIR /usr/app

# ENTRYPOINT [ "java", "-jar", "mywebapp-*.jar" ]
CMD java -jar mywebapp-*.jar