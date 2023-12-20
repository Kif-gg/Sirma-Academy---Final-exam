package com.identifyPairOfEmployees.models;

import java.util.ArrayList;

public class Employee {
    private final int ID;
    private final ArrayList<AssignedProject> assignedProjects = new ArrayList<>();

    public Employee(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<AssignedProject> getAssignedProjects() {
        return assignedProjects;
    }

    public void addAssignedProject(AssignedProject assignedProject) {
        this.assignedProjects.add(assignedProject);
    }
}
