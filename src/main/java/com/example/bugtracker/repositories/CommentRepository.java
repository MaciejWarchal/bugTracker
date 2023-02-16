package com.example.bugtracker.repositories;

import com.example.bugtracker.models.Comment.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
