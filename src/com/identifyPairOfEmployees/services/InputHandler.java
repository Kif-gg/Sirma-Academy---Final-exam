package com.identifyPairOfEmployees.services;

import com.identifyPairOfEmployees.Main;
import com.identifyPairOfEmployees.consoleUI.ConsoleMenus;
import com.identifyPairOfEmployees.fileManager.CSVReader;
import com.identifyPairOfEmployees.util.DateParser;

import java.util.Scanner;

public class InputHandler {
    public static DateParser createDateParser() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(ConsoleMenus.welcome);
            String command = sc.nextLine();
            while (!command.equals("0")) {
                switch (command) {
                    case "1":
                        String dayFormat = null;
                        String monthFormat = null;
                        String yearFormat = null;
                        String separator = null;
                        StringBuilder sb = new StringBuilder();
                        System.out.println(ConsoleMenus.selectDayFormat);
                        boolean isCommandValid = false;
                        while (!isCommandValid) {
                            command = sc.nextLine();
                            switch (command) {
                                case "1":
                                    dayFormat = "d";
                                    isCommandValid = true;
                                    break;
                                case "2":
                                    dayFormat = "dd";
                                    isCommandValid = true;
                                    break;
                                default:
                                    System.out.println("Invalid input, please try again!");
                            }
                        }
                        System.out.println(ConsoleMenus.selectMonthFormat);
                        isCommandValid = false;
                        while (!isCommandValid) {
                            command = sc.nextLine();
                            switch (command) {
                                case "1":
                                    monthFormat = "M";
                                    isCommandValid = true;
                                    break;
                                case "2":
                                    monthFormat = "MM";
                                    isCommandValid = true;
                                    break;
                                case "3":
                                    monthFormat = "MMM";
                                    isCommandValid = true;
                                    break;
                                case "4":
                                    monthFormat = "MMMM";
                                    isCommandValid = true;
                                    break;
                                default:
                                    System.out.println("Invalid input, please try again!");
                            }
                        }
                        System.out.println(ConsoleMenus.selectYearFormat);
                        isCommandValid = false;
                        while (!isCommandValid) {
                            command = sc.nextLine();
                            switch (command) {
                                case "1":
                                    yearFormat = "yy";
                                    isCommandValid = true;
                                    break;
                                case "2":
                                    yearFormat = "yyyy";
                                    isCommandValid = true;
                                    break;
                                default:
                                    System.out.println("Invalid input, please try again!");
                            }
                        }
                        System.out.println(ConsoleMenus.selectSeparator);
                        isCommandValid = false;
                        while (!isCommandValid) {
                            command = sc.nextLine();
                            switch (command) {
                                case "1":
                                    separator = "/";
                                    isCommandValid = true;
                                    break;
                                case "2":
                                    separator = ".";
                                    isCommandValid = true;
                                    break;
                                case "3":
                                    separator = ",";
                                    isCommandValid = true;
                                    break;
                                case "4":
                                    separator = "-";
                                    isCommandValid = true;
                                    break;
                                case "5":
                                    separator = " ";
                                    isCommandValid = true;
                                    break;
                                default:
                                    System.out.println("Invalid input, please try again!");
                            }
                        }
                        System.out.println(ConsoleMenus.selectOrder);
                        isCommandValid = false;
                        while (!isCommandValid) {
                            command = sc.nextLine();
                            switch (command) {
                                case "1":
                                    sb
                                            .append(dayFormat)
                                            .append(separator)
                                            .append(monthFormat)
                                            .append(separator)
                                            .append(yearFormat);
                                    isCommandValid = true;
                                    break;
                                case "2":
                                    sb
                                            .append(monthFormat)
                                            .append(separator)
                                            .append(dayFormat)
                                            .append(separator)
                                            .append(yearFormat);
                                    isCommandValid = true;
                                    break;
                                case "3":
                                    sb
                                            .append(yearFormat)
                                            .append(separator)
                                            .append(monthFormat)
                                            .append(separator)
                                            .append(dayFormat);
                                    isCommandValid = true;
                                    break;
                                case "4":
                                    sb
                                            .append(yearFormat)
                                            .append(separator)
                                            .append(dayFormat)
                                            .append(separator)
                                            .append(monthFormat);
                                    isCommandValid = true;
                                    break;
                                case "5":
                                    sb
                                            .append(dayFormat)
                                            .append(separator)
                                            .append(yearFormat)
                                            .append(separator)
                                            .append(monthFormat);
                                    isCommandValid = true;
                                    break;
                                case "6":
                                    sb
                                            .append(monthFormat)
                                            .append(separator)
                                            .append(yearFormat)
                                            .append(separator)
                                            .append(dayFormat);
                                    isCommandValid = true;
                                    break;
                                default:
                                    System.out.println("Invalid input, please try again!");
                            }
                        }
                        return new DateParser(sb.toString());
                    case "2":
                        return new DateParser("yyyy-MM-dd");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void printResult() {
        Main.employees = CSVReader.read();
        PairGetter pg = new PairGetter();
        pg.findLongestWorkingPair();
        System.out.println(ConsoleMenus.printEmployeesAndProjects(pg));
    }
}
