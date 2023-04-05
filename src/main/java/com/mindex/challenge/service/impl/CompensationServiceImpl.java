package com.mindex.challenge.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation create(Compensation comp) {
        LOG.debug("Creating compensation [{}]", comp);

        String id = comp.getEmployeeId();

        if (id == null) {
            throw new RuntimeException("Employee ID must not be null");
        }

        if (id.isBlank() || employeeRepository.findByEmployeeId(id) == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        if (comp.salary == 0.0) {
            throw new RuntimeException("Salary must be >0");
        }

        if (comp.effectiveDate == null) {
            throw new RuntimeException("Effective Date must not be null");
        }

        compRepository.insert(comp);

        return comp;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Fetching compensation with id [{}]", id);

        Compensation comp = compRepository.findByEmployeeId(id);

        if (comp == null) {
            throw new RuntimeException("Invalid employee id: " + id);
        }

        return comp;
    }
}
