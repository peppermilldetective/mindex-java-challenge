package com.mindex.challenge.data;

import java.util.Date;

/**
 * Represents a given employee's compensation represented as a double and a Date
 * indicating when the given salary starts affecting the employee.
 */
public class Compensation {
    public String employeeId;
    public double salary;
    public Date effectiveDate;

    public Compensation() {
        this.effectiveDate = new Date();
    }

    public Compensation setEmployeeId(String id) {
        this.employeeId = id;
        return this;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public Compensation setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    public double getSalary() {
        return this.salary;
    }

    public Compensation setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
}
