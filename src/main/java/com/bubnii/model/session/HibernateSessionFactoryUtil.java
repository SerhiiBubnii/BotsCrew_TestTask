package com.bubnii.model.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(){
    }

    public static Session getSession(){
        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration();
                configuration.configure();
                sessionFactory = configuration.buildSessionFactory();
            }catch (Exception ex){
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory.openSession();
    }
}
