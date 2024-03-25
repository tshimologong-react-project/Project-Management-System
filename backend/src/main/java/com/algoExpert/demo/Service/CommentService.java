package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.Comment;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

//  create comment
    Task createComment(Comment comment,int member_id,int task_id)throws InvalidArgument;

//  get all comments
    List<Comment> getAllComments();

//   update comment by id
    Comment editComment(int commentId,Comment newComment);

//  delete comment by id
    List<Comment> deleteComment(int commentId);
}
