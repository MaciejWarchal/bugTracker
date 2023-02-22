package com.example.bugtracker.repositories;

import com.example.bugtracker.models.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
