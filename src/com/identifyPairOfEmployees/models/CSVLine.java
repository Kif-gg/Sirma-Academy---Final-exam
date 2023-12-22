package com.identifyPairOfEmployees.models;

import java.time.LocalDate;

public class CSVLine {
    private final int empID;
    private final int projectID;
    private LocalDate startDate;
    private LocalDate endDate;

    public CSVLine(int empID, int projectID, LocalDate startDate, LocalDate endDate) {
        this.empID = empID;
        this.projectID = projectID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getEmpID() {
        return empID;
    }

    public int getProjectID() {
        return projectID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
