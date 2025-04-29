import java.util.Comparator;
import java.util.List;

import static java.lang.System.*;

public class EmployeeService {
    private final List<Employee> employees;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    public void printEmploees() {
        for (Employee employee : employees) {
            out.println(employee);
        }
    }

    public double calculateSalaryAndBonus() {
        return employees.stream().mapToDouble(Employee::getSalary).sum() +
                employees.stream().mapToDouble(x -> x.getFixedBugs() * x.getDefaultBugRate()).sum();
    }

    public Employee getById(long id) throws EmployeeNotFoundException {
        Employee result = employees.stream().
                filter(x -> x.getId() == id)
                .findFirst().orElse(null);
        if (result == null) {
            throw new EmployeeNotFoundException();
        }
        return result;
    }

    public Employee getByName(String name) throws EmployeeNotFoundException {
        Employee result = employees.stream().
                filter(x -> x.getName().equals(name))
                .findFirst().orElse(null);
        if (result == null) {
            throw new EmployeeNotFoundException();
        }
        return result;
    }

    public Employee[] sortByName() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .toArray(Employee[]::new);
    }

    public Employee[] sortByNameAndSalary() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .sorted(Comparator.comparing(Employee::getName))
                .toArray(Employee[]::new);
    }

    public Employee edit(Employee employee) throws EmployeeNotFoundException {
        Employee desiredEmployee = getById(employee.getId());
        if (desiredEmployee == null) {
            throw new EmployeeNotFoundException();
        }
        employees.set(employees.indexOf(desiredEmployee), employee);
        return desiredEmployee;
    }

    public Employee remove(long id) throws EmployeeNotFoundException {
        Employee result = employees.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (result != null) {
            employees.removeIf(x -> x.getId() == id);
        } else {
            throw new EmployeeNotFoundException();
        }
        return result;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
