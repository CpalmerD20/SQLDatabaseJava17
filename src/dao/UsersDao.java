package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import entity.Users;

public class UsersDao {

  public static void deleteUser(int userId) {
    try (Connection connection = DBconnection.getConnection()) {
      String sql = "DELETE FROM user WHERE user_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, userId);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void updateUser(int userId, String firstName, String lastName, String email,
      String userName, String userBio, String password) {
    try (Connection connection = DBconnection.getConnection()) {
      String sql = "UPDATE user SET username = ? WHERE user_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, email);
        statement.setString(4, userName);
        statement.setString(5, userBio);
        statement.setString(6, password);
        statement.setInt(7, userId);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Users> findUsers() {
    try (Connection connection = DBconnection.getConnection();) {
      String sql = "SELECT * FROM user ORDER BY user_id";

      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet rs = statement.executeQuery()) {
          List<Users> users = new LinkedList<>();

          while (rs.next()) {
            int userId = rs.getInt("user_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String userName = rs.getString("username");
            String userBio = rs.getString("user_bio");
            String password = rs.getString("password");

            Users user = new Users(userId, firstName, lastName, email, userName, userBio, password);
            users.add(user);
          }

          return users;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void createUser(String firstName, String lastName, String email, String userName,
      String userBio, String password) {
    String sql =
        "INSERT INTO user (first_name, last_name, email, username, user_bio, password) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection connection = DBconnection.getConnection()) {
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, email);
        statement.setString(4, userName);
        statement.setString(5, userBio);
        statement.setString(6, password);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}