package com.algoExpert.demo.Service.Impl;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.Table;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.TableRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import com.algoExpert.demo.Service.TableService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;


//  create a new table
    @Override

    public Project createTable(int project_id, int member_id) throws InvalidArgument {
        // Retrieve the project by its ID
        Project project = projectRepository.findById(project_id)
                .orElseThrow(() -> new InvalidArgument("Project with ID " + project_id + " not found"));

        // Ensure that the tables list is initialized properly
        List<Table> tables = project.getTables();
        if (tables == null) {
            tables = new ArrayList<>();
            project.setTables(tables);
        }

        // Create a new table and task
        int count = tables.size();
        Table table = new Table(0, "Table " + count, null);
        Task task = new Task(0, "task", "description", member_id, "", ""
                , "", "", null);

        // Add the table to the project's tables list
        tables.add(table);
        // Set the tasks list for the table
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        table.setTasks(taskList);

        // Save the updated project and return it
        return projectRepository.save(project);
    }


    /*public Project createTable(int project_id, int member_id) throws InvalidArgument{
        Project project =  projectRepository.findById(project_id).orElseThrow(()->
        new InvalidArgument("Project with ID " + project_id + " not found"));

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

        return projectRepository.save(project);
    }*/

//  get all tables
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

//  update table
    @Override
    public Table editTable(Table newTableValue,int table_id) throws InvalidArgument {
        return tableRepository.findById(table_id)
                .map(existingTable->{
                    if(newTableValue !=null){
                        Optional.ofNullable(newTableValue.getTable_name()).ifPresent(existingTable::setTable_name);
                    }
                    return tableRepository.save(existingTable);
                }).orElseThrow(() -> new InvalidArgument("Task with ID " + table_id + " not found"));

    }

    //    delete table
    @Override
    @Transactional
    public List<Table> deleteTable(Integer project_id, Integer table_id) throws InvalidArgument{
        Project project = projectRepository.findById(project_id).orElseThrow(()->new InvalidArgument("Project with ID " + project_id + " not found"));
        Table table = tableRepository.findById(table_id).orElseThrow(()->new InvalidArgument("Table with ID " + table_id + " not found"));

        List<Table> tablesList = project.getTables();
        tablesList.remove(table);
        project.setTables(tablesList);

        projectRepository.save(project);
        return tableRepository.findAll();
    }

}
