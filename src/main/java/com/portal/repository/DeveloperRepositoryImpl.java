package com.portal.repository;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.repository.interfaces.DeveloperRepository;
import com.portal.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public List<Developer> readAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Developer> developers = session.createQuery("FROM Developer ").getResultList();
        session.getTransaction().commit();
        session.close();
        return developers;
    }

    @Override
    public Developer read(Integer integer) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Developer developer = session.get(Developer.class, integer);
        session.getTransaction().commit();
        session.close();
        return developer;
    }

    @Override
    public Developer create(Developer developer) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(developer);
        session.getTransaction().commit();
        session.close();
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Developer developer1 = session.get(Developer.class, developer.getId());
        developer1.setFirstName(developer.getFirstName());
        developer1.setLastName(developer.getLastName());
        developer1.setSkills(developer1.getSkills());
        session.getTransaction().commit();
        session.close();
        return developer;
    }

    @Override
    public void delete(Integer integer) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Developer where id = " + integer).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
