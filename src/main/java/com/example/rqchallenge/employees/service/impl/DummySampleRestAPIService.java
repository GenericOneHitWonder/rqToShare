/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.example.rqchallenge.employees.service.impl;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.IDummySampleRestAPIService;
import com.example.rqchallenge.employees.service.response.EmployeeListResponse;
import com.example.rqchallenge.employees.service.response.EmployeeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.rqchallenge.employees.EmployeesConstants.CREATE_EMPLOYEE_URL;
import static com.example.rqchallenge.employees.EmployeesConstants.DELETE_EMPLOYEE_URL;
import static com.example.rqchallenge.employees.EmployeesConstants.EMPLOYEES_URL;
import static com.example.rqchallenge.employees.EmployeesConstants.GET_ALL_EMPLOYEES_FALLBACK_DATA_JSON_FILE;


/**
 * Default implementation of {@link IDummySampleRestAPIService}.
 */
@Service
public class DummySampleRestAPIService implements IDummySampleRestAPIService {

    private static final Logger LOG = LoggerFactory.getLogger(DummySampleRestAPIService.class);

    private RestTemplate restTemplate;

    @Autowired
    public DummySampleRestAPIService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {

        try {

            final ResponseEntity<EmployeeListResponse> responseEntity =
                    getRestTemplate().getForEntity(EMPLOYEES_URL, EmployeeListResponse.class);
            final EmployeeListResponse response = responseEntity.getBody();
            return response.getData();
        } catch (final RestClientException ex) {

            LOG.error(ex.getLocalizedMessage(), ex);
            return getAllEmployeesFromFile();
        }
    }

    protected List<Employee> getAllEmployeesFromFile() {

        try {

            final String jsonString = getFallbackDataString(GET_ALL_EMPLOYEES_FALLBACK_DATA_JSON_FILE);
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, new TypeReference<List<Employee>>() {});
        } catch (final JsonProcessingException ex) {

            LOG.error(ex.getLocalizedMessage(), ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Employee getEmployeeById(final String id) {

        try {

            return getRestTemplate().getForObject(EMPLOYEES_URL + "/" + id, Employee.class);
        } catch (final RestClientException ex) {

            LOG.error(ex.getLocalizedMessage(), ex);

            final List<Employee> allEmployees = getAllEmployeesFromFile();
            final Optional<Employee> employeeOptional = allEmployees.stream()
                    .filter(employee -> id.equals(String.valueOf(employee.getId())))
                    .findAny();
            return employeeOptional.orElse(null);
        }
    }

    @Override
    public Employee createEmployee(final Employee employee) {

        try {

            final EmployeeResponse response =
                    getRestTemplate().postForObject(CREATE_EMPLOYEE_URL, employee, EmployeeResponse.class);
            return response.getData();
        } catch (final RestClientException ex) {

            LOG.error(ex.getLocalizedMessage(), ex);

            //Fallback
            employee.setId(Integer.MAX_VALUE);
            return employee;
        }
    }

    @Override
    public void deleteEmployee(final String id) {

        try {

            getRestTemplate().delete(DELETE_EMPLOYEE_URL + "/" + id);
        } catch (final RestClientException ex) {

            LOG.error(ex.getLocalizedMessage(), ex);
        }
    }

    private String getFallbackDataString(final String pathToData) {

        try {

            final Path path = Paths.get(pathToData);
            final StringBuilder stringBuilder = new StringBuilder();
            for (final String line : Files.readAllLines(path)) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (final IOException ex) {

            LOG.error(ex.getLocalizedMessage(), ex);
            //If there is an issue with the fallback data then application shouldn't continue
            throw new RuntimeException(ex);
        }
    }

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
