# RMI-Client-Server-App

## Problem representation	
Network programming project for implementing Client Server communication model using RMI
A distributed exchange system will be developed for this project.
Using a simple request-reply protocol. Clients send to Server a Request and then the Server responds with a Response and their connection terminates.

## How to executed
App executed through the jars file.
Turn on the server with the **java server <port number>** command.
Turn on the client with the **java client <ip> <port number> <FN_ID> <args>** command.

### More details 
ip: The IP address of the Server
port number: The port to which the Server is listening
FN_ID: The ID of the function to be performed
args: the parameters of the operation

### App's fuction
Create Account (FN_ID: 1)
```
java client <ip> <port number> 1 <username>
```
Show Accounts (FN_ID: 2)
```
java client <ip> <port number> 2 <authToken>
```
Send Message (FN_ID: 3)
```
java client <ip> <port number> 3 <authToken> <recipient>
<message_body>
```
Show Inbox (FN_ID: 4)
```
java client <ip> <port number> 4 <authToken>
```
ReadMessage (FN_ID: 5)
```
java client <ip> <port number> 5 <authToken> <message_id>
```
DeleteMessage (FN_ID: 6)
```
java client <ip> <port number> 6 <authToken> <message_id>
```

## Implementation details
#### Client
Implements connection to the server
There is an if for each command it can accept and execute. Calls the appropriate Interface method that is connected
to the server to provide its services and print the appropriate message.

#### Messaging
Interface that can create an account and manage messages between users

#### Message
Class that represents a message with message id information about the contents of the sender and the recipient

#### Account
A class that represents a user account with the username, token and a list of received messages.

#### MessagingServer
Implements Messaging to provide its services. There is a check in each function for the correctness of the token
and returns the appropriate message to the user. 

