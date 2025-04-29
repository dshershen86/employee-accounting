import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeFactory {

    static List<String> names = new ArrayList<>() {
        {
            add("Петренко");
            add("Прокопенко");
            add("Марченко");
            add("Шевченко");
            add("Кириленко");
        }
    };

    static List<Employee> generateEmployees(int size) {

        List<Employee> employees = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Employee newEmployee = new Employee();

            newEmployee.setName(names.get(random.nextInt(names.size())));
            newEmployee.setSalary(random.nextDouble(100) + 10);
            newEmployee.setAge(random.nextInt(50) + 20);
            List<Sex> sexs = new ArrayList<>(List.of(Sex.values()));
            newEmployee.setSex(sexs.get(random.nextInt(sexs.size())));
            newEmployee.setFixedBugs(random.nextInt(27));
            newEmployee.setDefaultBugRate(random.nextInt(27));
            employees.add(newEmployee);
        }
        return employees;
    }

}
