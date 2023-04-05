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

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Fetching reporting structure for employee with id [{}]", id);

        List<String> directReports =
            employeeRepository
                .findByEmployeeId(id)
                .getDirectReports();

        int numberOfDirectReports = directReports.size();

        while (directReports != null && directReports.size() > 0) {
            directReports = 
                directReports.stream()
                    .map(e_id -> employeeRepository.findByEmployeeId(e_id))
                    .flatMap(employee -> employee.getDirectReports().stream())
                    .collect(Collectors.toList());

            numberOfDirectReports += directReports.size();
        }

        ReportingStructure rs =
            new ReportingStructure()
                .setEmployeeId(id)
                .setNumberOfReports(numberOfDirectReports);

        return rs;
    }
}