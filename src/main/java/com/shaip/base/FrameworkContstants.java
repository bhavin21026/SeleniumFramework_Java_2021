package com.shaip.base;

import java.time.Duration;

public final class FrameworkContstants {

	private FrameworkContstants() {

	}	
	
	private static final Duration IMPLICITWAIT = Duration.ofSeconds(10);
	private static final Duration EXPLICITWAIT = Duration.ofSeconds(10);;
	private static final Duration EXPLICITWAIT2 = Duration.ofSeconds(10);;

	private static final String RESOURCE = System.getProperty("user.dir")+"/Shaip_Resources";
	private static final String CONGIFPATH = RESOURCE +"/Properties/config.properties";
	private static final String EMAILATTACHMENTPATH = RESOURCE+"/AutomationReports/ShaipCloudQAAutomationReport.html";
	private static final String EXTENTREPORTPATH = RESOURCE+"/AutomationReports/";
	private static final String SCRRENSHOTPATH = RESOURCE+"/Screenshots/";
	private static final String JASONDATAPATH = RESOURCE+"/TestData_";
	private static final String GUIDELINE1 = RESOURCE+"/FilesToUpload/QAsample1.pdf";
	private static final String GUIDELINE2 = RESOURCE+"/FilesToUpload/QAsample2.pdf";
	private static final String GUIDELINE3 = RESOURCE+"/FilesToUpload/QAsample3.pdf";
	private static final String GUIDELINE4 = RESOURCE+"/FilesToUpload/QAsample4.pdf";
	private static final String GUIDELINE5 = RESOURCE+"/FilesToUpload/QAsample5.pdf";
	private static final String GUIDELINE6 = RESOURCE+"/FilesToUpload/QAsample6_3mb.pdf";
	private static final String Audio1 = RESOURCE+"/audioToUpload/Sample-16kHz-24Bit.wav";
	private static final String Audio2 = RESOURCE+"/audioToUpload/Sample-22050Hz-8Bit.wav";

	//Audio
	public static String getAudio1() {

		return Audio1;
	}
	public static String getAudio2() {

		return Audio2;
	}
	
	
	//GUIDELINES
	
	public static String getSample1() {

		return GUIDELINE1;
	}
	public static String getSample2() {

		return GUIDELINE2;
	}
	public static String getSample3() {

		return GUIDELINE3;
	}
	public static String getSample4() {

		return GUIDELINE4;
	}
	public static String getSample5() {

		return GUIDELINE5;
	}
	public static String getSample6() {

		return GUIDELINE6;
	}

	//AmazoneS3
	
	private static final String AccessKeyAmazoneS3 = "AKIA6HMMKddvcccv2U2YDHLJULZ";
	private static final String SecreatKeyAmazoneS3 = "F3uaoUH8CqdvvdvdvMfjdnRHHlBHbRm9908JBTrsT8LQa1H";
	private static final String BucketNameAmazoneS3 = "qa-shaipcloud-bucketvdvv";
	private static final String FolderNameAmazoneS3 = "cloud-sync-test/AutomationFilesDontChange/Automation-Monovdvdv-All/";
			
			
	public static String getAccessKeyAmazoneS3() {

		return AccessKeyAmazoneS3;
	}
	
	public static String getSecreatKeyAmazoneS3()
	{

		return SecreatKeyAmazoneS3;
	}
	
	public static String getBucketNameAmazoneS3()
	{

		return BucketNameAmazoneS3;
	}
	
	public static String getFolderNameAmazoneS3() {

		return FolderNameAmazoneS3;
	}
	
	
	public static String getJsonDataPath() {

		return JASONDATAPATH;
	}
	
	public static Duration getExplicitwait()
	{

		return EXPLICITWAIT;
	}
	
	public static Duration getExplicitwait2()
	{

		return EXPLICITWAIT2;
	}
	
	public static Duration getImplicitwait()
	{

		return IMPLICITWAIT;
	}
	
	public static String getScreenShotPath() {

		return SCRRENSHOTPATH;
	}

	public static String getEmailAttachmentPath() {

		return EMAILATTACHMENTPATH;
	}

	public static String getCongifpath() {

		return CONGIFPATH;
	}
	
	public static String getExtentReportPath() {

		return EXTENTREPORTPATH;
	}

}
