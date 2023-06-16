package com.example.bugtracker.repositories;

import com.example.bugtracker.models.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
    @Query(value = "select * from project where enabled = :enabled",
            nativeQuery = true)
    List<Project> findAllByEnabledNative(@Param("enabled") boolean enabled);

    List<Project> findAllByEnabled(boolean enabled);
}
