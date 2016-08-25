# MiniQueue

How to start the MiniQueue application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/MiniQueue-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Available API
-------------
1. `http://localhost:8081/send`
    This api lets you create new message in the Queue.
    Sample response: 
    `{
      "messageId": "c3a92239-84dd-4fa0-8313-995f746adbeb"
    }`
2. `http://localhost:8081/receive`
    This api to get message for processing for consumers from Queue.
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
    
