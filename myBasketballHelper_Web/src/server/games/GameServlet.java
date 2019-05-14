package server.games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import PlayerInfo.server.GameDataCount;
import PlayerInfo.server.Player;
import PlayerInfo.server.PlayerDao;

@SuppressWarnings("serial")
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	GameDao gameDao = null;
	GameDataDao gameDataDao = null;
	PlayerDao playerDao = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
//		 將輸入資料列印出來除錯用
		System.out.println("input: " + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (gameDao == null) {
			gameDao = new GameDaoMySqlImpl();
		}
		if (gameDataDao == null) {
			gameDataDao = new GameDataDaoMySqlImpl();
		}

		String action = jsonObject.get("action").getAsString();

		if (action.equals("getAll")) {

			List<Game> games = gameDao.getAll();
			writeText(response, gson.toJson(games));
		} else if (action.equals("gameUpdate")) {
			String gameJson = jsonObject.get("game").getAsString();
			System.out.println("gameJson = " + gameJson);
			Game game = gson.fromJson(gameJson, Game.class);
			int count = 0;
			count = gameDao.update(game);
//			if (action.equals("gameInsert")) {
//				count = gameDao.insert(game);
//			} else if (action.equals("gameUpdate")) {
//				count = gameDao.update(game);
//			}
			writeText(response, String.valueOf(count));
		} else if (action.equals("gameDelete")) {
			int count = 0;
			int gameId = jsonObject.get("gameId").getAsInt();
			count = gameDao.delete(gameId);
			if (count != 0) {
				gameDataDao.delete(gameId);
			}
			writeText(response, String.valueOf(count));
		} else if (action.equals("findById")) {
			int id = jsonObject.get("id").getAsInt();
			Game game = gameDao.findById(id);
			writeText(response, gson.toJson(game));
		} else if (action.equals("gameAndGameDataInsert")) {
			String gameDataJson = jsonObject.get("gameDatas").getAsString();
			System.out.println("gameDataJson = " + gameDataJson);
			List<GameDataCount> gameDatas = gson.fromJson(gameDataJson, new TypeToken<List<GameDataCount>>() {
			}.getType());

			String gameJson = jsonObject.get("game").getAsString();
			System.out.println("gameJson = " + gameJson);
			Game game = gson.fromJson(gameJson, Game.class);
			
			int gameId = 0;
			int count = 0;

			gameId = gameDao.insert(game);
			if (gameId > 0) {
				count = gameDataDao.insert(gameId, gameDatas);
				if(count == 0) {
					int result = 0;
					gameDao.delete(gameId);
					System.out.println("result = " + result);
				}
			} else {
				// insert失敗時要刪除最後一筆資料
			}
			// 有較嚴謹的java api能判斷 當複數條件滿足時才執行sql語法
			writeText(response, String.valueOf(count));
		} else if (action.equals("gameDataUpdate")) {
			String gameDataJson = jsonObject.get("gameData").getAsString();
			System.out.println("gameDataJson = " + gameDataJson);
			GameDataCount gameData = gson.fromJson(gameDataJson, GameDataCount.class);
			int count = 0;
			count = gameDataDao.update(gameData);
			writeText(response, String.valueOf(count));
		} else if (action.equals("findByGameData")) {
			int id = jsonObject.get("id").getAsInt();
			Game game = gameDao.findById(id);
			writeText(response, gson.toJson(game));
		} else if (action.equals("getGamePlayer")) {
			int gameID = jsonObject.get("gameID").getAsInt();
			List<Player> players = gameDataDao.getGamePlayer(gameID);
			writeText(response, gson.toJson(players));
		} else if (action.equals("getSingleData")) {
			int playerID = jsonObject.get("playerID").getAsInt();
			int gameID = jsonObject.get("gameID").getAsInt();
			GameDataCount gameData = gameDataDao.getSingleGameData(playerID, gameID);
//			GameDataCount gameData = gson.fromJson(gameDataJson, GameDataCount.class);
			writeText(response, gson.toJson(gameData));
		} else if (action.equals("getSinglePeriodData")) {
			int playerID = jsonObject.get("playerID").getAsInt();
			int gameID = jsonObject.get("gameID").getAsInt();
			int period = jsonObject.get("period").getAsInt();
			GameDataCount gameData = gameDataDao.getSinglePeriodData(playerID, gameID, period);
			writeText(response, gson.toJson(gameData));
		}	else if (action.equals("getPlayerEachPeriod")) {
			int gameID = jsonObject.get("gameID").getAsInt();
			int period = jsonObject.get("period").getAsInt();
			List<Player> players = gameDataDao.getPlayerEachPeriod(gameID, period);
			writeText(response, gson.toJson(players));
		} else {
			writeText(response, "");
		}
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output: " + outText);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (gameDao == null) {
			gameDao = new GameDaoMySqlImpl();
		}
		List<Game> games = gameDao.getAll();
		writeText(response, new Gson().toJson(games));
	}

}
