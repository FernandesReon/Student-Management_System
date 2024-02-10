public class Students {

    private String name;
    private int age;
    private String address;
    private double marks;

    Students(String name, int age, String address, double marks) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.marks = marks;
    }

    // Getters --> used to access data value through a method.
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public double getMarks() {
        return marks;
    }

    // Setters --> set values to variables....
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

}
