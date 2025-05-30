import java.util.*;

class Student {
    private String name;
    private String id;
    private Map<String, Double> courseGrades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.courseGrades = new HashMap<>();
    }

    public void addGrade(String course, double grade) {
        courseGrades.put(course, grade);
    }

    public double getAverageGrade() {
        if (courseGrades.isEmpty()) return 0.0;
        double total = 0;
        for (double grade : courseGrades.values()) {
            total += grade;
        }
        return total / courseGrades.size();
    }

    public void printGrades() {
        System.out.println("Student: " + name + " (ID: " + id + ")");
        for (Map.Entry<String, Double> entry : courseGrades.entrySet()) {
            System.out.println("  Course: " + entry.getKey() + ", Grade: " + entry.getValue());
        }
        System.out.println("  Average Grade: " + getAverageGrade());
        System.out.println();
    }

    public String getId() {
        return id;
    }
}

public class StudentGradeTracker {
    private static Map<String, Student> studentMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGrade();
                case 3 -> displayStudentGrades();
                case 4 -> running = false;
                default -> System.out.println("Invalid option, try again.");
            }
        }
        System.out.println("Exiting Student Grade Tracker.");
    }

    private static void printMenu() {
        System.out.println("\n--- Student Grade Tracker ---");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade");
        System.out.println("3. Display Student Grades");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        if (studentMap.containsKey(id)) {
            System.out.println("Student ID already exists.");
        } else {
            studentMap.put(id, new Student(name, id));
            System.out.println("Student added.");
        }
    }

    private static void addGrade() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        Student student = studentMap.get(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course name: ");
        String course = scanner.nextLine();
        System.out.print("Enter grade (0.0 - 100.0): ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        student.addGrade(course, grade);
        System.out.println("Grade added.");
    }

    private static void displayStudentGrades() {
        if (studentMap.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        for (Student student : studentMap.values()) {
            student.printGrades();
        }
    }
}