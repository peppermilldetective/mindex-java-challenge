package com.mindex.challenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    private String reportingIdUrl;

    @Autowired
    private ReportingStructureService reportingService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        this.reportingIdUrl = "http://localhost:" + this.port + "/directReports/{id}";
    }

    @Test
    public void testRead() {
        String johnLennonId     = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        String paulMcCartneyId  = "b7839309-3348-463b-a7e3-5de1c168beb3";
        String ringoStarrId     = "03aa1462-ffa9-4978-901b-7c001562cf6f";
        String peteBestId       = "62c1084e-6e34-4630-93fd-9153afb65309";
        String georgeHarrisonId = "c0c2293d-16bd-4603-8e08-638a9d18b22c";

        List<String> employeeIds = List.of(
            johnLennonId,
            paulMcCartneyId,
            ringoStarrId,
            peteBestId,
            georgeHarrisonId
        );

        List<ReportingStructure> directReports = List.of(
            new ReportingStructure()
                .setEmployeeId(johnLennonId)
                .setNumberOfReports(4),
            new ReportingStructure()
                .setEmployeeId(paulMcCartneyId)
                .setNumberOfReports(0),
            new ReportingStructure()
                .setEmployeeId(ringoStarrId)
                .setNumberOfReports(2),
            new ReportingStructure()
                .setEmployeeId(peteBestId)
                .setNumberOfReports(0),
            new ReportingStructure()
                .setEmployeeId(georgeHarrisonId)
                .setNumberOfReports(0)
        );

        for (int i = 0; i < 5; i++) {
            ReportingStructure s = restTemplate.getForEntity(this.reportingIdUrl, ReportingStructure.class, employeeIds.get(i)).getBody();
            assertNotNull(s);
            assertEquals(s.getEmployeeId(), directReports.get(i).getEmployeeId());
            assertEquals(s.getNumberOfReports(), directReports.get(i).getNumberOfReports());
        }
    }
}
