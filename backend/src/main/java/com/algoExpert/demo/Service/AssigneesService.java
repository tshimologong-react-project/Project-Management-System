package com.algoExpert.demo.Service;

import com.algoExpert.demo.Dto.AssigneeDto;
import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Mapper.AssigneeMapper;
import com.algoExpert.demo.Repository.AssigneesRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssigneesService {

	@Autowired
	private AssigneesRepository assigneesRepository;
	@Autowired
	AssigneeMapper assigneeMapper;

//	 Assign a task to member
	public AssigneeDto assignTaskToMember(int dtoMember_id, int dtoTask_id){
		AssigneeDto assigneeDto = new AssigneeDto(0,dtoMember_id,dtoTask_id);
		Assignee assignee = new Assignee(assigneeDto.getAssignee_id(), assigneeDto.getMember_id(), assigneeDto.getTask_id());
		Assignee assigneeResult = assigneesRepository.save(assignee);
		return assigneeMapper.assigneeToAssigneeDto(assigneeResult);
	}

//	get all assignees
	Assignee assignTaskToMember(int member_id, int task_id) throws InvalidArgument;

//  get all projects
	public List<AssigneeDto> getAllAssignees(){
		List<Assignee> assignees = assigneesRepository.findAll();
		return assigneeMapper.assigneeDtos(assignees);
	}
	List<Assignee> getAllAssignees();

//	get all assignees

}
