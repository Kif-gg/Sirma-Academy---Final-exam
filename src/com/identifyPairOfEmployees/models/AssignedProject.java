package com.identifyPairOfEmployees.models;

import java.time.LocalDate;

public class AssignedProject {
    private final int projectID;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public AssignedProject(int projectID, LocalDate startDate, LocalDate endDate) {
        this.projectID = projectID;
        this.startDate = startDate;
        this.endDate = endDate;
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
}