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
import com.example.rqchallenge.employees.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Default implementation of {@link IEmployeeService}.
 */
@Service
public class EmployeeService implements IEmployeeService {

    private IDummySampleRestAPIService dummySampleRestAPIService;

    @Autowired
    public EmployeeService(final IDummySampleRestAPIService dummySampleRestAPIService) {
        this.dummySampleRestAPIService = dummySampleRestAPIService;
    }

    @Override
    public List<Employee> getAllEmployees() {

        return getDummySampleRestAPIService().getAllEmployees();
    }

    @Override
    public List<Employee> getEmployeesByNameSearch(final String searchString) {

        final List<Employee> allEmployees = getAllEmployees();
        return allEmployees.stream()
                .filter(employee -> employee.getEmployee_name().contains(searchString))
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(final String id) {

        return getDummySampleRestAPIService().getEmployeeById(id);
    }

    @Override
    public Integer getHighestSalaryOfEmployees() {

        final Optional<Employee> highestEarningEmployee = getAllEmployees().stream()
                .max(Comparator.comparing(Employee::getEmployee_salary));
        return highestEarningEmployee
                .map(Employee::getEmployee_salary)
                .orElse(0);
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {

        final List<Employee> allEmployees = getAllEmployees();
        allEmployees.sort((e1, e2) -> Integer.compare(e2.getEmployee_salary(), e1.getEmployee_salary()));

        final int newSize = Math.min(allEmployees.size(), 10);
        return allEmployees.subList(0, newSize).stream()
                .map(Employee::getEmployee_name)
                .collect(Collectors.toList());
    }

    @Override
    public Employee createEmployee(final Map<String, Object> employeeInput) {

        final String name = (String) employeeInput.get("name");
        final int salary = (Integer) employeeInput.get("salary");
        final int age = (Integer) employeeInput.get("age");
        final Employee employee = new Employee(name, salary, age);

        return getDummySampleRestAPIService().createEmployee(employee);
    }

    @Override
    public String deleteEmployeeById(final String id) {

        final Employee employeeToDelete = getEmployeeById(id);
        getDummySampleRestAPIService().deleteEmployee(id);
        return employeeToDelete.getEmployee_name();
    }

    protected IDummySampleRestAPIService getDummySampleRestAPIService() {
        return dummySampleRestAPIService;
    }

    public void setDummySampleRestAPIService(final IDummySampleRestAPIService dummySampleRestAPIService) {
        this.dummySampleRestAPIService = dummySampleRestAPIService;
    }
}
