package com.dvdrental.inventory.dao;

import java.util.List;
import java.util.Map;

import com.dvdrental.inventory.model.Language;

public interface LanguageDao {
	public Integer createLanguage(Language language);
	public Integer updateLanguage(Language language);
	public Integer deleteLanguage(Integer language);
	public Language loadLanguage(Integer languageId);
	public List<Language> getAllLanguages();
	public List<Map<String, Object>> loadAllLanguages();

}
