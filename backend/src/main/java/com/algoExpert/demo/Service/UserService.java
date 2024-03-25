package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface UserService {

//    create user
    User create(User user);

// get all users
    List<User> getUsers();

//  get all projects
    List<Project> getUserProjectIds(int userId);

//    delete user by id
    List<User> deleteUser(int userId);
}
