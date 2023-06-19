package com.shaip.factories;

import com.shaip.enums.ProjectType;


public final class ProjectTypeFactory {
	
	private ProjectTypeFactory() {}

	
	public static String selectProjectType(ProjectType type) {
		String category = null;
		if(type == ProjectType.AudioCollection) {
			
			category="Audio Collection";
		}
		else if(type == ProjectType.AudioTranscription)  {
			category="Audio Transcription";

		}
		
		return category;
	}

}
