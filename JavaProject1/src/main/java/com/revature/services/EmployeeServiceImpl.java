package com.revature.services;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeRepo;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService
{
    EmployeeRepo er;

    public EmployeeServiceImpl(EmployeeRepo er){this.er = er;}

    @Override
    public Employee getEmployee(int id)
    {
        return er.getEmployee(id);
    }

    @Override
    public List<Integer> getHierarchy(int id)
    {
        return er.getHierarchy(id);
    }

    @Override
    public List<Employee> getAllEmployees()
    {
        return er.getAllEmployees();
    }

    @Override
    public Employee addEmployee(Employee e)
    {
        return er.addEmployee(e);
    }

    @Override
    public Employee updateEmployee(Employee change)
    {
        return er.updateEmployee(change);
    }

    @Override
    public Employee deleteEmployee(int id)
    {
        return er.deleteEmployee(id);
    }

    @Override
    public Employee login(String username, String password)
    {
        List<Employee> employeeList = er.getAllEmployees();
        for(Employee e : employeeList)
        {
            if(e.getUserName().equals(username) && e.getPassWord().equals(password))
            {
                return e;
            }
        }
        return null;
    }
}
