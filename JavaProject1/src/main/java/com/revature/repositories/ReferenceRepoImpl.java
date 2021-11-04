package com.revature.repositories;

import com.revature.models.EventType;
import com.revature.models.GradeFormat;
import com.revature.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class ReferenceRepoImpl implements ReferenceRepo
{
    @Override
    public List<EventType> getEvents()
    {
        Session session = HibernateUtil.getSession();
        List<EventType> eventTypes = null;
        try
        {
            eventTypes = session.createQuery("FROM EventType").list();
        }catch (HibernateException ex)
        {
            ex.printStackTrace();
        }finally
        {
            session.close();
        }
        return eventTypes;
    }

    @Override
    public List<GradeFormat> getGradeFormats()
    {
        Session session = HibernateUtil.getSession();
        List<GradeFormat> gradeFormats = null;
        try
        {
            gradeFormats = session.createQuery("From GradeFormat").list();
        }catch (HibernateException e)
        {
            e.printStackTrace();
        }finally
        {
            session.close();
        }
        return gradeFormats;
    }
}
