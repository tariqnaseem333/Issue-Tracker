package com.issuetracker.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.IssueReport;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.service.IssueService;

public class ShowThread implements Runnable {

    private IssueService issueService;
    
    private static PropertiesConfiguration propertiesConfiguration;
    private static final Log LOG = LogFactory.getLog(ShowThread.class);

    static {
	try {
	    propertiesConfiguration = new Configurations().properties("configuration.properties");
	} catch (ConfigurationException e) {
	    LOG.error(e.getMessage());
	}
    }

    public ShowThread(IssueService issueService) {
	this.issueService = issueService;
    }

    @Override
    public void run() {
	LOG.info("Mont Trance Inc. | Showing Issues");

	Map<Character, Object> filterCriteria = new HashMap<>();
	filterCriteria.put('S', IssueStatus.CLOSED);

	try {
	    List<IssueReport> issueReportList = issueService.showIssues(filterCriteria);

	    if (filterCriteria.containsKey('A')) {
		LOG.info(String.format("Filter Criteria: Assignee Email > %s", filterCriteria.get('A')));
		LOG.info("=".repeat(84));
		LOG.info(String.format("%s %-12s %s %-50s %s %-12s %s", "|",
		                       "Issue ID", "|", "Issue Description",
		                       "|", "Issue Status", "|"));
		LOG.info("=".repeat(84));

		for (IssueReport issueReport : issueReportList) {
		    LOG.info(String.format("%s %-12s %s %-50s %s %-12s %s", "|",
		                           issueReport.getIssueId(), "|",
		                           issueReport.getIssueDescription(),
		                           "|", issueReport.getStatus(), "|"));
		}
		LOG.info("=".repeat(84));
	    } else {
		LOG.info(String.format("Filter Criteria: Issue Status > %s", filterCriteria.get('S')));
		LOG.info("=".repeat(87));
		LOG.info(String.format("%s %-12s %s %-50s %s %-15s %s", "|",
		                       "Issue ID", "|", "Issue Description",
		                       "|", "Assignee EMail", "|"));
		LOG.info("=".repeat(87));

		for (IssueReport issueReport : issueReportList) {
		    LOG.info(String.format("%s %-12s %s %-50s %s %-15s %s", "|",
		                           issueReport.getIssueId(), "|",
		                           issueReport.getIssueDescription(),
		                           "|", issueReport.getAssigneeEmail(), "|"));
		}
		LOG.info("=".repeat(87));
	    }
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

