package com.portal.service;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.repository.DeveloperRepositoryImpl;
import com.portal.repository.SkillRepositoryImpl;
import com.portal.repository.TeamRepositoryImpl;
import com.portal.repository.interfaces.DeveloperRepository;
import com.portal.repository.interfaces.SkillRepository;
import com.portal.repository.interfaces.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class DeveloperService {

    private DeveloperRepository repository;
    private SkillRepository skillRepository;

    public DeveloperService() {
        this.repository = new DeveloperRepositoryImpl();
        this.skillRepository = new SkillRepositoryImpl();
    }

    public DeveloperService(DeveloperRepository repository, SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
        this.repository = repository;
    }

    public List<Developer> readAll(){
        List<Developer> developers = repository.readAll();
        for (Developer developer : developers){
            System.out.println(developer);
        }
        return developers;
    }

    public Developer read(String info){
        String id = info.replace("developer read ", "");
        Developer developer = repository.read(Integer.parseInt(id));
        System.out.println(developer);
        return developer;
    }

    public Developer create(String info){
        String[] data = info.replace("developer create ", "").split(";");
        List<Skill> skills = new ArrayList<>();
        for (String s : data[2].split(",")){
            skills.add(skillRepository.read(Integer.parseInt(s)));
        }
        //Developer developer = repository.create(new Developer(0, data[0], data[1], skills, teamRepository.read(Integer.parseInt(data[3]))));
        Developer developer = repository.create(new Developer(0, data[0], data[1], skills));
        System.out.println(developer);
        return developer;
    }

    public Developer update(String info){
        String[] data = info.replace("developer update ", "").split(";");
        List<Skill> skills = new ArrayList<>();
        for (String s : data[3].split(",")){
            skills.add(skillRepository.read(Integer.parseInt(s)));
        }
        Developer developer = repository.update(new Developer(Integer.parseInt(data[0]), data[1], data[2], skills));
        System.out.println(developer);
        return developer;
    }

    public void delete(String info){
        String id = info.replace("developer delete ", "");
        repository.delete(Integer.parseInt(id));
        System.out.println(id + " deleted!");
    }
}
