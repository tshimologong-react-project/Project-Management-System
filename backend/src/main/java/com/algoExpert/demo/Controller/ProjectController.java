package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

//  create project using user id
    @PostMapping("/createProject/{user_id}")
    public Integer createProject(@RequestBody Project project, @PathVariable int user_id) throws InvalidArgument{
        return projectService.createProject(project,user_id);
    }

//    get all projects
    @GetMapping("/findAllProject")
    public List<ProjectDto> getAllProject(){
        return projectService.getAllProjects();
    }

    @GetMapping("/getSingleProject/{project_id}")
    public ProjectDto getSingleProject(@PathVariable int project_id) throws InvalidArgument{
        return projectService.findProject(project_id);
    }
    @DeleteMapping("/deleteProject/{project_id}")
    public List<Project> deleteProject(@PathVariable int project_id) throws InvalidArgument{
        return projectService.deleteProjectById(project_id);
    }

    @PutMapping("/editProject/{project_id}")
    public ProjectDto deleteProject(@RequestBody ProjectDto project,@PathVariable int project_id) throws InvalidArgument{
        return projectService.editProject(project,project_id);
    }

//    @GetMapping("/fetchUserProject/{user_id}")
//    public List<Project> d(@PathVariable int user_id) throws InvalidArgument {
//        return projectService.getProjectByUserId(user_id);
//    }

}
