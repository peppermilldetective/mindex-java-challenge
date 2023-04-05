package com.mindex.challenge.data;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    // Changed because the service was not filling out the whole Employee, so no need to
    // send a full class when only the string was being used.
    private List<String> directReports;

    public Employee() {
        // Initialized to empty list to remove nulls.
        this.directReports = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<String> directReports) {
        this.directReports = directReports;
    }
}
