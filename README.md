# Multithreaded-dictionary-server

- **University:** University of Melbourne
- **Session:** Semester 1 2022
- **Subject:** COMP90015 Distributed Systems

## Getting started

DictionaryClient:

```bash
$ java -Daddress=127.0.0.1 -Dport=2022 -jar DictionaryServer.jar
```

DictionaryServer:

```bash
$ java -Dport=2022 -Dpath=C:\Dictionary.json -jar DictionaryServer.jar
```

## Main components of the system

### Message exchange protocol
- In order to ensure the integrity of the data in transit, I developed a new packet format to regulate the interaction between the client and the server.
- length[4] + action[2] + content[N]
- The header is fixed at 6 bytes:
- length: Indicates the length of the following content bytes
- action: Indicates the type of message, 0=Response, 1=Query, 2=Add, 3=Remove, 4=Update

### Sockets
- The java.net package provides support for two common network protocols, TCP and UDP. The difference between TCP and UDP is that TCP provides reliable data transfer services while UDP provides unreliable transfers. At the same time, since UDP needs to be considered reliable at the logical level (packet loss and disorder, etc.), using TCP in this project will also greatly reduce my workload.

### Multithreading
- In this project, the server will create a new thread for each connection from the client, and when this thread finishes processing the request and returns the result, the thread will automatically die and close the connection (short connection).
- However, when multiple threads modify the dictionary file at the same time, the dictionary file will be modified multiple times resulting in inconsistencies between the actual result and the expected result. So I added a synchronization lock to the handleRequest method in MultithreadedProcessing class to ensure that only one thread accesses the dictionary file at the same time.

## Interaction diagram

![Interaction diagram](https://user-images.githubusercontent.com/68240769/163997984-0a93c7fa-793e-46e3-a210-2816d96fedec.svg)
