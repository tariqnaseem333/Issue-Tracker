package com.issuetracker.model;

import java.time.LocalDate;

public class Assignee {
    
    private String assigneeId;
    private String assigneeName;
    private String assigneeEmail;
    private Unit workingunit;
    private LocalDate employeeSince;
    private Integer numberofIssuesActive;
    
    public Assignee(String assigneeId, String assigneeName, String assigneeEmail, 
                    Unit workingunit, LocalDate employeeSince, Integer numberofIssuesActive) { 
	this.assigneeId = assigneeId;
	this.assigneeName = assigneeName;
	this.assigneeEmail = assigneeEmail;
	this.workingunit = workingunit;
	this.employeeSince = employeeSince;
	this.numberofIssuesActive = numberofIssuesActive;
    }
    
    public String getAssigneeId(){
        return assigneeId;
    }
    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }
    public String getAssigneeName() {
        return assigneeName;
    }
    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }
    public String getAssigneeEmail() {
        return assigneeEmail;
    }
    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }
    public Unit getWorkingunit() {
        return workingunit;
    }
    public void setWorkingunit(Unit workingunit) {
        this.workingunit = workingunit;
    }
    public LocalDate getEmployeeSince() {
        return employeeSince;
    }
    public void setEmployeeSince(LocalDate employeeSince) {
        this.employeeSince = employeeSince;
    }
    public Integer getNumberofIssuesActive() {
        return numberofIssuesActive;
    }
    public void setNumberofIssuesActive(Integer numberofIssuesActive) {
        this.numberofIssuesActive = numberofIssuesActive;
    }
    
}
