package com.gptclass.javacoreweek1;



abstract class Employee{
    protected int id;
    protected String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    abstract protected double calculateSalary();
    public void printPaySlip(){
        System.out.println("Employee name: "+name+"\nSalary: "+calculateSalary());
    }
}
class FullTimeEmployee extends Employee{
    private double monthlySalary;

    public FullTimeEmployee(double monthlySalary, String name,int id) {
        super(id,name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    protected double calculateSalary() {
        return monthlySalary;
    }
}
class PartTimeEmployee extends Employee{
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name,int hoursWorked, double hourlyRate,int id) {
        super(id,name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    protected double calculateSalary() {
        return hourlyRate*hoursWorked;
    }
}

public class EmployeeMain {
    public static void main(String[] args) {
        Employee emp1=new FullTimeEmployee(1234,"Rakesh",123);
        Employee emp2=new PartTimeEmployee("Rajesh",10,200,234);
        emp1.printPaySlip();
        emp2.printPaySlip();

    }
}
