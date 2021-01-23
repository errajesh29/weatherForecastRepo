FROM adoptopenjdk/openjdk11:ubi
#VOLUME /tmp
ADD target/forecast-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]