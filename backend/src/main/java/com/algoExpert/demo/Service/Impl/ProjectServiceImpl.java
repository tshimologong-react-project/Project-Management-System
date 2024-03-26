package com.algoExpert.demo.Service.Impl;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.ProjectMapper;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.TableRepository;
import com.algoExpert.demo.Repository.UserRepository;
import com.algoExpert.demo.Service.MemberService;
import com.algoExpert.demo.Service.ProjectService;
import com.algoExpert.demo.Service.TableService;
import com.algoExpert.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TableService tableService;

    @Autowired
    private MemberService memberService;

    @Autowired
    ProjectMapper projectMapper;

    //  create project
    @Override
    public Integer createProject(Project project, int user_id) throws InvalidArgument {
        // Find user by id
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new InvalidArgument("User with ID " + user_id + " not found"));
        project.setUser(user);

        // Save the project and retrieve the saved instance
        Project savedProject = projectRepository.save(project);

        // save member
        Member newMember = memberService.inviteMember(savedProject.getProject_id(), user.getUser_id());

        // Create a default table using new member id
        tableService.createTable(savedProject.getProject_id(), newMember.getMember_id());

        // Save the updated project with the added member
        savedProject = projectRepository.save(savedProject);

        return savedProject.getProject_id();
    }

/*
* public Integer createProject(Project project, int user_id) throws InvalidArgument {
//        find user by id
        User user = userRepository.findById(user_id).orElseThrow(()->new InvalidArgument("User with ID " + user_id + " not found"));
//        Project project = projectMapper.projectDtoToProject(projectDto);
        project.setUser(user);
        Project savedProjects = projectRepository.save(project);

//        add owner to the project as a member
        List<Member> members = savedProjects.getMembersList();

        System.err.println(555555);
        Member newMember = new Member(0, user.getUser_id(), savedProjects.getProject_id(), null);
        members.add(newMember);
        members.forEach(System.err::println);
        project.setMembersList(members);

//        create a default table
        tableService.createTable(project.getProject_id(), user.getUser_id());

        Project savedProject = projectRepository.save(savedProjects);
        return savedProject.getProject_id();
    }*/

    //  get all projects
    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.projectDtos(projects);
    }

    //get one project
    @Override
    public ProjectDto findProject(int project_id) throws InvalidArgument {
        Project project = projectRepository.findById(project_id).orElseThrow(() -> new InvalidArgument("Project with ID " + project_id + " not found"));
        return projectMapper.projectToProjectDto(project);
    }

    //    delete project
    @Override
    public List<Project> deleteProjectById(Integer projectId) throws InvalidArgument {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new InvalidArgument("Project with ID " + projectId + " not found"));

        // Delete associated tables
        for (Table table : project.getTables()) {
            tableRepository.delete(table);
        }

        // Delete associated members
        for (Member member : project.getMemberList()) {
            memberRepository.delete(member);
        }
        // Now delete the project
        projectRepository.delete(project);

        return projectRepository.findAll();
    }

    //    update project
    @Override
    public ProjectDto editProject(ProjectDto projectDto, int project_id) throws InvalidArgument {

        Project project = projectRepository.findById(project_id).map(existingProject -> {
            if (projectDto != null) {
                Optional.ofNullable(projectDto.getTitle()).ifPresent(existingProject::setTitle);
                Optional.ofNullable(projectDto.getDescription()).ifPresent(existingProject::setDescription);
            }
            return projectRepository.save(existingProject);
        }).orElseThrow(() -> new InvalidArgument("Task with ID " + project_id + " not found"));

        return projectMapper.projectToProjectDto(project);

    }

//    public Project editProject(Project newProjectValue, int project_id) throws InvalidArgument{
//        return projectRepository.findById(project_id)
//                .map(existingProject -> {
//                    if (newProjectValue != null) {
//                        Optional.ofNullable(newProjectValue.getTitle()).ifPresent(existingProject::setTitle);
//                        Optional.ofNullable(newProjectValue.getDescription()).ifPresent(existingProject::setDescription);
//                    }
//                    return projectRepository.save(existingProject);
//                }).orElseThrow(() -> new InvalidArgument("Task with ID " + project_id + " not found"));
//
//    }

}