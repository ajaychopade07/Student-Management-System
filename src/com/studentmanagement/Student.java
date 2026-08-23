package com.studentmanagement;

/**
 * Represents a student entity in the Student Management System.
 */
public class Student {
    private String studentId;
    private String name;
    private int age;
    private String course;
    private String email;

    // Constructor
    public Student(String studentId, String name, int age, String course, String email) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
        this.email = email;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Converts the Student object into a CSV string format for file storage.
     */
    public String toCsv() {
        return studentId + "," + name + "," + age + "," + course + "," + email;
    }

    /**
     * Creates a Student object from a CSV formatted string.
     */
    public static Student fromCsv(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            return null;
        }
        String[] parts = csvLine.split(",", -1);
        if (parts.length < 5) {
            return null;
        }
        try {
            String id = parts[0].trim();
            String name = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            String course = parts[3].trim();
            String email = parts[4].trim();
            return new Student(id, name, age, course, email);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %-8s | Name: %-18s | Age: %-3d | Course: %-15s | Email: %s",
                studentId, name, age, course, email);
    }
}
