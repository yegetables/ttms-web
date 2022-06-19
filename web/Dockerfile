FROM openjdk:11
ADD web.jar demo.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+8","demo.jar"]
