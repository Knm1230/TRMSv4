package com.revature.repositories;

import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RequestRepoImpl implements RequestRepo
{
    @Override
    public Request addRequest(Request r)
    {
        Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            r.setId((int) session.save(r));
            session.getTransaction().commit();
        }catch(HibernateException ex)
        {
            ex.printStackTrace();
            session.getTransaction().rollback();
            r = null;
        }finally
        {
            session.close();
        }
        return r;
    }

    @Override
    public List<Request> getRequestsByEmpId(int id)
    {
        Session session = HibernateUtil.getSession();
        List<Request> requests = null;
        try
        {
            requests = session.createQuery("FROM Request Where empId = " + id).list();
        }catch (HibernateException e)
        {
            e.printStackTrace();
        }finally
        {
            session.close();
        }
        return requests;
    }

    @Override
    public List<Request> getDeskByEmpId(int id)
    {
        Session session = HibernateUtil.getSession();
        List<Request> requests = null;
        try
        {
            requests = session.createQuery("FROM Request Where currentDesk = " + id).list();

        }catch (HibernateException e)
        {
            e.printStackTrace();
        }finally
        {
            session.close();
        }
        return requests;
    }

    @Override
    public Request getRequest(int id)
    {
        Session session = HibernateUtil.getSession();
        Request r = null;
        try
        {
            r = session.get(Request.class, id);
        }catch (HibernateException ex)
        {
            ex.printStackTrace();
        }finally
        {
            session.close();
        }
        return r;
    }

    @Override
    public Request updateRequest(Request change)
    {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.update(change);
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
    public Request deleteRequest(int id)
    {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Request r;
        try
        {
            tx = session.beginTransaction();
            r = session.get(Request.class,id);
            session.delete(r);
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
