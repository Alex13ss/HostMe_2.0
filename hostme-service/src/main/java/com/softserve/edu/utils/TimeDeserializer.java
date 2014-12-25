package com.softserve.edu.utils;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
@Component
public class TimeDeserializer extends JsonDeserializer<Calendar> {

	@Override
	public Calendar deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		System.out.println("has entered");
		Calendar date=Calendar.getInstance();
		long miliseconds=Long.parseLong(jp.getText());
		date.setTimeInMillis(miliseconds);
		System.exit(0);
		return date;
	}

}
