package com.ernesto.many_to_one;

import com.ernesto.many_to_one.Address;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private Address address;

    public Employee() {
    }

    public Employee(String fName, String lName, int salary) {
        this(fName, lName, salary, null);
    }

    public Employee(String fName, String lName, int salary, Address address) {
        this.firstName = fName;
        this.lastName = lName;
        this.salary = salary;
        this.address = address;
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

    public Address getAddress() {
        return address;
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

    public void setAddress(Address address) {
        this.address = address;
    }
}