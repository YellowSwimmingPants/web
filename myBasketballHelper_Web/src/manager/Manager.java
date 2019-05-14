package manager;

public class Manager {
	private int id;
	private String name, account, priority;
	
	
	public Manager(int id, String name, String account, String priority) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.priority = priority;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
