package com.softserve.edu.dao;

import com.softserve.edu.entity.Language;

public interface LanguageDao extends GenericDao<Language, Short> {
	
	Language findLanguageByName(String name);
	
	
}
