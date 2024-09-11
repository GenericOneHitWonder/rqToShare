/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.example.rqchallenge.employees;

/**
 * Constants for application.
 */
public class EmployeesConstants {

    //Dummy REST API Example Constants
    public static String BASE_URL = "https://dummy.restapiexample.com/api/v1";
    public static String EMPLOYEES_URL = BASE_URL + "/employees";
    public static String CREATE_EMPLOYEE_URL = BASE_URL + "/create";
    public static String DELETE_EMPLOYEE_URL = BASE_URL + "/delete";

    //Fallback Paths
    public static final String GET_ALL_EMPLOYEES_FALLBACK_DATA_JSON_FILE = "src/main/resources/fallback_responses/getAllEmployees_data.json";
}
