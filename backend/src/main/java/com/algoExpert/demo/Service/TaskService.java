package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {


//  create new task
    Table createTask(int member_id, int table_id) throws InvalidArgument;

//  delete task
    @Transactional
    Table deleteTaskById(Integer taskId, Integer table_id) throws InvalidArgument;

//  get all tasks
    List<Task> getAllTask();

//  update task
    Task editTask(Task newTask,int task_id) throws InvalidArgument;

//  duplicate task
    Table duplicateTask(Task task, Integer table_id);
}

