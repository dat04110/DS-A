package P4;

public class Student {
    private int id;
    public String name; // Giữ public như code gốc
    public double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        if (marks < 0 || marks > 10) {
            throw new IllegalArgumentException("Marks must be between 0 and 10");
        }
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public double getMark() {  // Sửa getMarks thành getMark cho nhất quán
        return marks;
    }

    @Override
    public String toString() {
        return "Student{ID=" + id + ", Name=" + name + ", Marks=" + marks + "}";
    }
}