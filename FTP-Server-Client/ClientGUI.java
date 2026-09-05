// Imports Swing classes used to create the graphical user interface.
import javax.swing.*;

// Imports AWT classes used for layouts, fonts and GUI components.
import java.awt.*;

// Imports file handling classes.
import java.io.*;


// ClientGUI inherits from JFrame.
// JFrame provides the main window for the GUI application.
public class ClientGUI extends JFrame
{
    // Stores the FTPClient object used to communicate with the FTP server.
    FTPClient ftpClient;

    // Text field used to enter the server address.
    JTextField txtServer;

    // Text field used to enter the server port number.
    JTextField txtPort;

    // Text field used to enter the username.
    JTextField txtUsername;

    // Password field used to enter the password.
    JPasswordField txtPassword;

    // Text field used to enter or display a file path.
    JTextField txtFile;

    // Text field used to enter the old filename during rename.
    JTextField txtOldFile;

    // Text field used to enter the new filename during rename.
    JTextField txtNewFile;

    // Text area used to display messages and server responses.
    JTextArea txtOutput;


    // Constructor of the ClientGUI class.
    public ClientGUI()
    {
        // Sets the title displayed on the GUI window.
        setTitle("Marvellous FTP Client");

        // Sets the width and height of the GUI window.
        setSize(800,650);


        // Closes the application when the window close button is clicked.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Places the window at the center of the screen.
        setLocationRelativeTo(null);

        // Calls the method responsible for creating
        // all GUI components.
        CreateGUI();
    }


    // This method creates and arranges all components of the FTP client GUI.
    public void CreateGUI()
    {
        // Creates the main panel that contains all other panels.
        JPanel mainPanel = new JPanel();

        // Sets BorderLayout for the main panel.
        // 10 represents horizontal and vertical spacing.
        mainPanel.setLayout(
            new BorderLayout(
                10,
                10
            )
        );


        // Creates empty space around the main panel so components do not touch the window edges.
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                15,
                15,
                15,
                15
            )
        );


        // Creates a panel for server connection details.
        JPanel connectionPanel = new JPanel();


        // Uses GridLayout with 3 rows and 4 columns.
        // 10 represents horizontal and vertical gaps.
        connectionPanel.setLayout(
            new GridLayout(
                3,
                4,
                10,
                10
            )
        );


        // Adds a titled border around the connection panel.
        connectionPanel.setBorder(
            BorderFactory.createTitledBorder(
                "Server Connection"
            )
        );


        // Creates a label for the server address.
        JLabel lblServer =
            new JLabel("Server : ");


        // Creates a text field with localhost as the default server address.
        txtServer = new JTextField("127.0.0.1");


        // Creates a label for the server port.
        JLabel lblPort = new JLabel("Port : ");


        // Creates a text field with 9000 as the default port number.
        txtPort = new JTextField("9000");


        // Creates a label for the username.
        JLabel lblUsername = new JLabel("Username : ");


        // Creates an empty text field for username input.
        txtUsername = new JTextField();


        // Creates a label for the password.
        JLabel lblPassword = new JLabel("Password : ");


        // Creates a password field.
        // Characters entered into this field are hidden.
        txtPassword = new JPasswordField();


        // Creates the Connect button.
        JButton btnConnect = new JButton("Connect");


        // Adds the server label to the connection panel.
        connectionPanel.add(lblServer);

        // Adds the server text field to the connection panel.
        connectionPanel.add(txtServer);


        // Adds the port label to the connection panel.
        connectionPanel.add(lblPort);

        // Adds the port text field to the connection panel.
        connectionPanel.add(txtPort);


        // Adds the username label to the connection panel.
        connectionPanel.add(lblUsername);

        // Adds the username field to the connection panel.
        connectionPanel.add(txtUsername);


        // Adds the password label to the connection panel.
        connectionPanel.add(lblPassword);

        // Adds the password field to the connection panel.
        connectionPanel.add(txtPassword);


        // Adds an empty label to occupy one grid position.
        connectionPanel.add(
            new JLabel("")
        );


        // Adds the Connect button to the connection panel.
        connectionPanel.add(btnConnect);


        // Creates the panel used for file selection.
        JPanel filePanel = new JPanel();


        // Uses BorderLayout for the file panel.
        filePanel.setLayout(
            new BorderLayout(
                10,
                10
            )
        );


        // Adds a titled border named "File".
        filePanel.setBorder(
            BorderFactory.createTitledBorder(
                "File"
            )
        );


        // Creates the file path text field.
        txtFile = new JTextField();


        // Creates the Browse button.
        JButton btnBrowse = new JButton("Browse");


        // Adds the File label to the left side.
        filePanel.add(
            new JLabel("File : "),
            BorderLayout.WEST
        );


        // Adds the file text field to the center.
        filePanel.add(
            txtFile,
            BorderLayout.CENTER
        );


        // Adds the Browse button to the right side.
        filePanel.add(
            btnBrowse,
            BorderLayout.EAST
        );


        // Creates the panel that contains FTP command buttons.
        JPanel commandPanel = new JPanel();


        // Uses GridLayout with 2 rows and 5 columns.
        commandPanel.setLayout(
            new GridLayout(
                2,
                5,
                10,
                10
            )
        );


        // Adds a titled border around the command panel.
        commandPanel.setBorder(
            BorderFactory.createTitledBorder(
                "FTP Commands"
            )
        );


        // Creates the LIST button.
        JButton btnList = new JButton("LIST");


        // Creates the EXIST button.
        JButton btnExist = new JButton("EXIST");


        // Creates the INFO button.
        JButton btnInfo = new JButton("INFO");


        // Creates the SIZE button.
        JButton btnSize = new JButton("SIZE");


        // Creates the GET button.
        JButton btnGet = new JButton("GET");


        // Creates the PUT button.
        JButton btnPut = new JButton("PUT");


        // Creates the DELETE button.
        JButton btnDelete = new JButton("DELETE");


        // Creates the RENAME button.
        JButton btnRename = new JButton("RENAME");


        // Creates the QUIT button.
        JButton btnQuit = new JButton("QUIT");


        // Adds the LIST button to the command panel.
        commandPanel.add(btnList);

        // Adds the EXIST button to the command panel.
        commandPanel.add(btnExist);

        // Adds the INFO button to the command panel.
        commandPanel.add(btnInfo);

        // Adds the SIZE button to the command panel.
        commandPanel.add(btnSize);

        // Adds the GET button to the command panel.
        commandPanel.add(btnGet);


        // Adds the PUT button to the command panel.
        commandPanel.add(btnPut);

        // Adds the DELETE button to the command panel.
        commandPanel.add(btnDelete);

        // Adds the RENAME button to the command panel.
        commandPanel.add(btnRename);

        // Adds the QUIT button to the command panel.
        commandPanel.add(btnQuit);


        // Creates the panel used for renaming files.
        JPanel renamePanel = new JPanel();


        // Uses GridLayout with 1 row and 4 columns.
        renamePanel.setLayout(
            new GridLayout(
                1,
                4,
                10,
                10
            )
        );


        // Adds a titled border named "Rename File".
        renamePanel.setBorder(
            BorderFactory.createTitledBorder(
                "Rename File"
            )
        );


        // Creates a text field for the old filename.
        txtOldFile = new JTextField();


        // Creates a text field for the new filename.
        txtNewFile = new JTextField();


        // Adds the Old File label.
        renamePanel.add(
            new JLabel("Old File : ")
        );


        // Adds the old filename text field.
        renamePanel.add(
            txtOldFile
        );


        // Adds the New File label.
        renamePanel.add(
            new JLabel("New File : ")
        );


        // Adds the new filename text field.
        renamePanel.add(
            txtNewFile
        );


        // Creates the panel used to display server responses.
        JPanel outputPanel = new JPanel();


        // Uses BorderLayout for the output panel.
        outputPanel.setLayout(
            new BorderLayout()
        );


        // Adds a titled border named "Server Response".
        outputPanel.setBorder(
            BorderFactory.createTitledBorder(
                "Server Response"
            )
        );


        // Creates a text area for displaying messages.
        txtOutput = new JTextArea();


        // Prevents the user from manually editing
        // the server response area.
        txtOutput.setEditable(false);


        // Sets a monospaced font for the output.
        // This makes command output easier to read.
        txtOutput.setFont(
            new Font(
                "Monospaced",
                Font.PLAIN,
                14
            )
        );


        // Adds scrolling support to the output text area.
        JScrollPane scrollPane = new JScrollPane(txtOutput);


        // Adds the scroll pane to the center
        // of the output panel.
        outputPanel.add(
            scrollPane,
            BorderLayout.CENTER
        );


        // Creates a panel for the connection and file panels.
        JPanel topPanel = new JPanel();


        // Uses BorderLayout for the top panel.
        topPanel.setLayout(
            new BorderLayout(
                10,
                10
            )
        );


        // Places the connection panel at the top.
        topPanel.add(
            connectionPanel,
            BorderLayout.NORTH
        );


        // Places the file panel in the center.
        topPanel.add(
            filePanel,
            BorderLayout.CENTER
        );


        // Creates a panel for FTP commands and rename controls.
        JPanel middlePanel = new JPanel();


        // Uses BorderLayout for the middle panel.
        middlePanel.setLayout(
            new BorderLayout(
                10,
                10
            )
        );


        // Places the command panel at the top.
        middlePanel.add(
            commandPanel,
            BorderLayout.NORTH
        );


        // Places the rename panel at the bottom.
        middlePanel.add(
            renamePanel,
            BorderLayout.SOUTH
        );


        // Adds the top panel to the top of the main panel.
        mainPanel.add(
            topPanel,
            BorderLayout.NORTH
        );


        // Adds the middle panel to the center.
        mainPanel.add(
            middlePanel,
            BorderLayout.CENTER
        );


        // Adds the output panel to the bottom.
        mainPanel.add(
            outputPanel,
            BorderLayout.SOUTH
        );


        // Sets the preferred size of the output panel.
        outputPanel.setPreferredSize(
            new Dimension(
                750,
                250
            )
        );


        // Adds the main panel to the JFrame.
        add(mainPanel);


        // Calls Connect() when the Connect button is clicked.
        btnConnect.addActionListener(
            e -> Connect()
        );


        // Calls BrowseFile() when Browse is clicked.
        btnBrowse.addActionListener(
            e -> BrowseFile()
        );


        // Calls ListFiles() when LIST is clicked.
        btnList.addActionListener(
            e -> ListFiles()
        );


        // Calls CheckExist() when EXIST is clicked.
        btnExist.addActionListener(
            e -> CheckExist()
        );


        // Calls GetInfo() when INFO is clicked.
        btnInfo.addActionListener(
            e -> GetInfo()
        );


        // Calls GetSize() when SIZE is clicked.
        btnSize.addActionListener(
            e -> GetSize()
        );


        // Calls DownloadFile() when GET is clicked.
        btnGet.addActionListener(
            e -> DownloadFile()
        );


        // Calls UploadFile() when PUT is clicked.
        btnPut.addActionListener(
            e -> UploadFile()
        );


        // Calls DeleteFile() when DELETE is clicked.
        btnDelete.addActionListener(
            e -> DeleteFile()
        );


        // Calls RenameFile() when RENAME is clicked.
        btnRename.addActionListener(
            e -> RenameFile()
        );


        // Calls Quit() when QUIT is clicked.
        btnQuit.addActionListener(
            e -> Quit()
        );
    }


    // This method connects the GUI client to the FTP server.
    public void Connect()
    {
        try
        {
            // Gets the server address entered by the user.
            // trim() removes unnecessary spaces.
            String Server = txtServer.getText().trim();


            // Gets the port number from the text field.
            // Integer.parseInt() converts String into int.
            int Port =
                Integer.parseInt(
                    txtPort.getText().trim()
                );


            // Creates an FTPClient object.
            ftpClient = new FTPClient();


            // Connects the FTP client to the specified server.
            ftpClient.Connect(
                Server,
                Port
            );


            // Displays a successful connection message.
            ShowMessage(
                "Connection with server is successful..."
            );


            // Displays the server connection message.
            ShowMessage(
                "Connected to Marvellous Server"
            );
        }
        catch(Exception e)
        {
            // Displays the exception message if connection fails.
            ShowMessage(
                "Exception occured : " + e
            );
        }
    }


    // This method opens a file chooser
    // so the user can select a file.
    public void BrowseFile()
    {
        // Creates a file selection dialog.
        JFileChooser chooser = new JFileChooser();


        // Opens the file chooser window.
        int result = chooser.showOpenDialog(this);


        // Checks whether the user selected a file
        // and clicked the Approve button.
        if(result == JFileChooser.APPROVE_OPTION)
        {
            // Gets the selected file.
            File file = chooser.getSelectedFile();


            // Displays the complete path of the selected file
            // in the file text field.
            txtFile.setText(
                file.getAbsolutePath()
            );
        }
    }


    // This method requests the list of files
    // available on the server.
    public void ListFiles()
    {
        // Checks whether the client is connected.
        if(CheckConnection() == false)
        {
            // Stops execution if there is no connection.
            return;
        }


        try
        {
            // Requests the file list from the FTP server.
            String response = ftpClient.ListFiles();


            // Displays the list received from the server.
            ShowMessage(
                "Files present on server :\n" +
                response
            );
        }
        catch(Exception e)
        {
            // Displays an error if the operation fails.
            ShowMessage(
                "Exception occured : " + e
            );
        }
    }


    // This method checks whether a file exists
    // on the server.
    public void CheckExist()
    {
        // Checks whether the client is connected.
        if(CheckConnection() == false)
        {
            return;
        }


        // Gets the filename entered by the user.
        String FileName = txtFile.getText().trim();


        // Checks whether the filename field is empty.
        if(FileName.length() == 0)
        {
            // Asks the user to enter a filename.
            ShowMessage("Enter file name");

            return;
        }


        try
        {
            // Sends the filename to FTPClient
            // to check whether it exists on the server.
            String response = ftpClient.CheckExist(FileName);


            // Displays the server response.
            ShowMessage(response);
        }
        catch(Exception e)
        {
            // Displays an error message.
            ShowMessage("Exception occured : " + e);
        }
    }


    // This method gets information about a file
    // from the server.
    public void GetInfo()
    {
        // Checks whether the client is connected.
        if(CheckConnection() == false)
        {
            return;
        }


        // Gets the filename from the text field.
        String FileName = txtFile.getText().trim();


        // Checks whether the filename is empty.
        if(FileName.length() == 0)
        {
            ShowMessage("Enter file name");

            return;
        }


        try
        {
            // Requests file information from the FTP server.
            String response = ftpClient.GetInfo(FileName);


            // Displays the received file information.
            ShowMessage("File information :\n" + response);
        }
        catch(Exception e)
        {
            // Displays an error message.
            ShowMessage("Exception occured : " + e);
        }
    }


    // This method gets the size of a file
    // from the server.
    public void GetSize()
    {
        // Checks whether the client is connected.
        if(CheckConnection() == false)
        {
            return;
        }


        // Gets the filename from the text field.
        String FileName = txtFile.getText().trim();


        // Checks whether the filename is empty.
        if(FileName.length() == 0)
        {
            ShowMessage("Enter file name");

            return;
        }


        try
        {
            // Requests the file size from the server.
            String response = ftpClient.GetSize(FileName);


            // Displays the server response.
            ShowMessage(response);
        }
        catch(Exception e)
        {
            // Displays an error message.
            ShowMessage("Exception occured : " + e);
        }
    }


    // This method downloads a file from the server.
    public void DownloadFile()
    {
        // Checks whether the client is connected.
        if(CheckConnection() == false)
        {
            return;
        }


        // Gets the filename entered by the user.
        String FileName = txtFile.getText().trim();


        // Checks whether a filename was entered.
        if(FileName.length() == 0)
        {
            ShowMessage("Enter file name");

            return;
        }


        // Creates a file chooser for selecting
        // where the downloaded file should be saved.
        JFileChooser chooser = new JFileChooser();


        // Sets a default filename for the download.
        chooser.setSelectedFile(
            new File(
                "Download_" + FileName
            )
        );


        // Opens the save dialog.
        int result = chooser.showSaveDialog(this);


        // Stops the operation if the user cancels the dialog.
        if(result != JFileChooser.APPROVE_OPTION)
        {
            return;
        }


        // Gets the location selected by the user.
        File file = chooser.getSelectedFile();


        try
        {
            // Requests FTPClient to download the file.
            String response =
                ftpClient.DownloadFile(
                    FileName,
                    file.getAbsolutePath()
                );


            // Displays the download result.
            ShowMessage(response);
        }
        catch(Exception e)
        {
            // Displays an error message.
            ShowMessage("Exception occured : " + e);
        }
    }


    // This method uploads a file from the client
    // to the server.
    public void UploadFile()
    {
        // Checks whether the client is connected.
        if(CheckConnection() == false)
        {
            return;
        }


        // Gets the selected file path.
        String FileName = txtFile.getText().trim();


        // Checks whether a file has been selected.
        if(FileName.length() == 0)
        {
            ShowMessage("Select file first");

            return;
        }


        // Creates a File object using the selected path.
        File file = new File(FileName);


        // Checks whether the file exists
        // and is a regular file.
        if(file.exists() == false ||
           file.isFile() == false)
        {
            ShowMessage("File does not exist");

            return;
        }


        try
        {
            // Sends the file path to FTPClient
            // for uploading to the server.
            String response =
                ftpClient.UploadFile(
                    file.getAbsolutePath()
                );


            // Displays the upload result.
            ShowMessage(response);
        }
        catch(Exception e)
        {
            // Displays an error message.
            ShowMessage("Exception occured : " + e);
        }
    }


    // This method deletes a file from the server.
    public void DeleteFile()
    {
        // Checks whether the client is connected.
        if(CheckConnection() == false)
        {
            return;
        }


        // Gets the filename entered by the user.
        String FileName = txtFile.getText().trim();


        // Checks whether the filename is empty.
        if(FileName.length() == 0)
        {
            ShowMessage("Enter file name");

            return;
        }


        // Displays a confirmation dialog before deleting the file.
        int choice =
            JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete " +
                FileName + "?",
                "Delete File",
                JOptionPane.YES_NO_OPTION
            );


        // Stops the operation if the user does not confirm.
        if(choice != JOptionPane.YES_OPTION)
        {
            return;
        }


        try
        {
            // Sends the DELETE request to the FTP server.
            String response = ftpClient.DeleteFile(FileName);


            // Displays the server response.
            ShowMessage(response);
        }
        catch(Exception e)
        {
            // Displays an error message.
            ShowMessage("Exception occured : " + e);
        }
    }


    // This method renames a file on the server.
    public void RenameFile()
    {
        // Checks whether the client is connected.
        if(CheckConnection() == false)
        {
            return;
        }


        // Gets the old filename entered by the user.
        String OldFileName = txtOldFile.getText().trim();


        // Gets the new filename entered by the user.
        String NewFileName = txtNewFile.getText().trim();


        // Checks whether either filename is empty.
        if(OldFileName.length() == 0 ||
           NewFileName.length() == 0)
        {
            ShowMessage("Enter old and new file name");

            return;
        }


        try
        {
            // Sends the old and new filenames
            // to FTPClient for renaming.
            String response =
                ftpClient.RenameFile(
                    OldFileName,
                    NewFileName
                );


            // Displays the server response.
            ShowMessage(response);
        }
        catch(Exception e)
        {
            // Displays an error message.
            ShowMessage(
                "Exception occured : " + e
            );
        }
    }


    // This method terminates the FTP connection
    // and closes the GUI.
    public void Quit()
    {
        // Checks whether an FTPClient object was created.
        if(ftpClient == null)
        {
            // Closes the GUI window.
            dispose();

            return;
        }


        try
        {
            // Sends the QUIT command to the FTP server
            // and closes the FTP connection.
            String response =
                ftpClient.Quit();


            // Displays the server response.
            ShowMessage(response);
        }
        catch(Exception e)
        {
            // Displays an error message.
            ShowMessage(
                "Exception occured : " + e
            );
        }


        // Closes the GUI window.
        dispose();
    }


    // This method checks whether the FTP client
    // is currently connected to the server.
    public boolean CheckConnection()
    {
        // Checks whether the FTPClient object does not exist
        // or whether its socket is not connected.
        if(ftpClient == null ||
           ftpClient.IsConnected() == false)
        {
            // Informs the user that connection is required.
            ShowMessage(
                "Please connect to server first"
            );


            // Returns false because there is no connection.
            return false;
        }


        // Returns true when the client is connected.
        return true;
    }


    // This method displays a message in the output area.
    public void ShowMessage(String Message)
    {
        // Adds the message to the output text area.
        // "\n\n" adds two new lines after each message.
        txtOutput.append(
            Message +
            "\n\n"
        );
    }


    // Main method is the starting point of the GUI application.
    public static void main(String A[])
    {
        // Executes the GUI creation on Swing's Event Dispatch Thread.
        // This is the recommended way to create Swing components.
        SwingUtilities.invokeLater(
            () ->
            {
                // Creates an object of the ClientGUI class.
                ClientGUI gui =
                    new ClientGUI();


                // Makes the GUI window visible.
                gui.setVisible(true);
            }
        );
    }
}
