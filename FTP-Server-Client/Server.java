// Imports classes required for input and output operations.
import java.io.*;

// Imports classes required for network programming.
import java.net.*;


// Server class contains the FTP server implementation.
class Server
{
    // Stores the number of connected clients.
    // It starts from 1 for displaying client numbers.
    public static int ClientCount = 1;


    // Main method is the starting point of the server program.
    public static void main(String A[])
    {
        try
        {
            // Creates a ServerSocket on port 9000.
            // The server listens for client connection requests on this port.
            ServerSocket serversocket = new ServerSocket(9000);


            // Displays a separator line.
            System.out.println("--------------------------------------------------------");

            // Displays the server started message.
            System.out.println("---Marvellous Server Started---");

            // Displays another separator line.
            System.out.println("--------------------------------------------------------");


            // Continuously waits for multiple client requests.
            while(true)
            {
                // Displays a message while waiting for a client.
                System.out.println("Server is waiting for client request");


                // accept() waits until a client connects.
                // After connection, it returns a Socket object used to communicate with that client.
                Socket clientsocket = serversocket.accept();


                // Displays a successful connection message.
                System.out.println("Client connected successfully...");


                // Creates a separate thread to handle this client.
                // This allows the server to handle multiple clients without making other clients wait.
                Thread t = new Thread(() -> HandleClientRequest(clientsocket));


                // Starts the newly created client-handling thread.
                t.start();

            }
            // End of while loop.
        }
        catch(Exception e)
        {
            // Displays the exception if an error occurs
            // while starting or running the server.
            System.out.println("Exception occured : "+e);
        }

    }
    // End of main method.


    // This method handles all requests received
    // from one particular client.
    public static void HandleClientRequest(Socket socket)
    {
        // Displays the client number for which
        // a new thread has been created.
        System.out.println("New thread gets created for client no : "+ClientCount);

        // Increases the client count for the next client.
        ClientCount++;

        try
        {
            // Creates DataInputStream using the socket input stream.
            // It is used to receive data from the client.
            DataInputStream dis =new DataInputStream(socket.getInputStream());

            // Creates DataOutputStream using the socket output stream.
            // It is used to send data to the client.
            DataOutputStream dos =new DataOutputStream(socket.getOutputStream());


            // Sends an initial message to the client.
            // writeUTF() sends a String in UTF format.
            dos.writeUTF("Connected to Marvellous Server");


            // Continuously waits for commands from the client.
            while(true)
            {
                // Reads the command sent by the client.
                String command = dis.readUTF();


                // Displays the received command on the server console.
                System.out.println("Command received from client : "+command);


                // Splits the command into separate parts using space.
                // Example:
                // "GET Demo.txt"
                // becomes ["GET", "Demo.txt"].
                String parts[] = command.split(" ");


                // Gets the first part of the command.
                // toUpperCase() makes the command case-insensitive.
                String operation = parts[0].toUpperCase();


                // Checks whether the client wants to disconnect.
                if(operation.equals("QUIT"))
                {
                    // QUIT does not require any additional argument.
                    if(parts.length != 1)
                    {
                        // Sends the correct command format to the client.
                        dos.writeUTF("Usage : QUIT");

                        // Skips the remaining code and waits for the next command.
                        continue;
                    }


                    // Sends a disconnection message to the client.
                    dos.writeUTF("Disconnected from server");


                    // Decreases the client count because this client is disconnecting.
                    ClientCount--;


                    // Breaks the loop and ends this client's thread.
                    break;
                }


                // Handles the GET command.
                // GET is used to download a file from server to client.
                if(operation.equals("GET"))
                {
                    // GET requires exactly one filename.
                    if(parts.length != 2)
                    {
                        // Sends the correct GET syntax to the client.
                        dos.writeUTF("Usage : GET <FileName>");

                        continue;
                    }


                    // Gets the filename from the command.
                    String fileName = parts[1];


                    // Creates a File object representing the requested file.
                    File file = new File(fileName);


                    // Checks whether the file exists and whether it is a regular file.
                    if(file.exists() == false || file.isFile() == false)
                    {
                        // Informs the client that the file was not found.
                        dos.writeUTF("FILE_NOT_FOUND");

                        continue;
                    }


                    // Tells the client that the requested file exists.
                    dos.writeUTF("File found");


                    // Gets the size of the file in bytes.
                    long fileSize = file.length();


                    // Sends the file size to the client.
                    // The client uses this value to know
                    // how many bytes it must receive.
                    dos.writeLong(fileSize);


                    // Opens the file for reading.
                    FileInputStream fis = new FileInputStream(file);


                    // Creates a buffer of 1024 bytes.
                    // The file is transferred in small blocks
                    // instead of loading the complete file into memory.
                    byte buffer[] = new byte[1024];


                    // Stores the number of bytes read
                    // during each iteration.
                    int bytesRead = 0;


                    // Reads the file until the end is reached.
                    while((bytesRead = fis.read(buffer)) != -1)
                    {
                        // Sends the bytes read from the file
                        // to the client.
                        dos.write(buffer, 0,bytesRead);
                    }


                    // Ensures that all buffered data is sent to the client.
                    dos.flush();


                    // Closes the file input stream.
                    fis.close();


                    // Displays a success message on the server console.
                    System.out.println("File sent successfully to the client");
                }


                // Handles the PUT command.
                // PUT is used to upload a file from client to server.
                else if(operation.equals("PUT"))
                {
                    // PUT requires exactly one filename.
                    if(parts.length != 2)
                    {
                        // Sends the correct PUT syntax to the client.
                        dos.writeUTF("Usage : PUT <FileName>");

                        continue;
                    }


                    // Gets the filename from the command.
                    String fileName = parts[1];


                    // Tells the client that the server is ready to receive the file.
                    dos.writeUTF("READY");


                    // Reads the file size sent by the client.
                    long fileSize = dis.readLong();


                    // Creates a FileOutputStream.
                    // It creates the file on the server and writes the received data into it.
                    FileOutputStream fos = new FileOutputStream(fileName);


                    // Creates a buffer of 1024 bytes.
                    byte buffer[] = new byte[1024];


                    // Keeps track of the total number of bytes received from the client.
                    long received = 0;


                    // Continues receiving data until the complete file is received.
                    while(received < fileSize)
                    {
                        // Calculates how many bytes are still remaining.
                        long remaining = fileSize - received;

                        // Stores the number of bytes to read in the current iteration.
                        int toread;

                        // Checks whether the remaining data is larger than the buffer size.
                        if(remaining > buffer.length)
                        {
                            // Reads a maximum of 1024 bytes.
                            toread = buffer.length;
                        }
                        else
                        {
                            // Reads only the remaining bytes when they are less than the buffer size.
                            toread = (int)remaining;
                        }


                        // Reads bytes received from the client into the buffer.
                        int bytesread =
                            dis.read(buffer, 0,toread);

                        // Checks whether the input stream has ended.
                        if(bytesread == -1)
                        {
                            // Stops receiving data.
                            break;
                        }

                        // Writes the received bytes into the file.
                        fos.write(buffer,0,bytesread);

                        // Updates the total number of bytes received.
                        received = received + bytesread;
                    }
                    // End of file receiving loop.


                    // Closes the file output stream.
                    fos.close();


                    // Checks whether the complete file was received.
                    if(received == fileSize)
                    {
                        // Sends a successful upload message to the client.
                        dos.writeUTF("File uploaded successfully");
                    }
                    else
                    {
                        // Sends a failure message when
                        // the complete file was not received.
                        dos.writeUTF( "File uploaded failed");
                    }
                }


                // Handles the INFO command.
                // INFO provides information about a file.
                else if(operation.equals("INFO"))
                {
                    // INFO requires exactly one filename.
                    if(parts.length != 2)
                    {
                        // Sends the correct INFO syntax.
                        dos.writeUTF("Usage : INFO <FileName>");

                        continue;
                    }


                    // Creates a File object using the filename.
                    File file = new File(parts[1]);


                    // Checks whether the file exists.
                    if(file.exists())
                    {
                        // Creates an empty String to store
                        // the file information.
                        String info = "";


                        // Adds the filename to the information.
                        info = info +"File name : " + file.getName() +"\n";

                        // Adds the file size to the information.
                        info = info +"File size : " +file.length() +"\n";

                        // Adds the file read permission information.
                        info = info +
                               "Readable : " +
                               file.canRead() +
                               "\n";

                        // Adds the file write permission information.
                        info = info +"Writable : " +file.canWrite() +"\n";

                        // Sends the complete file information
                        // to the client.
                        dos.writeUTF(info);
                    }
                    else
                    {
                        // Sends a message when the file does not exist.
                        dos.writeUTF("File does not exist");
                    }
                }


                // Handles the SIZE command.
                // SIZE returns the size of a specified file.
                else if(operation.equals("SIZE"))
                {
                    // SIZE requires exactly one filename.
                    if(parts.length != 2)
                    {
                        // Sends the correct SIZE syntax.
                        dos.writeUTF("Usage : SIZE <FileName>");

                        continue;
                    }


                    // Creates a File object using the filename.
                    File file = new File(parts[1]);


                    // Checks whether the file exists
                    // and is a regular file.
                    if(file.exists() && file.isFile())
                    {
                        // Sends the file size to the client.
                        dos.writeUTF("File size is : "+file.length());
                    }
                    else
                    {
                        // Sends an error message if the file
                        // does not exist.
                        dos.writeUTF("File does not exist");
                    }
                }


                // Handles the EXIST command.
                // EXIST checks whether a file is available.
                else if(operation.equals("EXIST"))
                {
                    // EXIST requires exactly one filename.
                    if(parts.length != 2)
                    {
                        // Sends the correct EXIST syntax.
                        dos.writeUTF("Usage : EXIST <FileName>");

                        continue;
                    }


                    // Creates a File object using the filename.
                    File file = new File(parts[1]);


                    // Checks whether the file exists.
                    if(file.exists())
                    {
                        // Sends a positive response to the client.
                        dos.writeUTF("File exist");
                    }
                    else
                    {
                        // Sends a negative response to the client.
                        dos.writeUTF("File does not exist");
                    }
                }


                // Handles the RENAME command.
                // RENAME changes the name of a file.
                else if(operation.equals("RENAME"))
                {
                    // RENAME requires an old filename
                    // and a new filename.
                    if(parts.length != 3)
                    {
                        // Sends the correct RENAME syntax.
                        dos.writeUTF(
                            "Usage : RENAME <OldFileName> <NewFileName>"
                        );

                        continue;
                    }


                    // Creates a File object for the old filename.
                    File oldFile = new File(parts[1]);


                    // Creates a File object for the new filename.
                    File newFile = new File(parts[2]);


                    // Checks whether the source file exists.
                    if(oldFile.exists() == false)
                    {
                        // Sends an error message when
                        // the source file does not exist.
                        dos.writeUTF(
                            "Source file does not exists"
                        );

                        continue;
                    }


                    // Attempts to rename the old file
                    // to the new filename.
                    if(oldFile.renameTo(newFile))
                    {
                        // Sends a success message if renaming works.
                        dos.writeUTF(
                            "File renamed successfully"
                        );
                    }
                    else
                    {
                        // Sends a failure message if renaming fails.
                        dos.writeUTF(
                            "Unable to rename file"
                        );
                    }
                }


                // Handles the DELETE command.
                // DELETE removes a file from the server.
                else if(operation.equals("DELETE"))
                {
                    // DELETE requires exactly one filename.
                    if(parts.length != 2)
                    {
                        // Sends the correct DELETE syntax.
                        dos.writeUTF("Usage : DELETE <FileName>");

                        continue;
                    }


                    // Creates a File object using the filename.
                    File file = new File(parts[1]);


                    // Checks whether the file exists.
                    if(file.exists() == false)
                    {
                        // Sends an error when the file is not found.
                        dos.writeUTF(
                            "There is no such file"
                        );

                        continue;
                    }


                    // Attempts to delete the file.
                    if(file.delete())
                    {
                        // Sends a success message if deletion works.
                        dos.writeUTF(
                            "File deleted successfully"
                        );
                    }
                    else
                    {
                        // Sends a failure message if deletion fails.
                        dos.writeUTF(
                            "Unable to delete the file"
                        );
                    }
                }


                // Handles the LIST command.
                // LIST displays the files available
                // in the server's current directory.
                else if(operation.equals("LIST"))
                {
                    // LIST does not require any argument.
                    if(parts.length != 1)
                    {
                        // Sends the correct LIST syntax.
                        dos.writeUTF("Usage : LIST");

                        continue;
                    }


                    // Creates a File object representing
                    // the current directory.
                    // "." means the current directory.
                    File folder = new File(".");


                    // Gets all files and directories
                    // present in the current directory.
                    File files[] = folder.listFiles();


                    // Creates an empty String to store
                    // the names of available files.
                    String result = "";


                    // Checks whether files were successfully obtained.
                    if(files != null)
                    {
                        // Loops through every file and directory.
                        for(File f : files)
                        {
                            // Checks whether the current item is a file.
                            if(f.isFile())
                            {
                                // Adds the filename to the result.
                                result =
                                    result +
                                    f.getName() +
                                    "\n";
                            }
                        }
                    }


                    // Checks whether no files were found.
                    if(result.length() == 0)
                    {
                        // Displays a message when the directory
                        // contains no files.
                        result = "No files available";
                    }


                    // Sends the file list to the client.
                    dos.writeUTF(result);
                }


                // Handles commands that are not recognized.
                else
                {
                    // Sends an invalid operation message
                    // to the client.
                    dos.writeUTF("Invalid operation");
                }

            }
            // End of command processing while loop.


            // Closes the socket connection with this client.
            socket.close();


            // Closes the input stream.
            dis.close();


            // Closes the output stream.
            dos.close();


            // Displays a message after the client disconnects.
            System.out.println("Client disconnected...");
        }
        // End of try block.
        catch(Exception e)
        {
            // Displays the exception if an error occurs
            // while handling the client.
            System.out.println(
                "Exception occured : "+e
            );
        }

    }
    // End of HandleClientRequest method.

}
// End of Server class.