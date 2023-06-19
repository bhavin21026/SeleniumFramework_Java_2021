package com.shaip.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ISuiteListener;
import org.testng.annotations.ITestAnnotation;


public class TestRetryAnalyzerListener implements IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		annotation.setRetryAnalyzer(TestRetryAnalyzer.class);
	}

}
