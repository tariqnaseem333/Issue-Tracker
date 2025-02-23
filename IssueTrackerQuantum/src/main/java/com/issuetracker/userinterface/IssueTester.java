package com.issuetracker.userinterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.configuration2.ex.ConfigurationException;
import com.issuetracker.service.IssueService;
import com.issuetracker.service.IssueServiceImpl;
import com.issuetracker.thread.DeleteThread;
import com.issuetracker.thread.ReportThread;
import com.issuetracker.thread.ShowThread;
import com.issuetracker.thread.UpdateThread;

public class IssueTester {
    
    private static IssueService issueService;

    public static void main(String[] args) throws ConfigurationException {
	issueService = new IssueServiceImpl();
	ExecutorService service = Executors.newSingleThreadExecutor();
	
	service.execute(new ReportThread(issueService));
	service.execute(new UpdateThread(issueService));
	service.execute(new ShowThread(issueService));
	service.execute(new DeleteThread(issueService));
	
	service.shutdown();
	
	while(!service.isTerminated()) {
	}
    }
}