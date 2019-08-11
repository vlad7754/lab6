package lab.lab7;

import java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.PreparedStatement;
import  java.sql.SQLException;
public class DatebaseHandler extends Configs{
    Connection dbConnection;


    public Connection getDbConnection()
            throws ClassNotFoundException,SQLException{

        String connectionString = "jdbc:postgresql://" + dbHost+":"
                + dbPort + "/" + dbName;

        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }


public void signUpUser(String userName, String userPassword){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_NAME + "," + Const.USER_PASSWORD + ")"+
                "VALUES(?,?)";
    try {
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, userName );
        prSt.setString(2, userPassword);
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
}
