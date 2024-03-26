package com.algoExpert.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer member_id;

    private Integer user_id;

    private Integer project_id;

    private  String username;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> taskList;

}
