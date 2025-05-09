public class Employee {
    private int id;
    private String name;
    private String email;
    private int leaveDays;

    public Employee(int id, String name, String email, int leaveDays) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.leaveDays = leaveDays;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    // Метод toCSV для представления сотрудника в формате CSV
    public String toCSV() {
        return id + "," + name + "," + email + "," + leaveDays;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Leave days: " + leaveDays;
    }

    // Форматированная строка для отображения информации о сотруднике
    public String toFormattedString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Leave days: " + leaveDays;
    }
}

