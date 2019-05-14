package PlayerInfo.server;

public class Player {
	private int playerID;
	private String name;
	private String nickname;
	private String phone;
	private String birthday;
	private String number;
	private String position;
	private String email;
	private String teamID;

	// 資料的建構式 命名為Player
	public Player(int playerID, String name, String nickname, String phone, String birthday, String number,
			String position, String email, String teamID) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
		this.birthday = birthday;
		this.number = number;
		this.position = position;
		this.email = email;
		this.teamID = teamID;
	}

	public Player(int playerID, String name, String nickname, String phone, String birthday, String number,
			String position, String email) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
		this.birthday = birthday;
		this.number = number;
		this.position = position;
		this.email = email;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

}
