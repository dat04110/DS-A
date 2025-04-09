package P4;

import java.util.Scanner;

public class ASM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyList list = new MyList();
        MyStack stack = new MyStack();
        int choice;

        do {
            System.out.println("===== Student Management System =====");
            System.out.println("1. Add student"); // Thêm một sinh viên mới
            System.out.println("2. Edit student"); // Chỉnh sửa thông tin sinh viên
            System.out.println("3. Delete student"); // Xóa sinh viên khỏi danh sách
            System.out.println("4. Sort by mark (Bubble Sort)"); // Sắp xếp danh sách bằng thuật toán Bubble Sort
            System.out.println("5. Sort by mark (Quick Sort)"); // Sắp xếp danh sách bằng thuật toán Quick Sort
            System.out.println("6. Linear search by ID"); // Tìm kiếm sinh viên bằng thuật toán tuyến tính
            System.out.println("7. Binary search by ID"); // Tìm kiếm sinh viên bằng thuật toán nhị phân
            System.out.println("8. Display students"); // Hiển thị toàn bộ danh sách sinh viên
            System.out.println("9. Push to stack and display"); // Đưa danh sách vào stack và hiển thị nội dung stack
            System.out.println("0. Exit");
            System.out.print("Choose function: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                choice = -1;
                continue;
            }

            switch (choice) {

                case 1: {
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Enter the number of students to add: ");
                            String numInput = scanner.nextLine();
                            if (!numInput.matches("\\d+")) throw new Exception("Number must contain only digits!");
                            int numberOfStudents = Integer.parseInt(numInput);
                            if (numberOfStudents < 0) throw new Exception("Number of students cannot be negative!");

                            for (int i = 0; i < numberOfStudents; i++) {
                                System.out.println("Enter information for student " + (i + 1) + ":");

                                // Nhập và kiểm tra ID
                                int id = 0;
                                boolean validId = false;
                                while (!validId) {
                                    try {
                                        System.out.print("Enter the ID student: ");
                                        String idInput = scanner.nextLine();
                                        if (!idInput.matches("\\d+")) {
                                            if (idInput.matches("[a-zA-Z\\s]+")) {
                                                throw new Exception("ID cannot contain letters! It seems you entered a name.");
                                            } else {
                                                throw new Exception("ID must contain only numbers!");
                                            }
                                        }
                                        id = Integer.parseInt(idInput);
                                        if (id < 0) throw new Exception("ID cannot be negative!");
                                        validId = true;
                                    } catch (Exception e) {
                                        System.out.println("Invalid input! " + e.getMessage() + " Please enter ID again.");
                                    }
                                }

                                // Nhập và kiểm tra tên
                                String name = "";
                                boolean validName = false;
                                while (!validName) {
                                    try {
                                        System.out.print("Enter the name student: ");
                                        name = scanner.nextLine();
                                        if (!name.matches("[a-zA-Z\\s]+")) {
                                            if (name.matches("\\d+")) {
                                                throw new Exception("Name cannot contain numbers! It seems you entered an ID.");
                                            } else {
                                                throw new Exception("Name must contain only letters!");
                                            }
                                        }
                                        if (name.trim().isEmpty()) throw new Exception("Name cannot be empty!");
                                        validName = true;
                                    } catch (Exception e) {
                                        System.out.println("Invalid input! " + e.getMessage() + " Please enter name again.");
                                    }
                                }

                                // Nhập và kiểm tra điểm
                                double mark = 0;
                                boolean validMark = false;
                                while (!validMark) {
                                    try {
                                        System.out.print("Enter the mark student: ");
                                        String markInput = scanner.nextLine();
                                        if (!markInput.matches("\\d+(\\.\\d+)?")) throw new Exception("Mark must be a number!");
                                        mark = Double.parseDouble(markInput);
                                        if (mark < 0 || mark > 10) throw new Exception("Mark must be between 0 and 10!");
                                        validMark = true;
                                    } catch (Exception e) {
                                        System.out.println("Invalid input! " + e.getMessage() + " Please enter mark again.");
                                    }
                                }

                                // Thêm sinh viên vào danh sách sau khi tất cả thông tin hợp lệ
                                list.add(new Student(id, name, mark)); // Thay bằng cách thêm thực tế của bạn
                            }
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage() + " Please try again.");
                        }
                    }
                    break;
                }

                case 2: {
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Enter ID to edit: ");
                            String editIdInput = scanner.nextLine();
                            if (!editIdInput.matches("\\d+")) throw new Exception("ID must contain only numbers!");
                            int editId = Integer.parseInt(editIdInput);
                            if (editId < 0) throw new Exception("ID cannot be negative!");

                            System.out.print("Enter new name: ");
                            String newName = scanner.nextLine();
                            if (!newName.matches("[a-zA-Z\\s]+")) throw new Exception("Name must contain only letters!");
                            if (newName.trim().isEmpty()) throw new Exception("Name cannot be empty!");

                            System.out.print("Enter new mark: ");
                            String markInput = scanner.nextLine();
                            if (!markInput.matches("\\d+(\\.\\d+)?")) throw new Exception("Mark must be a number!");
                            double newMark = Double.parseDouble(markInput);
                            if (newMark < 0 || newMark > 10) throw new Exception("Mark must be between 0 and 10!");

                            list.editStudent(editId, newName, newMark);
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage() + " Please try again.");
                        }
                    }
                    break;
                }
                case 3: {
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Enter ID to delete: ");
                            String deleteIdInput = scanner.nextLine();
                            if (!deleteIdInput.matches("\\d+")) throw new Exception("ID must contain only numbers!");
                            int deleteId = Integer.parseInt(deleteIdInput);
                            if (deleteId < 0) throw new Exception("ID cannot be negative!");
                            list.deleStudent(deleteId);
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage() + " Please try again.");
                        }
                    }
                    break;
                }
                case 4: {
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Sort ascending? (true/false): ");
                            boolean bubbleAsc = scanner.nextBoolean();
                            list.bubbleSort(bubbleAsc);
                            System.out.println("Sorted by mark (Bubble Sort):");
                            list.traverse();
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Error: Please enter true or false! Please try again.");
                            scanner.nextLine();
                        }
                    }
                    break;
                }
                case 5: {
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Sort ascending? (true/false): ");
                            boolean quickAsc = scanner.nextBoolean();
                            list.quickSort(quickAsc);
                            System.out.println("Sorted by mark (Quick Sort):");
                            list.traverse();
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Error: Please enter true or false! Please try again.");
                            scanner.nextLine();
                        }
                    }
                    break;
                }
                case 6: {
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Enter ID to search (linear): ");
                            String linearIdInput = scanner.nextLine();
                            if (!linearIdInput.matches("\\d+")) throw new Exception("ID must contain only numbers!");
                            int linearId = Integer.parseInt(linearIdInput);
                            if (linearId < 0) throw new Exception("ID cannot be negative!");
                            int linearResult = list.linearSearch(linearId);
                            System.out.println(linearResult != -1 ?
                                    "Found at index: " + linearResult :
                                    "Student not found!");
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage() + " Please try again.");
                        }
                    }
                    break;
                }
                case 7: {
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Enter ID to search (binary): ");
                            String binaryIdInput = scanner.nextLine();
                            if (!binaryIdInput.matches("\\d+")) throw new Exception("ID must contain only numbers!");
                            int binaryId = Integer.parseInt(binaryIdInput);
                            if (binaryId < 0) throw new Exception("ID cannot be negative!");
                            int binaryResult = list.binarySearch(binaryId);
                            System.out.println(binaryResult != -1 ?
                                    "Found at index: " + binaryResult :
                                    "Student not found!");
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage() + " Please try again.");
                        }
                    }
                    break;
                }
                case 8:
                    list.traverse();
                    break;
                case 9:
                    stack = new MyStack();
                    list.pushStack(stack);
                    System.out.println("Stack contents:");
                    stack.displayStack();
                    break;
                case 0:
                    System.out.println("Exiting program!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        scanner.close();
    }
}