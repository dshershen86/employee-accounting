import java.util.concurrent.atomic.AtomicLong;

public class Employee implements Cloneable {


    private static final AtomicLong count = new AtomicLong(0);

    private long id;
    private String name;
    private int age;
    private double salary;
    private Sex sex;
    private int fixedBugs, defaultBugRate;

    public Employee() {
        id = count.getAndIncrement();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getFixedBugs() {
        return fixedBugs;
    }

    public void setFixedBugs(int fixedBugs) {
        this.fixedBugs = fixedBugs;
    }

    public int getDefaultBugRate() {
        return defaultBugRate;
    }

    public void setDefaultBugRate(int defaultBugRate) {
        this.defaultBugRate = defaultBugRate;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", sex=" + sex +
                ", fixedBugs=" + fixedBugs +
                ", defaultBugRate=" + defaultBugRate +
                '}';
    }
}
