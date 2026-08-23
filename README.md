# Student Management System

A simple, clean, and beginner-friendly Java console application for managing student records using pure Core Java (Java 17+)
first project for the github

---

## 📌 Features

- **Add Student**: Register new students with validation for unique ID, valid age, non-empty fields, and email format.
- **View All Students**: Display all registered students in a formatted tabular layout.
- **Search Student**: Lookup student details by ID.
- **Update Student**: Modify student information (Name, Age, Course, Email) with support for keeping existing values.
- **Delete Student**: Remove a student record with confirmation safety.
- **Total Students**: View the total count of registered students.
- **File-Based Storage**: Automatic loading from and saving to `data/students.txt`.
- **Input Validation**: Resilient against invalid input and prevents application crashes.

---

## 🛠️ Technologies Used

- **Language**: Java 17+ (Core Java)
- **Concepts Applied**:
  - Object-Oriented Programming (Encapsulation, Classes & Objects)
  - Java Collections Framework (`ArrayList`, `List`)
  - File I/O Handling (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`)
  - Robust Exception Handling (`try-catch`, `try-with-resources`)
  - Regex & Input Validation

---

## 📂 Project Structure

```text
Student-Management-System/
│
├── src/
│   └── com/
│       └── studentmanagement/
│           ├── Main.java
│           ├── Student.java
│           └── StudentManager.java
│
├── data/
│   └── students.txt
│
├── README.md
└── .gitignore
```

---

## 🚀 How to Run

### Prerequisites
- **Java Development Kit (JDK 17 or higher)** installed.
- Verify your installation:
  ```bash
  java -version
  javac -version
  ```

### Step 1: Compile the Project

From the project root directory, run:

```bash
javac -d bin src/com/studentmanagement/*.java
```

### Step 2: Run the Application

Run the compiled classes using:

```bash
java -cp bin com.studentmanagement.Main
```

---

## 🖥️ Example Application Output

```text
=====================================
    STUDENT MANAGEMENT SYSTEM
=====================================
1. Add Student
2. View Students
3. Search Student
4. Update Student
5. Delete Student
6. Total Students
7. Exit
=====================================
Enter your choice: 2

--------------------------------------------------------------------------------------------------
ID         | Name                 | Age   | Course               | Email                    
--------------------------------------------------------------------------------------------------
STU101     | John Doe             | 20    | Computer Science     | john.doe@example.com     
STU102     | Jane Smith           | 22    | Information Tech     | jane.smith@example.com   
STU103     | Alex Johnson         | 21    | Data Science         | alex.j@example.com       
--------------------------------------------------------------------------------------------------
Total: 3 student(s) displayed.
```

---

## 📦 Pushing to GitHub

To initialize Git and push this repository to GitHub, run the following commands in the project root:

```bash
# 1. Initialize a new Git repository
git init

# 2. Add all files (the .gitignore will exclude compiled binaries)
git add .

# 3. Commit the files
git commit -m "Initial commit: Student Management System in Core Java"

# 4. Set main branch
git branch -M main

# 5. Link to your remote GitHub repository
git remote add origin https://github.com/ajaychopade07/Student-Management-System.git

# 6. Push to GitHub
git push -u origin main
```

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
