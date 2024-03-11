package com.issuetracker.service;

import java.util.List;
import java.util.Map;

import com.issuetracker.dao.IssueDAO;
import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueReport;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.validator.Validator;

public class IssueServiceImpl implements IssueService {
    
    private AssigneeService assigneeService;
    private IssueDAO issueDAO; 
    
    /**
     * @params
     *         issue - The new issue to be reported
     * 
     * @operation Reports a new issue by
     *            * validating its details
     *            * fetch an assignee
     *            * persists the issue in the issueList
     * 
     * @returns
     *          String - The issue id
     */
    @Override
    public String reportAnIssue(Issue issue) throws IssueTrackerException {
	// will implement this method
	return null;
    }

    /**
     * @params
     *         issueId - The issue id
     *         status - The new status
     * 
     * @operation Updates the status of the given issue with the given status
     * 
     * @returns
     *          Boolean - Result of the status update
     */
    @Override
    public Boolean updateStatus(String issueId, IssueStatus status) throws IssueTrackerException {
	// will implement this method 
	return null;
    }

    /**
     * @params
     *         filterCriteria - A map where its
     *         key denotes an attribute of the issue object
     *         value contains the filter value
     * 
     * @operation Generates a report of issues based on the filter criteria
     * 
     * @returns
     *          List<IssueReport> - The list of filtered issue objects
     */
    @Override
    public List<IssueReport> showIssues(Map<Character, Object> filterCriteria) throws IssueTrackerException {
	// will implement this method
	return null;
    }

    /**
     * @operation Deletes the issue object which are resolved or closed,
     *            at least 14 days ago
     * 
     * @returns
     *          List<Issue> - The list of issues which had been deleted
     */
    @Override
    public List<Issue> deleteIssues() throws IssueTrackerException {
	// will implement this method
	return null;
    }
    
}