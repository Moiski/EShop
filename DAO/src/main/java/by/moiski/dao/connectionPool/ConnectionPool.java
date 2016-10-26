package by.moiski.dao.connectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import by.moiski.dao.impl.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

	Logger log = Logger.getLogger(UserDaoImpl.class);

	private static DataSource dataSource;

	static {
		try {
			Context initContext = new InitialContext();
			dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/EShop");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private ConnectionPool() {
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}

	public static void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}
	}

}
