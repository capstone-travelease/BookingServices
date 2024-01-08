FROM openjdk:21

VOLUME /tmp

COPY target/*.jar BookingServices-0.0.1-SNAPSHOT.jar

EXPOSE 1947
ENTRYPOINT ["java","-jar","/BookingServices-0.0.1-SNAPSHOT.jar"]