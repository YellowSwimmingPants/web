package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import user.User;
import user.UserDao;
import user.UserDaoMySqlImpl;

/**
 * Servlet implementation class ManagersServlet
 */
@WebServlet("/ManagersServlet")
public class ManagersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	ManagerDao managerDao = null;

	public ManagersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		String outStr = "";
		JsonObject result = new JsonObject();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (managerDao == null) {
			managerDao = new ManagerDaoMySqlImpl();
		}
		UserDao userDao = new UserDaoMySqlImpl();
		String action = jsonObject.get("action").getAsString();
		int count = 0;
		if (action.equals("createTeam")) {
			String teamInfo = jsonObject.get("teamInfo").getAsString();
			String userAccount = jsonObject.get("userAccount").getAsString();
			String userPassword = jsonObject.get("userPassword").getAsString();
			count = managerDao.createTeam(teamInfo, userAccount);
			if (count != 0) {
				User user = userDao.login(userAccount, userPassword);
				String userInfo = gson.toJson(user);
				result.addProperty("success", "Yes");
				result.addProperty("userInfo", userInfo);
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("joinTeam")) {
			String teamInfo = jsonObject.get("teamInfo").getAsString();
			String userAccount = jsonObject.get("userAccount").getAsString();
			String userPassword = jsonObject.get("userPassword").getAsString();
			int teamExists = managerDao.teamExists(teamInfo);
			if (teamExists != 0) {
				count = managerDao.joinTeam(teamInfo, userAccount);
				if (count != 0) {
					User user = userDao.login(userAccount, userPassword);
					String userInfo = gson.toJson(user);
					result.addProperty("success", "Yes");
					result.addProperty("userInfo", userInfo);
				} else {
					result.addProperty("success", "No");
				}
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("exitTeam")) {
			String userAccount = jsonObject.get("userAccount").getAsString();
			count = managerDao.quitTeam(userAccount);
			outStr = gson.toJson(count);
		} else if (action.equals("quitTeam")) {
			String teamInfo = jsonObject.get("teamInfo").getAsString();
			count = managerDao.deleteTeam(teamInfo);
			outStr = gson.toJson(count);
		} else if (action.equals("teamExist")) {
			String teamInfo = jsonObject.get("teamInfo").getAsString();
			count = managerDao.teamExists(teamInfo);
			outStr = gson.toJson(count);
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
		response.getWriter().append("Served at: ").append(request.getContextPath());

		if (managerDao == null) {
			managerDao = new ManagerDaoMySqlImpl();
		}
//		writeText(response, new Gson().toJson(managers));
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
