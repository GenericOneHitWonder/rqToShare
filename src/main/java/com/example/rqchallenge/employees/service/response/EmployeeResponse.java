/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.example.rqchallenge.employees.service.response;

import com.example.rqchallenge.employees.model.Employee;


/**
 * Object to handle response for calls to Dummy REST API Example webservice, which return a single {@link Employee}.
 */
public class EmployeeResponse {

    private String status;
    private Employee data;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Employee getData() {
        return data;
    }

    public void setData(final Employee data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
