package com.revature.services;

import com.revature.models.Employee;

import java.util.List;

public interface EmployeeService
{
    public Employee getEmployee(int id);
    public List<Integer> getHierarchy(int id);
    public List<Employee> getAllEmployees();
    public Employee addEmployee(Employee e);
    public Employee updateEmployee(Employee change);
    public Employee deleteEmployee(int id);
    public Employee login(String username, String password);
}
