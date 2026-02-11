package com.javacoreweek2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DAY 8 — Java Streams
 * Topics covered:
 * 1. partitioningBy (binary grouping)
 * 2. groupingBy (multi-category grouping)
 * 3. groupingBy with downstream collectors (counting)
 *
 * This file is meant for REVISION and INTERVIEW PREP.
 *
 */


public class StreamsDay8_GroupingAndPartitioning {

    // Simple model class
    static class Student {
        private final int id;
        private final String name;
        private final int marks;

        public Student(int id, String name, int marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }

        public int getMarks() {
            return marks;
        }

        public String getName() {
            return name;
        }
    }



    public static void main(String[] args) {

        List<Student> students = List.of(
                new Student(1, "Rakesh", 95),
                new Student(2, "Rajesh", 78),
                new Student(3, "Ramesh", 42),
                new Student(4, "Bholu", 65),
                new Student(5, "Nitu", 88),
                new Student(6, "Badal", 35)
        );

        /* =========================================================
           1️⃣ PARTITIONING — YES / NO DECISION
           ========================================================= */

        // Split students into PASSED (true) and FAILED (false)
        Map<Boolean, List<Student>> partitionedByPassFail =
                students.stream()
                        .collect(Collectors.partitioningBy(
                                s -> s.getMarks() >= 50
                        ));

        // Accessing results (NO re-streaming)
        System.out.println("Passed students count  : " +
                partitionedByPassFail.get(true).size());
        System.out.println("Failed students count  : " +
                partitionedByPassFail.get(false).size());


        /* =========================================================
           2️⃣ GROUPING — MULTIPLE CATEGORIES
           ========================================================= */

        // Group students into LOW / MID / HIGH based on marks
        Map<String, List<Student>> groupedByCategory =
                students.stream()
                        .collect(Collectors.groupingBy(
                                s -> s.getMarks() > 80 ? "HIGH"
                                        : s.getMarks() >= 50 ? "MID"
                                        : "LOW"
                        ));

        // Access grouped lists
        System.out.println("HIGH students : " + groupedByCategory.get("HIGH"));
        System.out.println("MID students  : " + groupedByCategory.get("MID"));
        System.out.println("LOW students  : " + groupedByCategory.get("LOW"));


        /* =========================================================
           3️⃣ GROUPING + DOWNSTREAM COLLECTOR (COUNTING)
           ========================================================= */

        // Count students in each category directly during grouping
        Map<String, Long> countPerCategory =
                students.stream()
                        .collect(Collectors.groupingBy(
                                s -> s.getMarks() > 80 ? "HIGH"
                                        : s.getMarks() >= 50 ? "MID"
                                        : "LOW",
                                Collectors.counting()
                        ));

        // Access counts directly (Map<String, Long>)
        System.out.println("Count per category:");
        System.out.println("HIGH : " + countPerCategory.get("HIGH"));
        System.out.println("MID  : " + countPerCategory.get("MID"));
        System.out.println("LOW  : " + countPerCategory.get("LOW"));
    }
}
