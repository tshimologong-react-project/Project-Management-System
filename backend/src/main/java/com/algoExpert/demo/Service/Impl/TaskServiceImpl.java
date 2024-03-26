package com.algoExpert.demo.Service.Impl;

import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.*;
import com.algoExpert.demo.Service.TaskService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
     @Autowired
     private TaskRepository taskRepository;
     @Autowired
     private ProjectRepository projectRepository;

     @PersistenceContext
     private EntityManager entityManager;

     @Autowired
     private AssigneesRepository assigneesRepository;

     @Autowired
     private TableRepository tableRepository;

     @Autowired
     private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

//    create new task
    @Override
    public Table createTask(int member_id, int table_id) throws InvalidArgument {

        // check if table and member exist
        Table table = tableRepository.findById(table_id).orElseThrow(()->
                new InvalidArgument("Table with ID " + table_id + " not found"));
        Member member = memberRepository.findById(member_id).orElseThrow(()->
                new InvalidArgument("Member wth ID "+member_id+" not found"));

        List<Task> taskList = table.getTasks();
        int count = taskList.size()+1;
        Task task = new Task(0,"task "+count,""
                ,member.getMember_id(),"","","","",null);

        taskList.add(task);
        table.setTasks(taskList);

        return tableRepository.save(table);
    }

//  delete task
    @Override
    @Transactional
    public Table deleteTaskById(Integer task_id, Integer table_id) throws InvalidArgument{
        Task storedTask = taskRepository.findById(task_id).orElseThrow(()->
                new InvalidArgument("Task with ID " + task_id + " not found"));
        Table table = tableRepository.findById(table_id).orElseThrow(()->
                new InvalidArgument("Table with ID " + table_id + " not found"));

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
    @Override
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

//    update task
    public Task editTask(Task newTask,int task_id) throws InvalidArgument{
        return   taskRepository.findById(newTask.getTask_id())
                .map(existingTask ->{
                    if (newTask != null){
//                        Optional.ofNullable(newTask.getTask_id()).ifPresent(existingTask::setTask_id);
                        Optional.ofNullable(newTask.getTitle()).ifPresent(existingTask::setTitle);
                        Optional.ofNullable(newTask.getDescription()).ifPresent(existingTask::setDescription);
                        Optional.ofNullable(newTask.getStart_date()).ifPresent(existingTask::setStart_date);
                        Optional.ofNullable(newTask.getEnd_date()).ifPresent(existingTask::setEnd_date);
                        Optional.ofNullable(newTask.getStatus()).ifPresent(existingTask::setStatus);
                        Optional.ofNullable(newTask.getPriority()).ifPresent(existingTask::setPriority);
                    }
                    return taskRepository.save(existingTask);
                }).orElseThrow(() -> new InvalidArgument("Task with ID " + task_id + " not found"));
    }

    //duplicate task
    @Override
    public Table duplicateTask(Task task, Integer table_id){
        Table table = tableRepository.findById(table_id).get();

        Task newTask = new Task(0,task.getTitle(),task.getDescription()
                ,task.getOwner(),task.getStart_date(),task.getEnd_date(),task.getStatus(),
                task.getPriority(),null);
        List<Task> taskList =table.getTasks();

        taskList.add(newTask);
        table.setTasks(taskList);
        return  tableRepository.save(table);
    }

}

