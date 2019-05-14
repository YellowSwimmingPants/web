package PlayerInfo.server;

import static PlayerInfo.server.Common.CLASS_NAME;
import static PlayerInfo.server.Common.URL;
import static PlayerInfo.server.Common.USER;
import static PlayerInfo.server.Common.PASSWORD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoMySqllmpl implements PlayerDao {
	public PlayerDaoMySqllmpl() {
		super();
		try {
			Class.forName(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//球員數據動作
	@Override
	public GameDataCount gameDataCount(String playerID) {
		String sql = "SELECT sum(FT), sum(FTL), sum(FG), sum(FGL), sum(TPM), sum(TPL), sum(Foul), sum(OfnReb), sum(DefReb), sum(TurnOver), sum(Steal), sum(Block), sum(Assist) from GameDataCount WHERE playerID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		GameDataCount data = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, playerID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int FT = rs.getInt(1);
				int FTL = rs.getInt(2);
				int FG = rs.getInt(3);
				int FGL = rs.getInt(4);
				int TPM = rs.getInt(5);
				int TPL = rs.getInt(6);
				int Foul = rs.getInt(7);
				int OfnReb = rs.getInt(8);
				int DefReb = rs.getInt(9);
				int TurnOver = rs.getInt(10);
				int Steal = rs.getInt(11);
				int Block = rs.getInt(12);
				int Assist = rs.getInt(13);
				data = new GameDataCount(FT, FTL, FG, FGL, TPM, TPL, Foul, OfnReb, DefReb, TurnOver, Steal,
						Block, Assist);
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
		return data;
	}

//新增動作
	@Override
	public int insert(Player player, byte[] image) {
		int count = 0;
		String sql = "INSERT INTO Player" + "(name, nickname, phone, birthday, number, position, email, image , teamID) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, player.getName());
			ps.setString(2, player.getNickname());
			ps.setString(3, player.getPhone());
			ps.setString(4, player.getBirthday());
			ps.setString(5, player.getNumber());
			ps.setString(6, player.getPosition());
			ps.setString(7, player.getEmail());
			ps.setBytes(8, image);
			ps.setString(9, player.getTeamID());
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

////更新動作
	@Override
	public int update(Player player, byte[] image) {
		int count = 0;
		String sql = "";
		// image為null就不更新image欄位內容
		if (image != null) {
			sql = "UPDATE Player SET name = ?, nickname = ?, phone = ?, "
					+ "birthday = ?, number = ?, position = ?, email =?, image = ? WHERE playerID = ?;";
		} else {
			sql = "UPDATE Player SET name = ?, phoneNo = ?, address = ?, "
					+ "birthday = ?, number = ?, position = ?, email =?, WHERE playerID = ?;";
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, player.getName());
			ps.setString(2, player.getNickname());
			ps.setString(3, player.getPhone());
			ps.setString(4, player.getBirthday());
			ps.setString(5, player.getNumber());
			ps.setString(6, player.getPosition());
			ps.setString(7, player.getEmail());
			if (image != null) {
				ps.setBytes(8, image);
				ps.setInt(9, player.getPlayerID());
			} else {
				ps.setInt(8, player.getPlayerID());
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
	public int delete(int playerID) {
		int count = 0;
		String sql = "DELETE FROM Player WHERE playerID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, playerID);
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

//取得相片
	@Override
	public byte[] getImage(int playerID) {
		String sql = "SELECT image FROM Player WHERE playerID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		byte[] image = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, playerID);
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
	public Player findById(int playerID) {
		String sql = "SELECT name, nickname, phone, birthday, number, position, email FROM Player WHERE id = ?;";
		Connection conn = null;
		PreparedStatement ps = null;
		Player player = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, playerID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString(1);
				String nickname = rs.getString(2);
				String phone = rs.getString(3);
				String birthday = rs.getString(4);
				String number = rs.getString(5);
				String position = rs.getString(6);
				String email = rs.getString(7);
				String teamID = rs.getString(8);
				player = new Player(playerID, name, nickname, phone, birthday, number, position, email, teamID);
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
		return player;
	}

	//
	@Override
	public List<Player> getAll(String teamID) {
		String sql = "SELECT playerID, name, nickname, phone, birthday, number, position, email " + 
				"FROM Player " + 
				"WHERE teamID = ? " + 
				"ORDER BY  timestamp DESC;";
		Connection connection = null;
		PreparedStatement ps = null;
		List<Player> playerList = new ArrayList<Player>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, teamID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int playerID = rs.getInt(1);
				String name = rs.getString(2);
				String nickname = rs.getString(3);
				String phone = rs.getString(4);
				String birthday = rs.getString(5);
				String number = rs.getString(6);
				String position = rs.getString(7);
				String email = rs.getString(8);
				Player player = new Player(playerID, name, nickname, phone, birthday, number, position, email, teamID);
				playerList.add(player);
			}
			return playerList;
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
		return playerList;
	}
}
