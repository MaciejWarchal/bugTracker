package com.example.bugtracker.repositories;

import com.example.bugtracker.models.Issue.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue,Long> {
}
