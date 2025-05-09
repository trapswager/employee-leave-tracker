import java.util.*;
import java.io.*;

public class ReportGenerator {
    private int operationCount = 0;

    public void log(String action) {
        operationCount++;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/log.txt", true))) {
            writer.write(new Date() + " - " + action);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка записи лога: " + e.getMessage());
        }
    }

    public void generateReport(List<Employee> employees) {
        System.out.println("====== REPORT ======");
        System.out.println("Total employees: " + employees.size());
        System.out.println("Total operations: " + operationCount);
        System.out.println("Log saved in data/log.txt");
    }
}


