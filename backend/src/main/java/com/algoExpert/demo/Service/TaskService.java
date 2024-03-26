package com.algoExpert.demo.Service;

import com.algoExpert.demo.Dto.TaskDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.TaskMapper;
import com.algoExpert.demo.Repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
     @Autowired
     private TaskRepository taskRepository;
     @Autowired
     private TableRepository tableRepository;
     @Autowired
     private CommentRepository commentRepository;
     @Autowired
     private TaskMapper taskMapper;


//    create new task
    public Table createTask(int member_id, int table_id) throws  InvalidArgument {

        Table table = tableRepository.findById(table_id).orElseThrow(()->new InvalidArgument("Table with ID " + table_id + " not found"));

        List<Task> taskList =table.getTasks();
        int count = taskList.size()+1;
        Task task=new Task(0,"task"+count,""
                ,member_id,"","","","",null);

        taskList.add(task);
        table.setTasks(taskList);

        return tableRepository.save(table);
    }

    //delete task
    @Transactional
    public Table deleteTaskById(Integer taskId, Integer table_id) throws InvalidArgument{
        Task storedTask = taskRepository.findById(taskId).orElseThrow(()->new InvalidArgument("Task with ID " + taskId + " not found"));
        Table table = tableRepository.findById(table_id).orElseThrow(()->new InvalidArgument("Table with ID " + table_id + " not found"));
            List<Comment> comments = storedTask.getComments();
            if (!comments.isEmpty()) {
                for (Comment comment : comments) {
                    commentRepository.delete(comment);
                }
            }
           List<Task> taskList = table.getTasks();
           taskList.remove(storedTask);
           table.setTasks(taskList);
           tableRepository.save(table);
        return table;
    }

//    get all tasks
    public List<TaskDto> getAllTask(){
        List<Task> tasks =  taskRepository.findAll();
        return taskMapper.taskDtos(tasks);
    }

//    update task
    public TaskDto editTask(TaskDto newTaskDto) throws InvalidArgument{
       Task task =   taskRepository.findById(newTaskDto.getTask_id())
                .map(existingTask ->{
                    if (newTaskDto != null){
                        Optional.ofNullable(newTaskDto.getTitle()).ifPresent(existingTask::setTitle);
                        Optional.ofNullable(newTaskDto.getDescription()).ifPresent(existingTask::setDescription);
                        Optional.ofNullable(newTaskDto.getStart_date()).ifPresent(existingTask::setStart_date);
                        Optional.ofNullable(newTaskDto.getEnd_date()).ifPresent(existingTask::setEnd_date);
                        Optional.ofNullable(newTaskDto.getStatus()).ifPresent(existingTask::setStatus);
                        Optional.ofNullable(newTaskDto.getPriority()).ifPresent(existingTask::setPriority);
                    }
                    return taskRepository.save(existingTask);
                }).orElseThrow(() -> new InvalidArgument("Task with ID " + newTaskDto.getTask_id() + " not found"));
       return taskMapper.taskToTaskDto(task);
    }

    //duplicate task
    public Table duplicateTask(Task task, Integer table_id){
        Table table = tableRepository.findById(table_id).get();

        Task newTask=new Task(0,task.getTitle(),task.getDescription()

                ,task.getOwner(),task.getStart_date(),task.getEnd_date(),task.getStatus(),
                task.getPriority(),null);
        List<Task> taskList =table.getTasks();



        taskList.add(newTask);
        table.setTasks(taskList);
        return  tableRepository.save(table);
    }

}

