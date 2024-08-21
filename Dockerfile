FROM openjdk:17-jdk-alpine 

#Set working directory
WORKDIR /app 

#Copy jar file
COPY target/courses-backend-0.0.1-SNAPSHOT.jar /app/courses-backend-0.0.1-SNAPSHOT.jar

#Expose a port
EXPOSE 8080

#Run your jar file
CMD [ "java", "-jar", "courses-backend-0.0.1-SNAPSHOT.jar" ]
