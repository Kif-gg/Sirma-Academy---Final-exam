package com.identifyPairOfEmployees.services;

import com.identifyPairOfEmployees.Main;
import com.identifyPairOfEmployees.consoleUI.ConsoleMenus;
import com.identifyPairOfEmployees.fileManager.CSVReader;
import com.identifyPairOfEmployees.models.AssignedProject;
import com.identifyPairOfEmployees.models.CSVLine;
import com.identifyPairOfEmployees.models.Employee;
import com.identifyPairOfEmployees.util.ColorfulLogging;
import com.identifyPairOfEmployees.util.DateParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
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
                                    System.err.println("Invalid input, please try again!");
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
                                    System.err.println("Invalid input, please try again!");
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
                                    System.err.println("Invalid input, please try again!");
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
                                    System.err.println("Invalid input, please try again!");
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
                                    System.err.println("Invalid input, please try again!");
                            }
                        }
                        return new DateParser(sb.toString());
                    case "2":
                        return new DateParser("yyyy-MM-dd");
                    default:
                        System.err.println("Invalid input, please try again!");
                        command = sc.nextLine();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Something went wrong while creating date parser!");
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
                        break;
                    default:
                        System.err.println("Invalid input, please try again!");
                        command = sc.nextLine();
                        continue;
                }
                System.out.println(ConsoleMenus.mainMenu);
                command = sc.nextLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Something went wrong while managing employees!");
        }
    }

    private static void manageAllEmployeesMenu(Scanner sc) {
        System.out.println(ConsoleMenus.showAllEmployees(Main.employees));
        String command = sc.nextLine();
        while (!command.equals("0")) {
            if (Main.employees.isEmpty()) {
                System.err.println("Invalid input, please try again!");
                command = sc.nextLine();
                continue;
            } else {
                try {
                    int employeeIndex = Integer.parseInt(command) - 1;
                    if (employeeIndex >= 0 && employeeIndex < Main.employees.size()) {
                        manageEmployeeDetailsMenu(sc, employeeIndex);
                    } else {
                        System.err.println("Invalid input, please try again!");
                        command = sc.nextLine();
                        continue;
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    System.err.println("Invalid input, please try again!");
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
                    if (deleteEmployee(sc, employeeIndex)) {
                        return;
                    }
                    break;
                default:
                    System.err.println("Invalid input, please try again!");
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
            System.err.println("Invalid input, please try again!");
            command = sc.nextLine();
        }
    }

    private static void createEmployee(Scanner sc) {
        System.out.println(ColorfulLogging.ANSI_BLUE + "Create new employee record" + ColorfulLogging.ANSI_RESET);
        int empID = generateUniqueEmployeeID();

        Employee addedEmployee = new Employee(empID);
        assignProjectsToEmployee(sc, addedEmployee);

        Main.employees.add(addedEmployee);
        for (AssignedProject ap : addedEmployee.getAssignedProjects()) {
            CSVLine line = new CSVLine(addedEmployee.getID(), ap.getProjectID(), ap.getStartDate(), ap.getEndDate());
            Main.lines.add(line);
        }
        Main.changesMade = true;
        System.out.println(ColorfulLogging.ANSI_GREEN + "Employee created successfully!" + ColorfulLogging.ANSI_RESET);
    }

    private static int generateUniqueEmployeeID() {
        int empID = (int) Math.floor(Math.random() * 10000);
        while (isEmployeeIDTaken(empID)) {
            empID = (int) Math.floor(Math.random() * 10000);
        }
        return empID;
    }

    private static boolean isEmployeeIDTaken(int empID) {
        for (Employee employee : Main.employees) {
            if (employee.getID() == empID) {
                return true;
            }
        }
        return false;
    }

    private static void assignProjectsToEmployee(Scanner sc, Employee employee) {
        boolean hasFinishedAssigning = false;
        while (!hasFinishedAssigning) {
            assignProjectToEmployee(sc, employee);

            System.out.println(ColorfulLogging.ANSI_BLUE + "Will you add more assignments to this employee? (type 'Yes' or 'No')" + ColorfulLogging.ANSI_RESET);
            String command = sc.nextLine();
            if (command.equals("No")) {
                hasFinishedAssigning = true;
            } else if (!command.equals("Yes")) {
                System.err.println("Invalid input, please try again!");
            }
        }
    }

    private static void assignProjectToEmployee(Scanner sc, Employee employee) {
        System.out.println(ColorfulLogging.ANSI_BLUE + "Assign a project to the employee (Just type the ID of the project):" + ColorfulLogging.ANSI_RESET);
        boolean isCommandValid = false;
        LocalDate startDate;
        LocalDate endDate;
        while (!isCommandValid) {
            int projID = getProjectID(sc, employee);
            startDate = getStartDate(sc);
            endDate = getEndDate(sc);

            while (endDate.isBefore(startDate)) {
                System.err.println("Start date must be before or equal to end date!");
                endDate = getEndDate(sc);
            }
            AssignedProject ap = new AssignedProject(projID, startDate, endDate);
            employee.addAssignedProject(ap);
            isCommandValid = true;
        }
    }

    private static int getProjectID(Scanner sc, Employee addedEmployee) {
        int projID = 0;
        boolean doesHaveAssignedPr = true;
        String command;
        while (doesHaveAssignedPr) {
            command = sc.nextLine();
            try {
                projID = Integer.parseInt(command);
                int finalProjID = projID;
                doesHaveAssignedPr = addedEmployee.getAssignedProjects()
                        .stream()
                        .anyMatch(assignedProject -> assignedProject.getProjectID() == finalProjID);
                if (doesHaveAssignedPr) {
                    System.err.println("This project is already assigned to the employee!");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println("Invalid project ID! Use numbers only!");
            }
        }
        return projID;
    }

    private static LocalDate getStartDate(Scanner sc) {
        System.out.printf(ColorfulLogging.ANSI_YELLOW + "REMINDER! Your preferred date format is '%s'%n", Main.dateParser.getDateFormat() + ColorfulLogging.ANSI_RESET);
        System.out.println(ColorfulLogging.ANSI_BLUE + "Type in the starting date of the assignment:" + ColorfulLogging.ANSI_RESET);
        String command = sc.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Main.dateParser.getDateFormat(), Locale.ENGLISH);
        while (true) {
            try {
                if (Main.dateParser.validateFormat(command)) {
                    if (LocalDate.parse(command, dtf).isBefore(LocalDate.now()) || LocalDate.parse(command, dtf).isEqual(LocalDate.now())) {
                        return LocalDate.parse(command, dtf);
                    } else {
                        System.err.println("Date must not be after the current date!");
                        command = sc.nextLine();
                    }
                } else {
                    System.err.println("Invalid input, please try again!");
                    command = sc.nextLine();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println("Invalid input, please try again!");
                command = sc.nextLine();
            }
        }
    }

    private static LocalDate getEndDate(Scanner sc) {
        System.out.printf(ColorfulLogging.ANSI_YELLOW + "REMINDER! Your preferred date format is '%s'%n", Main.dateParser.getDateFormat() + ColorfulLogging.ANSI_RESET);
        System.out.println(ColorfulLogging.ANSI_BLUE + "Type in the ending date of the assignment or type 'NULL' if the assignment has not ended:" + ColorfulLogging.ANSI_RESET);
        while (true) {
            String command = sc.nextLine();
            try {
                if (command.equals("NULL")) {
                    return LocalDate.now();
                } else {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Main.dateParser.getDateFormat(), Locale.ENGLISH);
                    if (Main.dateParser.validateFormat(command)) {
                        if (LocalDate.parse(command, dtf).isBefore(LocalDate.now()) || LocalDate.parse(command, dtf).isEqual(LocalDate.now())) {
                            return LocalDate.parse(command, dtf);
                        } else {
                            System.err.println("Date must not be after the current date!");
                        }
                    } else {
                        System.err.println("Invalid input, please try again!");
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println("Invalid input, please try again!");
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
                    for (AssignedProject ap : employee.getAssignedProjects()) {
                        boolean isExisting = false;
                        for (CSVLine line : Main.lines) {
                            if (ap.getProjectID() == line.getProjectID() && line.getEmpID() == employee.getID()) {
                                isExisting = true;
                                break;
                            }
                        }
                        if (!isExisting) {
                            CSVLine lineToAdd = new CSVLine(employee.getID(), ap.getProjectID(), ap.getStartDate(), ap.getEndDate());
                            Main.lines.add(lineToAdd);
                        }
                    }
                    Main.changesMade = true;
                    break;
                case "2":
                    editProjectAssignment(sc, employee);
                    break;
                case "3":
                    if (employee.getAssignedProjects().size() == 1) {
                        System.err.println("Employee must have at least two projects assigned to delete one!");
                    } else {
                        deleteAssignment(sc, employee);
                    }
                    break;
                default:
                    System.err.println("Invalid input, please try again!");
                    command = sc.nextLine();
                    continue;
            }
            System.out.println(ConsoleMenus.editEmployeeMenu);
            command = sc.nextLine();
        }
    }

    private static void editProjectAssignment(Scanner sc, Employee employee) {
        ArrayList<AssignedProject> assignedProjects = employee.getAssignedProjects();
        int empID = employee.getID();
        System.out.println(ConsoleMenus.projectAssignmentsMenu(assignedProjects));
        String command = sc.nextLine();
        while (!command.equals("0")) {
            try {
                int projectIndex = Integer.parseInt(command) - 1;
                if (projectIndex >= 0 && projectIndex < assignedProjects.size()) {
                    AssignedProject ap = assignedProjects.get(projectIndex);
                    manageProjectDetailsMenu(sc, ap, empID);
                } else {
                    System.err.println("Invalid input, please try again!");
                    command = sc.nextLine();
                    continue;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println("Invalid input, please try again!");
                command = sc.nextLine();
                continue;
            }
            System.out.println(ConsoleMenus.projectAssignmentsMenu(assignedProjects));
            command = sc.nextLine();
        }
    }

    private static void manageProjectDetailsMenu(Scanner sc, AssignedProject ap, int empID) {
        System.out.println(ConsoleMenus.editAssignmentDatesMenu(ap));
        String command = sc.nextLine();
        while (!command.equals("0")) {
            switch (command) {
                case "1":
                    LocalDate startDate = getStartDate(sc);
                    while (ap.getEndDate().isBefore(startDate)) {
                        System.err.println("Start date must be before or equal to end date!");
                        startDate = getStartDate(sc);
                    }
                    ap.setStartDate(startDate);
                    for (CSVLine line : Main.lines) {
                        if (line.getEmpID() == empID && line.getProjectID() == ap.getProjectID()) {
                            line.setStartDate(startDate);
                            break;
                        }
                    }
                    Main.changesMade = true;
                    break;
                case "2":
                    LocalDate endDate = getEndDate(sc);
                    while (endDate.isBefore(ap.getStartDate())) {
                        System.err.println("End date must be after or equal to start date!");
                        endDate = getEndDate(sc);
                    }
                    ap.setEndDate(endDate);
                    for (CSVLine line : Main.lines) {
                        if (line.getEmpID() == empID && line.getProjectID() == ap.getProjectID()) {
                            line.setEndDate(endDate);
                            break;
                        }
                    }
                    Main.changesMade = true;
                    break;
                default:
                    System.err.println("Invalid input, please try again!");
                    command = sc.nextLine();
                    continue;
            }
            System.out.println(ConsoleMenus.editAssignmentDatesMenu(ap));
            command = sc.nextLine();
        }
    }

    private static void deleteAssignment(Scanner sc, Employee employee) {
        ArrayList<AssignedProject> assignedProjects = employee.getAssignedProjects();
        System.out.println(ConsoleMenus.projectAssignmentsMenu(assignedProjects));
        String command = sc.nextLine();
        while (!command.equals("0")) {
            try {
                int projectIndex = Integer.parseInt(command) - 1;
                if (projectIndex >= 0 && projectIndex < assignedProjects.size()) {
                    System.out.println(ColorfulLogging.ANSI_YELLOW + "Are You sure You want to DELETE this assignment? (type 'Yes' or 'No')" + ColorfulLogging.ANSI_RESET);
                    while (true) {
                        command = sc.nextLine();
                        if (command.equals("Yes")) {
                            AssignedProject ap = assignedProjects.get(projectIndex);
                            for (int i = Main.lines.size() - 1; i >= 0; i--) {
                                if (Main.lines.get(i).getEmpID() == employee.getID() && Main.lines.get(i).getProjectID() == ap.getProjectID()) {
                                    Main.lines.remove(i);
                                    Main.changesMade = true;
                                    break;
                                }
                            }
                            assignedProjects.remove(projectIndex);
                            return;
                        } else if (command.equals("No")) {
                            break;
                        } else {
                            System.err.println("Invalid input, please try again!");
                        }
                    }
                } else {
                    System.err.println("Invalid input, please try again!");
                    command = sc.nextLine();
                    continue;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println("Invalid input, please try again!");
                command = sc.nextLine();
                continue;
            }
            System.out.println(ConsoleMenus.projectAssignmentsMenu(assignedProjects));
            command = sc.nextLine();
        }
    }

    private static boolean deleteEmployee(Scanner sc, int employeeIndex) {
        System.out.println(ColorfulLogging.ANSI_YELLOW + "Are You sure You want to DELETE this employee? (type 'Yes' or 'No')" + ColorfulLogging.ANSI_RESET);
        String command;
        while (true) {
            command = sc.nextLine();
            if (command.equals("Yes")) {
                for (int i = Main.lines.size() - 1; i >= 0; i--) {
                    if (Main.lines.get(i).getEmpID() == Main.employees.get(employeeIndex).getID()) {
                        Main.lines.remove(i);
                    }
                }
                Main.employees.remove(employeeIndex);
                Main.changesMade = true;
                return true;
            } else if (command.equals("No")) {
                break;
            } else {
                System.err.println("Invalid input, please try again!");
            }
        }
        return false;
    }
}