package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Repository.AssigneesRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssigneesService {

	@Autowired
	private AssigneesRepository assigneesRepository;

//	 Assign a task to member
	public Assignee assignTaskToMember(int member_id, int task_id){

		Assignee assignee = new Assignee(0, member_id, task_id);

		return assigneesRepository.save(assignee);
	}
}
