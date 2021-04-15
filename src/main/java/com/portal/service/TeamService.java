package com.portal.service;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.model.Team;
import com.portal.repository.DeveloperRepositoryImpl;
import com.portal.repository.TeamRepositoryImpl;
import com.portal.repository.interfaces.DeveloperRepository;
import com.portal.repository.interfaces.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    private TeamRepository repository;
    private DeveloperRepository developerRepository;

    public TeamService() {
        repository = new TeamRepositoryImpl();
        developerRepository = new DeveloperRepositoryImpl();
    }

    public TeamService(TeamRepository teamRepository, DeveloperRepository developerRepository) {
        repository = teamRepository;
        this.developerRepository = developerRepository;
    }

    public List<Team> readAll(){
        List<Team> teams = repository.readAll();
        for (Team team : teams){
            System.out.println(team);
        }
        return teams;
    }

    public Team read(String info){
        String id = info.replace("team read ", "");
        Team team = repository.read(Integer.parseInt(id));
        System.out.println(team);
        return team;
    }

    public Team create(String info){
        String[] data = info.replace("team create ", "").split(";");
        List<Developer> developers = new ArrayList<>();
        Team team = new Team(data[0]);
        for (String s : data[1].split(",")){
            Developer dev = developerRepository.read(Integer.parseInt(s));
            dev.setTeam(team);
            developers.add(dev);
        }
        team.setDevelopers(developers);
        repository.create(team);
        System.out.println(team);
        return team;
    }

    public Team update(String info){
        String[] data = info.replace("team update ", "").split(";");
        List<Developer> developers = new ArrayList<>();
        Team team = new Team(Integer.parseInt(data[0]), data[1]);
        for (String s : data[2].split(",")){
            Developer dev = developerRepository.read(Integer.parseInt(s));
            dev.setTeam(team);
            developers.add(dev);
        }
        team.setDevelopers(developers);
        repository.update(team);
        System.out.println(team);
        return team;
    }

    public void delete(String info){
        String id = info.replace("team delete ", "");
        repository.delete(Integer.parseInt(id));
        System.out.println(id + " deleted!");
    }

}
