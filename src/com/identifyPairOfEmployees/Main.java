package com.identifyPairOfEmployees;

import com.identifyPairOfEmployees.models.Employee;
import com.identifyPairOfEmployees.services.InputHandler;
import com.identifyPairOfEmployees.util.DateParser;

import java.util.ArrayList;


public class Main {
    public static DateParser dateParser;
    public static ArrayList<Employee> employees;

    public static void main(String[] args) {
        startApp();
        InputHandler.printResult();
    }

    public static void startApp() {
        dateParser = InputHandler.createDateParser();
    }
}
