package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

/**
 * The REST API controller for the "/directReports" endpoint.
 */
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService rsService;

    /**
     * Fetches the {@link com.mindex.challenge.data.ReportingStructure ReportingStructure} for
     * the given employee from the {@link com.mindex.challenge.service.impl.ReportingStructureServiceImpl ReportingStructureService}.
     * <p>
     * Endpoint: "/directReports/{id}"
     * <p>
     * Method: GET
     */
    @GetMapping("/directReports/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received direct report create request for id [{}]", id);

        return rsService.read(id);
    }
}
