import java.util.*;

public class EmployeeManager {
    private List<Employee> employees = new ArrayList<>();

    // Добавить сотрудника
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Получить всех сотрудников
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Найти сотрудника по ID
    public Employee findById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    // Обновить информацию о сотруднике
    public boolean updateEmployee(int id, String name, String email, int leaveDays) {
        Employee e = findById(id);
        if (e != null) {
            // Сеттерами обновляем значения
            e.setName(name);      // setName должен быть в Employee
            e.setEmail(email);    // setEmail должен быть в Employee
            e.setLeaveDays(leaveDays);  // setLeaveDays должен быть в Employee
            return true;
        }
        return false;
    }

    // Удалить сотрудника по ID
    public boolean deleteEmployee(int id) {
        return employees.removeIf(e -> e.getId() == id);
    }
}
