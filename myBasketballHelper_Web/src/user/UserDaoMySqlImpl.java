package user;

import static main.Common.CLASS_NAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Common;

public class UserDaoMySqlImpl implements UserDao {
	private Map<String, String> users = new HashMap<>();

	public UserDaoMySqlImpl() {
		super();
		try {
			Class.forName(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(User user) {
		int count = 0;
		String sql = "INSERT INTO UserInfo(userAccount, userPassword, userName, email) " + "VALUES(?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUserAccount());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getEmail());
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
	public int update(User user, byte[] image) {
		int count = 0;
		String sql = "";
		if (image != null) {
			sql = "UPDATE UserInfo SET userName = ?, email = ?, image = ? WHERE userAccount = ?;";
		} else {
			sql = "UPDATE UserInfo SET userName = ?, email = ? WHERE userAccount = ?;";
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			if (image != null) {
				ps.setBytes(3, image);
				ps.setString(4, user.getUserAccount());
			} else {
				ps.setString(3, user.getUserAccount());
			}
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
	public int delete(String userAccount) {
		int count = 0;
		String sql = "DELETE FROM UserInfo WHERE userAccount = ?;";
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
	public List<User> getUser(String teamInfo) {
		String sql = "SELECT userId, userAccount, userPassword, userName, email, priority FROM UserInfo WHERE teamInfo = ? && priority = ? ORDER BY timestamp;";
		Connection connection = null;
		PreparedStatement ps = null;
		List<User> userList = new ArrayList<User>();
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, teamInfo);
			ps.setInt(2, 0);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(1);
				String userAccount = rs.getString(2);
				String userPassword = rs.getString(3);
				String userName = rs.getString(4);
				String email = rs.getString(5);
				int priority = rs.getInt(6);
				User user = new User(userId, userAccount, userPassword, userName, email, priority, teamInfo);
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	@Override
	public User login(String userAccount, String userPassword) {
		String sql = "SELECT userId, userAccount, userPassword, userName, email, priority, teamInfo FROM UserInfo WHERE userAccount = ? AND userPassword = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		User user = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, userAccount);
			ps.setString(2, userPassword);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int userId = rs.getInt(1);
				String account = rs.getString(2);
				String password = rs.getString(3);
				String userName = rs.getString(4);
				String email = rs.getString(5);
				int priority = rs.getInt(6);
				String teamInfo = rs.getString(7);
				if (teamInfo != null) {
					user = new User(userId, account, password, userName, email, priority, teamInfo);
				} else {
					user = new User(userId, account, password, userName, email, priority, "");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public byte[] getImage(String userAccount) {
		String sql = "SELECT image FROM UserInfo WHERE userAccount = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		byte[] image = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, userAccount);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				image = rs.getBytes(1);
			}
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
		return image;
	}

	@Override
	public List<User> getManager(String teamInfo) {
		String sql = "SELECT userId, userAccount, userPassword, userName, email, priority FROM UserInfo WHERE teamInfo = ? && priority = ? ORDER BY timestamp;";
		Connection connection = null;
		PreparedStatement ps = null;
		List<User> userList = new ArrayList<User>();
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, teamInfo);
			ps.setInt(2, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(1);
				String userAccount = rs.getString(2);
				String userPassword = rs.getString(3);
				String userName = rs.getString(4);
				String email = rs.getString(5);
				int priority = rs.getInt(6);
				User user = new User(userId, userAccount, userPassword, userName, email, priority, teamInfo);
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	@Override
	public Map<String, String> findById(String userAccount) {
		String sql = "SELECT userAccount, userPassword FROM UserInfo WHERE userAccount = ?;";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, userAccount);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userAccount = rs.getString(1);
				String userPassword = rs.getString(2);
				users.put("account", userAccount);
				users.put("password", userPassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public int passwordUpdate(User user) {
		String sql = "UPDATE userInfo SET userPassword = ? WHERE userAccount = ?;";
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserPassword());
			ps.setString(2, user.getUserAccount());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int changePriority(String userAccount, int priority) {
		String sql = "";
		if (priority == 1) {
			sql = "UPDATE userInfo SET priority = 0 WHERE userAccount = ?;";
		} else {
			sql = "UPDATE userInfo SET priority = 1 WHERE userAccount = ?;";
		}
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, userAccount);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
