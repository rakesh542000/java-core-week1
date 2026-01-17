package com.gptclass.javacoreweek1;

import java.util.*;

class Student{
    private int id;

    public String getName() {
        return name;
    }



    public int getId() {
        return id;
    }

    

    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }



}
class User{
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class StudentService{
    Student findStudentById(List<Student> students, int id1){
        for(Student student: students){
            if(student.getId()==id1)
                return student;
        }
        return null;

    }
}
class Box<T>{
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}


public class Collection_GenericsDay4 {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(
                new Student(123,"Rakesh")
        );
        students.add(
                new Student(123,"Rakesh")
        );
        students.add(
                new Student(423,"Rajesh")
        );
        students.add(
                new Student(123,"Litu")
        );
        students.add(
                new Student(415,"Badal")
        );
        for (Student student1:students){
            System.out.println("Id: "+student1.getId()+"\nName: "+student1.getName());
        }
        StudentService studentService = new StudentService();
        Student student2 = studentService.findStudentById(students,123);
        System.out.println("Student id is: "+student2.getId()+"Student Name is: "+student2.getName());

        Set<String> email=new HashSet<>();
        email.add("rakesh@gmail.com");
        email.add("rajeshpradhan@gmail.com");
        email.add("rakesh@gmail.com"); //testing if set is taking duplicate value or not
        email.add("pradhanrakeshkumar90@gmail.com");
        for (String emails:email){
            System.out.println(emails); //print to prove that
        }

        Map<Integer,User> userList = new HashMap<Integer,User>();
        userList.put(1,new User(2380289,"Rakesh"));
        userList.put(2,new User(2380290,"Rajesh"));
        userList.put(3,new User(2380291,"Chintu"));
        userList.put(4,new User(2380292,"Litu"));
        System.out.println("id is: "+userList.get(2).id+ "\n" +
                "Name is: " +userList.get(2).name);


        Box<String> stringBox = new Box<String>("Cognizant");
        System.out.println(stringBox.getValue());
        Box<Integer> integerBox = new Box<>(78);
        integerBox.setValue(78529);
        System.out.println(integerBox.getValue());




    }
}
