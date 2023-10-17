package org.example;

import java.util.ArrayList;

public class User {
    private String firstname;
    private String lastname;
    private ArrayList<User> userList = new ArrayList<>();

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "User: " + firstname + " " + lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void addUserToUserList(User user) {
        userList.add(user);
    }
}