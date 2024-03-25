package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.Table;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableService {

//  create a new table
    Project createTable(int project_id, int member_id) throws InvalidArgument;

//  get all tables
    List<Table> getAllTables();
    Table editTable(Table newTableValue,int table_id) throws InvalidArgument;

//  delete table
    @Transactional
    List<Table> deleteTable(Integer project_id, Integer table_id) throws InvalidArgument;

}

