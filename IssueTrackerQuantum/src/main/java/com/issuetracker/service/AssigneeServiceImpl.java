package com.issuetracker.service;

import java.util.List;
import java.util.stream.Collectors;

import com.issuetracker.dao.AssigneeDAO;
import com.issuetracker.dao.AssigneeDAOImpl;
import com.issuetracker.model.Assignee;
import com.issuetracker.model.Unit;

public class AssigneeServiceImpl implements AssigneeService {
    
//  Instance Variables
    private AssigneeDAO assigneeDao = new AssigneeDAOImpl();

    /**
     * @params
     *         unit - The assignee unit
     * 
     * @operation Fetches the assignees list for the given unit
     * 
     * @returns
     *          List<Assignee> - List of assignees fetched
     */
    @Override
    public List<Assignee> fetchAssignee(Unit unit) {
	return assigneeDao.fetchAssignees(unit).stream()
		       .filter(assignee -> assignee.getNumberOfIssuesActive() < 3)
		       .collect(Collectors.toList());
}

    /**
     * @params
     *         assigneeEmail - The assignee email id
     *         operation - The operation code
     * 
     * @operation Updates the active issues count for the given assignee email,
     *            by incrementing or decrementing it based on the operation code
     * 
     */
    @Override
    public void updateActiveIssueCount(String assigneeEmail, Character operation) {
	
	Assignee matchedAssignee = assigneeDao.getAssigneeByEmail(assigneeEmail);
	if( operation.equals('I') ) {
	    matchedAssignee.setNumberOfIssuesActive(matchedAssignee.getNumberOfIssuesActive()+1);
	} else if( operation.equals('D') ) {
	    matchedAssignee.setNumberOfIssuesActive(matchedAssignee.getNumberOfIssuesActive()-1);
	}

    }
    
}