package com.issuetracker.userinterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.issuetracker.exception.IssueTrackerException;


import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueReport;
import com.issuetracker.service.IssueServiceImpl;
//Uncomment the commented lines of code after implementing modal classes.
public class IssueTester
{
    private static IssueServiceImpl issueService;

    public static void main(String[] args) {
	issueService = new IssueServiceImpl();

	System.out.println();
	System.out.print("Mont Trance Inc.");

//	reportAnIssue();
//	updateStatus();
//	showIssues();
//	deleteIssues();
	
    }

    public static void reportAnIssue() {
	System.out.println(" | Reporting An Issue");
//	will implement this method
    }

    public static void updateStatus() {
	System.out.println(" | Updating Status");
//	will implement this method
    }

    public static void showIssues() {
	System.out.println(" | Showing Issues");
//	will implement this method
    }

    public static void deleteIssues() {
	System.out.println(" | Deleting Issues");
//	will implement this method
    }
}