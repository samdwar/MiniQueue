# MiniQueue
  MiniQueue is a DropWizard application which provides RESTful API for Producer and Consumer to produce and consume messages respectively.Once consumer processed the message it will notify MiniQ that message with messageIds has been processed and hence delete these message.
  Also if processing is taking more than 30 second then message can be read by any consumer.
 
 Prerequisites:
--- 
  Java, Maven and MySQL.
 
 How to start the MiniQueue application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/MiniQueue-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Available API
-------------
1. Create New Message 
    `http://localhost:8081/send`
    
    This api lets you create new message in the Queue.
   
    Sample JSON response: 
   `{
      "messageId": "c3a92239-84dd-4fa0-8313-995f746adbeb"
    }`
    
2. Receive unprocessed message for processing 
    `http://localhost:8081/receive`
    This api to get message for processing for consumers from Queue.
    
    Sample Output : JSON format
    `[
       {
         "messageId": "141e624a-c939-446d-a858-d3a8eebee30b",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "47d9f580-c4e0-4146-928a-e3f6bf33469e",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "4a6d1059-9aa8-4297-8e26-6bda73585f40",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "503c07db-78e4-43f7-b103-05c4706232e2",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "56b3d0b4-4447-4dfc-830c-16c52e8fcaa7",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "5f83225d-7dd1-4e51-97e4-569826eb31d4",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "6bfacf3b-e1f3-46b6-a14d-21f77c97b9d6",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "7f780e19-6d38-408f-97ae-9deeec862061",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "805ee87e-9b34-4345-b90e-eeb13de8a4e1",
         "isProcessed": 1,
         "isProcessing": 1
       },
       {
         "messageId": "84659298-d242-4cf6-8d51-cab898fa4a09",
         "isProcessed": 1,
         "isProcessing": 1
       }
     ]`

1. Notify that messages with these message id has been processed.
    `localhost:8080/notifyProcessed`
    
    Input Payload: JSON format
    `[
       {
         "messageId": "9908d1cf-8bce-404a-bd14-793a920ac2f2"
       },
       {
         "messageId": "cd1887cf-8679-46bb-b3ba-1a3ba70a0b26"
       },
       {
         "messageId": "d9f91c7a-465b-415f-aaa8-48d5582ebbee"
       },
       {
         "messageId": "f0af76ef-a782-4fb1-bce9-4450d518ae51"
       }
     ]`
    
    Output:
    Status Code : 200 OK
    
Abstracting data storage
------------------------
 The abstraction of data storage is created for this project. There is DataSource interface. You can use this interface for different Data Storage , like File, Network etc. In this case i have used MySQL.
    
Scaling System to support high volume request:
----------------------------------------------
 We can use cluster of machines and ELB (Elastic Load Balancer) to distribute the traffic between these machines. If any machines failes alos then we can easily rotate traffic.
 For faster access of Data we can opt for aerospike and  mongodb.
 