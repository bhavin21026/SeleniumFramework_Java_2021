package com.shaip.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.shaip.base.FrameworkContstants;



public final class PropertiesOperationsShaip {

	
	private PropertiesOperationsShaip() {}
	
	
	static Properties prop = new Properties();

	public static String getPropertyValueByKey(String key) {
		// 1. load data from properties file
		FileInputStream fis;
		try {
			fis = new FileInputStream(FrameworkContstants.getCongifpath());
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2. read data
		String value = prop.get(key).toString();
		//System.out.println(value);

		if(StringUtils.isEmpty(value)) {
			try {
				throw new Exception("Value is not specified for key: " + key
						+ " in Config properties file under properties folder.");
			} catch (Exception e) {
			}
		}

		return value;
	}

}
