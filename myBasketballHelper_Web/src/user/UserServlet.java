package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import main.ImageUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	private Map<String, String> users = new HashMap<>();

	public UserServlet() {
		super();
		//
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		JsonObject result = new JsonObject();
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
//		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		UserDao userDao = new UserDaoMySqlImpl();
		String outStr = "";
		String action = jsonObject.get("action").getAsString();
		if (action.equals("getAll")) {
			String teamInfo = jsonObject.get("teamInfo").getAsString();
			List<User> users = userDao.getUser(teamInfo);
			List<User> managers = userDao.getManager(teamInfo);
			writeText(response, gson.toJson(users));
			writeText(response, gson.toJson(managers));
		} else if (action.equals("login")) {
			String userAccount = jsonObject.get("userAccount").getAsString();
			String userPassword = jsonObject.get("userPassword").getAsString();
			User user = userDao.login(userAccount, userPassword);
			users = userDao.findById(userAccount);
			String account = users.get("account");
			String password = users.get("password");
			String userInfo = gson.toJson(user);
			if (account.equals(userAccount) && password.equals(userPassword)) {
				result.addProperty("success", "Yes");
				result.addProperty("userInfo", userInfo);
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("insertUser")) {
			String userJson = jsonObject.get("user").getAsString();
			User user = gson.fromJson(userJson, User.class);
			int count = 0;
			count = userDao.insert(user);
			if (count != 0) {
				String userAccount = user.getUserAccount();
				String userPassword = user.getUserPassword();
				User newUser = userDao.login(userAccount, userPassword);
				String userInfo = gson.toJson(newUser);
				result.addProperty("success", "Yes");
				result.addProperty("userInfo", userInfo);
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("userUpdate")) {
			String userJson = jsonObject.get("user").getAsString();
			User user = gson.fromJson(userJson, User.class);
			int count = 0;
			byte[] image = null;
			// 檢查是否有上傳圖片
			if (jsonObject.get("imageBase64") != null) {
				String imageBase64 = jsonObject.get("imageBase64").getAsString();
				if (imageBase64 != null && !imageBase64.isEmpty()) {
					image = Base64.getMimeDecoder().decode(imageBase64);
				}
			}
			String userAccount = user.getUserAccount();
			String userPassword = user.getUserPassword();
			users = userDao.findById(userAccount);
			count = userDao.update(user, image);
			if (count != 0) {
				User userLogin = userDao.login(userAccount, userPassword);
				String userInfo = gson.toJson(userLogin);
				result.addProperty("success", "Yes");
				result.addProperty("userInfo", userInfo);
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("deleteUser")) {
			String userAccount = jsonObject.get("userAccount").getAsString();
			int count = userDao.delete(userAccount);
			writeText(response, String.valueOf(count));
		} else if (action.equals("passwordUpdate")) {
			String userJson = jsonObject.get("user").getAsString();
			User user = gson.fromJson(userJson, User.class);
			int count = userDao.passwordUpdate(user);
			if (count != 0) {
				result.addProperty("success", "Yes");
				String userAccount = user.getUserAccount();
				String userPassword = user.getUserPassword();
				User userLogin = userDao.login(userAccount, userPassword);
				String userInfo = gson.toJson(userLogin);
				result.addProperty("userInfo", userInfo);
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("findById")) {
			String userAccount = jsonObject.get("userAccount").getAsString();
			String userPassword = jsonObject.get("userPassword").getAsString();
			users = userDao.findById(userAccount);
			String account = users.get("account");
			String password = users.get("password");
			if (account.equals(userAccount) && password.equals(userPassword)) {
				result.addProperty("success", "Yes");
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("getManager")) {
			String teamInfo = jsonObject.get("teamInfo").getAsString();
			List<User> managers = userDao.getManager(teamInfo);
			outStr = gson.toJson(managers);
		} else if (action.equals("getMember")) {
			String teamInfo = jsonObject.get("teamInfo").getAsString();
			List<User> members = userDao.getUser(teamInfo);
			outStr = gson.toJson(members);
		} else if (action.equals("changePriority")) {
			String userAccount = jsonObject.get("userAccount").getAsString();
			int priority = jsonObject.get("priority").getAsInt();
			int count = 0;
			count = userDao.changePriority(userAccount, priority);
			outStr = gson.toJson(count);
		} else if (action.equals("getImage")) {
			OutputStream os = response.getOutputStream();
			String userAccount = jsonObject.get("userAccount").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = userDao.getImage(userAccount);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
				os.write(image);
			}
		} else {
			writeText(response, "");
		}
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outStr);
		System.out.println("output: " + outStr);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("UserServlet is on");
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
