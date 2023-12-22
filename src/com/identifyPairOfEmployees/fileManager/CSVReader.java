package com.identifyPairOfEmployees.fileManager;

import com.identifyPairOfEmployees.Main;
import com.identifyPairOfEmployees.models.AssignedProject;
import com.identifyPairOfEmployees.models.CSVLine;
import com.identifyPairOfEmployees.models.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CSVReader {
    public static ArrayList<Employee> read() {
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<CSVLine> lines = new ArrayList<>();
        String path = "src\\com\\identifyPairOfEmployees\\database\\data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skipping the headers of CSV file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(", ");
                int empID = Integer.parseInt(values[0]);
                int projID = Integer.parseInt(values[1]);
                LocalDate dateFrom = LocalDate.parse(values[2]);
                LocalDate dateTo;
                if (values[3].equals("NULL")) {
                    LocalDate dateToInit = LocalDate.now();
                    dateTo = LocalDate.parse(dateToInit.toString());
                } else {
                    dateTo = LocalDate.parse(values[3]);
                }
                AssignedProject assignedProject = new AssignedProject(projID, dateFrom, dateTo);

                Employee searchEmployee = findEmployeeByID(empID, employees);
                if (searchEmployee == null) {
                    Employee employee = new Employee(empID);
                    employee.addAssignedProject(assignedProject);
                    employees.add(employee);
                } else {
                    searchEmployee.addAssignedProject(assignedProject);
                }
                lines.add(new CSVLine(empID, projID, dateFrom, dateTo));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        Main.lines = lines;
        return employees;
    }

    public static Employee findEmployeeByID(int ID, ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.getID() == ID) {
                return employee;
            }
        }
        return null;
    }
}
