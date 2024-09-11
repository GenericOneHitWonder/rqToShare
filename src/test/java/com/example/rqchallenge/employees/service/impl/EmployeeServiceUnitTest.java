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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class EmployeeServiceUnitTest {

    private static final String SEARCH_TERM = "ric";

    private IEmployeeService employeeService;
    private IDummySampleRestAPIService dummySampleRestAPIService;

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;

    @BeforeEach
    void setUp() {

        dummySampleRestAPIService = Mockito.mock(IDummySampleRestAPIService.class);
        employeeService = new EmployeeService(dummySampleRestAPIService);

        employee1 = new Employee(17, "Paul Byrd", 725000, 33);
        employee2 = new Employee(4, "Cedric Kelly", 234567, 44);
        employee3 = new Employee(15, "Tatyana Fitzpatrick", 345678, 55);

        final List<Employee> allEmployees = new ArrayList<>();
        allEmployees.add(employee1);
        allEmployees.add(employee2);
        allEmployees.add(employee3);

        when(dummySampleRestAPIService.getAllEmployees()).thenReturn(allEmployees);
        when(dummySampleRestAPIService.getEmployeeById("15")).thenReturn(employee3);
    }

    @Test
    public void testGetAllEmployees() throws IOException {

        final List<Employee> allEmployees = employeeService.getAllEmployees();
        assertEquals(3, allEmployees.size());
    }

    @Test
    public void testGetEmployeesByNameSearch() {
        final List<Employee> searchResult = employeeService.getEmployeesByNameSearch(SEARCH_TERM);
        assertEquals(2, searchResult.size());
    }

    @Test
    public void testGetEmployeeById() {
        final Employee employee = employeeService.getEmployeeById("15");
        assertEquals("Tatyana Fitzpatrick", employee.getEmployee_name());
        assertEquals(345678, employee.getEmployee_salary());
        assertEquals(55, employee.getEmployee_age());
    }

    @Test
    public void testGetHighestSalaryOfEmployees() {
        final Integer highestSalary = employeeService.getHighestSalaryOfEmployees();
        assertEquals(725000, highestSalary);
    }

    @Test
    public void testGetTopTenHighestEarningEmployeeNames() {
        final List<String> highestEarnersNames = employeeService.getTopTenHighestEarningEmployeeNames();
        assertEquals(3, highestEarnersNames.size());
        assertTrue(highestEarnersNames.contains("Paul Byrd"));
        assertTrue(highestEarnersNames.contains("Tatyana Fitzpatrick"));
    }

    @Test
    public void testCreateEmployee() {
        final Map<String, Object> employeeData = Map.of("name", "John Doe", "salary", 123456, "age", 55);
        doAnswer(invocation -> {
            final Employee employee = invocation.getArgument(0);
            employee.setId(Integer.MIN_VALUE);
            return employee;
        }).when(dummySampleRestAPIService).createEmployee(any(Employee.class));

        final Employee createdEmployee = employeeService.createEmployee(employeeData);
        assertEquals(Integer.MIN_VALUE, createdEmployee.getId());
    }

    @Test
    public void testDeleteEmployeeById() {
        final String employeeName = employeeService.deleteEmployeeById("15");
        verify(dummySampleRestAPIService, times(1)).deleteEmployee("15");
        assertEquals("Tatyana Fitzpatrick", employeeName);
    }
}