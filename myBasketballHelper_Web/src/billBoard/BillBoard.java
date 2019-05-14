package billBoard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BillBoard {
	private int billBoardId;
	private Date date;
	private String title;
	private String content;
	private String type;
	private String teamInfo;

	public BillBoard(int billBoardId, Date date, String title, String content, String type, String teamInfo) {
		super();
		this.billBoardId = billBoardId;
		this.date = date;
		this.title = title;
		this.content = content;
		this.type = type;
		this.teamInfo = teamInfo;
	}

	public BillBoard(int billBoardId, Date date, String title, String content, String type) {
		super();
		this.billBoardId = billBoardId;
		this.date = date;
		this.title = title;
		this.content = content;
		this.type = type;
	}

	public BillBoard(Date date, String title, String content, String type, String teamInfo) {
		super();
		this.date = date;
		this.title = title;
		this.content = content;
		this.type = type;
		this.teamInfo = teamInfo;
	}

	public int getBillBoardId() {
		return billBoardId;
	}

	public void setBillBoardId(int billBoardId) {
		this.billBoardId = billBoardId;
	}

	public Date getDate() {
		return date;
	}

	public String getFormatedDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		return dateFormat.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(String teamInfo) {
		this.teamInfo = teamInfo;
	}
}
