
# Java FTP Server-Client

A simple **FTP (File Transfer Protocol) Server-Client application** developed in Java using **Socket Programming**.

This project demonstrates how a client and server communicate over a network and perform basic file operations such as **file upload and download**.

## Features

* Client-Server communication using TCP sockets
* FTP server implementation in Java
* Client connection with the server
* File information checking
* File upload from client to server
* File download from server to client
* FTP command execution
* File size transfer using `DataInputStream` and `DataOutputStream`
* Byte-level file transfer using `FileInputStream` and `FileOutputStream`

## Technologies Used

* **Java**
* **Socket Programming**
* **TCP/IP**
* **File Handling**
* **DataInputStream**
* **DataOutputStream**
* **FileInputStream**
* **FileOutputStream**

## Project Structure

```text
Java-FTP-Server-Client/
│
├── FTPServer.java
├── FTPClient.java
├── README.md
│
└── screenshots/
    ├── client-server-connection.png
    ├── file-information.png
    ├── ftp-command-execution.png
    └── file-upload-download.png
```

## How It Works

The project follows a client-server architecture.

```text
        Client
          |
          | TCP Connection
          |
          v
        Server
          |
          |
    File Operations
     /           \
 Upload         Download
```

### Server

The server:

1. Creates a `ServerSocket`.
2. Waits for a client connection.
3. Accepts the client request.
4. Communicates with the client using input/output streams.
5. Performs requested file operations.

### Client

The client:

1. Connects to the server using a socket.
2. Sends requests to the server.
3. Receives responses from the server.
4. Uploads or downloads files according to the selected operation.

## How to Run

### 1. Compile the Server

Open Command Prompt in the project directory and run:

```bash
javac FTPServer.java
```

### 2. Compile the Client

```bash
javac FTPClient.java
```

### 3. Start the Server

```bash
java FTPServer
```

The server will start and wait for a client connection.

### 4. Start the Client

Open another Command Prompt and run:

```bash
java FTPClient
```

The client will connect to the FTP server and allow file operations.

## Screenshots

### Client-Server Connection

![Client Server Connection](screenshots/client-server-connection.png)

### File Information

![File Information](screenshots/file-information.png)

### FTP Command Execution

![FTP Command Execution](screenshots/ftp-command-execution.png)

### File Upload and Download

![File Upload and Download](screenshots/file-upload-download.png)

## Learning Outcomes

Through this project, I learned:

* Java Socket Programming
* TCP Client-Server communication
* Network programming concepts
* File handling in Java
* Input and Output streams
* File upload and download
* Basic FTP concepts
* Communication between multiple Java programs

## Future Improvements

* Add user authentication
* Add multiple client support
* Add GUI-based client
* Add directory listing
* Add file deletion and renaming
* Improve error handling
* Add secure file transfer

## Author

**Sharvari Bhosale**
