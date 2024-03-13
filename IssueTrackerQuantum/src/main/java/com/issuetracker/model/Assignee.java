package com.issuetracker.model;

import java.time.LocalDate;

public class Assignee {
    
//  Instance Variables
    private String assigneeId;
    private String asigneeName;
    private String assigneeEmail;
    private Unit workingUnit;
    private LocalDate employeeSince;
    private Integer numberOfIssuesActive;
    
//  Constructor
    public Assignee(String assigneeId, String asigneeName, String assigneeEmail, 
                    Unit workingUnit, LocalDate employeeSince, Integer numberOfIssuesActive) {
	this.assigneeId = assigneeId;
	this.asigneeName = asigneeName;
	this.assigneeEmail = assigneeEmail;
	this.workingUnit = workingUnit;
	this.employeeSince = employeeSince;
	this.numberOfIssuesActive = numberOfIssuesActive;
    }
    
//  Getters and Setters
    public String getAssigneeId() {
        return assigneeId;
    }
    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }
    public String getAsigneeName() {
        return asigneeName;
    }
    public void setAsigneeName(String asigneeName) {
        this.asigneeName = asigneeName;
    }
    public String getAssigneeEmail() {
        return assigneeEmail;
    }
    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }
    public Unit getWorkingUnit() {
        return workingUnit;
    }
    public void setWorkingUnit(Unit workingUnit) {
        this.workingUnit = workingUnit;
    }
    public LocalDate getEmployeeSince() {
        return employeeSince;
    }
    public void setEmployeeSince(LocalDate employeeSince) {
        this.employeeSince = employeeSince;
    }
    public Integer getNumberOfIssuesActive() {
        return numberOfIssuesActive;
    }
    public void setNumberOfIssuesActive(Integer numberOfIssuesActive) {
        this.numberOfIssuesActive = numberOfIssuesActive;
    }
    
}