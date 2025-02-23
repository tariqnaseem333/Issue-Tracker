package com.issuetracker.thread;

import java.util.List;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.Issue;
import com.issuetracker.service.IssueService;

public class DeleteThread implements Runnable {

    private IssueService issueService;

    private static PropertiesConfiguration propertiesConfiguration;
    private static final Log LOG = LogFactory.getLog(DeleteThread.class);

    static {
	try {
	    propertiesConfiguration = new Configurations().properties("configuration.properties");
	} catch (ConfigurationException e) {
	    LOG.error(e.getMessage());
	}
    }

    public DeleteThread(IssueService issueService) {
	this.issueService = issueService;
    }

    @Override
    public void run() {
	LOG.info("Mont Trance Inc. | Deleting Issues");

	try {
	    List<Issue> deletedIssueList = issueService.deleteIssues();

	    if (!deletedIssueList.isEmpty()) {
		LOG.info(deletedIssueList.size() + " " +
			 propertiesConfiguration.getProperty("IssueTester.ISSUE_DELETE_SUCCESS"));
	    }

	    LOG.info("=".repeat(98));
	    LOG.info(String.format("%s %-12s %s %-50s %s %-15s %s %-8s %s", "|",
	                           "Issue ID", "|", "Issue Description", "|",
	                           "Assignee EMail", "|", "Status", "|"));
	    LOG.info("=".repeat(98));
	    for (Issue issue : deletedIssueList) {
		LOG.info(String.format("%s %-12s %s %-50s %s %-15s %s %-8s %s",
		                       "|", issue.getIssueId(), "|",
		                       issue.getIssueDescription(), "|",
		                       issue.getAssigneeEmail(), "|",
		                       issue.getStatus(), "|"));
	    }
	    LOG.info("=".repeat(98));
	} catch (IssueTrackerException issueTrackerException) {
	    String exceptionMessage = issueTrackerException.getMessage();
	    if (exceptionMessage == null) {
		exceptionMessage = "IssueTester.GENERAL_EXCEPTION";
	    }

	    String errorMessage = (String) propertiesConfiguration.getProperty(exceptionMessage);
	    if (errorMessage == null) {
		errorMessage = (String) propertiesConfiguration.getProperty("IssueTester.GENERAL_EXCEPTION");
	    }

	    LOG.info(String.format("ERROR: %s", errorMessage));
	}
    }

}

