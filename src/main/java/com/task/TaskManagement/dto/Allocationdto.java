package com.task.TaskManagement.dto;

import java.time.LocalDateTime;

public class Allocationdto {

    private Integer allocationId;
    private Integer projectId;
    private String roleInTask;
    //private LocalDateTime allocationOn;
    private String remarks;
    private LocalDateTime allocatedOn = LocalDateTime.now();
    private Integer userId;
    private Integer taskId;
    private Boolean deleted = false;

    public Integer getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(Integer allocationId) {
        this.allocationId = allocationId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getRoleInTask() {
        return roleInTask;
    }

    public void setRoleInTask(String roleInTask) {
        this.roleInTask = roleInTask;
    }



    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getAllocatedOn() {
        return allocatedOn;
    }

    public void setAllocatedOn(LocalDateTime allocatedOn) {
        this.allocatedOn = allocatedOn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
