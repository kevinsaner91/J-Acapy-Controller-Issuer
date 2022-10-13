# J-Acapy-Controller-issuer

A sample implementation of an Aries Controller in Java-Web. The controller is written to call the faber agent described in numerous demos. The main task of faber is to issue credentials. With the written implementation of the controller schema and credential definitions can be defined via the UI. 

To run the controller, clone the repo, go to the docker folder and first set the properties in the file config.properties. Here you need to specify the URL of the faber agent. Next build the image with the command:
```
docker build -t faber-controller .
```

The built image can be run with:
```
docker run -p 8080:8080 -d faber-controller
```
