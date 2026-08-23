package com.studentmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all business logic and CRUD operations for students.
 */
public class StudentManager {
    private final List<Student> students;
    private final String filePath;

    public StudentManager() {
        this("data/students.txt");
    }

    public StudentManager(String filePath) {
        this.students = new ArrayList<>();
        this.filePath = filePath;
        loadFromFile();
    }

    /**
     * Adds a new student if the ID is not already taken.
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }

        // Check if student with same ID already exists
        if (searchStudent(student.getStudentId()) != null) {
            return false;
        }

        students.add(student);
        saveToFile();
        return true;
    }

    /**
     * Displays all students in a formatted table.
     */
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo student records found.");
            return;
        }

        System.out.println("\n--------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-20s | %-5s | %-20s | %-25s%n", "ID", "Name", "Age", "Course", "Email");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("%-10s | %-20s | %-5d | %-20s | %-25s%n",
                    student.getStudentId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getEmail());
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Total: " + students.size() + " student(s) displayed.");
    }

    /**
     * Searches for a student by ID.
     */
    public Student searchStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return null;
        }

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId.trim())) {
                return student;
            }
        }
        return null;
    }

    /**
     * Updates an existing student's details.
     */
    public boolean updateStudent(String studentId, String newName, int newAge, String newCourse, String newEmail) {
        Student student = searchStudent(studentId);
        if (student == null) {
            return false;
        }

        if (newName != null && !newName.trim().isEmpty()) {
            student.setName(newName.trim());
        }
        if (newAge > 0) {
            student.setAge(newAge);
        }
        if (newCourse != null && !newCourse.trim().isEmpty()) {
            student.setCourse(newCourse.trim());
        }
        if (newEmail != null && !newEmail.trim().isEmpty()) {
            student.setEmail(newEmail.trim());
        }

        saveToFile();
        return true;
    }

    /**
     * Deletes a student by ID.
     */
    public boolean deleteStudent(String studentId) {
        Student student = searchStudent(studentId);
        if (student == null) {
            return false;
        }

        students.remove(student);
        saveToFile();
        return true;
    }

    /**
     * Returns the total count of registered students.
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Returns an unmodifiable list or copy of all students.
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Saves all student records to the data file.
     */
    public void saveToFile() {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                writer.write(student.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving student data to file: " + e.getMessage());
        }
    }

    /**
     * Loads student records from the data file.
     */
    public void loadFromFile() {
        students.clear();
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = Student.fromCsv(line);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading student data from file: " + e.getMessage());
        }
    }
}
