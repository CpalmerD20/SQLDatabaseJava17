package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import entity.Dishes;


public class DishesDao {

  public static List<Dishes> findDishes() {
    try (Connection connection = DBconnection.getConnection();) {
      String sql = "SELECT * FROM dish ORDER BY dish_id";

      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet rs = statement.executeQuery()) {
          List<Dishes> dishes = new LinkedList<>();

          while (rs.next()) {
            int dishId = rs.getInt("dish_id");
            String dishName = rs.getString("dish_name");
            String orderDate = rs.getString("order_date");
            String dishComment = rs.getString("dish_comment");
            double dishPrice = rs.getDouble("dish_price");
            int dishScore = rs.getInt("dish_score");

            Dishes dish = new Dishes(dishId, dishName, orderDate, dishComment, dishPrice, dishScore);
            dishes.add(dish);
          }
          return dishes;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void updateDish(int dishId, String dishName, String orderDate, String dishComment,
      double dishPrice, int dishScore) {
    try (Connection connection = DBconnection.getConnection()) {
      String sql =
          "UPDATE dish SET dish_name = ?, order_date = ?, dish_comment = ?, dish_price = ?, dish_score = ? WHERE dish_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, dishName);
        statement.setString(2, orderDate);
        statement.setString(3, dishComment);
        statement.setDouble(4, dishPrice);
        statement.setInt(5, dishScore);
        statement.setInt(6, dishId);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void createDish(String dishName, String orderDate, String dishComment,
      double dishPrice, int dishScore) {
    String sql =
        "INSERT INTO dish (dish_name, order_date, dish_comment, dish_price, dish_score) VALUES (?, ?, ?, ?, ?)";
    try (Connection connection = DBconnection.getConnection()) {
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, dishName);
        statement.setString(2, orderDate);
        statement.setString(3, dishComment);
        statement.setDouble(4, dishPrice);
        statement.setInt(5, dishScore);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void deleteDish(int dishId) {
    try (Connection connection = DBconnection.getConnection()) {
      String sql = "DELETE FROM dish WHERE dish_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, dishId);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
