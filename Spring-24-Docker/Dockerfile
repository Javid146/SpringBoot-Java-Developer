#Baseimage /it has to be in any dockerfile
#When you use the FROM openjdk:17-jdk image in your Dockerfile, it provides you with a pre-configured
#operating system (OS) environment along with the Java Development Kit (JDK) installed
FROM openjdk:17-jdk
#will get jar file and copy to image
COPY ./target/Spring-24-Docker-0.0.1-SNAPSHOT.jar  /usr/app/
#to direct any folder
WORKDIR /usr/app
#will provide exacutable application when container starts to run
ENTRYPOINT ["java","-jar","Spring-24-Docker-0.0.1-SNAPSHOT.jar"]

#docker container rm -f containerID = force remove
#docker container start containerID
#docker container stop containerID
#docker container logs containerID = show logs
#docker image build -t imageName . = build image in current dir
#docker push imageName = image will be pushed to your docker account

#delete all
#docker container prune
#docker image prune
#docker volume prune

#docker-compose.yaml
#docker-compose up
#docker-compose down