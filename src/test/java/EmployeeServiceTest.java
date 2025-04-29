import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeesService;

    @BeforeEach
    void setUp() {
        employeesService = new EmployeeService(EmployeeFactory.generateEmployees(5));
    }

    @Test
    void printEmploeesTest() {
        employeesService.printEmploees();
    }

    @DisplayName("Testing calculateSalaryAndBonusTest result positive")
    @Test
    void calculateSalaryAndBonusResultIsPositiveTest() {
        Assumptions.assumeTrue(0 <= employeesService.calculateSalaryAndBonus());
    }

    @DisplayName("Testing calculateSalaryAndBonusTest result is 4300")
    @Test
    void calculateSalaryAndBonusResultIs4300Test() {
        employeesService = new EmployeeService(EmployeeFactory.generateEmployees(5));
        employeesService.getEmployees().forEach(x -> x.setSalary(200));
        employeesService.getEmployees().forEach(x -> x.setFixedBugs(10));
        employeesService.getEmployees().forEach(x -> x.setDefaultBugRate(50));
        assertEquals((double) (200 * 5) + (10 * 5) * 50, employeesService.calculateSalaryAndBonus());

    }

    @Test
    void getByIdTest() {
        try {
            assertEquals(3, employeesService.getById(3).getId());

            assertEquals(2, employeesService.getById(2).getId());
            assertNotEquals(2, employeesService.getById(1).getId());
            assertNotEquals(0, employeesService.getById(4).getId());
            assertEquals(2, employeesService.getById(-1).getId());
        } catch (EmployeeNotFoundException e) {
            getByIdThrowsException();
        }
    }

    @Test
    void getByIdThrowsException() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeesService.getById(-1).getId();
        });
    }

    @Test
    void getByNameTest() {
        for (String name : EmployeeFactory.names) {
            try {
                assertEquals(name, employeesService.getByName(name).getName());
            } catch (EmployeeNotFoundException e) {
                getByIdThrowsException();
            }

        }
    }

    @Test
    public void sortByNameTest() {
        Employee[] expectedEmployee = employeesService.getEmployees().stream()
                .sorted(Comparator.comparing(Employee::getName))
                .toArray(Employee[]::new);
        Employee[] testEmployee = employeesService.sortByName();
        assertArrayEquals(expectedEmployee, testEmployee);
    }

    @Test
    public void sortByNameAndSalary() {
        Employee[] expectedEmployee = employeesService.getEmployees().stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .sorted(Comparator.comparing(Employee::getName))
                .toArray(Employee[]::new);
        Employee[] testEmployee = employeesService.sortByNameAndSalary();
        assertArrayEquals(expectedEmployee, testEmployee);
    }

    @Test
    public void editTest() {
        try {
            Employee desiredEmployee = employeesService.getById(12);

            Employee testedEmployee = new Employee();
            testedEmployee.setName("Some name");
            testedEmployee.setSalary(100);
            testedEmployee.setFixedBugs(10);
            testedEmployee.setDefaultBugRate(50);
            testedEmployee.setSex(Sex.MALE);
            testedEmployee.setId(2);
            Employee result = employeesService.edit(testedEmployee);
            assertNotEquals(testedEmployee.getName(), result.getName());
            assertEquals(desiredEmployee.getName(), result.getName());
        } catch (EmployeeNotFoundException e) {
            getByIdThrowsException();
        }
    }

    @Test
    void removeByIdTest() {
        try {
            assertEquals(3, employeesService.remove(3).getId());
            assertEquals(2, employeesService.remove(2).getId());
            assertEquals(3, employeesService.getEmployees().size());
            assertNotEquals(2, employeesService.remove(1).getId());
            assertEquals(2, employeesService.getEmployees().size());
            assertEquals(2, employeesService.getById(-1).getId());
        } catch (EmployeeNotFoundException e) {
            getByIdThrowsException();
        }
    }
}
