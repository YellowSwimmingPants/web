package billBoard;

import static main.Common.CLASS_NAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.Common;

public class BillBoardDaoMySqlImpl implements BillBoardDao {

	public BillBoardDaoMySqlImpl() {
		super();
		try {
			Class.forName(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertBillBoard(BillBoard billBoard, String teamInfo) {
		int count = 0;
		String sql = "INSERT INTO BillBoard(date, title, content, type, teamInfo) " + "VALUES(?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setDate(1, java.sql.Date.valueOf(billBoard.getFormatedDate()));
			ps.setString(2, billBoard.getTitle());
			ps.setString(3, billBoard.getContent());
			ps.setString(4, billBoard.getType());
			ps.setString(5, billBoard.getTeamInfo());
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
	public int deleteBillBoard(int billBoardId) {
		int count = 0;
		String sql = "DELETE FROM BillBoard WHERE billBoardId = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, billBoardId);
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
	public List<BillBoard> getBillBoard(String teamInfo) {
		String sql = "SELECT billBoardId, date, title, content, type FROM BillBoard WHERE teamInfo = ? ORDER BY date DESC;";
		Connection connection = null;
		PreparedStatement ps = null;
		List<BillBoard> billBoardList = new ArrayList<BillBoard>();
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, teamInfo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int billBoardId = rs.getInt(1);
				Date date = rs.getDate(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				String type = rs.getString(5);
				BillBoard billBoard = new BillBoard(billBoardId, date, title, content, type, teamInfo);
				billBoardList.add(billBoard);
			}
			return billBoardList;
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
		return billBoardList;
	}

}
