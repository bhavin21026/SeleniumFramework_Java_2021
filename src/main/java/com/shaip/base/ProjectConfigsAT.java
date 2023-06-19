package com.shaip.base;

public final class ProjectConfigsAT {

	private ProjectConfigsAT() {

	}

	private static final String RESOURCE = System.getProperty("user.dir")+"/Shaip_Resources";
	
	private static final String ProjectName = "Automation";
	private static final String ProjectDisplayName = "QA";
	private static final String ProjectDescription = "This is project created using automation scripts, Please Do not delete it.";
	
	
	//config fields
	
	private static final String samplingRate = "16 Khz";
	private static final String samplingDepth = "24 bit";
	

	
	
	private static final String maxDurationAT = "1600";
	private static final String minDurationAT = "5";
	
	//tags
	private static final String tagLabel = "NonSpeechTag";
	private static final String tagDescription =  "This Tag field created using automation script.Do not delete it.";
	private static final String tag_Name = "cry";
	private static final String tag_lable = "cry";
	private static final String enclosedtagLabel = "BracketTags";
	private static final String enclosedtagDescription =  "This Emclosed Tag field created using automation script.Do not delete it.";
	private static final String enclosedtag_Name = "initial";
	private static final String enclosedtag_lable = "initial";
	private static final String fillerLabel = "BracketTags";
	private static final String fillerDescription =  "This filler words field created using automation script.Do not delete it.";
	private static final String filler_Name = "Gujarati";
	private static final String filler_lable = "આહ";
	
	private static final String batchName = "AutoBatch";
	private static final String batchTotal = "5";


	public static String setBatchName() {

		return batchName;
	}
	

	public static String setBatchTotal() {

		return batchTotal;
	}


	public static String setTag_name() {

		return tag_Name;
	}
	

	public static String setTag_label() {

		return tag_lable;
	}
	

	public static String setTagLabel() {

		return tagLabel;
	}
	
	public static String setTagDescription() {

		return tagDescription;
	}
	
	//enclosed tags
	
	


	public static String setenclosedTag_name() {

		return enclosedtag_Name;
	}
	

	public static String setenclosedTag_label() {

		return enclosedtag_lable;
	}
	

	public static String setenclosedTagLabel() {

		return enclosedtagLabel;
	}
	
	public static String setenclosedTagDescription() {

		return enclosedtagDescription;
	}
	

	//filler words
	
	


	public static String setFiller_name() {

		return filler_Name;
	}
	

	public static String setFiller_label() {

		return filler_lable;
	}
	

	public static String setFillerLabel() {

		return fillerLabel;
	}
	
	public static String setFillerDescription() {

		return fillerDescription;
	}
	
	

	//Projects
	
	public static String setProjectName() {

		return ProjectName;
	}
	public static String setProjectDisplayName() {

		return ProjectDisplayName;
	}
	public static String setProjectDiscription() {

		return ProjectDescription;
	}
	
	public static String setRate() {

		return samplingRate;
	}
	public static String setDepth() {

		return samplingDepth;
	}
	
	public static String setMax_AT() {

		return maxDurationAT;
	}
	public static String setMin_AT() {

		return minDurationAT;
	}

	
	

	

	

}
