package com.task.TaskManagement.services.Impl;


import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.Entity.TasksEntity;
import com.task.TaskManagement.dao.ProjectsRepository;
import com.task.TaskManagement.dao.TasksRepository;
import com.task.TaskManagement.dto.ResponseWrapper;
import com.task.TaskManagement.dto.TasksDto;
import com.task.TaskManagement.services.TasksService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TasksServiceImpl implements TasksService {
    @Autowired
    private TasksRepository taskRepository;
    @Autowired
    private ProjectsRepository projectRepository;

    @Override
    public ResponseWrapper<TasksEntity> createTask(TasksDto dto) {
        ProjectsEntity project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + dto.getProjectId()));

        TasksEntity task= new TasksEntity();
        BeanUtils.copyProperties(dto, task); // Automatically maps matching fields
        task.setProject(project);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        TasksEntity saved = taskRepository.save(task);
        return new ResponseWrapper<>("task created successfully", null);
    }

    @Override
    public ResponseWrapper<TasksEntity> updateTask(Integer id, TasksDto dto) {
        TasksEntity existing = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("task not found"));

        ProjectsEntity project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + dto.getProjectId()));

        BeanUtils.copyProperties(dto, existing,"taskId"); // Copy all matching fields from DTO
        existing.setProject(project);
        existing.setUpdatedAt(LocalDateTime.now());

        TasksEntity updated =taskRepository.save(existing);
        return new ResponseWrapper<>("task updated successfully", null);
    }

    @Override
    public ResponseWrapper<String> deleteTask(Integer id) {
        TasksEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("task not found"));

        task.setIsDeleted(true);  // Mark as deleted
        taskRepository.save(task); // Save the updated project

        return new ResponseWrapper<>("task deleted successfully",null);
    }

    @Override
    public ResponseWrapper<TasksEntity> getTaskById(Integer id) {
        TasksEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("task not found"));
        return new ResponseWrapper<>("task fetched successfully", task);
    }

    @Override
    public ResponseWrapper<List<TasksEntity>> getAllTasks() {
        List<TasksEntity> list = taskRepository.findAll();
        return new ResponseWrapper<>("All Tasks fetched successfully", list);

    }
    @Override
    public ResponseWrapper<List<TasksEntity>> searchTasks(String name, Integer pageNo, Integer pageSize) {
        List<TasksEntity> task = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
        List<TasksEntity> taskEntities = taskRepository.searchtasks(name, pageRequest);

        task.forEach(tasksEntity -> {
            TasksDto tasks = new TasksDto();
            BeanUtils.copyProperties(tasksEntity, tasks);
            task.add(tasksEntity);
        });

        return new ResponseWrapper<>("Task Detailes fetched successfully", task);
    }

    @Override
    public List<TasksEntity> getTasksPageOnly(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<TasksEntity> page = taskRepository.findAll(pageable);
        return page.getContent();  // ✅ Only the user list, no metadata
    }
    @Override
    public ResponseWrapper<TasksDto> markTaskAsComplete(Integer taskId) {
        TasksEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));

        if (task.isDeleted()) {
            throw new RuntimeException("Cannot update a deleted task.");
        }

        task.setStatus("Completed");
        task.setPercentComplete(100);
        task.setUpdatedAt(LocalDateTime.now());

        TasksEntity updated = taskRepository.save(task);

        TasksDto dto = new TasksDto();
        dto.setTaskId(updated.getTaskId());
        dto.setTaskName(updated.getTaskName());
        dto.setStatus(updated.getStatus());
        dto.setPercentComplete(updated.getPercentComplete());
        dto.setTaskDate(updated.getTaskDate());

        return new ResponseWrapper<>("Task marked as completed", null);
    }



    @Override
    public List<TasksEntity> filterTasks(String projectName, String status) {
        return taskRepository.filterTasks(projectName, status);
    }
}
