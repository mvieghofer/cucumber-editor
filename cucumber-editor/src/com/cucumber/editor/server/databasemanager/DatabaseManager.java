package com.cucumber.editor.server.databasemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * filename: DataLayer.DatabaseManager.java
 * 
 * @date: 13.05.2011
 * @author: Markus Vieghofer
 * 
 */
public class DatabaseManager {

	private static final Logger LOG = Logger.getLogger(DatabaseManager.class
			.toString());

	private static ConfigManager confManager = ConfigManager.getInstance();

	private static String databaseUrl = confManager
			.getProperty(PropertyKey.DATABASE_PATH);

	private static String databaseType = confManager
			.getProperty(PropertyKey.DATABASE_TYPE);

	private static Connection conn;
	private static boolean connectionOpened;

	private DatabaseManager() {

	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		if (conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(createDatabaseUrl());
			connectionOpened = !conn.isClosed();
		}
		return conn;
	}

	private static String createDatabaseUrl() {
		StringBuilder url = new StringBuilder();
		url.append("jdbc:");
		url.append(databaseType);
		url.append(":");
		url.append(databaseUrl);
		LOG.info(url.toString());
		return url.toString();
	}

	/**
	 * @return
	 */
	public static String getDatabaseUrl() {
		return databaseUrl;
	}

	public static Statement getStatement() throws SQLException {
		return getConnection().createStatement();
	}

	public static PreparedStatement getPreparedStatement(final String sql)
			throws SQLException {
		return getConnection().prepareStatement(sql);
	}

	public static void closeConnection() throws SQLException {
		if (isConnectionOpen())
			conn.close();
	}

	/**
	 * gets the last generated key form the database
	 * 
	 * @param ps
	 * @return
	 * @throws SQLException
	 */
	public static int getIdFromDatabase(PreparedStatement ps,
			boolean generatedKeys) throws SQLException {
		int id = -1;
		ResultSet rs;
		if (generatedKeys)
			rs = ps.getGeneratedKeys();
		else
			rs = ps.executeQuery();
		if (rs != null && rs.next())
			id = rs.getInt(1);
		return id;
	}

	/**
	 * @return
	 */
	public static boolean isConnectionOpen() {
		return connectionOpened;
	}
}
