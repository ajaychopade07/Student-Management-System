package com.studentmanagement;

import java.util.Scanner;

/**
 * Entry point for the Student Management System console application.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager studentManager = new StudentManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readIntChoice("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleViewStudents();
                    break;
                case 3:
                    handleSearchStudent();
                    break;
                case 4:
                    handleUpdateStudent();
                    break;
                case 5:
                    handleDeleteStudent();
                    break;
                case 6:
                    handleShowTotalStudents();
                    break;
                case 7:
                    running = false;
                    System.out.println("\nThank you for using Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=====================================");
        System.out.println("    STUDENT MANAGEMENT SYSTEM");
        System.out.println("=====================================");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Total Students");
        System.out.println("7. Exit");
        System.out.println("=====================================");
    }

    private static void handleAddStudent() {
        System.out.println("\n--- Add New Student ---");
        String id;
        while (true) {
            id = readNonEmptyString("Enter Student ID: ");
            if (studentManager.searchStudent(id) != null) {
                System.out.println("[Error] A student with ID '" + id + "' already exists! Please use a unique ID.");
            } else {
                break;
            }
        }

        String name = readNonEmptyString("Enter Name: ");
        int age = readPositiveInt("Enter Age: ");
        String course = readNonEmptyString("Enter Course: ");
        String email = readValidEmail("Enter Email: ");

        Student newStudent = new Student(id, name, age, course, email);
        if (studentManager.addStudent(newStudent)) {
            System.out.println("\nStudent added successfully!");
        } else {
            System.out.println("\nFailed to add student.");
        }
    }

    private static void handleViewStudents() {
        studentManager.viewStudents();
    }

    private static void handleSearchStudent() {
        System.out.println("\n--- Search Student ---");
        String id = readNonEmptyString("Enter Student ID to search: ");
        Student student = studentManager.searchStudent(id);

        if (student != null) {
            System.out.println("\nStudent found:");
            System.out.println("-------------------------------------");
            System.out.println("ID     : " + student.getStudentId());
            System.out.println("Name   : " + student.getName());
            System.out.println("Age    : " + student.getAge());
            System.out.println("Course : " + student.getCourse());
            System.out.println("Email  : " + student.getEmail());
            System.out.println("-------------------------------------");
        } else {
            System.out.println("\nStudent with ID '" + id + "' not found.");
        }
    }

    private static void handleUpdateStudent() {
        System.out.println("\n--- Update Student ---");
        String id = readNonEmptyString("Enter Student ID to update: ");
        Student student = studentManager.searchStudent(id);

        if (student == null) {
            System.out.println("\nStudent with ID '" + id + "' not found.");
            return;
        }

        System.out.println("\nCurrent Details:");
        System.out.println(student);
        System.out.println("\nEnter new details (press Enter to keep current value):");

        System.out.print("New Name [" + student.getName() + "]: ");
        String nameInput = scanner.nextLine().trim();
        String updatedName = nameInput.isEmpty() ? student.getName() : nameInput;

        int updatedAge = student.getAge();
        while (true) {
            System.out.print("New Age [" + student.getAge() + "]: ");
            String ageInput = scanner.nextLine().trim();
            if (ageInput.isEmpty()) {
                break;
            }
            try {
                int parsedAge = Integer.parseInt(ageInput);
                if (parsedAge > 0 && parsedAge < 130) {
                    updatedAge = parsedAge;
                    break;
                } else {
                    System.out.println("Age must be between 1 and 129.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a valid number.");
            }
        }

        System.out.print("New Course [" + student.getCourse() + "]: ");
        String courseInput = scanner.nextLine().trim();
        String updatedCourse = courseInput.isEmpty() ? student.getCourse() : courseInput;

        String updatedEmail = student.getEmail();
        while (true) {
            System.out.print("New Email [" + student.getEmail() + "]: ");
            String emailInput = scanner.nextLine().trim();
            if (emailInput.isEmpty()) {
                break;
            }
            if (isValidEmail(emailInput)) {
                updatedEmail = emailInput;
                break;
            } else {
                System.out.println("Invalid email format (example: name@domain.com). Please try again.");
            }
        }

        if (studentManager.updateStudent(id, updatedName, updatedAge, updatedCourse, updatedEmail)) {
            System.out.println("\nStudent updated successfully!");
        } else {
            System.out.println("\nFailed to update student.");
        }
    }

    private static void handleDeleteStudent() {
        System.out.println("\n--- Delete Student ---");
        String id = readNonEmptyString("Enter Student ID to delete: ");
        Student student = studentManager.searchStudent(id);

        if (student == null) {
            System.out.println("\nStudent with ID '" + id + "' not found.");
            return;
        }

        System.out.print("Are you sure you want to delete student '" + student.getName() + "' (ID: " + id + ")? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            if (studentManager.deleteStudent(id)) {
                System.out.println("\nStudent deleted successfully!");
            } else {
                System.out.println("\nFailed to delete student.");
            }
        } else {
            System.out.println("\nDeletion cancelled.");
        }
    }

    private static void handleShowTotalStudents() {
        int total = studentManager.getStudentCount();
        System.out.println("\n=====================================");
        System.out.println(" Total Registered Students: " + total);
        System.out.println("=====================================");
    }

    // Helper Input Methods with Validation

    private static int readIntChoice(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid numeric choice.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Field cannot be empty. Please enter a valid value.");
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val > 0 && val < 130) {
                    return val;
                }
                System.out.println("Please enter a realistic age between 1 and 129.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    private static String readValidEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (isValidEmail(input)) {
                return input;
            }
            System.out.println("Invalid email format (example: name@domain.com). Please try again.");
        }
    }

    private static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
