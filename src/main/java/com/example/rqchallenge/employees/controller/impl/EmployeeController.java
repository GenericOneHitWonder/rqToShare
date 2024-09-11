/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.example.rqchallenge.employees.controller.impl;

import com.example.rqchallenge.employees.controller.IEmployeeController;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Implementation of interface {@link IEmployeeController}.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController implements IEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        final List<Employee> allEmployees = getEmployeeService().getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(final String searchString) {
        final List<Employee> employees = getEmployeeService().getEmployeesByNameSearch(searchString);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(final String id) {
        final Employee employee = getEmployeeService().getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        final Integer maxSalary = getEmployeeService().getHighestSalaryOfEmployees();
        return new ResponseEntity<>(maxSalary, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        final List<String> employees = getEmployeeService().getTopTenHighestEarningEmployeeNames();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> createEmployee(final Map<String, Object> employeeInput) {
        final Employee employee = getEmployeeService().createEmployee(employeeInput);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(final String id) {
        final String deletedEmployeeName = getEmployeeService().deleteEmployeeById(id);
        return new ResponseEntity<>(deletedEmployeeName, HttpStatus.OK);
    }

    public IEmployeeService getEmployeeService() {
        return employeeService;
    }
}
