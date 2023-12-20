package com.identifyPairOfEmployees.fileManager;

import com.identifyPairOfEmployees.Main;
import com.identifyPairOfEmployees.models.AssignedProject;
import com.identifyPairOfEmployees.models.Employee;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CSVReader {
    public static ArrayList<Employee> read() {
        ArrayList<Employee> employees = new ArrayList<>();
        String path = "src\\com\\identifyPairOfEmployees\\database\\data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skipping the headers of CSV file
            while ((line = br.readLine()) != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Main.dateParser.getDateFormat());
                String[] values = line.split(", ");
                int empID = Integer.parseInt(values[0]);
                int projId = Integer.parseInt(values[1]);
                LocalDate dateFrom = LocalDate.parse(values[2], formatter);
                LocalDate dateTo;
                if (values[3].equals("NULL")) {
                    LocalDate dateToInit = LocalDate.now();
                    dateTo = LocalDate.parse(dateToInit.format(formatter));
                } else {
                    dateTo = LocalDate.parse(values[3], formatter);
                }

                AssignedProject assignedProject = new AssignedProject(projId, dateFrom, dateTo);

                Employee searchEmployee = findEmployeeById(empID, employees);
                if (searchEmployee == null) {
                    Employee employee = new Employee(empID);
                    employee.addAssignedProject(assignedProject);
                    employees.add(employee);
                } else {
                    searchEmployee.addAssignedProject(assignedProject);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return employees;
    }

    public static Employee findEmployeeById(int id, ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.getID() == id) {
                return employee;
            }
        }
        return null;
    }
}