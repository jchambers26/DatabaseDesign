import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Prog4 {
    public static void main(String[] args) {

        final String oracleURL =   // Magic lectura -> aloe access spell
        "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";


        String username = null,    // Oracle DBMS username
        password = null;    // Oracle DBMS password


        if (args.length == 2) {    // get username/password from cmd line args
            username = args[0];
            password = args[1];
        } else {
        System.out.println("\nUsage:  java JDBC <username> <password>\n"
                    + "    where <username> is your Oracle DBMS"
                    + " username,\n    and <password> is your Oracle"
                    + " password (not your system password).\n");
        System.exit(-1);
        }

         // load the (Oracle) JDBC driver by initializing its base
            // class, 'oracle.jdbc.OracleDriver'.

            try {

                Class.forName("oracle.jdbc.OracleDriver");

        } catch (ClassNotFoundException e) {

                System.err.println("*** ClassNotFoundException:  "
                    + "Error loading Oracle JDBC driver.  \n"
                    + "\tPerhaps the driver is not on the Classpath?");
                System.exit(-1);

        }


        // make and return a database connection to the user's
        // Oracle database

        Connection dbconn = null;

        try {
                dbconn = DriverManager.getConnection
                               (oracleURL,username,password);

        } catch (SQLException e) {

                System.err.println("*** SQLException:  "
                    + "Could not open JDBC connection.");
                System.err.println("\tMessage:   " + e.getMessage());
                System.err.println("\tSQLState:  " + e.getSQLState());
                System.err.println("\tErrorCode: " + e.getErrorCode());
                System.exit(-1);

        }
        Scanner scanner = new Scanner(System.in);

        while (takeInput(scanner, dbconn)) {
        }
        System.out.println("Exiting.");
    }

    public static boolean takeInput(Scanner scanner, Connection dbconn) {
        System.out.println("\nEnter an action with one of the following forms (or 'exit'):");
        System.out.println("\t{add|update|delete} {patient|staff|appointment}");
        System.out.println("\tschedule immunization");
        System.out.println("\tquery {1|2|3|4|5}");

        String input = scanner.nextLine().toUpperCase();

        if (input.equals("EXIT")) {
            return false;
        } else if (input.startsWith("S")) {
            System.out.println(
                    "Which immunization are you scheduling? ('COVID-19', 'Influenza', 'DTaP', 'Tdap', 'IPV', 'MMR')");
            String type = scanner.nextLine().toUpperCase();
            if (type.equals("COVID-19")
                    || type.equals("INFLUENZA")
                    || type.equals("DTAP")
                    || type.equals("TDAP")
                    || type.equals("IPV")
                    || type.equals("MMR")) {
                System.out.println("Dose number?");
                String dose = scanner.nextLine();
                try {
                    int doseNum = Integer.parseInt(dose);
                    if (doseNum <= 0 || doseNum > 4) {
                        System.out.println("Invalid dose number.");
                    } else {
                        scheduleImmunization(type, doseNum);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid dose number.");
                    return true;
                }
            } else {
                System.out.println("Invalid immunization name.");
            }
            return true;

        } else if (input.startsWith("QUERY")) {
            try {
                String queryInput = input.split(" ")[1];
                int queryNum = Integer.parseInt(queryInput);
                if (queryNum <= 0 || queryNum > 5) {
                    System.out.println("Invalid query number.");
                } else {
                    executeQuery(queryNum, dbconn, scanner);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid query number.");
            }
            return true;

        } else if (input.startsWith("ADD")
                || input.startsWith("UPDATE")
                || input.startsWith("DELETE")) {
            try {
                String entity = input.split(" ")[1].toUpperCase();
                if (entity.equals("PATIENT") || entity.equals("STAFF") || entity.equals("APPOINTMENT")) {
                    editEntry(input.split(" ")[0], entity);
                } else {
                    System.out.println("Cannot edit that entity.");
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid command.");
            }

        } else {
            System.out.println("Invalid command.");
        }

        return true;
    }

    // type will be one of: 'COVID-19', 'Influenza', 'DTaP', 'Tdap', 'IPV', or 'MMR'
    // doseNum will be in range [1, 4]
    public static void scheduleImmunization(String type, int doseNum) {
        System.out.println(type + ", " + doseNum);
    }

    // queryNum will be in range [1, 5]
    public static void executeQuery(int queryNum, Connection dbconn, Scanner scanner) {
        switch (queryNum) {
            case 1: queryOne(dbconn, scanner);
            break;
            case 2: queryTwo(dbconn, scanner);
            break;
            case 3: queryThree(dbconn, scanner);
            break;
            case 4: queryFour(dbconn);
            break;
            case 5: queryFive(dbconn, scanner);
            break;
        }
    }

    /*---------------------------------------------------------------------
    |  Method queryOne
    |-
    |  Purpose:  This method prints the result of query 1, which reports the following: 
    |            Print a list of patients who have their 2nd, 3rd or 4th doses of the COVID-19
    |            vaccine scheduled by a certain date (given that the date is entered by the user).
    |
    |
    |  Pre-condition:  The user has successfully connected to the database using JDBC and has entered "Query 1"
    |
    |  Post-condition: The first query has been run and the correct information has been printed
    |
    |  Parameters:
    |      Connection dbconn: The connection to the database
    |      Scanner scanner: Scanner to take user input
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    private static void queryOne(Connection dbconn, Scanner scanner){
        System.out.println("Query 1: Print a list of patients who have their 2nd, 3rd or 4th doses of the COVID-19 vaccine scheduled by a"
        + " certain date ");

        String date = getDate(scanner);


        String query = "select max(name) as Name, max(trunc(months_between(sysdate, birthday)/12)) as Age, max(doseNumber) as Dose, max(begin) as \"Date\" from " +  
        "jacobchambers.Patient join jacobchambers.Appointment on (Patient.patientID = Appointment.patientID) " +
        "join jacobchambers.Vaccine on (Vaccine.apptID = Appointment.apptID) " +
        "where Vaccine.type='COVID-19' and " +
        "begin <= TO_DATE('" + date + "', 'mm/dd/yyyy') and " +
        "doseNumber >= 2 group by Patient.patientID"; // Store the query in a string
        Statement stmt = null; // Store the statement for the query
        ResultSet answer = null; // Store the result of the query

        try {
            stmt = dbconn.createStatement();
            answer = stmt.executeQuery(query);
            if (answer != null){
                System.out.println("\n\n");
                System.out.println("Name              | Age | Dose | Appointment Time");

                while (answer.next()){
                    String doseNum = String.format("%-7.7s", answer.getInt("Dose"));
                    System.out.println(String.format("%-20.20s", answer.getString("Name")) + 
                    String.format("%-6.6s", answer.getInt("Age")) + doseNum + answer.getTimestamp("Date"));

                }
                System.out.println("\n\n");
            }

        }
        catch (SQLException e) {

            System.err.println("*** SQLException:  "
                + "Could not fetch query results.");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);

        }

    }

    /*---------------------------------------------------------------------
    |  Method queryTwo
    |-
    |  Purpose:  This method prints the result of query 2, which reports the following: 
    |            Given a certain date, output which patients had a non–walk–in appointment. 
    |            Sort in order by appointment time and group by type of service
    |
    |
    |  Pre-condition:  The user has successfully connected to the database using JDBC and has entered "Query 2"
    |
    |  Post-condition: The second query has been run and the correct information has been printed
    |
    |  Parameters:
    |      Connection dbconn: The connection to the database
    |      Scanner scanner: Scanner to take user input
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    private static void queryTwo(Connection dbconn, Scanner scanner){
        System.out.println("Query 2: Given a certain date, output which patients had a non–walk–in appointment. " + 
        "\nSort in order by appointment time and group by type of service");

        String[] date = getDate(scanner).split("-");

        String m = date[0];
        String d = date[1];
        String y = date[2];

        String query = "select Patient.name, providedServiceType, begin from " + 
        "jacobchambers.Appointment join jacobchambers.Patient on (Patient.patientID = Appointment.patientID) " + 
        "join jacobchambers.CareProvider on (CareProvider.careProviderID = Appointment.careProviderID) " + 
        "where extract(year from begin) = " + y + " and extract(month from begin) = " + m + 
        " and extract(day from begin) = " + d + " and isWalkIn = 0 order by providedServiceType, begin";


        Statement stmt = null; // Store the statement for the query
        ResultSet answer = null; // Store the result of the query

        try {
            stmt = dbconn.createStatement();
            answer = stmt.executeQuery(query);
            if (answer != null){
                System.out.println("\n\n");
                System.out.println("Name              | Service Type      | Date");

                while (answer.next()){
                    System.out.println(String.format("%-20.20s", answer.getString("name")) + 
                    String.format("%-20.20s", answer.getString("providedServiceType")) + answer.getTimestamp("begin"));

                }
                System.out.println("\n\n");
            }
        }
        catch (SQLException e) {

            System.err.println("*** SQLException:  "
                + "Could not fetch query results.");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);

        }

    }


    /*---------------------------------------------------------------------
    |  Method queryThree
    |-
    |  Purpose:  This method prints the result of query 3, which reports the following: 
    |            Print the schedule of staff given a certain date (input by the user). A schedule contains the list of staff
    |            members working that day (including those who were working that day as usual and those who were
    |            working to handle an appointment) and a staff member’s working hours (start and stop times).
    |
    |
    |  Pre-condition:  The user has successfully connected to the database using JDBC and has entered "Query 3"
    |
    |  Post-condition: The third query has been run and the correct information has been printed
    |
    |  Parameters:
    |      Connection dbconn: The connection to the database
    |      Scanner scanner: Scanner to take user input
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    private static void queryThree(Connection dbconn, Scanner scanner){
        System.out.println("Query 3: Print the schedule of staff for a given date");

        String[] date = getDate(scanner).split("-");
        String m = date[0];
        String d = date[1];
        String y = date[2];

        String query = "select name, min(begin) as startTime, max(end) as endTime from " + 
        "jacobchambers.Appointment join jacobchambers.CareProvider on (Appointment.careProviderID = CareProvider.careProviderID) " + 
        "where extract(year from begin) = " + y + " and extract(month from begin) = " + m +
        " and extract(day from begin) = " + d + " group by name";


        Statement stmt = null; // Store the statement for the query
        ResultSet answer = null; // Store the result of the query

        try {
            stmt = dbconn.createStatement();
            answer = stmt.executeQuery(query);
            if (answer != null){
                System.out.println("\n\n");
                System.out.println("Name              | Start Time            | End Time");

                while (answer.next()){
                    System.out.println(String.format("%-20.20s", answer.getString("name")) + 
                    answer.getTimestamp("startTime") + "  " + answer.getTimestamp("endTime"));

                }
                System.out.println("\n\n");
            }
        }
        catch (SQLException e) {

            System.err.println("*** SQLException:  "
                + "Could not fetch query results.");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);

        }

    }

    /*---------------------------------------------------------------------
    |  Method queryFour
    |-
    |  Purpose:  This method prints the result of query 4, which reports the following: 
    |            Print the vaccine statistics of the two categories of patients (student, employees). The statistics include
    |            the count of patients that have completed all 4 doses of a vaccine series, the count of patients that have
    |            received three doses, but not the 4th, the count of patients that have received two doses but not the 3rd,
    |            and the count of patients who have only received the first dose.
    |
    |  Pre-condition:  The user has successfully connected to the database using JDBC and has entered "Query 4"
    |
    |  Post-condition: The fourth query has been run and the correct information has been printed
    |
    |  Parameters:
    |      Connection dbconn: The connection to the database
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/ 
    private static void queryFour(Connection dbconn){
        System.out.println("Query 4: Print the vaccine statistics of the two categories of patients (student, employees)");
        for (int i = 4; i > 0; i--){

            String query = "select Patient.type, count(distinct Appointment.apptID) as dosesAdministered from " + 
            "jacobchambers.Appointment join jacobchambers.Patient on (Appointment.patientID = Patient.patientID) " + 
            "join jacobchambers.Vaccine on (Vaccine.apptID = Appointment.apptID)" + 
            "where doseNumber >= " + String.valueOf(i) + "and Vaccine.type='COVID-19' group by Patient.type";
    
    
            Statement stmt = null; // Store the statement for the query
            ResultSet answer = null; // Store the result of the query
    
            try {
                stmt = dbconn.createStatement();
                answer = stmt.executeQuery(query);
                if (answer != null){
                    System.out.println("\n\n");
                    System.out.println("Patients who have received " + String.valueOf(i) + " doses:");
                    System.out.println("Patient Type | Doses Administered | Dose Number");
    
                    while (answer.next()){
                        System.out.println(String.format("%-15.15s", answer.getString("type")) + 
                        String.format("%-21.21s", answer.getInt("dosesAdministered")) + String.valueOf(i));
    
                    }
                    System.out.println("\n\n");
                }
            }
            catch (SQLException e) {
    
                System.err.println("*** SQLException:  "
                    + "Could not fetch query results.");
                System.err.println("\tMessage:   " + e.getMessage());
                System.err.println("\tSQLState:  " + e.getSQLState());
                System.err.println("\tErrorCode: " + e.getErrorCode());
                System.exit(-1);
    
            }

        }
        
    }

    /*---------------------------------------------------------------------
    |  Method queryFive
    |-
    |  Purpose:  This method prints the result of query 5, which reports the following: 
    |            For each patient that has received a non-covid vaccine, how many have they received by a given user date?
    |
    |  Pre-condition:  The user has successfully connected to the database using JDBC and has entered "Query 5"
    |
    |  Post-condition: The fifth query has been run and the correct information has been printed
    |
    |  Parameters:
    |      Connection dbconn: The connection to the database
    |      Scanner scanner: Scanner for user inputs
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    private static void queryFive(Connection dbconn, Scanner scanner){
        System.out.println("Query 5: For each patient that has received a non-covid vaccine, output how many have they received by a given date");

        String date = getDate(scanner);


        String query = "select max(Patient.name) as Name, count(distinct Appointment.apptID) as \"Vaccines Received\" from " +  
        "jacobchambers.Patient join jacobchambers.Appointment on (Patient.patientID = Appointment.patientID) " +
        "join jacobchambers.Vaccine on (Vaccine.apptID = Appointment.apptID) " +
        "join jacobchambers.CareProvider on (CareProvider.careProviderID = Appointment.careProviderID) " + 
        "where providedServiceType = 'Immunization' and Vaccine.type<>'COVID-19' and " +
        "begin <= TO_DATE('" + date + "', 'mm/dd/yyyy') group by Patient.name"; // Store the query in a string

        Statement stmt = null; // Store the statement for the query
        ResultSet answer = null; // Store the result of the query

        try {
            stmt = dbconn.createStatement();
            answer = stmt.executeQuery(query);
            if (answer != null){
                System.out.println("\n\n");
                System.out.println("Name              | Non-COVID Vaccines Received");

                while (answer.next()){
                    System.out.println(String.format("%-20.20s", answer.getString("name")) + 
                    answer.getInt("Vaccines Received"));

                }
                System.out.println("\n\n");
            }
        }
        catch (SQLException e) {

            System.err.println("*** SQLException:  "
                + "Could not fetch query results.");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);

        }
    }

    /*---------------------------------------------------------------------
    |  Method getDate
    |
    |  Purpose:  This method prints collects user inputs for year, date, and month and returns them in an arrayList
    |
    |  Pre-condition:  Scanner has been initialized
    |
    |  Post-condition: The date components have been collected and validated, and the ArrayList returned
    |
    |  Parameters:
    |      Scanner scanner: Scanner to take user input
    |
    |  Returns:  String = 'mm-dd-yyyy'
    *-------------------------------------------------------------------*/
    private static String getDate(Scanner scanner){
        
        while (true){
            int day = 0; // The day to query
            int month = 0; // The month to query
            int year = 0; // The year to query
    
            System.out.println("Enter the year");
            while (!scanner.hasNextInt()){
                System.out.println("Enter the year");
                scanner.next();
            }
            year = scanner.nextInt();
    
    
            System.out.println("Enter the month");
            while (!scanner.hasNextInt()){
                System.out.println("Enter the month");
                scanner.next();
            }
            month = scanner.nextInt();
    
    
            System.out.println("Enter the day");
            while (!scanner.hasNextInt()){
                System.out.println("Enter the day");
                scanner.next();
            }
            day = scanner.nextInt();
    
            String y = String.valueOf(year); // Year as a string
            String m = String.valueOf(month); // Month as a string
            if (m.length() == 1){
                m = "0" + m;
            }
            String d = String.valueOf(day); // Day as a string
            if (d.length() == 1){
                d = "0" + d;
            }
            String date = m + "-" + d + "-" + y;
            
            DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            sdf.setLenient(false);
            try{
                sdf.parse(date);
                return date;
            } catch (ParseException e){
                System.out.println("Invalid Date. Try again");
                continue;
            }
        }

    }


    // action will be one of: 'add', 'update', or 'delete'
    // entity will be one of: 'patient', 'staff', or 'appointment'
    public static void editEntry(String action, String entity) {
        System.out.println(action + ", " + entity);
    }

}
