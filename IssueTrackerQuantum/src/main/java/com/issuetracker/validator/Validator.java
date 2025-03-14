package com.issuetracker.validator;

import java.time.LocalDate;

import org.apache.commons.logging.LogFactory;

import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueStatus;

public class Validator {
    
    public void validate(Issue issue) throws IssueTrackerException {
	String errorMessage = null;
	
	if(!isValidIssueId(issue.getIssueId()))
	    errorMessage = "Validator.INVALID_ISSUE_ID";
	else if(!isValidIssueDescription(issue.getIssueDescription()))
	    errorMessage = "Validator.INVALID_ISSUE_DESCRIPTION";
	else if(!isValidReportedOn(issue.getReportedOn()))
	    errorMessage = "Validator.INVALID_REPORTED_DATE";
	else if(!isValidStatus(issue.getStatus()))
	    errorMessage = "Validator.INVALID_STATUS";
	
	if(errorMessage != null) {
	    IssueTrackerException exception = new IssueTrackerException(errorMessage);
	    LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
	    throw exception;
	}
	
    }

    public Boolean isValidIssueId(String issueId) {
	return !(issueId.isBlank() || issueId == null) && 
	       (issueId.matches("(MTI-I)-([\\d]{3})-(LS|MS|HS)")) && 
	       !issueId.contains("000");
    }

    public Boolean isValidIssueDescription(String issueDescription) {
	return !(issueDescription.isBlank() || issueDescription == null) &&
		(issueDescription.length() >= 1 && issueDescription.length() <= 50 ) && 
		issueDescription.matches("(?! )[A-Za-z]+([ ][A-Za-z]+)*(?! )");
    }

    public Boolean isValidReportedOn(LocalDate reportedOn) {
	return reportedOn != null && reportedOn.isBefore(LocalDate.now());
    }

    public Boolean isValidStatus(IssueStatus status) {
	return status != null && (status.equals(IssueStatus.OPEN) || status.equals(IssueStatus.IN_PROGRESS));
    }
    
}
