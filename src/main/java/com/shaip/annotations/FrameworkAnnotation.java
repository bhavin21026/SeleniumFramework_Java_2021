package com.shaip.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.shaip.enums.CategoryType;
import com.shaip.enums.PortalType;
import com.shaip.enums.story;





@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotation {
	
	public CategoryType[] category();
	public story[] storyId();
	public PortalType[] portal();




}
