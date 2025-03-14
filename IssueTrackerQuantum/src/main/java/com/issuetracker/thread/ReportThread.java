package com.issuetracker.thread;

import java.time.LocalDate;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.model.Unit;
import com.issuetracker.service.IssueService;

public class ReportThread implements Runnable {

    private IssueService issueService;

    private static PropertiesConfiguration propertiesConfiguration;
    private static final Log LOG = LogFactory.getLog(ReportThread.class);

    static {
	try {
	    propertiesConfiguration = new Configurations().properties("configuration.properties");
	} catch (ConfigurationException e) {
	    LOG.error(e.getMessage());
	}
    }

    public ReportThread(IssueService issueService) {
	this.issueService = issueService;
    }

    @Override
    public void run() {
	LOG.info("Mont Trance Inc. | Reporting An Issue");

	Issue issue = new Issue("MTI-I-013-LS", "Booting up time is very high",
	                        Unit.ADMINISTRATION, LocalDate.now().minusDays(1), null,
	                        null, IssueStatus.OPEN);

	try {
	    String issueId = issueService.reportAnIssue(issue);
	    LOG.info(propertiesConfiguration.getProperty("IssueTester.REPORT_ISSUE_SUCCESS")
	             + " " + issueId);
	} catch (IssueTrackerException issueTrackerException) {
	    String exceptionMessage = issueTrackerException.getMessage();
	    if (exceptionMessage == null) {
		exceptionMessage = "IssueTester.GENERAL_EXCEPTION";
	    }

	    String errorMessage = (String)propertiesConfiguration.getProperty(exceptionMessage);
	    if (errorMessage == null) {
		errorMessage = (String)propertiesConfiguration.getProperty("IssueTester.GENERAL_EXCEPTION");
	    }

	    LOG.info(String.format("ERROR: %s", errorMessage));
	}
    }

}

