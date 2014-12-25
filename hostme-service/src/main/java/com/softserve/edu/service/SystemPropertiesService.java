package com.softserve.edu.service;

public interface SystemPropertiesService {

	public static final String IMAGE_PATH_PROP = "IMAGE_PATH";
	public static final String IMAGE_URL_PROP = "IMAGE_URL";
	public static final String EMAIL_PASS_SEND = "password";
	public static final String EMAIL_LOGIN = "username";
	public static final String BASE_SEND_URL = "url";
	String getImagePath();
	String getImageUrl();
	public String getMailPass();
	public String getMailUsername();
	public String getBaseUrl();
}
