package entity;

public record Dishes(
    int dishId, 
    String dishName, 
    String orderDate, 
    String dishComment, 
    double dishPrice, 
    int dishScore
) {}
