package com.portal.repository;

import com.portal.model.Developer;
import com.portal.model.Team;
import com.portal.repository.interfaces.TeamRepository;
import com.portal.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class TeamRepositoryImpl implements TeamRepository {
    @Override
    public List<Team> readAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Team> teams = session.createQuery("FROM Team ").getResultList();
        session.getTransaction().commit();
        session.close();
        return teams;
    }

    @Override
    public Team read(Integer integer) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Team team = session.get(Team.class, integer);
        session.getTransaction().commit();
        session.close();
        return team;
    }

    @Override
    public Team create(Team team) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(team);
        session.getTransaction().commit();
        session.close();
        return team;
    }

    @Override
    public Team update(Team team) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Team team1 = session.get(Team.class, team.getId());
        team1.setName(team.getName());
        team1.setDevelopers(team.getDevelopers());
        session.getTransaction().commit();
        session.close();
        return team;
    }

    @Override
    public void delete(Integer integer) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Team where id = " + integer).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
