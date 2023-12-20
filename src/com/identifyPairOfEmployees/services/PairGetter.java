package com.identifyPairOfEmployees.services;

import com.identifyPairOfEmployees.Main;
import com.identifyPairOfEmployees.models.AssignedProject;
import com.identifyPairOfEmployees.models.Employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PairGetter {
    private int firstEmployeeID;
    private int secondEmployeeID;
    private int totalDaysWorkingTogether;
    private ArrayList<String> projectsAndDays;

    public void findLongestWorkingPair() {
        for (int i = 0; i < Main.employees.size(); i++) {
            for (int j = i + 1; j < Main.employees.size(); j++) {
                Employee firstEmployee = Main.employees.get(i);
                Employee secondEmployee = Main.employees.get(j);
                int daysTogether = calculateDaysTogether(firstEmployee, secondEmployee);
                if (daysTogether > totalDaysWorkingTogether) {
                    totalDaysWorkingTogether = daysTogether;
                    firstEmployeeID = firstEmployee.getID();
                    secondEmployeeID = secondEmployee.getID();
                }
            }
        }
    }

    private int calculateDaysTogether(Employee firstEmployee, Employee secondEmployee) {
        ArrayList<AssignedProject> assignedProjectsFirst = firstEmployee.getAssignedProjects();
        ArrayList<AssignedProject> assignedProjectsSecond = secondEmployee.getAssignedProjects();
        int totalDays = 0;
        ArrayList<String> projDays = new ArrayList<>();

        for (AssignedProject firstAssign : assignedProjectsFirst) {
            for (AssignedProject secondAssign : assignedProjectsSecond) {
                if (firstAssign.getProjectID() == secondAssign.getProjectID()) {
                    LocalDate startDateFirst = firstAssign.getStartDate();
                    LocalDate endDateFirst = firstAssign.getEndDate();
                    LocalDate startDateSecond = secondAssign.getStartDate();
                    LocalDate endDateSecond = secondAssign.getEndDate();

                    LocalDate rangeStart = startDateFirst.isBefore(startDateSecond) ? startDateSecond : startDateFirst;
                    LocalDate rangeEnd = endDateFirst.isBefore(endDateSecond) ? endDateFirst : endDateSecond;

                    if (rangeStart.isBefore(rangeEnd)) {
                        long daysBetween = ChronoUnit.DAYS.between(rangeStart, rangeEnd);
                        totalDays += (int) daysBetween;
                        String singleProjDays = "Project ID: " + firstAssign.getProjectID() + ", Days: " + daysBetween;
                        projDays.add(singleProjDays);
                    }
                }
            }
        }
        if (totalDays > getTotalDaysWorkingTogether()) {
            projectsAndDays = projDays;
        }
        return totalDays;
    }

    public int getFirstEmployeeID() {
        return firstEmployeeID;
    }

    public int getSecondEmployeeID() {
        return secondEmployeeID;
    }

    public int getTotalDaysWorkingTogether() {
        return totalDaysWorkingTogether;
    }

    public ArrayList<String> getProjectsAndDays() {
        return projectsAndDays;
    }
}
