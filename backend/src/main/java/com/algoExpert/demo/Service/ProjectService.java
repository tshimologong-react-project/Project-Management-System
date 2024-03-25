package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    //  create project
    Integer createProject(Project project, int user_id) throws InvalidArgument;

    //  get all projects
    List<Project> getAllProjects();

    //get one project
    Project findProject(int project_id);

    // delete project by id
    @Transactional
    List<Project> deleteProjectById(Integer projectId) throws InvalidArgument;

    // update project
    Project editProject(Project newProjectValue, int project_id) throws InvalidArgument;

}