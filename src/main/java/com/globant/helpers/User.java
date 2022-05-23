package com.globant.helpers;

public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;

    public User(String name, String lastName, String email, String password){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static User createRandomUser(){
        String name = RandomString.getAlphaNumericString(5);
        String lastName = RandomString.getAlphaNumericString(5);
        String password = RandomString.getAlphaNumericString(10) + "!?:)";
        String email = RandomString.getAlphaNumericString(7) + "@gmail.com";
        return new User(name, lastName, email, password);
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



}
