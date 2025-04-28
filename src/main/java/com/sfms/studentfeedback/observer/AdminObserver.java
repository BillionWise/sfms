package com.sfms.studentfeedback.observer;

public class AdminObserver implements Observer {

    private String adminName;

    public AdminObserver(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public void update(String message) {
        System.out.println("Admin " + adminName + " received notification: " + message);
    }
}
