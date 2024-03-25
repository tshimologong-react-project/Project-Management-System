package com.algoExpert.demo.Service.Impl;

import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Repository.AssigneesRepository;
import com.algoExpert.demo.Service.AssigneesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneesServiceImpl implements AssigneesService {

    @Autowired
    private AssigneesRepository assigneesRepository;

//	 Assign a task to member
    @Override
    public Assignee assignTaskToMember(int member_id, int task_id){

        Assignee assignee = new Assignee(0, member_id, task_id);

        return assigneesRepository.save(assignee);
    }

//  get all projects
    @Override
    public List<Assignee> getAllAssignees(){return assigneesRepository.findAll(); }

//	get all assignees

}

