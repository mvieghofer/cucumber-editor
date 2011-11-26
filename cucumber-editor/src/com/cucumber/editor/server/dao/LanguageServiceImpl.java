/**
 */
package com.cucumber.editor.server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.cucumber.editor.client.exceptions.DatabaseException;
import com.cucumber.editor.client.services.LanguageService;
import com.cucumber.editor.server.databasemanager.DatabaseManager;
import com.cucumber.editor.shared.dto.Language;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * filename: LanguageDaoImpl.java
 * 
 * @date: 24.08.2011
 * @author: Markus Vieghofer
 * 
 */
public class LanguageServiceImpl extends RemoteServiceServlet implements
		LanguageService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Language> getLanguages() throws DatabaseException {
		List<Language> lList = new LinkedList<Language>();
		String sql = "SELECT ID, LanguageKey, Language FROM Language";
		try {
			PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int id;
			String langKey;
			String lang;
			while (rs.next()) {
				id = rs.getInt("ID");
				langKey = rs.getString("LanguageKey");
				lang = rs.getString("Language");
				lList.add(new Language(id, langKey, lang));
			}
		} catch (SQLException e) {
			throw new DatabaseException();
		}
		return lList;
	}

	@Override
	public Language getByKey(String key) {
		String sql = "SELECT ID, LanguageKey, Language FROM Language Where Language = \""
				+ key + "\"";
		Language l = null;
		PreparedStatement stmt;
		try {
			stmt = DatabaseManager.getPreparedStatement(sql);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("ID");
				String langKey = rs.getString("LanguageKey");
				String lang = rs.getString("Language");
				l = new Language(id, langKey, lang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

}
