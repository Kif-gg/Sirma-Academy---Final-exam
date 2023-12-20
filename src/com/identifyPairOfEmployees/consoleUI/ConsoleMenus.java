package com.identifyPairOfEmployees.consoleUI;

import com.identifyPairOfEmployees.services.PairGetter;

import java.util.ArrayList;

public class ConsoleMenus {
    public static String welcome = """
            ╭───────────────────────────────────╮
            │              Welcome!             │
            │    Please choose an option by     │
            │       submitting a number.        │
            │    Default format: yyyy-mm-dd     │
            │───────────────────────────────────│
            │ 1. Customize date format          │
            │ 2. Skip                           │
            │ 0. Exit                           │
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

    public static String printEmployeesAndProjects(PairGetter pairGetter) {
        StringBuilder sb = new StringBuilder();

        int id1 = pairGetter.getFirstEmployeeID();
        int id2 = pairGetter.getSecondEmployeeID();
        int daysTotal = pairGetter.getTotalDaysWorkingTogether();
        ArrayList<String> projectsAndDays = pairGetter.getProjectsAndDays();
        for (String line : projectsAndDays) {
            sb.append("│ ").append(line).append(" ".repeat(34 - line.length())).append("│\r\n");
        }
        sb.append("╰───────────────────────────────────╯");

        return """
                ╭───────────────────────────────────╮
                │ First employee Id:\s""" + id1 + " ".repeat(15 - String.valueOf(id1).length()) + "│\r\n"
                + """
                │ Second employee Id:\s""" + id2 + " ".repeat(14 - String.valueOf(id2).length()) + "│\r\n"
                + """
                │ Total days working:\s""" + daysTotal + " ".repeat(14 - String.valueOf(daysTotal).length()) + "│\r\n"
                + """
                │ Days per single project:          │
                """ + sb;
    }
}
