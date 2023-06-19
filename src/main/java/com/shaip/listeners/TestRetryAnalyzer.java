package com.shaip.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.shaip.utils.PropertiesOperationsShaip;

public class TestRetryAnalyzer implements IRetryAnalyzer {

	int counter = 1;
	int retryMaxLimit  = Integer.valueOf(PropertiesOperationsShaip.getPropertyValueByKey("retryCount"));
	
	@Override
	public boolean retry(ITestResult result) {
		if(counter<=retryMaxLimit) {
			counter++;
			return true;
		}
		return false;
	}

}
