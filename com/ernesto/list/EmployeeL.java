package com.ernesto.list;

import java.util.List;

public class EmployeeL {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private List certificates;

    public EmployeeL() {
    }

    public EmployeeL(String fName, String lName, int salary) {
        this(fName, lName, salary, null);
    }

    public EmployeeL(String fName, String lName, int salary, List certificates) {
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

    public List getCertificates() {
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

    public void setCertificates(List certificates) {
        this.certificates = certificates;
    }
}