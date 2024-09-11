/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Service methods used in relation to {@link Employee}(s).
 */
public interface IEmployeeService {

    /**
     * Retrieves a {@link List} of all {@link Employee}(s).
     *
     * @return {@link List} of all {@link Employee}(s).
     * @throws IOException
     */
    List<Employee> getAllEmployees() throws IOException;

    /**
     * Retrieves a {@link List} of all {@link Employee}(s) whose {@link Employee#employee_name} matches
     * the provided 'searchString' parameter.
     *
     * @param searchString {@link String} compared to {@link Employee#employee_name}.
     * @return {@link List} of {@link Employee}(s) whose name matches parameter.
     */
    List<Employee> getEmployeesByNameSearch(String searchString);

    /**
     * Retrieves an {@link Employee} using the provided 'id' parameter.
     *
     * @param id {@link Employee#id}
     * @return {@link Employee} found by {@link Employee#id}.
     */
    Employee getEmployeeById(String id);

    /**
     * Retrieves the highest {@link Employee#employee_salary} value.
     *
     * @return Highest {@link Employee#employee_salary} value.
     */
    Integer getHighestSalaryOfEmployees();

    /**
     * Retrieves a {@link List} of the names of the 10 highest earning {@link Employee}(s).
     *
     * @return {@link List} of 10 highest earning {@link Employee}('s) names.
     */
    List<String> getTopTenHighestEarningEmployeeNames();

    /**
     * Creates a new record of {@link Employee}.
     *
     * @param employeeInput Data used to populate and create {@link Employee} record.
     * @return Newly created {@link Employee}.
     */
    Employee createEmployee(Map<String, Object> employeeInput);

    /**
     * Deletes the record of an {@link Employee}, which is mapped to the provided 'id' parameter.
     *
     * @param id {@link Employee#id}
     * @return {@link Employee#employee_name} of deleted {@link Employee}.
     */
    String deleteEmployeeById(String id);
}
