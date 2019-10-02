# Running Mongo Db

Open new terminal and run the follow command to start mongo:

```
docker run -d --name "mongodb" -p "27016:27017" mongo
```

To start the application run the follow command:

```
mvn spring-boot:run
```