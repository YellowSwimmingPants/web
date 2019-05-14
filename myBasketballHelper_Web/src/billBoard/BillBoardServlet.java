package billBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class BillBoardServlet
 */
@WebServlet("/BillBoardServlet")
public class BillBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	BillBoardDao billBoardDao = null;

	public BillBoardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();;
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject result = new JsonObject();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (billBoardDao == null) {
			billBoardDao = new BillBoardDaoMySqlImpl();
		}
		String outStr = "";
		String action = jsonObject.get("action").getAsString();
		if (action.equals("insertBillBoard")) {
			String userJson = jsonObject.get("billBoard").getAsString();
			BillBoard billBoard = gson.fromJson(userJson, BillBoard.class);
			String teamInfo = billBoard.getTeamInfo();
			int count = 0;
			count = billBoardDao.insertBillBoard(billBoard, teamInfo);
			if (count == 1) {
				result.addProperty("success", "Yes");
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("deleteBillBoard")) {
			int billBoardId = jsonObject.get("billBoardId").getAsInt();
			int count = 0;
			count = billBoardDao.deleteBillBoard(billBoardId);
			if (count == 1) {
				result.addProperty("success", "Yes");
			} else {
				result.addProperty("success", "No");
			}
			outStr = gson.toJson(result);
		} else if (action.equals("getBillBoard")) {
			String teamInfo = jsonObject.get("teamInfo").getAsString();
			List<BillBoard> billBoards = billBoardDao.getBillBoard(teamInfo);
			outStr = gson.toJson(billBoards);
		} else {
			writeText(response, "");
		}
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println(outStr);
		System.out.println("output: " + outStr);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (billBoardDao == null) {
			billBoardDao = new BillBoardDaoMySqlImpl();
		}
	}
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}

}
