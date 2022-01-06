package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
  private static final String URL = "jdbc:mysql://localhost:3306/not_yelp";
  private static final String USERNAME = "user";
  private static final String PASSWORD = "user";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }
}
