package com.issuetracker.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;

import com.issuetracker.dao.IssueDAO;
import com.issuetracker.dao.IssueDAOImpl;
import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.Assignee;
import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueReport;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.validator.Validator;

public class IssueServiceImpl implements IssueService {
    
    private AssigneeService assigneeService = new AssigneeServiceImpl();
    private IssueDAO issueDAO = new IssueDAOImpl();
    private Validator validator = new Validator();

    @Override
    public synchronized String reportAnIssue(Issue issue) throws IssueTrackerException {
	try {
	    validator.validate(issue);
	    
	    List<Assignee> assignees = assigneeService.fetchAssignee(issue.getIssueUnit());
	    if(!assignees.isEmpty()) {
		issue.setAssigneeEmail(assignees.get(0).getAssigneeEmail());
		assigneeService.updateActiveIssueCount(assignees.get(0).getAssigneeEmail(), 'I');
	    }
	    
	    String issueId = issueDAO.reportAnIssue(issue);
	    if(issueId == null)
	         throw new IssueTrackerException("IssueService.DUPLICATE_ISSUE_ID");
	    
	    return issueId;
	} catch (IssueTrackerException exception) {
	    if(exception.getMessage().contains("IssueService"))
	         LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
	    throw exception;
	}
    }

    @Override
    public synchronized Boolean updateStatus(String issueId, IssueStatus status) throws IssueTrackerException {
	try {
	    Issue issue = issueDAO.getIssueById(issueId);
	    
	    if(issue == null)
	        throw new IssueTrackerException("IssueService.ISSUE_NOT_FOUND");
	    else if(issue.getStatus().equals(status))
	        throw new IssueTrackerException("IssueService.NO_STATUS_CHANGE");
	    else if(status.equals(IssueStatus.RECALLED) && !issue.getStatus().equals(IssueStatus.OPEN))
	        throw new IssueTrackerException("IssueService.INCOMPATIBLE_STATUS");
	    else
	        issueDAO.updateStatus(issue, status);
	    
	    if( !(issue.getStatus().equals(IssueStatus.OPEN) || issue.getStatus().equals(IssueStatus.IN_PROGRESS)) )
	        assigneeService.updateActiveIssueCount(issue.getAssigneeEmail(), 'D');
		
	    return true;
	} catch (IssueTrackerException exception) {
	    if(exception.getMessage().contains("IssueService"))
	        LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
	    throw exception;
	}
    }

    @Override
    public synchronized List<IssueReport> showIssues(Map<Character, Object> filterCriteria) throws IssueTrackerException {
	try {
	    List<Issue> issues = issueDAO.getIssueList();
	    List<Issue> filteredIssues = new ArrayList<>();
	    
	    if(filterCriteria.containsKey('A')) {
		filteredIssues = issues.stream()
			  	       .filter(issue -> issue.getAssigneeEmail().equals(filterCriteria.get('A')))
			  	       .collect(Collectors.toList());
	    }
	    else if(filterCriteria.containsKey('S')) {
		filteredIssues = issues.stream()
				       .filter(issue -> issue.getStatus().equals(filterCriteria.get('S')))
				       .collect(Collectors.toList());
	    }
	    
	    if(filteredIssues.isEmpty())
	          throw new IssueTrackerException("IssueService.NO_ISSUES_FOUND");
	    List<IssueReport> issuesReport = new ArrayList<>();
	    filteredIssues.forEach(issue -> {
		IssueReport issueReport = new IssueReport(issue.getIssueId(), issue.getIssueDescription(),
		                                          issue.getAssigneeEmail(), issue.getStatus());
		issuesReport.add(issueReport);
	    });
	    
	    return issuesReport;
	} catch (IssueTrackerException exception) {
	    if(exception.getMessage().contains("IssueService"))
	          LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
	    throw exception;
	}
    }

    @Override
    public synchronized List<Issue> deleteIssues() throws IssueTrackerException {
	try {
	    List<Issue> issuesToBeDeleted = issueDAO.getIssueList()
		    			 	    .stream()
		    			 	    .filter(issue -> (issue.getStatus().equals(IssueStatus.RESOLVED) || 
		    			 		    	      issue.getStatus().equals(IssueStatus.CLOSED)) && 
		    			 		    	     (ChronoUnit.DAYS.between(issue.getUpdatedOn(), LocalDate.now().minusDays(14)) >= 0))
		    			 	    .collect(Collectors.toList());
	    
	    if(issuesToBeDeleted.isEmpty())
	        throw new IssueTrackerException("IssueService.NO_ISSUES_DELETED");
	    
	    List<Issue> issuesToBePersisted = new ArrayList<>();
	    List<Issue> issues = issueDAO.getIssueList();
	    for(Issue issue : issues) {
		for(Issue deletedIssue : issuesToBeDeleted) {
		    if(!issue.getIssueId().equals(deletedIssue.getIssueId()) && 
		       !issuesToBePersisted.contains(issue) ) {
			issuesToBePersisted.add(issue);
			break;
		    }
		}
	    }
	    issueDAO.setIssueList(issuesToBePersisted);
	    
	    return issuesToBeDeleted;
	    
	} catch (IssueTrackerException exception) {
	    if(exception.getMessage().contains("IssueService"))
	        LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
	    throw exception;
	}

    }
    
}