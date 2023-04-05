package com.mindex.challenge.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

/**
 * The implementation of the {@link com.mindex.challenge.service.ReportingStructureService ReportingStructureService}
 * which is designed to act as the itermediary between the REST API for the
 * "/directReport" endpoint and the backend db.
 * <p />
 * Does not commit anything to the database. It is only designed for fetching data.
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Using the given <code>employeeId</code>, fetches the employee and creates
     * a {@link com.mindex.challenge.data.ReportingStructure ReportingService}
     * containing the <code>employeeId</code> and the number of direct reports
     * said employee has.
     */
    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Fetching reporting structure for employee with id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employee ID: " + employeeId);
        }

        List<String> directReports =
            employee
                .getDirectReports();

        int numberOfDirectReports = directReports.size();

        while (directReports != null && directReports.size() > 0) {
            directReports = 
                directReports.stream()
                    .map(e_id -> employeeRepository.findByEmployeeId(e_id))
                    .flatMap(e -> e.getDirectReports().stream())
                    .collect(Collectors.toList());

            numberOfDirectReports += directReports.size();
        }

        ReportingStructure rs =
            new ReportingStructure()
                .setEmployeeId(employeeId)
                .setNumberOfReports(numberOfDirectReports);

        return rs;
    }
}