package com.mindex.challenge.data;

/**
 * Used to represent a given employee's number of direct reports.
 */
public class ReportingStructure {
    private String employeeId;
    private int numberOfReports;

    public ReportingStructure() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public ReportingStructure setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    /**
     * Sets this instance's number of direct reports to the given amount.
     */
    public ReportingStructure setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
        return this;
    }

    /**
     * Adds the number of direct reports given to the total amount of
     * direct reports.
     */
    public ReportingStructure addDirectReportCount(int numberOfReports) {
        this.numberOfReports += numberOfReports;
        return this;
    }
}
