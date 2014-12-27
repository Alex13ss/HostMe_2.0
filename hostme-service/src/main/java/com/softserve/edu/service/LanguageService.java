package com.softserve.edu.service;

import com.softserve.edu.model.Language;

import java.util.List;

public interface LanguageService {
	
	public List<Language> getAllLanguages();

	Language findLanguageByName(String name);

}
