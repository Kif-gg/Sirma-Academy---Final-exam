package com.identifyPairOfEmployees.services;

import com.identifyPairOfEmployees.Main;
import com.identifyPairOfEmployees.consoleUI.ConsoleMenus;
import com.identifyPairOfEmployees.fileManager.CSVReader;
import com.identifyPairOfEmployees.models.AssignedProject;
import com.identifyPairOfEmployees.models.CSVLine;
import com.identifyPairOfEmployees.models.Employee;
import com.identifyPairOfEmployees.util.DateParser;

import java.time.LocalDate;
import java.util.Scanner;

public class InputHandler {
    public static DateParser createDateParser(Scanner sc) {
        try {
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
                    default:
                        System.out.println("Invalid input, please try again!");
                        command = sc.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void manageMainMenu(Scanner sc) {
        try {
            Main.employees = CSVReader.read();
            System.out.println(ConsoleMenus.mainMenu);
            String command = sc.nextLine();
            while (!command.equals("0")) {
                switch (command) {
                    case "1":
                        manageAllEmployeesMenu(sc);
                        break;
                    case "2":
                        managePairMenu(sc);
                        break;
                    case "3":
                        createEmployee(sc);
                        Main.changesMade = true;
                        break;
                    default:
                        System.out.println("Invalid input, please try again!");
                        continue;
                }
                System.out.println(ConsoleMenus.mainMenu);
                command = sc.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void manageAllEmployeesMenu(Scanner sc) {
        System.out.println(ConsoleMenus.showAllEmployees(Main.employees));
        String command = sc.nextLine();
        while (!command.equals("0")) {
            if (Main.employees.isEmpty()) {
                System.out.println("Invalid input, please try again!");
                command = sc.nextLine();
                continue;
            } else {
                try {
                    int employeeIndex = Integer.parseInt(command) - 1;
                    if (employeeIndex >= 0 && employeeIndex - 1 < Main.employees.size()) {
                        manageEmployeeDetailsMenu(sc, employeeIndex);
                    } else {
                        System.out.println("Invalid input, please try again!");
                        command = sc.nextLine();
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Invalid input, please try again!");
                    command = sc.nextLine();
                    continue;
                }
            }
            System.out.println(ConsoleMenus.showAllEmployees(Main.employees));
            command = sc.nextLine();
        }
    }

    private static void manageEmployeeDetailsMenu(Scanner sc, int employeeIndex) {
        System.out.println(ConsoleMenus.showEmployeeDetails(employeeIndex));
        String command = sc.nextLine();
        while (!command.equals("0")) {
            switch (command) {
                case "1":
                    editEmployeeMenu(sc, employeeIndex);
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Invalid input, please try again!");
                    command = sc.nextLine();
                    continue;
            }
            System.out.println(ConsoleMenus.showEmployeeDetails(employeeIndex));
            command = sc.nextLine();
        }
    }

    private static void managePairMenu(Scanner sc) {
        PairGetter pg = new PairGetter();
        pg.findLongestWorkingPair();
        System.out.println(ConsoleMenus.printEmployeesAndProjects(pg));
        String command = sc.nextLine();
        while (!command.equals("0")) {
            System.out.println("Invalid input, please try again!");
            command = sc.nextLine();
        }
    }

    private static void createEmployee(Scanner sc) {
        System.out.println("Create new employee record");
        int empId = generateUniqueEmployeeId();

        Employee addedEmployee = new Employee(empId);
        assignProjectsToEmployee(sc, addedEmployee);

        Main.employees.add(addedEmployee);
        for (AssignedProject ap : addedEmployee.getAssignedProjects()) {
            CSVLine line = new CSVLine(addedEmployee.getID(), ap.getProjectID(), ap.getStartDate(), ap.getEndDate());
            Main.lines.add(line);
        }
        System.out.println("Employee created successfully!");
    }

    private static int generateUniqueEmployeeId() {
        int empId = (int) Math.floor(Math.random() * 10000);
        while (isEmployeeIdTaken(empId)) {
            empId = (int) Math.floor(Math.random() * 10000);
        }
        return empId;
    }

    private static boolean isEmployeeIdTaken(int empId) {
        for (Employee employee : Main.employees) {
            if (employee.getID() == empId) {
                return true;
            }
        }
        return false;
    }

    private static void assignProjectsToEmployee(Scanner sc, Employee employee) {
        boolean hasFinishedAssigning = false;
        while (!hasFinishedAssigning) {
            assignProjectToEmployee(sc, employee);

            System.out.println("Will you add more assignments to this employee? (type 'Yes' or 'No')");
            String command = sc.nextLine();
            if (command.equals("No")) {
                hasFinishedAssigning = true;
            } else if (!command.equals("Yes")) {
                System.out.println("Invalid input, please try again!");
            }
        }
    }

    private static void assignProjectToEmployee(Scanner sc, Employee employee) {
        System.out.println("Assign a project to the employee (Just type the ID of the project):");
        boolean isCommandValid = false;
        LocalDate startDate;
        LocalDate endDate;
        while (!isCommandValid) {
            int projId = getProjectId(sc, employee);
            startDate = getStartDate(sc);
            endDate = getEndDate(sc);

            while (!startDate.isBefore(endDate)) {
                System.out.println("Start date must be before end date!");
                endDate = getEndDate(sc);
            }
            AssignedProject ap = new AssignedProject(projId, startDate, endDate);
            employee.addAssignedProject(ap);
            isCommandValid = true;
        }
    }

    private static int getProjectId(Scanner sc, Employee addedEmployee) {
        int projId = 0;
        boolean doesHaveAssignedPr = true;
        String command;
        while (doesHaveAssignedPr) {
            command = sc.nextLine();
            try {
                projId = Integer.parseInt(command);
                int finalProjId = projId;
                doesHaveAssignedPr = addedEmployee.getAssignedProjects()
                        .stream()
                        .anyMatch(assignedProject -> assignedProject.getProjectID() == finalProjId);
                if (doesHaveAssignedPr) {
                    System.out.println("This project is already assigned to the employee!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid project ID!");
            }
        }
        return projId;
    }

    private static LocalDate getStartDate(Scanner sc) {
        System.out.printf("REMINDER! Your preferred date format is '%s'%n", Main.dateParser.getDateFormat());
        System.out.println("Type in the starting date of the assignment:");
        String command = sc.nextLine();
        while (true) {
            try {
                if (Main.dateParser.validateFormat(command)) {
                    return LocalDate.parse(command);
                } else {
                    System.out.println("Invalid input, please try again!");
                    command = sc.nextLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid input, please try again!");
                command = sc.nextLine();
            }
        }
    }

    private static LocalDate getEndDate(Scanner sc) {
        System.out.println("Type in the ending date of the assignment or type 'NULL' if the assignment has not ended:");
        String command = sc.nextLine();
        if (command.equals("NULL")) {
            return LocalDate.now();
        } else {
            while (true) {
                try {
                    if (Main.dateParser.validateFormat(command)) {
                        return LocalDate.parse(command);
                    } else {
                        System.out.println("Invalid input, please try again!");
                        command = sc.nextLine();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Invalid input, please try again!");
                    command = sc.nextLine();
                }
            }
        }
    }

    private static void editEmployeeMenu(Scanner sc, int employeeIndex) {
        System.out.println(ConsoleMenus.editEmployeeMenu);
        Employee employee = Main.employees.get(employeeIndex);
        String command = sc.nextLine();
        while (!command.equals("0")) {
            switch (command) {
                case "1":
                    assignProjectsToEmployee(sc, employee);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid input, please try again!");
                    command = sc.nextLine();
                    continue;
            }
            System.out.println(ConsoleMenus.editEmployeeMenu);
            command = sc.nextLine();
        }
    }
}