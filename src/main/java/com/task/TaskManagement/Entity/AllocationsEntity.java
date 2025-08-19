
        package com.task.TaskManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;


import jakarta.persistence.*;

        import java.time.LocalDateTime;

@Entity
@Table(name = "allocations")
public class AllocationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="allocation_id")
    private Integer allocationId;

    private String roleInTask;
    private LocalDateTime allocationOn = LocalDateTime.now();
    @Column(length = 100)
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProjectsEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TasksEntity tasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UsersEntity user;

    @Column(name="deleted")
    private Boolean Deleted = false;

    public Boolean getDeleted() {
        return Deleted;
    }

    public void setDeleted(Boolean deleted) {
        Deleted = deleted;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public TasksEntity getTasks() {
        return tasks;
    }

    public void setTasks(TasksEntity tasks) {
        this.tasks= tasks;
    }

    public ProjectsEntity getProject() {
        return project;
    }

    public void setProject(ProjectsEntity project) {
        this.project = project;
    }

    public int getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public String getRoleInTask() {
        return roleInTask;
    }

    public void setRoleInTask(String roleInTask) {
        this.roleInTask = roleInTask;
    }

    public LocalDateTime getAllocationOn() {
        return allocationOn;
    }

    public void setAllocationOn(LocalDateTime allocationOn) {
        this.allocationOn = allocationOn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }



}
