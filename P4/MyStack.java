package P4;

public class MyStack {
    private Node top;

    public MyStack() {
        this.top = null;
    }

    public void push(Student student) {
        Node newNode = new Node(student);
        newNode.next = this.top;
        this.top = newNode;
    }

    public Student pop() {
        if (this.top == null) {
            System.out.println("Stack is empty.");
            return null;
        }
        Student student = this.top.student;
        this.top = this.top.next;
        return student;
    }

    public void displayStack() {
        if (this.top == null) {
            System.out.println("Stack is empty.");
        } else {
            for (Node p = this.top; p != null; p = p.next) {
                System.out.println(p.student.toString());
            }
        }
    }
}
