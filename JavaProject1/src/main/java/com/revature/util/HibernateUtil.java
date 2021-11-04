package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static SessionFactory sf = cfgWithPath(new Configuration().configure());

    public static SessionFactory cfgWithPath(Configuration cfg)
    {
        SessionFactory sf;

        cfg.setProperty("hibernate.connection.username", System.getenv("AWS_DB_UNAME"));
        cfg.setProperty("hibernate.connection.password", System.getenv("AWS_DB_PWORD"));

        sf = cfg.buildSessionFactory();
        return sf;
    }

    public static Session getSession()
    {
        return sf.openSession();
    }
}
