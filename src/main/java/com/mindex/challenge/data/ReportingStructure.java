package com.mindex.challenge.data;

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

    public ReportingStructure setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
        return this;
    }

    /*
     * Adds the number of direct reports given to the total amount of
     * direct reports.
     */
    public ReportingStructure addDirectReportCount(int numberOfReports) {
        this.numberOfReports += numberOfReports;
        return this;
    }
}
