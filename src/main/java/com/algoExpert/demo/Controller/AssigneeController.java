package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Service.AssigneesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignee")
public class AssigneeController {

    @Autowired
    private AssigneesService assigneesService;

//    assign member to task using their IDs
    @PostMapping("/saveAssignee/{member_id}/{task_id}")
    private Assignee saveAssignee(@PathVariable int member_id, @PathVariable int task_id ){
        return assigneesService.assignTaskToMember(member_id, task_id);
    }
}
