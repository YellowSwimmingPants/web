package PlayerInfo.server;
import PlayerInfo.server.ImageUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
@WebServlet("/PlayerServlet")

public class PlayerServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
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
		// 將輸入資料列印出來除錯用
//		 System.out.println("input: " + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (playerDao == null) 		{
			playerDao = new PlayerDaoMySqllmpl();
		}
		
		String action = jsonObject.get("action").getAsString();
		
		if (action.equals("getAll")) {
			String teamID = jsonObject.get("teamID").getAsString();
			List<Player> players = playerDao.getAll(teamID);
			writeText(response, gson.toJson(players));
		} else if (action.equals("getImage")) {
			OutputStream os = response.getOutputStream();
			int playerID = jsonObject.get("playerID").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = playerDao.getImage(playerID);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
				os.write(image);
			}
		} else if (action.equals("playerInsert") || action.equals("playerUpdate")) {
			String playerJson = jsonObject.get("player").getAsString();
			System.out.println("playerJson = " + playerJson);
			Player player = gson.fromJson(playerJson, Player.class);
			byte[] image = null;
			// 檢查是否有上傳圖片
			if (jsonObject.get("imageBase64") != null) {
				String imageBase64 = jsonObject.get("imageBase64").getAsString();
				if (imageBase64 != null && !imageBase64.isEmpty()) {
					image = Base64.getMimeDecoder().decode(imageBase64);
				}
			}
			int count = 0;
			if (action.equals("playerInsert")) {
				count = playerDao.insert(player, image);
			} else if (action.equals("playerUpdate")) {
				count = playerDao.update(player, image);
			} 
			writeText(response, String.valueOf(count));
		} else if (action.equals("playerDelete")) {
			int playerId = jsonObject.get("playerId").getAsInt();
			int count = playerDao.delete(playerId);
			writeText(response, String.valueOf(count));
		} else if (action.equals("findById")) {
			int playerID = jsonObject.get("playerID").getAsInt();
			Player player = playerDao.findById(playerID);
			writeText(response, gson.toJson(player));	
			
		}else if (action.equals("getData")) {
			String playerID = jsonObject.get("playerID").getAsString();
			System.out.print("123"+playerID);
			GameDataCount gameDataCount = playerDao.gameDataCount(playerID);
			System.out.print(gameDataCount);
			writeText(response, gson.toJson(gameDataCount));
		} else {
			writeText(response, "");
		}
	}
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用;
		System.out.println("output: " + outText);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (playerDao == null) {
			playerDao = new PlayerDaoMySqllmpl();
		}

		
	}


}
