package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

/**
 * The REST API controller for the "/compensation" endpoint.
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compService;

    /**
     * Creates a new {@link com.mindex.challenge.data.Compensation Compensation} using the
     * given input {@link com.mindex.challenge.data.Compensation Compensation} retrieved
     * from the request's body.
     * <p>
     * Endpoint: "/compensation"
     * <p>
     * Method: POST
     */
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return compService.create(compensation);
    }

    /**
     * Fetches an employee's {@link com.mindex.challenge.data.Compensation Compensation}
     * using the given <code>employeeId</code>.
     * <p>
     * Endpoint: "/compensation/{id}"
     * <p>
     * Method: GET
     */
    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String employeeId) {
        LOG.debug("Received compensation GET request for id [{}]", employeeId);

        return compService.read(employeeId);
    }
}
