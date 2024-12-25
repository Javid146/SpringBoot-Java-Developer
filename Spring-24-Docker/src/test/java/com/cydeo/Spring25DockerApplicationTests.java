package com.cydeo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Spring25DockerApplicationTests {

    @Test
    void contextLoads() {
    }
}

/*
1. Pulling and Running Docker Images

Pulling an image from Docker Hub:
docker image pull cydeo/firstdocker
This command pulls the `cydeo/firstdocker` image from Docker Hub to your local machine.

Listing all Docker images on your system:
docker image ls
This command lists all the Docker images available on your local machine.

Running a Docker container from an image:
docker container run cydeo/firstdocker
This command runs a Docker container from the `cydeo/firstdocker` image.

Listing running containers:
docker container ls
This command shows all containers that are currently running.

Listing all containers (including stopped ones):
docker container ls -a
This command lists all containers on your system, whether they are running or stopped.

Removing a specific container:
docker container rm 9ead4990a642
This command removes the container with ID `9ead4990a642`. You can find the container ID from the output of `docker container ls -a`.



2. Mapping Ports in Docker

Understanding the `-p` flag:
-p 8080:8080
The first `8080` is the **host machine's port**.
The second `8080` is the **container’s internal port**.
This means that any traffic coming to port 8080 on your host machine will be forwarded to port 8080 inside the container.

Running a container with port mapping:
docker container run -p 8080:8080 cydeo/firstdocker
This runs the `cydeo/firstdocker` image, mapping port 8080 on the host to port 8080 inside the container.

Running a container in detached mode:
docker container run -p 8080:8080 -d 02c1218a35d5
-d: Detached mode (runs the container in the background without logging output).
02c1218a35d5: The container ID or image ID (can also use image names here).

Stopping a running container:
docker container stop 02c1218a35d5
This stops the running container with ID `02c1218a35d5`.



3. Building and Running a Custom Docker Image

Building a Docker image from a Dockerfile:
docker image build -t javid_repo/firstdocker .
-t javid_repo/firstdocker: This tags the image with the name `javid_repo/firstdocker`.
.: Refers to the current directory, which should contain the `Dockerfile`.

Running a container from your custom image:
docker run -p 8080:8080 javid_repo/firstdocker
This runs the `javid_repo/firstdocker` image, mapping port 8080 on the host to port 8080 inside the container.



4. Docker Image Tagging and Pushing

Tagging an image before pushing to Docker Hub:
docker tag javid_repo/firstdocker javid146/javid_repo/firstdocker:latest
This tags the image `javid_repo/firstdocker` with a new tag `latest` and assigns it to the repository `javid146/javid_repo/firstdocker`.
Docker does not always accept untagged images when pushing. If you try to push an image without a tag, Docker may reject it.
Therefore, we are required to tag the image before pushing it to Docker Hub.
NOTE: When tagging an image, the naming convention is as follows:
<docker_username>/<repository_name>/<image_name>:<tag>
In this example, `javid146` is the Docker username, `javid_repo` is the repository name, `firstdocker` is the image name, and `latest` is the tag.

Pushing the tagged image to Docker Hub:
docker push javid146/javid_repo:latest
This command pushes the image with tag `latest` to Docker Hub under the repository `javid146/javid_repo`.



5. Deleting Stopped Containers

Pruning (deleting) all stopped containers:
docker container prune
This command removes all stopped containers from your system to free up space.



6. Docker Login

Logging into Docker Hub:
docker login -u javid146
This command logs you into Docker Hub using the username `javid146`.
NOTE: When logging in through the CLI, you need to provide a Docker Personal Access Token (PAT), not your Docker Hub password.
If you try to use your password, it might not work. Instead, generate a PAT from Docker Hub and use it when prompted.



7. Renaming Containers

Renaming a running container and mapping ports:
docker container run --name front-end -d -p 8080:8080 javid_repo/firstdocker
--name front-end: Renames the container to `front-end`.
-d: Runs the container in detached mode.
-p 8080:8080: Maps the host's port 8080 to the container’s port 8080.
javid_repo/firstdocker: The image to run.



8. Running Java Applications in Docker

When running Java applications inside a Docker container, you don't need to manually install the JDK or a full OS.
You can choose a pre-built image, like `openjdk:17-jdk`, which includes the JDK and a minimal OS.

Example `Dockerfile`: FROM openjdk:17-jdk
This will pull an image that contains Java 17 and the necessary JDK to run your Java application.



9. The Target Folder in Maven Projects

What is the Target Folder?
The `target` folder is generated by Maven during the build lifecycle (e.g., after running `mvn clean install`). It contains the build outputs such as:
- **Compiled Classes**: `.class` files generated from `.java` files.
- **Test Results**: Reports from unit tests (if you run tests with Maven).
- **Artifacts**: Packaged files like `.jar`, `.war`, or `.ear`.
- **Dependency Files**: Temporary files or libraries used during the build process.
The `target` folder is important because it holds the final output (e.g., JARs or WARs) that can be used for deployment.



10. Understanding the `docker run` Command with Multiple Ports

Why Multiple Ports?
Mapping multiple ports is often required when a container runs several services that need access from outside the container, such as a web server and a database, each running on a different port.

Running a container with multiple port mappings:
docker run -p 8080:8080 -p 8081:8081 <image-name>
This command:
- Maps **Host Port 8080** to **Container Port 8080**.
- Maps **Host Port 8081** to **Container Port 8081**.

This is useful when you have different services inside the container running on different ports. For example:
- Port `8080` could be used for a web server.
- Port `8081` could be used for an API or another service.
*/
