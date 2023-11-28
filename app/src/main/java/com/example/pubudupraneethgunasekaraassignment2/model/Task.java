package com.example.pubudupraneethgunasekaraassignment2.model;

import java.util.Date;

public class Task {
    private String taskId = "";
    private String taskName = "";
    private String dueDate = "";
    private String time = "";

    public Task(String taskId, String taskName, String dueDate, String time) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.time = time;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
