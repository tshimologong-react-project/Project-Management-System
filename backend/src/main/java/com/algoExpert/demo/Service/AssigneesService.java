package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.Assignee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssigneesService {

//	 Assign a task to member
	Assignee assignTaskToMember(int member_id, int task_id);

//  get all projects
	List<Assignee> getAllAssignees();

//	get all assignees

}
