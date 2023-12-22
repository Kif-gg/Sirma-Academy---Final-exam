package com.identifyPairOfEmployees.consoleUI;

import com.identifyPairOfEmployees.Main;
import com.identifyPairOfEmployees.models.AssignedProject;
import com.identifyPairOfEmployees.models.Employee;
import com.identifyPairOfEmployees.services.PairGetter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ConsoleMenus {
    public static String welcome = """
            ╭───────────────────────────────────╮
            │              Welcome!             │
            │    Please choose an option by     │
            │       submitting a number.        │
            │    Default format: yyyy-mm-dd     │
            │───────────────────────────────────│
            │ 0. Exit                           │
            │ 1. Customize date format          │
            │ 2. Skip                           │
            ╰───────────────────────────────────╯
            """;

    public static String selectDayFormat = """
            ╭───────────────────────────────────╮
            │            Day format             │
            │───────────────────────────────────│
            │ 1. d (E.g: 1, 2, 3)               │
            │ 2. dd (E.g: 01, 02, 03)           │
            ╰───────────────────────────────────╯
            """;
    public static String selectMonthFormat = """
            ╭───────────────────────────────────╮
            │           Month format            │
            │───────────────────────────────────│
            │ 1. M (E.g: 1, 2, 3)               │
            │ 2. MM (E.g: 01, 02, 03)           │
            │ 3. MMM (E.g: Jan, Feb, Mar)       │
            │ 4. MMMM (E.g: January, February)  │
            ╰───────────────────────────────────╯
            """;
    public static String selectYearFormat = """
            ╭───────────────────────────────────╮
            │            Year format            │
            │───────────────────────────────────│
            │ 1. yy (E.g: 93, 13, 23)           │
            │ 2. yyyy (E.g: 1993, 2013, 2023)   │
            ╰───────────────────────────────────╯
            """;
    public static String selectSeparator = """
            ╭───────────────────────────────────╮
            │             Separator             │
            │───────────────────────────────────│
            │ 1. / (E.g: 1/1/2000)              │
            │ 2. . (E.g: 1.1.2000)              │
            │ 3. , (E.g: 1,1,2000)              │
            │ 4. - (E.g: 1-1-2000)              │
            │ 5.  (space) (E.g: 1 1 2000)       │
            ╰───────────────────────────────────╯
            """;
    public static String selectOrder = """
            ╭───────────────────────────────────╮
            │            Date order             │
            │───────────────────────────────────│
            │ 1. Day, Month, Year               │
            │ 2. Month, Day, Year               │
            │ 3. Year, Month, Day               │
            │ 4. Year, Day, Month               │
            │ 5. Day, Year, Month               │
            │ 6. Month, Year, Day               │
            ╰───────────────────────────────────╯
            """;
    public static String mainMenu = """
            ╭───────────────────────────────────╮
            │             Main menu             │
            │───────────────────────────────────│
            │ 0. Exit                           │
            │ 1. Show all employees             │
            │ 2. Show the longest working pair  │
            │ 3. Create new employee record     │
            ╰───────────────────────────────────╯
            """;

    public static String showAllEmployees(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            return """
                    ╭───────────────────────────────────╮
                    │    There are no employees yet!    │
                    │───────────────────────────────────│
                    │ 0. Go back                        │
                    ╰───────────────────────────────────╯
                    """;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= employees.size(); i++) {
            Employee employee = employees.get(i - 1);
            sb.append("│ ")
                    .append(i)
                    .append(". ")
                    .append(employee.getID())
                    .append(" ".repeat(32 - (Integer.toString(i).length() + Integer.toString(employee.getID()).length())))
                    .append("│\r\n");
        }
        return """
                ╭───────────────────────────────────╮
                │             Employees             │
                │───────────────────────────────────│
                │ 0. Go back                        │
                """ +
                sb + """
                ╰───────────────────────────────────╯
                """;
    }

    public static String showEmployeeDetails(int employeeIndex) {
        Employee employee = Main.employees.get(employeeIndex);
        StringBuilder sb = new StringBuilder();
        sb.append("│ Employee ID: ")
                .append(employee.getID())
                .append(" ".repeat(21 - String.valueOf(employee.getID()).length()))
                .append("│\r\n")
                .append("│───────────────────────────────────│\r\n")
                .append("│         Assigned projects         │\r\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Main.dateParser.getDateFormat(), Locale.ENGLISH);
        for (AssignedProject ap : employee.getAssignedProjects()) {
            int projectId = ap.getProjectID();
            String dateFrom = formatter.format(ap.getStartDate());
            String dateTo = formatter.format(ap.getEndDate());
            sb.append("│───────────────────────────────────│\r\n")
                    .append("│ ● Project ID: ")
                    .append(projectId)
                    .append(" ".repeat(20 - String.valueOf(projectId).length()))
                    .append("│\r\n")
                    .append("│  ◆ Start date: ")
                    .append(dateFrom)
                    .append(" ".repeat(19 - dateFrom.length()))
                    .append("│\r\n")
                    .append("│  ◆ End date: ")
                    .append(dateTo)
                    .append(" ".repeat(21 - dateTo.length()))
                    .append("│\r\n");
        }

        return """
                ╭───────────────────────────────────╮
                │         Employee details          │
                │───────────────────────────────────│
                │ 0. Go back                        │
                │ 1. Edit employee                  │
                │ 2. Delete employee                │
                │───────────────────────────────────│
                """ +
                sb + """
                ╰───────────────────────────────────╯
                """;
    }


    public static String printEmployeesAndProjects(PairGetter pairGetter) {
        StringBuilder sb = new StringBuilder();

        int id1 = pairGetter.getFirstEmployeeID();
        int id2 = pairGetter.getSecondEmployeeID();
        int daysTotal = pairGetter.getTotalDaysWorkingTogether();
        ArrayList<String> projectsAndDays = pairGetter.getProjectsAndDays();
        if (id1 == 0 || id2 == 0 || daysTotal == 0) {
            return """
                    ╭───────────────────────────────────╮
                    │    Insufficient data to show!     │
                    │───────────────────────────────────│
                    │ 0. Go back                        │
                    ╰───────────────────────────────────╯
                    """;
        }
        for (String line : projectsAndDays) {
            sb.append("│ ").append(line).append(" ".repeat(34 - line.length())).append("│\r\n");
        }
        sb.append("╰───────────────────────────────────╯");

        return """
                ╭───────────────────────────────────╮
                │ Longest working pair of employees │
                │───────────────────────────────────│
                │ 0. Go back                        │
                │ First employee Id:\s""" + id1 + " ".repeat(15 - String.valueOf(id1).length()) + "│\r\n"
                + """
                │ Second employee Id:\s""" + id2 + " ".repeat(14 - String.valueOf(id2).length()) + "│\r\n"
                + """
                │ Total days working:\s""" + daysTotal + " ".repeat(14 - String.valueOf(daysTotal).length()) + "│\r\n"
                + """
                │───────────────────────────────────│
                │ Project Id-s and days per project │
                │───────────────────────────────────│
                """ + sb;
    }
}
