import java.io.*;                 // Used for file handling and input/output operations
import java.util.*;               // Provides Scanner, ArrayList and TreeMap
import java.time.LocalDate;       // Provides LocalDate for storing and handling dates

// StudyLog class represents a single study record
class StudyLog
{
    private LocalDate Date;          // Stores the date of the study session
    private String Subject;          // Stores the name of the subject
    private double Duration;         // Stores the study duration in hours
    private String Description;      // Stores the description of the study session

    public StudyLog(LocalDate a, String b, double c, String d) // Parameterized constructor
    {
        this.Date = a;               // Initializes the study date
        this.Subject = b;            // Initializes the subject name
        this.Duration = c;           // Initializes the study duration
        this.Description = d;        // Initializes the study description
    }

    @Override                        // Overrides the toString() method of Object class
    public String toString()         // Returns the study log details as a String
    {
        return Date + " | " + Subject + " | " + Duration + " | " + Description; // Returns formatted study details
    }

    public LocalDate getDate()       // Getter method to access the study date
    {
        return this.Date;            // Returns the stored date
    }

    public String getSubject()       // Getter method to access the subject
    {
        return this.Subject;         // Returns the stored subject
    }

    public double getDuration()      // Getter method to access the study duration
    {
        return this.Duration;        // Returns the stored duration
    }

    public String getDescription()   // Getter method to access the study description
    {
        return this.Description;     // Returns the stored description
    }
}

class StudyTracker                                      // Manages study logs and performs different operations
{
    public ArrayList<StudyLog> Database;                // Stores multiple StudyLog objects using ArrayList

    public StudyTracker()                               // Constructor 
    {
        Database = new ArrayList<StudyLog>();            // Creates ArrayList for storing study logs
    }


    public void InsertLog()                             // Inserts a new study log into the database
    {
        Scanner sObj = new Scanner(System.in);           // Creates Scanner object to accept user input

        System.out.println("-----------------------------------------------------"); 
        System.out.println("Enter the details of your study");       
        System.out.println("-----------------------------------------------------"); 

        LocalDate lObj = LocalDate.now();                // Gets the current date from the system

        System.out.println("You are entering the date as : " + lObj);       // Displays the current date

        System.out.println("Enter the name of subject : ");           // Asks user to enter subject name
        String sub = sObj.nextLine();                     

        System.out.println("Enter the time period of your study :"); // Asks user to enter study duration
        double dur = sObj.nextDouble();                   

        sObj.nextLine();                                  

        System.out.println("Please provide the description of your study : "); // Asks for study description
        String desc = sObj.nextLine();                     

        StudyLog studyObj = new StudyLog(lObj, sub, dur, desc);         // Creates a new StudyLog object

        Database.add(studyObj);                            // Adds the study log object to ArrayList

        System.out.println("Study log inserted successfully..."); 
        System.out.println("-----------------------------------------------------");

    }

    public void DisplayLog()                              // Displays all stored study logs
    {
        System.out.println("-----------------------------------------------------"); 
        
        if(Database.isEmpty())                             // Checks whether the database is empty
        {
            System.out.println("Nothing to display - database is empty"); 
            System.out.println("-----------------------------------------------------"); 
            return;             // Exits the method if no logs are available
        }

        System.out.println("Log report of Marvellous Study Tracker"); 
        System.out.println("-----------------------------------------------------"); 

        for(StudyLog s : Database)                         // Iterates through all StudyLog objects
        {
            System.out.println(s);                         // Displays each study log using toString()
        }

        System.out.println("-----------------------------------------------------"); 
    }


    public void ExportToCSV()                              // Exports study logs into a CSV file
    {
        Scanner sObj = new Scanner(System.in);             

        System.out.println("Enter the name that you want to create for CSV file"); // Asks for CSV file name
        String FileName = sObj.nextLine();                

        System.out.println("-----------------------------------------------------"); 
        
        if(Database.isEmpty())                              // Checks whether there are study logs to export
        {
            System.out.println("Nothing to export - database is empty"); 
            System.out.println("-----------------------------------------------------"); 
            return;                 // Exits the method if database is empty
        }

        try(FileWriter fwObj = new FileWriter(FileName))   // Creates FileWriter using try-with-resources
        {
            fwObj.write("Date,Subject,Duration of study,Description of study\n"); 

            for(StudyLog s : Database)                      // Iterates through all study logs
            {
                fwObj.write(s.getDate() + "," +            // Writes study date
                            s.getSubject() + "," +          // Writes subject name
                            s.getDuration() + "," +         // Writes study duration
                            s.getDescription() + "\n");     // Writes description and moves to next line
            }

            System.out.println("Data gets exported to CSV successfully"); 
            System.out.println("-----------------------------------------------------"); 
        }

        catch(IOException iObj)                              // Handles input or output related exceptions
        {
            System.out.println(iObj);                       
        }
        
        catch(Exception eObj)                                 // Handles any other unexpected exceptions
        {
            System.out.println(eObj);                       
        }
    }


    public void SummaryByDate()                              // Calculates total study duration for each date
    {
        System.out.println("-----------------------------------------------------"); 
        System.out.println("Summary by Date from study tracker");     
        System.out.println("-----------------------------------------------------"); 

        TreeMap<LocalDate, Double> tObj =  new TreeMap<LocalDate, Double>();                 // Creates TreeMap with date as key and stores total study duration as value

        LocalDate lObj = null;                      // Stores the date of the current study log
        double d = 0.0;                            // Stores the current study duration
        double old = 0.0;                           // Stores the previously calculated duration

        for(StudyLog s : Database)                           // Iterates through all study logs
        {
            lObj = s.getDate();                              // Gets the date from the current study log
            d = s.getDuration();                             // Gets the duration from the current study log

            if(tObj.containsKey(lObj))                      // if date is already exists
            {
                old = tObj.get(lObj);                        // Gets the previously stored duration
                tObj.put(lObj, d + old);                     // Adds current duration to previous duration
            }
            else
            {
                tObj.put(lObj, d);                           // Adds new date with its study duration
            }
        }

        // Displays total study duration for each date
        for(LocalDate l : tObj.keySet())                     // Iterates through all dates in sorted order
        {
            System.out.println("Date : " + l + " Total study duration is : " + tObj.get(l));             // Displays the date and total duration
                                
        }

        System.out.println("-----------------------------------------------------"); 

    }


    public void SummaryBySubject()                            // Calculates total study duration for each subject
    {
        System.out.println("-----------------------------------------------------"); 
        System.out.println("Summary by Subject from study tracker"); 
        System.out.println("-----------------------------------------------------"); 

        TreeMap<String, Double> tObj = new TreeMap<String, Double>();                   // Creates TreeMap with subject as key and Stores total study duration as value
                    

        String sObj = null;                                  // Stores the subject of the current study log
        double d = 0.0;                                      // Stores the current study duration
        double old = 0.0;                                    // Stores the previously calculated duration

        for(StudyLog s : Database)                           // Iterates through all study logs
        {
            sObj = s.getSubject();                            // Gets the subject from the current study log
            d = s.getDuration();                             // Gets the duration from the current study log

            if(tObj.containsKey(sObj))                       // Checks whether the subject already exists
            {
                old = tObj.get(sObj);                         // Gets the previously stored duration
                tObj.put(sObj, d + old);                      // Adds current duration to previous duration
            }
            else
            {
                tObj.put(sObj, d);                            // Adds new subject with its study duration
            }
        }

        // Displays total study duration for each subject
        for(String str : tObj.keySet())                       // Iterates through all subjects in sorted order
        {
            System.out.println("Subject : " + str + " Total study duration is : " + tObj.get(str));       // Displays the subject name and total duration                      
        }

        System.out.println("-----------------------------------------------------");  
    }
}

// Shell to interact with the end-user
class Study_Tracker
{
    public static void main(String A[])                    
    {
        int iChoice = 0;                                   // Stores the menu option selected by the user

        StudyTracker stObj = new StudyTracker();            // Object of StudyTracker class

        Scanner sObj = new Scanner(System.in);              // Creates Scanner object to accept user input

        System.out.println("-----------------------------------------------------"); 
        System.out.println("--- Welcome to Marvellous Study Tracker ---");    
        System.out.println("-----------------------------------------------------"); 


        do                                                  // Starts the menu-driven loop
        {
            System.out.println("-----------------------------------------------------"); 
            System.out.println("Please select appropriate option : ");       
            System.out.println("-----------------------------------------------------"); 

            System.out.println("1 : Insert new study log");                  
            System.out.println("2 : View all study logs");                   
            System.out.println("3 : Export study logs to CSV");             
            System.out.println("4 : Summary of study log by date");          
            System.out.println("5 : Summary of study log by subject");       
            System.out.println("6 : Exit the application");              

            System.out.println("-----------------------------------------------------");


            iChoice = sObj.nextInt();                        // Reads the menu choice entered by the user


            switch(iChoice)                                  // Executes the operation according to user's choice
            {
                case 1:                                    
                    stObj.InsertLog();                       // Calls InsertLog() to add a new study log
                    break;                                  


                case 2:                                      
                    stObj.DisplayLog();                      // Calls DisplayLog() to display all study logs
                    break;                                  


                case 3:                                      
                    stObj.ExportToCSV();                     // Calls ExportToCSV() to create a CSV file
                    break;                              


                case 4:                                      
                    stObj.SummaryByDate();                   // Calls SummaryByDate() for date-wise summary
                    break;                                  


                case 5:                                      
                    stObj.SummaryBySubject();                // Calls SummaryBySubject() for subject-wise summary
                    break;                      


                case 6:                                     
                    break;                                   // Exits the switch statement


                default:                                     // Handles invalid menu choices
                    System.out.println("Please enter valid option"); 
                    break;                                   
            }


        } while(iChoice != 6);                    // Continues the loop until user selects option 6


        System.out.println("-----------------------------------------------------");
        System.out.println("--- Thank You for using Marvellous Study Tracker ---"); 
        System.out.println("-----------------------------------------------------"); 


    } // End of main method
} // End of program868 class