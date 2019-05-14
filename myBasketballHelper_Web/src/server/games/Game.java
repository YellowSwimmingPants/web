package server.games;


public class Game {
	private int id;
	private String gameName;
	private String gameDate;
	
	public Game(int id, String gameName, String gameDate) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.gameDate = gameDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameDate() {
		return gameDate;
	}

	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
	}
	
}

