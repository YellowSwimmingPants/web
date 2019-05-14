package manager;

import static main.Common.CLASS_NAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.Common;

public class ManagerDaoMySqlImpl implements ManagerDao {

	public ManagerDaoMySqlImpl() {
		super();
		try {
			Class.forName(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int createTeam(String teamInfo, String userAccount) {
		int count = 0;
		String sql = "UPDATE UserInfo SET teamInfo = ?, priority = '1' WHERE userAccount = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, teamInfo);
			ps.setString(2, userAccount);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int joinTeam(String teamInfo, String userAccount) {
		int count = 0;
		String sql = "UPDATE UserInfo SET teamInfo = ?, priority = '0' WHERE userAccount = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, teamInfo);
			ps.setString(2, userAccount);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int quitTeam(String userAccount) {
		int count = 0;
		String sql = "UPDATE userInfo SET teamInfo = '', priority = '0' WHERE userAccount = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, userAccount);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int deleteTeam(String teamInfo) {
		int count = 0;
		String sql = "UPDATE userInfo SET teamInfo = '', priority = '0' WHERE teamInfo = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, teamInfo);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int teamExists(String teamInfo) {
		int count = 0;
		String sql = "SELECT teamInfo FROM UserInfo WHERE teamInfo = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, teamInfo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = 1;
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
