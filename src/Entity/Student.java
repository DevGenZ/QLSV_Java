package Entity;

public class Student {
    private String id;
    private String name;
    private byte age;
    private String address;
    private double GPA;

    public Student() {}

    public Student(String id, String name, byte age, String address, double GPA) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.GPA = GPA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
}
