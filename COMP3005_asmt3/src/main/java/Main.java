import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Main {

    // Class Variables
    private static String url = "jdbc:postgresql://localhost:5432/comp3005_asmt3";
    private static String user = "postgres";
    private static String password = "S1BE57";
    private static Connection connection;
    private static Statement statement;


    public static void main (String[] args){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            // Function Calls - Uncomment whichever function you want to test and modify its values accordingly.
            //getAllStudents();
            //addStudent("First_name", "Last_name", "email@example.com", "2024-01-01");
            //updateStudentEmail(21, "email2@example.com");
            deleteStudent(21);

            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // Retrieves and displays all records from the students table
    public static void getAllStudents() throws SQLException {
        System.out.println("\ngetAllStudents Function: ");

        statement.executeQuery("SELECT * FROM students");
        ResultSet resultSet = statement.getResultSet();
        System.out.println("student_id \tfirst_name \tlast_name \temail \t\t\t\t\tenrollment_date");
        // For each student record, print out the column values.
        while (resultSet.next()){
            System.out.println(resultSet.getString("student_id") + " \t\t\t"
                    + resultSet.getString("first_name")  + " \t\t"
                    + resultSet.getString("last_name")  + " \t\t"
                    + resultSet.getString("email")  + " \t"
                    + resultSet.getString("enrollment_date")
            );
        }
        resultSet.close();
    }

    // Inserts a new student record into the students table
    public static void addStudent(String first_name, String last_name,
                                  String email, String enrollment_date) throws SQLException {
        System.out.println("\naddStudent Function: ");

        String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) " +
                String.format("VALUES ('%s', '%s', '%s', '%s')", first_name, last_name, email, enrollment_date);
        int rowsInserted = statement.executeUpdate(insertSQL);

        // Print out if the operation was successful
        if (rowsInserted > 0) System.out.println("The new student was inserted successfully.");
    }

    //Updates the email address for a student with the specified student_id.
    public static void updateStudentEmail(int student_id, String new_email) throws SQLException {
        System.out.println("\nupdateStudentEmail Function: ");

        String updateSQL = String.format("UPDATE students SET email = '%s' WHERE student_id = %d", new_email, student_id);
        int updatedEmails = statement.executeUpdate(updateSQL);

        // Print out if the operation was successful
        if (updatedEmails > 0) System.out.println("The student's email was updated successfully.");
    }

    //Deletes the record of the student with the specified student_id.
    public static void deleteStudent(int student_id) throws SQLException{
        System.out.println("\ndeleteStudent Function: ");

        String updateSQL = String.format("DELETE FROM students WHERE student_id = %d", student_id);
        int studentsDeleted = statement.executeUpdate(updateSQL);

        // Print out if the operation was successful
        if (studentsDeleted > 0) System.out.println("The student was deleted successfully.");
    }

}
