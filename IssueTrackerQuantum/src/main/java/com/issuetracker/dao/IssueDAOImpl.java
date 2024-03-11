package com.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueStatus;

public class IssueDAOImpl implements IssueDAO {
    
    private List<Issue> issueList = new ArrayList<>();

    public IssueDAOImpl() {
	// will implement this method
    }

    @Override
    public List<Issue> getIssueList() {
	return issueList;
    }

    @Override
    public void setIssueList(List<Issue> issueList) {
	this.issueList = issueList;
    }

    /**
     * @params
     *         issue - The new issue to be added
     * 
     * @operation Stores a new issue in the issueList
     * 
     * @returns
     *          String - The issue id
     */
    @Override
    public String reportAnIssue(Issue issue) {
//	will implement this method
    }

    /**
     * @params
     *         issue - The issue to be updated
     *         status - The new status
     * 
     * @operation Updates the status of the given issue
     *            with the given status
     */

    @Override
    public void updateStatus(Issue issue, IssueStatus status) {
//	will implement this method
    }

    /**
     * @params
     *         issueId - The issue id to be fetched
     * 
     * @operation Fetches the issue object based on the given issue id
     * 
     * @returns
     *          Issue - The fetched issue object
     */
    @Override
    public Issue getIssueById(String issueId) {
//	will implement this method
    }
}