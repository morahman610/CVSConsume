package sample;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class ConnectSQLite {

    public static Connection connection;
    public static boolean dbpopulated;

    // Creates ContactsDB and contact table
    public ResultSet createDatabase() throws ClassNotFoundException, SQLException {

        if (connection == null) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ContactsDB");
        }
        if(!dbpopulated ) {
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT name FROM sqlite_master WHERE type='table' AND name='contact'");
                if (!resultSet.next()) {
                    Statement createTableStatement = connection.createStatement();
                    createTableStatement.execute("CREATE TABLE contact(id integer," +
                            "A varchar(60), B varchar(60), C varchar(60), D varchar(60), E varchar(200)," +
                            "F varchar(60), G varchar(60), H varchar(60), I varchar(60), J varchar(60)," +
                            "primary key(id));");
                }
                parseCSV();

                dbpopulated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Statement getContactsStatement = connection.createStatement();
        ResultSet statementResult = getContactsStatement.executeQuery("SELECT * FROM contact");
        return statementResult;
    }

    //Parses ms3Interview.csv and adds to database
    public void parseCSV() throws IOException, SQLException {
        String fileName = "ms3Interview.csv";
        File ms3InterviewCSV = new File(fileName);
        FileWriter fileWriter = new FileWriter("contact-bad.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        if (ms3InterviewCSV.exists()) {
            Scanner inputStream = new Scanner(ms3InterviewCSV);
            inputStream.next();
            while (inputStream.hasNext()){
                 String data = inputStream.next();
                 String[] values = data.split(",");

                 PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO contact " +
                        "(A,B,C,D,E,F,G,H,I,J) " +
                         "values(?,?,?,?,?,?,?,?,?,?)");

                try {

                    for (int i = 0; i < values.length; i++) {

                        String value = values[i];
                        int parameterIndex = i + 1;

                        if (i == 4){
                            value = values[i] + values[i+1];
                            i++;
                            parameterIndex = i;
                        }

                        // if there is no data in the element than row is put in
                        // contact-bad.csv
                        if (value.equals("")) {
                            printWriter.println(data);
                            break;
                        }

                        prepStatement.setString(parameterIndex, value);
                        System.out.println(parameterIndex + " " + value);
                    }

                    prepStatement.execute();

                } catch (ArrayIndexOutOfBoundsException | SQLException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            inputStream.close();
        }
    }
}
