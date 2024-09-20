package com.issuetracker.service;

import java.util.List;
import java.util.stream.Collectors;

import com.issuetracker.dao.AssigneeDAO;
import com.issuetracker.dao.AssigneeDAOImpl;
import com.issuetracker.model.Assignee;
import com.issuetracker.model.Unit;

public class AssigneeServiceImpl implements AssigneeService {
    
    private AssigneeDAO assigneeDAO = new AssigneeDAOImpl();

    @Override
    public List<Assignee> fetchAssignee(Unit unit) {
	return assigneeDAO.fetchAssignees(unit)
		   .stream()
		   .filter(assignee -> assignee.getNumberofIssuesActive() < 3)
		   .collect(Collectors.toList());
    }

    @Override
    public void updateActiveIssueCount(String assigneeEmail, Character operation) {
	Assignee assignee = assigneeDAO.getAssigneeByEmail(assigneeEmail);
	if(operation == 'I')
	    assignee.setNumberofIssuesActive(assignee.getNumberofIssuesActive()+1);
	else if(operation == 'D')
	    assignee.setNumberofIssuesActive(assignee.getNumberofIssuesActive()-1);
    }
}
