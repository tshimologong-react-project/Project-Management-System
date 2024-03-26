package com.algoExpert.demo.Service.Impl;

import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.AssigneesRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import com.algoExpert.demo.Service.AssigneesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneesServiceImpl implements AssigneesService {

    @Autowired
    private AssigneesRepository assigneesRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskRepository taskRepository;

//	 Assign a task to member
    @Override
    public Assignee assignTaskToMember(int member_id, int task_id) throws InvalidArgument {
        // check if member and task exist
        Task storedTask = taskRepository.findById(task_id).orElseThrow(()->
                new InvalidArgument("Task with ID " + task_id + " not found"));
        Member member = memberRepository.findById(member_id).orElseThrow(()->
                new InvalidArgument("Member wth ID "+member_id+" not found"));

        Assignee assignee = new Assignee(0, member.getMember_id(), storedTask.getTask_id());

        return assigneesRepository.save(assignee);
    }

//	get all assignees
    @Override
    public List<Assignee> getAllAssignees(){return assigneesRepository.findAll(); }

}

