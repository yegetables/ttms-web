FROM jdk11
ADD web-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+8","demo.jar"]
