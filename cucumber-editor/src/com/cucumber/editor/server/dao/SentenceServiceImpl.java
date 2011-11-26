/**
 */
package com.cucumber.editor.server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.cucumber.editor.client.services.SentenceService;
import com.cucumber.editor.server.databasemanager.DatabaseManager;
import com.cucumber.editor.shared.dto.Keyword;
import com.cucumber.editor.shared.dto.Language;
import com.cucumber.editor.shared.dto.SentenceDto;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * filename: SentenceDAO.java
 * 
 * @date: 26.06.2011
 * @author: Markus Vieghofer
 * 
 */
public class SentenceServiceImpl extends RemoteServiceServlet implements
		SentenceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5191241743686975535L;

	public List<SentenceDto> getSentences(Language language, Keyword keyword,
			String sentenceTemplate) {
		List<SentenceDto> sentenceList = new LinkedList<SentenceDto>();
		// get sentences from db
		String sql = "SELECT s.sentence " //
				+ "FROM sentence s INNER JOIN keyword kw ON s.keywordId = kw.id " //
				+ "INNER JOIN language l ON kw.languageId = l.id " //
				+ "INNER JOIN key k ON k.id = kw.keyId " //
				+ "WHERE k.id = '?' " //
				+ "AND l.language = '?' " //
				+ "AND s.sentence like '?%'";
		try {
			PreparedStatement prepStmt = DatabaseManager
					.getPreparedStatement(sql);
			prepStmt.setInt(0, keyword.getValue());
			prepStmt.setInt(1, language.getId());
			prepStmt.setString(2, sentenceTemplate);
			ResultSet rs = prepStmt.executeQuery();
			int index = 0;
			while (rs.next()) {
				SentenceDto s = new SentenceDto(rs.getString(index));
				sentenceList.add(s);
			}
		} catch (SQLException e) {
			// TODO do something
		}
		return sentenceList;
	}
}
