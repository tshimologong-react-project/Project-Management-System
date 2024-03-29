package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    create user
    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user){
        return userService.create(user);
    }

//    get all users of the system
    @GetMapping("/getAllUsers")
    public List<User> getAll(){
        return userService.getUsers();
    }

    @GetMapping("/fetchUserProject/{user_id}")
    public List<Project> getUserProject(@PathVariable int user_id){
        return userService.getUserProjectIds(user_id);
    }
}
