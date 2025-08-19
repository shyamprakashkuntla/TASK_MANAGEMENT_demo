package com.task.TaskManagement.dao;

import com.task.TaskManagement.Entity.AllocationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationsRepository extends JpaRepository<AllocationsEntity, Integer> {
}
