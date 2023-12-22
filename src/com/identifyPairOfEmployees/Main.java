package com.identifyPairOfEmployees;

import com.identifyPairOfEmployees.fileManager.CSVWriter;
import com.identifyPairOfEmployees.models.CSVLine;
import com.identifyPairOfEmployees.models.Employee;
import com.identifyPairOfEmployees.services.InputHandler;
import com.identifyPairOfEmployees.util.ColorfulLogging;
import com.identifyPairOfEmployees.util.DateParser;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static DateParser dateParser;
    public static ArrayList<Employee> employees;
    public static ArrayList<CSVLine> lines;
    public static boolean changesMade = false;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            startApp(sc);
            if (changesMade) {
                System.out.println(ColorfulLogging.ANSI_BLUE + "CHANGES DETECTED! Saving..." + ColorfulLogging.ANSI_RESET);
                CSVWriter.write();
                System.out.println(ColorfulLogging.ANSI_GREEN + "Changes saved successfully!" + ColorfulLogging.ANSI_RESET);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Something went wrong! Restart to check if the error still occurs!");
        }
    }

    public static void startApp(Scanner sc) {
        dateParser = InputHandler.createDateParser(sc);
        if (dateParser == null) {
            return;
        }
        InputHandler.manageMainMenu(sc);
    }
}
