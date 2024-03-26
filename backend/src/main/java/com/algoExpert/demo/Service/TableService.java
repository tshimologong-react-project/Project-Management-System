package com.algoExpert.demo.Service;
import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Dto.TableDto;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.Table;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.ProjectMapper;
import com.algoExpert.demo.Mapper.TableMapper;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.TableRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TableMapper tableMapper;
    @Autowired
    ProjectMapper projectMapper;

//  create a new table
    public ProjectDto createTable(int project_id, int member_id) throws InvalidArgument{
        Project project =  projectRepository.findById(project_id).orElseThrow(()->new InvalidArgument("Project with ID " + project_id + " not found"));

        List<Table> tables = project.getTables();
        int count = project.getTables().size();
        Table table =new Table(0,"Table"+ count,null);
        Task task=new Task(0,"task","description"
                ,member_id,"","","","",null);


        tables.add(table);
        project.setTables(tables);
        List<Task> taskList=new ArrayList<>();
        taskList.add(task);
        table.setTasks(taskList);

        Project projectResult = projectRepository.save(project);
        return projectMapper.projectToProjectDto(projectResult);
    }
    @Transactional
    public List<TableDto> deleteTable(Integer project_id, Integer table_id) throws InvalidArgument{
        Project project = projectRepository.findById(project_id).orElseThrow(()->new InvalidArgument("Project with ID " + project_id + " not found"));
        Table table = tableRepository.findById(table_id).orElseThrow(()->new InvalidArgument("Table with ID " + table_id + " not found"));

        List<Table> tablesList = project.getTables();
        tablesList.remove(table);
        project.setTables(tablesList);
        projectRepository.save(project);
        List<Table> tables = tableRepository.findAll();
        return tableMapper.tableDtos(tables);
    }

//  get all tables
    public List<TableDto> getAllTables() {
        List<Table> tables = tableRepository.findAll();
        return tableMapper.tableDtos(tables);
    }



    public TableDto editTable(TableDto newTableValue,int table_id) throws InvalidArgument {
        Table table = tableRepository.findById(table_id)
                .map(existingTable->{
                    if(newTableValue !=null){
                        Optional.ofNullable(newTableValue.getTable_name()).ifPresent(existingTable::setTable_name);
                    }
                    return tableRepository.save(existingTable);
                }).orElseThrow(() -> new InvalidArgument("Task with ID " + table_id + " not found"));

        return tableMapper.tableToTableDto(table);

    }

}

