package server.games;

import static server.main.Common.CLASS_NAME;
import static server.main.Common.PASSWORD;
import static server.main.Common.URL;
import static server.main.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import PlayerInfo.server.GameDataCount;

public class GameDaoMySqlImpl implements GameDao {

	public GameDaoMySqlImpl() {
		super();
		try {
			Class.forName(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Game game) {
		int gameId = 0;
		String sql = "INSERT INTO BasketballHelper.Game (gameName, gameDate) " + "VALUES(?, ?);";
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, game.getGameName());
			ps.setString(2, game.getGameDate());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			while (generatedKeys.next()) {
				gameId = (int) generatedKeys.getLong(1);
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
		return gameId;
	}

	@Override
	public int update(Game game) {
		int count = 0;
		String sql = "UPDATE Game SET gameName = ?, gameDate = ? WHERE gameID = ?;";

		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, game.getGameName());
			ps.setString(2, game.getGameDate());
			ps.setInt(3, game.getId());

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
	public int delete(int id) {
		int count = 0;
		String sql = "DELETE FROM Game WHERE gameID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
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
	public Game findById(int id) {
		String sql = "SELECT gameName, gameDate FROM Game WHERE gameID = ? ORDER BY timestamp DESC;";
		Connection conn = null;
		PreparedStatement ps = null;
		Game game = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String gameName = rs.getString(1);
				String gameDate = rs.getString(2);
				game = new Game(id, gameName, gameDate);
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
		return game;
	}

	@Override
	public List<Game> getAll() {
		String sql = "SELECT gameID, gameName, gameDate FROM Game ORDER BY time DESC;";
		Connection connection = null;
		PreparedStatement ps = null;
		List<Game> gameList = new ArrayList<Game>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String gameName = rs.getString(2);
				String gameDate = rs.getString(3);
				Game game = new Game(id, gameName, gameDate);
				gameList.add(game);
			}
			return gameList;
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
		return gameList;
	}

	@Override
	public Map<Integer, Integer> insertID(int gameID, int[] playerIDs) {
		String sql = "INSERT INTO GamePlayer (gameID, playerID) VALUES(?, ?);";
		int count = 0;
		int key = 0;
		int playerID = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = ps.getGeneratedKeys();
			while (generatedKeys.next()) {
				key = (int) generatedKeys.getLong(1);
				}
			for(int i = 0; i < playerIDs.length; i++) {
			ps.setInt(1, key);
			ps.setInt(2, playerIDs[i]);
			count = ps.executeUpdate();
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
		return ids;
	}

	@Override
	public int getPlayerIDs(int gameID, Set<Integer> playersID) {
		String sql = "INSERT INTO BasketballHelper.GamePlayer (gameID, playerID) VALUES(?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			int player[] = null;
			for (int i = 0 ; i < playersID.size(); i++) {
//				player[i];
			}
			System.out.println("player = " + player);
			for (int x = 0; x < player.length; x++) {
				ps.setInt(1, gameID);
				ps.setInt(2, player[x]);
				count = ps.executeUpdate();
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
		return count;
	}

}