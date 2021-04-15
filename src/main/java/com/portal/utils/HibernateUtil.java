package com.portal.utils;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.model.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static  {
        sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Skill.class)
                .addAnnotatedClass(Developer.class)
                .addAnnotatedClass(Team.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
