package com.example.mybusinessmanager_final_project.repository;

import com.example.mybusinessmanager_final_project.model.entity.OverTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvertimeRepository extends JpaRepository<OverTimeEntity, Long> {
}
