package com.ernesto;

import java.util.SortedSet;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private SortedSet certificates;

    public Employee() {
    }

    public Employee(String fName, String lName, int salary) {
        this(fName, lName, salary, null);
    }

    public Employee(String fName, String lName, int salary, SortedSet certificates) {
        this.firstName = fName;
        this.lastName = lName;
        this.salary = salary;
        this.certificates = certificates;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public SortedSet getCertificates() {
        return certificates;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCertificates(SortedSet certificates) {
        this.certificates = certificates;
    }
}