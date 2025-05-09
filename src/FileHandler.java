import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileHandler {

    public static List<Employee> loadEmployeesFromFile(String filename) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                int leaveDays = Integer.parseInt(parts[3]);
                employees.add(new Employee(id, name, email, leaveDays));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return employees;
    }

    public static void saveEmployeesToFile(String filename, List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Employee e : employees) {
                bw.write(e.toCSV()); // Используем toCSV вместо toString
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
