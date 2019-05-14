package server.games;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PlayerInfo.server.GameDataCount;
import PlayerInfo.server.Player;
import server.main.*;

import static PlayerInfo.server.Common.PASSWORD;
import static PlayerInfo.server.Common.URL;
import static PlayerInfo.server.Common.USER;
import static server.main.Common.CLASS_NAME;

import static server.main.Common.PASSWORD;

public class GameDataDaoMySqlImpl implements GameDataDao {

	public GameDataDaoMySqlImpl() {
		super();
		try {
			Class.forName(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(GameDataCount gameData) {
		int count = 0;
		String sql = "INSERT INTO GameDataCount (gameID, playerID, period, FT, FTL, FG, FGL, TPM, TPL, Foul, OfnReb, DefReb, TurnOver, Steal, Block, Assist) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, gameData.getGameID());
			ps.setInt(2, gameData.getPlayerID());
			ps.setInt(3, gameData.getPeriod());
			ps.setInt(4, gameData.getFT());
			ps.setInt(5, gameData.getFTL());
			ps.setInt(6, gameData.getFG());
			ps.setInt(7, gameData.getFGL());
			ps.setInt(8, gameData.getTPM());
			ps.setInt(9, gameData.getTPL());
			ps.setInt(10, gameData.getFoul());
			ps.setInt(11, gameData.getOfnReb());
			ps.setInt(12, gameData.getDefReb());
			ps.setInt(13, gameData.getTurnOver());
			ps.setInt(14, gameData.getSteal());
			ps.setInt(15, gameData.getBlock());
			ps.setInt(16, gameData.getAssist());
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
	public int insert(int gameId, List<GameDataCount> gameDatas) {
		int count = 0;
		String sql = "INSERT INTO GameDataCount (gameID, playerID, period, FT, FTL, FG, FGL, TPM, TPL, Foul, OfnReb, DefReb, TurnOver, Steal, Block, Assist) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = connection.prepareStatement(sql);

			for (int i = 0; i < gameDatas.size(); i++) {
				ps.setInt(1, gameId);
				ps.setInt(2, gameDatas.get(i).getPlayerID());
				ps.setInt(3, gameDatas.get(i).getPeriod());
				ps.setInt(4, gameDatas.get(i).getFT());
				ps.setInt(5, gameDatas.get(i).getFTL());
				ps.setInt(6, gameDatas.get(i).getFG());
				ps.setInt(7, gameDatas.get(i).getFGL());
				ps.setInt(8, gameDatas.get(i).getTPM());
				ps.setInt(9, gameDatas.get(i).getTPL());
				ps.setInt(10, gameDatas.get(i).getFoul());
				ps.setInt(11, gameDatas.get(i).getOfnReb());
				ps.setInt(12, gameDatas.get(i).getDefReb());
				ps.setInt(13, gameDatas.get(i).getTurnOver());
				ps.setInt(14, gameDatas.get(i).getSteal());
				ps.setInt(15, gameDatas.get(i).getBlock());
				ps.setInt(16, gameDatas.get(i).getAssist());
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
		return count;
	}

	@Override
	public int update(GameDataCount gameData) {
		int count = 0;
		String sql = "UPDATE GameDataCount SET FT = ?, FTL = ?, FG = ?, FGL = ?, TPM = ?, TPL = ?, Foul = ?, OfnReb = ?, DefReb = ?, TurnOver = ?, Steal = ?, Block = ?, Assist = ? WHERE (gameID = ?) AND (playerID = ?) AND (period = ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, gameData.getFT());
			ps.setInt(2, gameData.getFTL());
			ps.setInt(3, gameData.getFG());
			ps.setInt(4, gameData.getFGL());
			ps.setInt(5, gameData.getTPM());
			ps.setInt(6, gameData.getTPL());
			ps.setInt(7, gameData.getFoul());
			ps.setInt(8, gameData.getOfnReb());
			ps.setInt(9, gameData.getDefReb());
			ps.setInt(10, gameData.getTurnOver());
			ps.setInt(11, gameData.getSteal());
			ps.setInt(12, gameData.getBlock());
			ps.setInt(13, gameData.getAssist());
			ps.setInt(14, gameData.getGameID());
			ps.setInt(15, gameData.getPlayerID());
			ps.setInt(16, gameData.getPeriod());

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
		String sql = "DELETE FROM GameDataCount WHERE gameID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
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

//	@Override
//	public int delete(GameDataCount gameData) {
//		int count = 0;
//		String sql = "DELETE FROM GameDataCount WHERE gameID = ?;";
//		Connection connection = null;
//		PreparedStatement ps = null;
//		try {
//			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER, server.main.Common.PASSWORD);
//			ps = connection.prepareStatement(sql);
//			ps.setInt(1, gameData.getGameID());
//			count = ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ps != null) {
//					// When a Statement object is closed,
//					// its current ResultSet object is also closed
//					ps.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return count;
//	}

	@Override
	public GameDataCount findByGameData(GameDataCount gameData) {
		String sql = "SELECT FT, FTL, FG, FGL, TPM, TPL, Foul, OfnReb, DefReb, TurnOver, Steal, Block, Assist from GameDataCount WHERE (playerID = ?) AND (gameID = ?) AND (period = ?);";
		Connection conn = null;
		PreparedStatement ps = null;
		GameDataCount gameDataFound = null;
		try {
			conn = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, gameData.getPlayerID());
			ps.setInt(2, gameData.getGameID());
			ps.setInt(3, gameData.getPeriod());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ft = rs.getInt(1);
				int ftl = rs.getInt(2);
				int fg = rs.getInt(3);
				int fgl = rs.getInt(4);
				int tpm = rs.getInt(5);
				int tpl = rs.getInt(6);
				int foul = rs.getInt(7);
				int ofnReb = rs.getInt(8);
				int defReb = rs.getInt(9);
				int turnOver = rs.getInt(10);
				int steal = rs.getInt(11);
				int block = rs.getInt(12);
				int assist = rs.getInt(13);
				gameDataFound = new GameDataCount(ft, ftl, fg, fgl, tpm, tpl, foul, ofnReb, defReb, turnOver, steal,
						block, assist);
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
		return gameDataFound;
	}

	@Override
	public List<Player> getGamePlayer(int gameID) {
		String sql = "SELECT * FROM Player WHERE playerID IN (SELECT playerID from GameDataCount WHERE gameID = ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		List<Player> playerList = new ArrayList<Player>();
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, gameID);
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
				Player player = new Player(playerID, name, nickname, phone, birthday, number, position, email);
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

	@Override
	public GameDataCount getSingleGameData(int playerId, int gameID) {
		String sql = "SELECT sum(FT), sum(FTL), sum(FG), sum(FGL), sum(TPM), sum(TPL), sum(Foul), sum(OfnReb), sum(DefReb), sum(TurnOver), sum(Steal), sum(Block), sum(Assist) from GameDataCount WHERE (playerID = ?) AND (gameID = ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		GameDataCount data = null;
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, playerId);
			ps.setInt(2, gameID);
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
				data = new GameDataCount(FT, FTL, FG, FGL, TPM, TPL, Foul, OfnReb, DefReb, TurnOver, Steal, Block,
						Assist);
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

	@Override
	public List<Integer> gamePlayers(int gameID) {
		String sql = "SELECT * FROM Player WHERE playerID IN (SELECT playerID from GameDataCount WHERE gameID = ? AND period = ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		List<Integer> playersID = new ArrayList<Integer>();
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, gameID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int playerID = rs.getInt(1);
				playersID.add(playerID);
			}
			return playersID;
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
		return playersID;
	}

	@Override
	public List<Player> getPlayerEachPeriod(int gameID, int period) {
		String sql = "SELECT * FROM Player WHERE playerID IN (SELECT playerID from GameDataCount WHERE gameID = ? AND period = ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		List<Player> playerList = new ArrayList<Player>();
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, gameID);
			ps.setInt(2, period);
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
				Player player = new Player(playerID, name, nickname, phone, birthday, number, position, email);
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

	@Override
	public GameDataCount getSinglePeriodData(int playerID, int gameID, int period) {
		String sql = "SELECT * from GameDataCount WHERE (playerID = ?) AND (gameID = ?) AND (period = ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		GameDataCount data = null;
		try {
			connection = DriverManager.getConnection(server.main.Common.URL, server.main.Common.USER,
					server.main.Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, playerID);
			ps.setInt(2, gameID);
			ps.setInt(3, period);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int gameDataID = rs.getInt(1);
				int gid = gameID;
				int	pid = playerID;
				int	p = period;
				int FT = rs.getInt(5);
				int FTL = rs.getInt(6);
				int FG = rs.getInt(7);
				int FGL = rs.getInt(8);
				int TPM = rs.getInt(9);
				int TPL = rs.getInt(10);
				int Foul = rs.getInt(11);
				int OfnReb = rs.getInt(12);
				int DefReb = rs.getInt(13);
				int TurnOver = rs.getInt(14);
				int Steal = rs.getInt(15);
				int Block = rs.getInt(16);
				int Assist = rs.getInt(17);
				data = new GameDataCount(gameDataID, gid, pid, p, FT, FTL, FG, FGL,
						TPM, TPL, Foul, OfnReb, DefReb, TurnOver, Steal, Block, Assist);
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
}
