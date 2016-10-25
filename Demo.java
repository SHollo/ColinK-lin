package no.westerdals;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Colin on 27.09.2016.
 */
public class Demo {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;

        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("Demo");
        ds.setServerName("localhost");
        ds.setUser("root");
        ds.setPassword("root");

        try {
            System.out.println("Connection to a selected database");
            con = (Connection) ds.getConnection();
            System.out.println("Connected database successfully...");


            System.out.println("Deleting table in a given database");
            dropTable("EMPLOYEES", con);
            System.out.println("Table deleted in given database...");


            System.out.println("Creating table in a given database");
            stmt = con.createStatement();

            String sql = "CREATE TABLE EMPLOYEES " +
                    "(ID INTEGER not NULL, " +
                    " First VARCHAR(255), " +
                    " Last VARCHAR(255), " +
                    " Email VARCHAR(254), " +
                    " PRIMARY KEY ( ID ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in a given database..");


            System.out.println("Insert records into the table");
            insertValues("EMPLOYEES", con);
            System.out.println("Inserted records into the table");


           /* System.out.println("Print the given records");
            readValues("EMPLOYEES", con);


             System.out.println("Update records into the table");
            updateValues("EMPLOYEES", con);


            System.out.println("Delete records from the table");
            deleteValues("EMPLOYEES", con);
            System.out.println("Records successfully deleted...");


            System.out.println("List of tables in the schema");
            getTable("EMPLOYEES", con);


            System.out.println("List of Columns in the given table");
            showTable("EMPLOYEES", con); */

            copyTable("fileName.txt");


        } catch (SQLException se) {
            se.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dropTable(String table, Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        String sql = "DROP TABLE " + table;

        stmt.executeUpdate(sql);
    }

    private static void insertValues(String table, Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO EMPLOYEES " +
                "VALUES (1, 'Mohamed', 'Ali', 'mohamed@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (2, 'Frank', 'Nguyen', 'frank@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (3, 'Philip', 'Brown', 'frank@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (4, 'Jonas', 'Eilertsen', 'jonas@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (5, 'George', 'Ntenga', 'george@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (6, 'Kirsten', 'Stewart', 'kirsten@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (7, 'Abdullah', 'Boukadour', 'abdullah@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (8, 'Rachid', 'Bigom', 'rachid@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (9, 'Nahom', 'Haile', 'nahom@live.no')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEES " +
                "VALUES (10, 'Puyan', 'Singh', 'puyan@live.no')";
        stmt.executeUpdate(sql);

    }

    private static void readValues(String table, Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet myRs = stmt.executeQuery("SELECT * FROM EMPLOYEES");

        while (myRs.next()) {
            System.out.println(myRs.getString("ID") + " / " + myRs.getString("Last") + " " + myRs.getString("First"));
        }

    }

    private static void updateValues(String table, Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        String sql = "UPDATE EMPLOYEES "
                + "SET Email='jonas@hotmail.com'"
                + "WHERE ID = 4";

        stmt.executeUpdate(sql);
    }

    private static void deleteValues(String table, Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        String sql = "DELETE FROM EMPLOYEES WHERE Last='Singh'";

        int rowsAffected = stmt.executeUpdate(sql);

        System.out.println("Rows affected: " + rowsAffected);
    }

    public static void showTable(String table, Connection con) throws SQLException {
        DatabaseMetaData md = con.getMetaData();

        ResultSet myRs = md.getColumns(null, null, table, null);

        while (myRs.next()) {
            String name = myRs.getString("COLUMN_NAME") + " / ";
            String type = myRs.getString("TYPE_NAME") + " / ";
            String size = myRs.getString("COLUMN_SIZE") + " / ";

            System.out.println(name + type + size);
        }

    }

    public static void getTable(String table, Connection con) throws SQLException {
        DatabaseMetaData md = con.getMetaData();

        ResultSet myRs = md.getTables(null, null, table, null);

        while (myRs.next()) {
            System.out.println(myRs.getString("TABLE_NAME"));
        }
    }

    public static void copyTable(String fileName) throws IOException {
        fileName = "fileName.txt";
        String line = null;

        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            String temp [] = line.split("/");
                System.out.println(line);

            }
        }




    }









