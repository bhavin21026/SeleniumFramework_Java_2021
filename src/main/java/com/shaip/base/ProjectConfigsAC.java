package com.shaip.base;

public final class ProjectConfigsAC {

	private ProjectConfigsAC() {

	}

	private static final String RESOURCE = System.getProperty("user.dir")+"/Shaip_Resources";
	
	private static final String ProjectName = "Test";
	private static final String ProjectDisplayName = "AutoQA";
	private static final String ProjectDescription = "This is project created using automation scripts, Please Do not delete it.";
	
	
	//config fields
	
	private static final String samplingRate = "16 Khz";
	private static final String samplingDepth = "16 bit";
	
	private static final String maxDurationAC = "300";
	private static final String minDurationAC = "15";
	private static final String minDurationAC2 = "5";

	
	private static final String batchName = "AutoBatch";
	private static final String batchTotal = "50";

	
	private static final String Audio1 = RESOURCE+"/audioToUpload/Sample-16kHz-24Bit.wav";
	private static final String Audio2 = RESOURCE+"/audioToUpload/Sample-22050Hz-8Bit.wav";
	private static final String Audio3 = RESOURCE+"/audioToUpload/sample1624_2.wav";

	private static final String MinAudio = RESOURCE+"/audioToUpload/Sample-min.wav";
	private static final String MaxAudio = RESOURCE+"/audioToUpload/Sample-max.wav";
	private static final String StrioChannel = RESOURCE+"/audioToUpload/Sample-sterio.wav";


	private static final String countryPair = 
			"india India\r\n"
			+ "usa USA\r\n"
			+ "canada Canada\r\n"
			+ "americansamoa American Samao\r\n"
			+ "srilanka SriLanka\r\n"
			+ "china China";

	public static String countryList() {

		return countryPair;
	}
	
	
	//Audio
	public static String getMinAudio() {

		return MinAudio;
	}
	
	public static String getMaxAudio() {

		return MaxAudio;
	}
	

	public static String getSterioAudio() {

		return StrioChannel;
	}
	
	public static String get16khz_24bit() {

		return Audio1;
	}
	public static String get22050khz_8bit() {

		return Audio2;
	}
	public static String get1624() {

		return Audio3;
	}

	public static String setBatchName() {

		return batchName;
	}
	

	public static String setBatchTotal() {

		return batchTotal;
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
	public static String setMax_AC() {

		return maxDurationAC;
	}
	public static String setMin_AC() {

		return minDurationAC;
	}
	
	public static String setMin_AC2() {

		return minDurationAC2;
	}

	
	
	

	

	

}
