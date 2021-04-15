package com.portal.repository;

import com.portal.model.Skill;
import com.portal.repository.interfaces.SkillRepository;
import com.portal.utils.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {
    @Override
    public List<Skill> readAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Skill> skills = session.createQuery("FROM Skill").getResultList();
        session.getTransaction().commit();
        session.close();
        return skills;
    }

    @Override
    public Skill read(Integer integer) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Skill skill = session.get(Skill.class, integer);
        session.getTransaction().commit();
        session.close();
        return skill;
    }

    @Override
    public Skill create(Skill skill) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(skill);
        session.getTransaction().commit();
        session.close();
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Skill skill1 = session.get(Skill.class, skill.getId());
        skill1.setName(skill.getName());
        session.getTransaction().commit();
        session.close();
        return skill;
    }

    @Override
    public void delete(Integer integer) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Skill where id = " + integer).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
