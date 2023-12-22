package com.identifyPairOfEmployees.models;

import java.time.LocalDate;

public class CSVLine {
    private final int empId;
    private int projectId;
    private LocalDate startDate;
    private LocalDate endDate;
    public CSVLine(int empId, int projectId, LocalDate startDate, LocalDate endDate) {
        this.empId = empId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getEmpId() {
        return empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
