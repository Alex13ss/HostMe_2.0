package com.softserve.edu.service;

import java.util.List;
import com.softserve.edu.entity.Language;

public interface LanguageService {
	
	public List<Language> getAllLanguages();

	Language findLanguageByName(String name);

}
