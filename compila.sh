mvn clean package
docker build -t todo .
docker stop todo
docker rm todo
docker run -d --name todo --add-host=host.docker.internal:host-gateway -p 8080:8080 todo
