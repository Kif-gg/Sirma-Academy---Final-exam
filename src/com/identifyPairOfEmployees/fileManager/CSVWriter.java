package com.identifyPairOfEmployees.fileManager;

import com.identifyPairOfEmployees.Main;
import com.identifyPairOfEmployees.models.CSVLine;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CSVWriter {
    public static void write() {
        String path = "src\\com\\identifyPairOfEmployees\\database\\data.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.println("EmpID, ProjectID, DateFrom, DateTo");
            for (CSVLine line : Main.lines) {
                ArrayList<String> lineAsArr = new ArrayList<>();
                lineAsArr.add(String.valueOf(line.getEmpId()));
                lineAsArr.add(String.valueOf(line.getProjectId()));
                lineAsArr.add(String.valueOf(line.getStartDate()));
                if (line.getEndDate().equals(LocalDate.now())) {
                    lineAsArr.add("NULL");
                } else {
                    lineAsArr.add(String.valueOf(line.getEndDate()));
                }
                String lineAsStr = String.join(", ", lineAsArr);
                writer.println(lineAsStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
