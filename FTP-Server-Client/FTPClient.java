import java.io.*;
import java.net.*;

public class FTPClient
{
    // Socket is used to establish communication between
    // the FTP client and the FTP server.
    private Socket socket;

    // DataInputStream is used to receive data from the server.
    private DataInputStream dis;

    // DataOutputStream is used to send data to the server.
    private DataOutputStream dos;


    // This method connects the client to the server.
    // Server contains the server address and Port contains
    // the port number on which the server is listening.
    public void Connect(String Server, int Port) throws Exception
    {
        // Creates a socket connection with the server
        // using the server address and port number.
        socket = new Socket(
                            Server,
                            Port
                           );

        // Gets the input stream from the socket.
        // It is used to receive data from the server.
        dis = new DataInputStream(
            socket.getInputStream()
        );

        // Gets the output stream from the socket.
        // It is used to send data to the server.
        dos = new DataOutputStream(
            socket.getOutputStream()
        );

        // Reads the first UTF message sent by the server.
        // This receives the server's initial response.
        dis.readUTF();
    }


    // This method checks whether the client is connected
    // to the server or not.
    public boolean IsConnected()
    {
        // Checks whether the socket exists,
        // whether it is connected, and whether it is not closed.
        if(socket != null &&
           socket.isConnected() &&
           socket.isClosed() == false)
        {
            // Returns true when the client is connected.
            return true;
        }

        // Returns false when the client is not connected.
        return false;
    }


    // This method requests the server to send
    // the list of available files.
    public String ListFiles() throws Exception
    {
        // Creates the LIST command.
        // LIST tells the server to return the file list.
        String command = "LIST";

        // Sends the LIST command to the server.
        dos.writeUTF(command);

        // Reads the response sent by the server.
        String response = dis.readUTF();

        // Returns the server response.
        return response;
    }


    // This method checks whether the specified file
    // exists on the server.
    public String CheckExist(String FileName) throws Exception
    {
        // Creates the EXIST command along with the filename.
        // Example: EXIST test.txt
        String command = "EXIST " + FileName;

        // Sends the EXIST command to the server.
        dos.writeUTF(command);

        // Reads the response sent by the server.
        String response = dis.readUTF();

        // Returns the server response.
        return response;
    }


    // This method requests information about a file
    // from the server.
    public String GetInfo(String FileName) throws Exception
    {
        // Creates the INFO command along with the filename.
        // Example: INFO test.txt
        String command = "INFO " + FileName;

        // Sends the INFO command to the server.
        dos.writeUTF(command);

        // Reads the response sent by the server.
        String response = dis.readUTF();

        // Returns the file information received from the server.
        return response;
    }


    // This method requests the size of a file
    // from the server.
    public String GetSize(String FileName) throws Exception
    {
        // Creates the SIZE command along with the filename.
        // Example: SIZE test.txt
        String command = "SIZE " + FileName;

        // Sends the SIZE command to the server.
        dos.writeUTF(command);

        // Reads the response sent by the server.
        String response = dis.readUTF();

        // Returns the file size received from the server.
        return response;
    }


    // This method downloads a file from the server
    // and saves it with a new filename on the client.
    public String DownloadFile(
                                String FileName,
                                String NewFileName
                              ) throws Exception
    {
        // Creates the GET command with the filename.
        // GET tells the server to send the requested file.
        String command = "GET " + FileName;

        // Sends the GET command to the server.
        dos.writeUTF(command);

        // Reads the server's response.
        String response = dis.readUTF();

        // Checks whether the requested file was found
        // on the server.
        if(response.equals("FILE_NOT_FOUND"))
        {
            // Returns an error message when the file
            // is not available on the server.
            return "File not found on server";
        }

        // Reads the file size sent by the server.
        // long is used because a file can be very large.
        long fileSize = dis.readLong();

        // Creates a FileOutputStream to create the downloaded
        // file and write the received bytes into it.
        FileOutputStream fos =
            new FileOutputStream(NewFileName);

        // Creates a byte array of 1024 bytes.
        // The file is transferred in small blocks.
        byte buffer[] = new byte[1024];

        // Stores the total number of bytes received.
        long received = 0;


        // Continues receiving data until the complete file
        // has been received.
        while(received < fileSize)
        {
            // Calculates the number of bytes still remaining.
            long remaining = fileSize - received;

            // Stores the number of bytes to read
            // during the current iteration.
            int toRead;

            // Checks whether the remaining data is larger
            // than the buffer size.
            if(remaining > buffer.length)
            {
                // Reads data equal to the buffer size.
                toRead = buffer.length;
            }
            else
            {
                // Reads only the remaining number of bytes
                // when the remaining data is smaller than the buffer.
                toRead = (int)remaining;
            }

            // Reads data received from the server
            // into the buffer.
            int bytesread =
                dis.read(
                         buffer,
                         0,
                         toRead
                        );

            // Checks whether the end of the input stream
            // has been reached.
            if(bytesread == -1)
            {
                // Stops the loop when no more data is available.
                break;
            }

            // Writes the received bytes into the new file.
            fos.write(
                      buffer,
                      0,
                      bytesread
                     );

            // Updates the total number of bytes received.
            received = received + bytesread;
        }

        // Closes the file output stream.
        fos.close();

        // Checks whether all bytes of the file were received.
        if(received == fileSize)
        {
            // Returns a success message when the complete
            // file has been downloaded.
            return "Download completed successfully";
        }
        else
        {
            // Returns a failure message when the complete
            // file was not received.
            return "Download failed";
        }
    }


    // This method uploads a file from the client
    // to the server.
    public String UploadFile(String FileName) throws Exception
    {
        // Creates a File object representing the file
        // that needs to be uploaded.
        File file = new File(FileName);

        // Checks whether the file exists and whether
        // the specified path represents a file.
        if(file.exists() == false ||
           file.isFile() == false)
        {
            // Returns an error message when the file
            // does not exist or is not a regular file.
            return "File does not exist";
        }

        // Creates the PUT command using the file name.
        // PUT tells the server that the client wants
        // to upload a file.
        String command =
            "PUT " + file.getName();

        // Sends the PUT command to the server.
        dos.writeUTF(command);

        // Reads the server's response.
        String response = dis.readUTF();

        // Checks whether the server is ready
        // to receive the file.
        if(response.equals("READY"))
        {
            // Gets the size of the file in bytes.
            long fileSize = file.length();

            // Sends the file size to the server.
            dos.writeLong(fileSize);

            // Opens the file for reading.
            FileInputStream fis =
                new FileInputStream(file);

            // Creates a byte array of 1024 bytes.
            // The file is read and sent in small blocks.
            byte buffer[] = new byte[1024];

            // Stores the number of bytes read from the file.
            int bytesread = 0;

            // Reads the file continuously until the
            // end of the file is reached.
            while((bytesread = fis.read(buffer)) != -1)
            {
                // Sends the bytes read from the file
                // to the server.
                dos.write(
                          buffer,
                          0,
                          bytesread
                         );
            }

            // Ensures that any buffered data is sent
            // to the server immediately.
            dos.flush();

            // Closes the file input stream.
            fis.close();

            // Reads the final response from the server
            // after the upload is completed.
            response = dis.readUTF();

            // Returns the server's final response.
            return response;
        }

        // Returns the server response when it is not READY.
        return response;
    }


    // This method requests the server to delete
    // the specified file.
    public String DeleteFile(String FileName) throws Exception
    {
        // Creates the DELETE command with the filename.
        String command = "DELETE " + FileName;

        // Sends the DELETE command to the server.
        dos.writeUTF(command);

        // Reads the response from the server.
        String response = dis.readUTF();

        // Returns the server response.
        return response;
    }


    // This method requests the server to rename a file.
    public String RenameFile(
                              String OldFileName,
                              String NewFileName
                            ) throws Exception
    {
        // Creates the RENAME command using
        // the old and new filenames.
        String command =
            "RENAME " +
            OldFileName +
            " " +
            NewFileName;

        // Sends the RENAME command to the server.
        dos.writeUTF(command);

        // Reads the response from the server.
        String response = dis.readUTF();

        // Returns the server response.
        return response;
    }


    // This method sends the QUIT command to the server
    // to terminate the FTP session.
    public String Quit() throws Exception
    {
        // Creates the QUIT command.
        String command = "QUIT";

        // Sends the QUIT command to the server.
        dos.writeUTF(command);

        // Reads the server's response.
        String response = dis.readUTF();

        // Closes the client connection.
        Close();

        // Returns the server's response.
        return response;
    }


    // This method closes the input stream,
    // output stream, and socket connection.
    public void Close()
    {
        try
        {
            // Checks whether the input stream exists
            // before trying to close it.
            if(dis != null)
            {
                // Closes the input stream.
                dis.close();
            }

            // Checks whether the output stream exists
            // before trying to close it.
            if(dos != null)
            {
                // Closes the output stream.
                dos.close();
            }

            // Checks whether the socket exists
            // before trying to close it.
            if(socket != null)
            {
                // Closes the socket connection.
                socket.close();
            }
        }
        catch(Exception e)
        {
            // Displays an error message if an exception
            // occurs while closing the resources.
            System.out.println(
                "Exception occured while closing : " + e
            );
        }
    }
}