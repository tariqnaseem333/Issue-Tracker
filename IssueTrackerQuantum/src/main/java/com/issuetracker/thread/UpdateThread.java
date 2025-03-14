package com.issuetracker.thread;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.service.IssueService;

public class UpdateThread implements Runnable {

    private IssueService issueService;
    
    private static PropertiesConfiguration propertiesConfiguration;
    private static final Log LOG = LogFactory.getLog(UpdateThread.class);

    static {
	try {
	    propertiesConfiguration = new Configurations().properties("configuration.properties");
	} catch (ConfigurationException e) {
	    LOG.error(e.getMessage());
	}
    }

    public UpdateThread(IssueService issueService) {
	this.issueService = issueService;
    }

    @Override
    public void run() {
	LOG.info("Mont Trance Inc. | Updating Status");

	String issueId = "MTI-I-001-MS";
	IssueStatus status = IssueStatus.RESOLVED;

	try {
	    Boolean isUpdateSuccess = issueService.updateStatus(issueId, status);
	    if (Boolean.TRUE.equals(isUpdateSuccess)) {
		LOG.info(propertiesConfiguration.getProperty("IssueTester.ISSUE_UPDATE_SUCCESS"));
	    }
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

