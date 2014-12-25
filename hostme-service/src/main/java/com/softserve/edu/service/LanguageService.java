package com.softserve.edu.service;

import com.softserve.edu.entity.Language;

import java.util.List;

public interface LanguageService {
	
	public List<Language> getAllLanguages();

	Language findLanguageByName(String name);

}
