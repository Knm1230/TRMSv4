package com.revature.repositories;

import com.revature.models.Employee;

import java.util.List;

public interface EmployeeRepo
{
    public Employee addEmployee(Employee e);
    public List<Employee> getAllEmployees();
    public Employee getEmployee(int id);
    public List<Integer> getHierarchy(int id);
    public Employee updateEmployee(Employee change);
    public Employee deleteEmployee(int id);
}
