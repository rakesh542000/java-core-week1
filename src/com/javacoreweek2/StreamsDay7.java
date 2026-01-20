package com.javacoreweek2;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private final int id;
    private final String name;
    private final int marks;

    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getMarks() { return marks; }

    @Override
    public String toString() {
        return name + " (" + marks + ")";
    }
}

public class StreamsDay7 {

    public static void main(String[] args) {

        List<Student> students = List.of(
                new Student(2380289, "Rakesh", 99),
                new Student(2380290, "Rajesh", 85),
                new Student(2380291, "Ramesh", 25),
                new Student(2380292, "Bholu", 65),
                new Student(2380288, "Nitu", 80),
                new Student(2380287, "Kandhei", 45),
                new Student(2380286, "Basanta", 70),
                new Student(2380285, "Badal", 50)
        );

        // Task 1: Print names of students scoring more than 60
        students.stream()
                .filter(s -> s.getMarks() > 60)
                .forEach(s -> System.out.println(s.getName() + " " + s.getMarks()));

        // Task 2: Extract names of all students
        List<String> studentNames =
                students.stream()
                        .map(Student::getName)
                        .collect(Collectors.toList());

        // Task 3: Names of students scoring more than 80
        List<String> toppers =
                students.stream()
                        .filter(s -> s.getMarks() > 80)
                        .map(Student::getName)
                        .collect(Collectors.toList());

        // Task 4: Count students scoring more than 50
        long countAbove50 =
                students.stream()
                        .filter(s -> s.getMarks() > 50)
                        .count();

        // Task 5: Collect students with marks between 50 and 80 (inclusive)
        List<Student> midScorers =
                students.stream()
                        .filter(s -> s.getMarks() >= 50 && s.getMarks() <= 80)
                        .collect(Collectors.toList());

        // Task 6A: Sort students by marks (ascending)
        List<Student> sortedAsc =
                students.stream()
                        .sorted(Comparator.comparing(Student::getMarks))
                        .collect(Collectors.toList());

        // Task 6B: Sort students by marks (descending)
        List<Student> sortedDesc =
                students.stream()
                        .sorted(Comparator.comparing(Student::getMarks).reversed())
                        .collect(Collectors.toList());

        // Task 7: Find first student scoring less than 50
        students.stream()
                .filter(s -> s.getMarks() < 50)
                .findFirst()
                .ifPresentOrElse(
                        s -> System.out.println("First low scorer: " + s.getName()),
                        () -> System.out.println("No low scorer found")
                );

        // Task 8: Match operations
        boolean allPassed = students.stream().allMatch(s -> s.getMarks() >= 35);
        boolean anyPerfectScore = students.stream().anyMatch(s -> s.getMarks() == 100);
        boolean noNegativeMarks = students.stream().noneMatch(s -> s.getMarks() < 0);

        // Task 9: Reduce — total marks of all students
        int totalMarks =
                students.stream()
                        .map(Student::getMarks)
                        .reduce(0, Integer::sum);

        // Optional prints for verification
        System.out.println("All passed: " + allPassed);
        System.out.println("Any perfect score: " + anyPerfectScore);
        System.out.println("No negative marks: " + noNegativeMarks);
        System.out.println("Total marks: " + totalMarks);
    }
}






