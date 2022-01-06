package app;

import java.util.List;
import java.util.Scanner;
import dao.DishesDao;
import dao.RestaurantsDao;
import dao.UsersDao;
import entity.Dishes;
import entity.Restaurants;
import entity.Users;

public class App {
  public static void main(String[] args) {
    new App().runMenu();
  }

  private Scanner scan = new Scanner(System.in);
  boolean end = false;

  private void runMenu() {
    while (!end) {
      printMenu();

      try {
        int selection = readIntInput("Input a selection");

        switch (selection) {
          case 1 -> readUsers();
          case 2 -> createUsers();
          case 3 -> updateUsers();
          case 4 -> deleteUsers();
          case 5 -> readResta();
          case 6 -> createResta();
          case 7 -> updateResta();
          case 8 -> deleteResta();
          case 9 -> readDishes();
          case 10 -> createDishes();
          case 11 -> updateDishes();
          case 12 -> deleteDishes();
          case 99 -> goodbye(); 
          default -> inputInvalid();
        }
      } catch (Exception e) {
        System.out.println("Caught exception" + e.toString());
        end = true;
      }
    }
  }
  
  private void goodbye() {
    System.out.println("Goodbye!");
    end = true;
    scan.close();
  }
  
  private void inputInvalid() {
    System.out.println("Input is not valid.");
    System.out.println("***");
  }

  private String readStringInput(String string) {
    System.out.println();
    System.out.println(string + ": ");
    return scan.nextLine();
  }

  private int readIntInput(String string) {
    try {
      System.out.println();
      System.out.println(string + ": ");
      return Integer.parseInt(scan.nextLine());
    } catch (Exception e) {
      System.out.println("An error occured: " + e.toString());
      return 99;
    }
  }

  private double readDoubleInput(String string) {
    try {
      System.out.println();
      System.out.println(string + ": ");
      return Double.parseDouble(scan.nextLine());
    } catch (Exception e) {
      System.out.println("An error occured: " + e.toString());
      return 99;
    }
  }

  private void deleteDishes() {
    System.out.println("****");
    System.out.println("You selected delete dishes");
    int dishId = readIntInput("Enter dish ID: ");

    DishesDao.deleteDish(dishId);
    System.out.println();
  }

  private void updateDishes() {
    System.out.println("***");
    System.out.println("You selected update dishes");
    int dishId = readIntInput("Enter Dish ID: ");
    String dishName = readStringInput("Enter name: ");
    String orderDate = readStringInput("Enter Date Ordered: ");
    String dishComment = readStringInput("Enter a Comment: ");
    double dishPrice = readDoubleInput("Enter the Dish Price: ");
    int dishScore = readIntInput("Enter a Dish Score");

    DishesDao.updateDish(dishId, dishName, orderDate, dishComment, dishPrice, dishScore);
    System.out.println("Dish has been updated");
  }


  private void createDishes() {
    System.out.println("***");
    System.out.println("You selected add a dish");
    String dishName = readStringInput("Enter name: ");
    String orderDate = readStringInput("Enter Date Ordered: ");
    String dishComment = readStringInput("Enter a Comment: ");
    double dishPrice = readDoubleInput("Enter the Dish Price: ");
    int dishScore = readIntInput("Enter a Dish Score (0-9)");

    DishesDao.createDish(dishName, orderDate, dishComment, dishPrice, dishScore);
    System.out.println("Dish has been added");

  }

  private void readDishes() {
    System.out.println("You selected list Dishes...");
    List<Dishes> dishes = DishesDao.findDishes();

    System.out.println("***");
    System.out.println("Here are the dishes: ");

    if (dishes.isEmpty()) {
      System.out.println("There is no data!");
    } else {
      for (Dishes d : dishes) {
        System.out.println(d.dishId() + " Name: " + d.dishName() + " Price: " + d.dishPrice() + 
        " Ordered: " + d.orderDate() + " Score: " + d.dishScore() + " Comment: " + d.dishComment());
      }
    }
  }

  private void deleteResta() {
    System.out.println("****");
    System.out.println("You selected delete restaurants");
    int restaId = readIntInput("Enter Restaurant ID: ");

    RestaurantsDao.deleteResta(restaId);
    System.out.println();
  }

  private void updateResta() {
    System.out.println("***");
    System.out.println("You selected update restaurants");
    int restaId = readIntInput("Enter Restaurant ID: ");
    String restaName = readStringInput("Enter Restaurant name: ");
    String visitDate = readStringInput("Enter Date Visited: ");
    int restaScore = readIntInput("Enter a Restaurant Score (0-9)");
    String restaCity = readStringInput("Enter City: ");

    RestaurantsDao.updateResta(restaId, restaName, restaCity, visitDate, restaScore);
    System.out.println("Restaurant has been updated");
  }

  private void createResta() {
    System.out.println("***");
    System.out.println("You selected add restaurants");
    String restaName = readStringInput("Enter Restaurant name: ");
    String visitDate = readStringInput("Enter Date Visited: YYYY-MM-DD");
    int restaScore = readIntInput("Enter a Restaurant Score (0-9)");
    String restaCity = readStringInput("Enter City: ");

    RestaurantsDao.createResta(restaName, restaCity, visitDate, restaScore);
    System.out.println("Restaurant has been added");
  }

  private void readResta() {
    System.out.println("You selected List Restaurants...");
    List<Restaurants> restaurants = RestaurantsDao.findRestas();

    System.out.println("***");
    System.out.println("Here are the Restaurants: ");

    if (restaurants.isEmpty()) {
      System.out.println("There is no data!");
    } else {
      for (Restaurants r : restaurants) {
        System.out.println("ID: " + r.restaId() + ", Restaurant Name: " + r.restaName() + ", City: " + r.restaCity());
        System.out.println(", Date Visited: " + r.visitDate() + ", Score: " + r.restaScore());
      }
    }
  }

  private void deleteUsers() {
    System.out.println("****");
    System.out.println("You selected delete user");
    int userId = readIntInput("Enter User ID: ");

    UsersDao.deleteUser(userId);
    System.out.println("user has been deleted");
  }

  private void updateUsers() {
    System.out.println("***");
    System.out.println("You selected update users");
    int userId = readIntInput("Enter User ID: ");
    String firstName = readStringInput("Enter first name: ");
    String lastName = readStringInput("Enter last name: ");
    String email = readStringInput("Enter email: ");
    String userName = readStringInput("Enter user name: ");
    String userBio = readStringInput("Enter Bio: ");
    String password = readStringInput("Enter password: ");

    UsersDao.updateUser(userId, firstName, lastName, email, userName, userBio, password);
    System.out.println("user has been updated");
  }

  private void createUsers() {
    System.out.println("***");
    System.out.println("You selected add a user");
    String firstName = readStringInput("Enter first name: ");
    String lastName = readStringInput("Enter last name: ");
    String email = readStringInput("Enter email: ");
    String userName = readStringInput("Enter user name: ");
    String userBio = readStringInput("Enter Bio: ");
    String password = readStringInput("Enter password: ");

    UsersDao.createUser(firstName, lastName, email, userName, userBio, password);
    System.out.println("user has been added");
  }

  private void readUsers() {
    System.out.println("You selected List users...");
    List<Users> users = UsersDao.findUsers();

    System.out.println("***");
    System.out.println("Here are the Users: ");

    if (users.isEmpty()) {
      System.out.println("There is no data!");
    } else {
      for (Users u : users) {
        System.out.println(u.userId() + " first name: " + u.firstName() + " last name: " + u.lastName() 
        + " email: " + u.email() + " username: " + u.userName() + " bio: " + u.userBio() + " password: " + u.password());
      }
    }
  }

  private void printMenu() {
    System.out.println();
    System.out.println("**What would you like to access?**");
    System.out.println("**1: List All Users");
    System.out.println("**2: Add a User");
    System.out.println("**3: Modify User Info");
    System.out.println("**4: Delete User Info");
    System.out.println("**5: List All Restaurants");
    System.out.println("**6: Add a Restaurant");
    System.out.println("**7: Modify Restaurant Info");
    System.out.println("**8: Delete Restaurant Info");
    System.out.println("**9: List All Dishes");
    System.out.println("*10: Add a Dish");
    System.out.println("*11: Modify Dish Info");
    System.out.println("*12: Delete Dish Info");
    System.out.println("*99: Exit the application");
  }
}
