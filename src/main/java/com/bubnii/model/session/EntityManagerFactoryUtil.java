package com.bubnii.model.session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtil {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerFactoryUtil(){
    }

    public static EntityManager getEntityManager(){
        if (entityManagerFactory == null){
            try {
               entityManagerFactory = Persistence.createEntityManagerFactory("University");
            }catch (Exception ex){
                throw new ExceptionInInitializerError(ex);
            }
        }
        return entityManagerFactory.createEntityManager();
    }
}
