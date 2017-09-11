FROM nonprod.dtr.trusted.visa.com/mbp/jre8:0.2.0
MAINTAINER timothy_hodkinson@yahoo.com

# Ports used by this container
EXPOSE 8080

# copies the application jar into the container and renames it to app.jar
COPY target/aqe-0.0.1-SNAPSHOT.jar /app.jar

# add a timestamp to the file (can be any shell command)
RUN touch /app.jar

# what process to start when a container is started
ENTRYPOINT ["java", "-jar", "/app.jar"]
