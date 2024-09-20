package com.issuetracker.test;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.model.Unit;
import com.issuetracker.service.IssueService;
import com.issuetracker.service.IssueServiceImpl;

public class IssueServiceTest {
    
    private IssueService issueService = new IssueServiceImpl();

    @Test
    public void reportAnIssueValidTest() throws IssueTrackerException {
	 Issue issue = new Issue("MTI-I-013-HS", "Third menu item is missing",
	                            Unit.ADMINISTRATION, LocalDate.now().minusDays(11), null, 
	                            null, IssueStatus.IN_PROGRESS);
	 Assertions.assertEquals("MTI-I-013-HS", issueService.reportAnIssue(issue));
    }

    @Test
    public void reportAnIssueInvalidReportedDateTest() { 
	 Issue issue = new Issue("MTI-I-013-HS", "Third menu item is missing",
	                            Unit.ADMINISTRATION, LocalDate.now().plusDays(5), null, 
	                            null, IssueStatus.IN_PROGRESS);
	 IssueTrackerException exception = Assertions.assertThrows(IssueTrackerException.class,
	                                                           () -> issueService.reportAnIssue(issue));
	 Assertions.assertEquals("Validator.INVALID_REPORTED_DATE", exception.getMessage());
    }

    @Test
    public void reportAnIssueInvalidStatusTest() {
	 Issue issue = new Issue("MTI-I-013-HS", "Third menu item is missing",
	                            Unit.ADMINISTRATION, LocalDate.now().minusDays(11), null, 
	                            null, IssueStatus.CLOSED);
	 IssueTrackerException exception = Assertions.assertThrows(IssueTrackerException.class,
	                                                           () -> issueService.reportAnIssue(issue));
	 Assertions.assertEquals("Validator.INVALID_STATUS", exception.getMessage());
    }

    @Test
    public void reportAnIssueDuplicateIssueIdTest() {
	 Issue issue = new Issue("MTI-I-001-HS", "Third menu item is missing",
	                            Unit.ADMINISTRATION, LocalDate.now().minusDays(11), null, 
	                            null, IssueStatus.IN_PROGRESS);
	 IssueTrackerException exception = Assertions.assertThrows(IssueTrackerException.class,
	                                                           () -> issueService.reportAnIssue(issue));
	 Assertions.assertEquals("IssueService.DUPLICATE_ISSUE_ID", exception.getMessage());
    }
    
}
