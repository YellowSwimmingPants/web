package user;

public class User {
	private int userId;
	private String userAccount;
	private String userPassword;
	private String userName;
	private String email;
	private int priority;
	private String teamInfo;
	
	public User(int userId, String userAccount, String userPassword, String userName,String email, int priority, String teamInfo) {
		super();
		this.userId = userId;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userName = userName;
		this.email = email;
		this.priority = priority;
		this.teamInfo = teamInfo;
	}

	public User(String userAccount, String userPassword, String userName, String email) {
		super();
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userName = userName;
		this.email = email;
	}
	
	public User(String userAccount, String userName, int priority) {
		super();
		this.userAccount = userAccount;
		this.userName = userName;
		this.priority = priority;
	}
	
	public User(String userAccount, String userName) {
		super();
		this.userAccount = userAccount;
		this.userName = userName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User)) {
			return false;
		} else {
			User user = (User) obj;
			return this.userAccount.equals(user.userPassword);
		}
	}
	
	@Override
	public int hashCode() {
		return userAccount.hashCode();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(String teamInfo) {
		this.teamInfo = teamInfo;
	}
}
