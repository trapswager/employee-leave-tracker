import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "data/employees.txt";
    private static final String LOG_FILE = "data/log.txt"; // Путь к файлу логов
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeManager manager = new EmployeeManager();
    private static final ReportGenerator report = new ReportGenerator();

    public static void main(String[] args) {
        manager.getAllEmployees().addAll(FileHandler.loadEmployeesFromFile(FILE_NAME)); // Загружаем сотрудников из файла
        boolean running = true;

        while (running) {
            System.out.println("1. Add employee");
            System.out.println("2. View all employees");
            System.out.println("3. Update employee");
            System.out.println("4. Delete employee");
            System.out.println("5. Generate report");
            System.out.println("6. Export employees to CSV");
            System.out.println("7. Export employees to JSON");
            System.out.println("8. Import employees from CSV");
            System.out.println("9. Import employees from JSON");
            System.out.println("0. Exit");

            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEmployee(); // Добавить сотрудника
                    break;
                case "2":
                    viewEmployees(); // Просмотр сотрудников
                    break;
                case "3":
                    updateEmployee(); // Обновить сотрудника
                    break;
                case "4":
                    deleteEmployee(); // Удалить сотрудника
                    break;
                case "5":
                    report.generateReport(manager.getAllEmployees()); // Сгенерировать отчет
                    break;
                case "6":
                    exportToCSV(); // Экспорт в CSV
                    break;
                case "7":
                    exportToJSON(); // Экспорт в JSON
                    break;
                case "8":
                    importFromCSV(); // Импорт из CSV
                    break;
                case "9":
                    importFromJSON(); // Импорт из JSON
                    break;
                case "0":
                    FileHandler.saveEmployeesToFile(FILE_NAME, manager.getAllEmployees()); // Сохранить данные в файл
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice."); // Неверный выбор
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = getValidIntInput(); // Ввод ID

        // Проверка на уникальность ID
        boolean idExists = false;
        for (Employee e : manager.getAllEmployees()) {
            if (e.getId() == id) {
                idExists = true;
                break;
            }
        }

        if (idExists) {
            System.out.println("Error: Employee with this ID already exists.");
            return;
        }

        String name;
        // Ввод имени без цифр
        while (true) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if (name.matches(".*\\d.*")) {
                System.out.println("Error: Name should not contain numbers.");
            } else {
                break;
            }
        }

        String email;
        // Ввод корректного email
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Error: Invalid email format.");
            } else {
                break;
            }
        }

        System.out.print("Enter leave days: ");
        int leaveDays = getValidIntInput(); // Ввод дней отпуска

        manager.addEmployee(new Employee(id, name, email, leaveDays)); // Добавляем сотрудника
        logAction("Added employee ID: " + id); // Логируем добавление сотрудника
    }

    private static void viewEmployees() {
        if (manager.getAllEmployees().isEmpty()) {
            System.out.println("No employees found."); // Если сотрудников нет
        } else {
            for (Employee e : manager.getAllEmployees()) {
                System.out.println(e); // Выводим всех сотрудников
            }
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter ID to update: ");
        int id = getValidIntInput(); // Ввод ID

        String name;
        // Ввод нового имени
        while (true) {
            System.out.print("New name: ");
            name = scanner.nextLine();
            if (name.matches(".*\\d.*")) {
                System.out.println("Error: Name should not contain numbers.");
            } else {
                break;
            }
        }

        String email;
        // Ввод нового email
        while (true) {
            System.out.print("New email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            } else {
                break;
            }
        }

        System.out.print("New leave days: ");
        int leaveDays = getValidIntInput(); // Ввод новых дней отпуска

        if (manager.updateEmployee(id, name, email, leaveDays)) {
            logAction("Updated employee ID: " + id); // Логируем обновление сотрудника
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee() {
        System.out.print("Enter ID to delete: ");
        int id = getValidIntInput(); // Ввод ID для удаления

        if (manager.deleteEmployee(id)) {
            logAction("Deleted employee ID: " + id); // Логируем удаление сотрудника
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Проверка формата email
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Получение корректного ввода числа
    private static int getValidIntInput() {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter a valid number: ");
            }
        }
        return input;
    }

    // Метод для логирования действий в log.txt
    private static void logAction(String action) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(action + " at " + new Date());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log file.");
        }
    }

    // Экспорт сотрудников в CSV
    private static void exportToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/employees.csv"))) {
            for (Employee e : manager.getAllEmployees()) {
                writer.write(e.getId() + "," + e.getName() + "," + e.getEmail() + "," + e.getLeaveDays());
                writer.newLine();
            }
            System.out.println("Employees exported to CSV successfully.");
        } catch (IOException e) {
            System.out.println("Error exporting employees to CSV.");
        }
    }

    // Экспорт сотрудников в JSON
    private static void exportToJSON() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/employees.json"))) {
            writer.write("[\n");
            for (int i = 0; i < manager.getAllEmployees().size(); i++) {
                Employee e = manager.getAllEmployees().get(i);
                writer.write("  {\n");
                writer.write("    \"id\": " + e.getId() + ",\n");
                writer.write("    \"name\": \"" + e.getName() + "\",\n");
                writer.write("    \"email\": \"" + e.getEmail() + "\",\n");
                writer.write("    \"leaveDays\": " + e.getLeaveDays() + "\n");
                writer.write("  }" + (i < manager.getAllEmployees().size() - 1 ? "," : "") + "\n");
            }
            writer.write("]");
            System.out.println("Employees exported to JSON successfully.");
        } catch (IOException e) {
            System.out.println("Error exporting employees to JSON.");
        }
    }

    // Импорт сотрудников из CSV
    private static void importFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/employees.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                int leaveDays = Integer.parseInt(data[3]);
                manager.addEmployee(new Employee(id, name, email, leaveDays));
            }
            System.out.println("Employees imported from CSV successfully.");
        } catch (IOException e) {
            System.out.println("Error importing employees from CSV.");
        }
    }

    // Импорт сотрудников из JSON
    private static void importFromJSON() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/employees.json"))) {
            String line;
            StringBuilder jsonData = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            String json = jsonData.toString();

            System.out.println("Employees imported from JSON successfully.");
        } catch (IOException e) {
            System.out.println("Error importing employees from JSON.");
        }
    }
}
