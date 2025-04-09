package P4;

import java.util.Scanner;

public class MyList {
    Node head;
    Node tail;

    public MyList() {
        this.head = this.tail = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void addStudentFromKeyBoard() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter the ID student: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter the name student: ");
            String name = sc.nextLine();
            System.out.print("Enter the mark student: ");
            double mark = sc.nextDouble();
            this.add(new Student(id, name, mark));
            System.out.println("Student added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            sc.nextLine(); // Xóa buffer
        }
    }

    public void add(Student s) {
        Node newNode = new Node(s);
        if (this.isEmpty()) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }

    public void editStudent(int id, String newName, double newMark) {
        Node p = this.head;
        while (p != null) {
            if (p.student.getId() == id) {
                try {
                    p.student = new Student(id, newName, newMark);
                    System.out.println("The student with ID " + id + " has been updated");
                    return;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    return;
                }
            }
            p = p.next;
        }
        System.out.println("Student with ID " + id + " not found");
    }

    public void deleStudent(int id) {
        if (this.isEmpty()) {
            System.out.println("No student to delete");
            return;
        }

        if (this.head.student.getId() == id) {
            this.head = this.head.next;
            if (this.head == null) this.tail = null;
            System.out.println("The student with ID " + id + " has been deleted");
            return;
        }

        Node p = this.head;
        while (p.next != null) {
            if (p.next.student.getId() == id) {
                p.next = p.next.next;
                if (p.next == null) this.tail = p;
                System.out.println("The student with ID " + id + " has been deleted");
                return;
            }
            p = p.next;
        }
        System.out.println("Student with ID " + id + " not found");
    }

    public void bubbleSort(boolean ascending) {
        if (this.head == null || this.head.next == null) {
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            Node p = this.head;
            while (p.next != null) {
                boolean condition = ascending ?
                        p.student.getMark() > p.next.student.getMark() :
                        p.student.getMark() < p.next.student.getMark();
                if (condition) {
                    Student temp = p.student;
                    p.student = p.next.student;
                    p.next.student = temp;
                    swapped = true;
                }
                p = p.next;
            }
        } while (swapped);
    }

    public void quickSort(boolean ascending) {
        this.head = quickSortRec(this.head, ascending);
        // Cập nhật tail
        if (this.head == null) {
            this.tail = null;
        } else {
            Node p = this.head;
            while (p.next != null) {
                p = p.next;
            }
            this.tail = p;
        }
    }

    private Node quickSortRec(Node start, boolean ascending) {
        if (start == null || start.next == null) {
            return start;
        }

        Node pivot = start;
        Node lessHead = null, lessTail = null;
        Node greaterHead = null, greaterTail = null;

        Node current = start.next;
        while (current != null) {
            Node next = current.next;
            current.next = null;
            if (ascending ? current.student.getMark() < pivot.student.getMark() :
                    current.student.getMark() > pivot.student.getMark()) {
                if (lessHead == null) {
                    lessHead = lessTail = current;
                } else {
                    lessTail.next = current;
                    lessTail = current;
                }
            } else {
                if (greaterHead == null) {
                    greaterHead = greaterTail = current;
                } else {
                    greaterTail.next = current;
                    greaterTail = current;
                }
            }
            current = next;
        }

        lessHead = quickSortRec(lessHead, ascending);
        greaterHead = quickSortRec(greaterHead, ascending);

        if (lessHead == null) {
            pivot.next = greaterHead;
            return pivot;
        }

        Node temp = lessHead;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = pivot;
        pivot.next = greaterHead;
        return lessHead;
    }

    public int linearSearch(int id) {
        Node p = this.head;
        int index = 0;
        while (p != null) {
            if (p.student.getId() == id) {
                return index;
            }
            p = p.next;
            index++;
        }
        return -1;
    }

    public int binarySearch(int id) {
        if (this.isEmpty()) return -1;

        // Sắp xếp theo ID trước khi tìm kiếm nhị phân
        bubbleSortById();

        int low = 0;
        int high = getSize() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Node p = getNodeAt(mid);
            if (p.student.getId() == id) {
                return mid;
            }
            if (p.student.getId() < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private void bubbleSortById() {
        if (this.head == null || this.head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Node p = this.head;
            while (p.next != null) {
                if (p.student.getId() > p.next.student.getId()) {
                    Student temp = p.student;
                    p.student = p.next.student;
                    p.next.student = temp;
                    swapped = true;
                }
                p = p.next;
            }
        } while (swapped);
    }

    private Node getNodeAt(int index) {
        Node p = this.head;
        for (int i = 0; i < index && p != null; i++) {
            p = p.next;
        }
        return p;
    }

    private int getSize() {
        int size = 0;
        Node p = this.head;
        while (p != null) {
            size++;
            p = p.next;
        }
        return size;
    }

    public void traverse() {
        if (this.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        Node p = this.head;
        while (p != null) {
            System.out.println(p.student.toString());
            p = p.next;
        }
    }

    public void pushStack(MyStack stack) {
        Node p = this.head;
        while (p != null) {
            stack.push(p.student);
            p = p.next;
        }
    }
}