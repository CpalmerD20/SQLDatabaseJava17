package entity;

public record Users(
    int userId, 
    String firstName, 
    String lastName, 
    String email, 
    String userName, 
    String userBio, 
    String password
) {}
