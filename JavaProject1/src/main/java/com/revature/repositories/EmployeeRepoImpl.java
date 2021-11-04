package com.revature.repositories;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepoImpl implements EmployeeRepo
{
    @Override
    public Employee addEmployee(Employee e)
    {
        Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            e.setId((int) session.save(e));
            session.getTransaction().commit();
        }catch(HibernateException ex)
        {
            ex.printStackTrace();
            session.getTransaction().rollback();
            e = null;
        }finally
        {
            session.close();
        }
        return e;
    }

    @Override
    public List<Employee> getAllEmployees()
    {
        Session session = HibernateUtil.getSession();
        List<Employee> employees = null;
        try
        {
            employees = session.createQuery("FROM Employee").list();
        }catch (HibernateException e)
        {
            e.printStackTrace();
        }finally
        {
            session.close();
        }
        return employees;
    }

    @Override
    public Employee getEmployee(int id)
    {
        Session session = HibernateUtil.getSession();
        Employee e = null;
        try
        {
            e = session.get(Employee.class, id);
        }catch (HibernateException ex)
        {
            ex.printStackTrace();
        }finally
        {
            session.close();
        }
        return e;
    }

    @Override
    public List<Integer> getHierarchy(int id)
    {
        Session session = HibernateUtil.getSession();
        List<Integer> employeeList = new ArrayList<>();
        Employee e = null;
        Integer f = null;
        Integer g = null;
        Integer h = null;
        try
        {
            e = session.get(Employee.class, id);
            f = e.getSupervisor();
            g = e.getDepHead();
            h = e.getBenCo();
            employeeList.add(f);
            employeeList.add(g);
            employeeList.add(h);
        }catch (HibernateException ex)
        {
            ex.printStackTrace();
        }finally
        {
            session.close();
        }
        return employeeList;
    }

    @Override
    public Employee updateEmployee(Employee change)
    {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.merge(change);
            tx.commit();
        }catch (HibernateException ex)
        {
            ex.printStackTrace();
            if(tx!=null)tx.rollback();
            return null;
        }finally
        {
            session.close();
        }
        return change;
    }

    @Override
    public Employee deleteEmployee(int id)
    {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Employee e;
        try
        {
            tx = session.beginTransaction();
            e = session.get(Employee.class,id);
            session.delete(e);
            tx.commit();
        }catch (HibernateException ex)
        {
            ex.printStackTrace();
            if(tx!=null) tx.rollback();
        }finally
        {
            session.close();
        }
        return null;
    }
}
