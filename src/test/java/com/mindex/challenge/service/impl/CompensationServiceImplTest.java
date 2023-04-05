package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compensationUrl;
    private String compensationIdUrl;

    @Autowired
    private CompensationService compService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateRead() {
        String johnLennonId     = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        String paulMcCartneyId  = "b7839309-3348-463b-a7e3-5de1c168beb3";
        String ringoStarrId     = "03aa1462-ffa9-4978-901b-7c001562cf6f";
        String peteBestId       = "62c1084e-6e34-4630-93fd-9153afb65309";
        String georgeHarrisonId = "c0c2293d-16bd-4603-8e08-638a9d18b22c";

        List<String> ids = List.of(
            johnLennonId,
            paulMcCartneyId,
            ringoStarrId,
            peteBestId,
            georgeHarrisonId
        );

        double johnSalary = 100000.0;
        double paulSalary = 202020.20;
        double ringoSalary = 314534.69;
        double peteSalary = 12.34;
        double georgeSalary = 0.99;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date johnEffective = new Date();
        Date paulEffective = new Date();
        Date ringoEffective = new Date();
        Date peteEffective = new Date();
        Date georgeEffective = new Date();

        try {
            paulEffective = formatter.parse("2021-05-02");
            ringoEffective = formatter.parse("1997-06-17");
            peteEffective = formatter.parse("1526-11-09");
            georgeEffective = formatter.parse("2077-08-30");
        } catch (ParseException e) {
            // Never happening...
            e.printStackTrace();
        }

        List<Compensation> t = List.of(
            new Compensation()
                .setEmployeeId(johnLennonId)
                .setSalary(johnSalary)
                .setEffectiveDate(johnEffective),
            new Compensation()
                .setEmployeeId(paulMcCartneyId)
                .setSalary(paulSalary)
                .setEffectiveDate(paulEffective),
            new Compensation()
                .setEmployeeId(ringoStarrId)
                .setSalary(ringoSalary)
                .setEffectiveDate(ringoEffective),
            new Compensation()
                .setEmployeeId(peteBestId)
                .setSalary(peteSalary)
                .setEffectiveDate(peteEffective),
            new Compensation()
                .setEmployeeId(georgeHarrisonId)
                .setSalary(georgeSalary)
                .setEffectiveDate(georgeEffective)
        );

        for (int i = 0; i < 5; i++) {
            Compensation created = restTemplate.postForEntity(compensationUrl, t.get(i), Compensation.class).getBody();
            assertTrue(areMatching(created, t.get(i)));
        }

        for (int i = 0; i < 5; i++) {
            Compensation read = restTemplate.getForEntity(compensationIdUrl, Compensation.class, ids.get(i)).getBody();
            assertTrue(areMatching(read, t.get(i)));
        }
    }

    private boolean areMatching(Compensation a, Compensation b) {
        boolean flag = true;

        if (!a.getEmployeeId().equalsIgnoreCase(b.getEmployeeId())) flag = false;
        if (a.getSalary() != b.getSalary()) flag = false;
        if (!a.getEffectiveDate().equals(b.getEffectiveDate())) flag = false;

        return flag;
    }
}
