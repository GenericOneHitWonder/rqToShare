/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;

import java.util.List;


/**
 * Methods exposed by the Dummy Sample Rest API webservice.
 */
public interface IDummySampleRestAPIService {

    /**
     * Retrieves a {@link List} of all {@link Employee}(s).
     *
     * @return {@link List} of all {@link Employee}(s).
     */
    List<Employee> getAllEmployees();

    /**
     * Retrieves an {@link Employee} using the provided 'id' parameter.
     *
     * @param id {@link Employee#id}
     * @return {@link Employee} found by {@link Employee#id}.
     */
    Employee getEmployeeById(String id);

    /**
     * Creates a new record of {@link Employee}.
     *
     * @param employee {@link Employee} record to persist.
     * @return Persisted {@link Employee}.
     */
    Employee createEmployee(Employee employee);

    /**
     * Deletes the record of an {@link Employee}, which is mapped to the provided 'id' parameter.
     *
     * @param id {@link Employee#id}
     */
    void deleteEmployee(String id);
}
