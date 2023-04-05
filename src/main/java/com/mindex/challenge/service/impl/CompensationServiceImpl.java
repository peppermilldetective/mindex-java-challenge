package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

/**
 * The implementation of the {@link com.mindex.challenge.service.CompensationService CompensationService}
 * which is designed to act as the intermediary between the REST API and the db.
 */
@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Creates a new {@link com.mindex.challenge.data.Compensation Compensation}
     * and inserts it into the persistance layer.
     * <p />
     * @throws RuntimeException if <code>id</code> is null, <code>id</code> is
     * blank or doesn't exist in the database, if <code>salary</code> is 0.0,
     * and if the <code>effectiveDate</code> is null.
     */
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

    /**
     * Fetches the {@link com.mindex.challenge.data.Compensation Compensation}
     * for a given <code>employeeId</code>.
     * <p />
     * @throws RuntimeException if the <code>employeeId</code> is not in the
     * database.
     */
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
