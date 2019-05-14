package PlayerInfo.server;

public class GameDataCount {
	private int gameDataID;
	private int gameID;
	private int playerID;
	private int period;
	private int FT;
	private int FTL;
	private int FG;
	private int FGL;
	private int TPM;
	private int TPL;
	private int Foul;
	private int OfnReb;
	private int DefReb;
	private int TurnOver;
	private int Steal;
	private int Block;
	private int Assist;

	public GameDataCount(int gameDataID, int gameID, int playerID, int period, int FT, int FTL, int FG, int FGL,
			int TPM, int TPL, int Foul, int OfnReb, int DefReb, int TurnOver, int Steal, int Block, int Assist) {
		super();
		this.gameDataID = gameDataID;
		this.gameID = gameID;
		this.playerID = playerID;
		this.period = period;
		this.FT = FT;
		this.FTL = FTL;
		this.FG = FTL;
		this.FGL = FGL;
		this.TPM = TPM;
		this.TPL = TPL;
		this.Foul = Foul;
		this.OfnReb = OfnReb;
		this.DefReb = DefReb;
		this.TurnOver = TurnOver;
		this.Steal = Steal;
		this.Block = Block;
		this.Assist = Assist;
	}
	
	public GameDataCount(int gameID, int playerID, int period, int FT, int FTL, int FG, int FGL,
			int TPM, int TPL, int Foul, int OfnReb, int DefReb, int TurnOver, int Steal, int Block, int Assist) {
		super();
		this.gameID = gameID;
		this.playerID = playerID;
		this.period = period;
		this.FT = FT;
		this.FTL = FTL;
		this.FG = FTL;
		this.FGL = FGL;
		this.TPM = TPM;
		this.TPL = TPL;
		this.Foul = Foul;
		this.OfnReb = OfnReb;
		this.DefReb = DefReb;
		this.TurnOver = TurnOver;
		this.Steal = Steal;
		this.Block = Block;
		this.Assist = Assist;
	}

	public GameDataCount(int gameID, int period, int FT, int FTL, int FG, int FGL, int TPM, int TPL, int Foul,
			int OfnReb, int DefReb, int TurnOver, int Steal, int Block, int Assist) {
		super();
		this.gameID = gameID;
		this.period = period;
		this.FT = FT;
		this.FTL = FTL;
		this.FG = FG;
		this.FGL = FGL;
		this.TPM = TPM;
		this.TPL = TPL;
		this.Foul = Foul;
		this.OfnReb = OfnReb;
		this.DefReb = DefReb;
		this.TurnOver = TurnOver;
		this.Steal = Steal;
		this.Block = Block;
		this.Assist = Assist;
	}

	public GameDataCount(int FT, int FTL, int FG, int FGL, int TPM, int TPL, int Foul, int OfnReb, int DefReb,
			int TurnOver, int Steal, int Block, int Assist) {
		super();
		this.FT = FT;
		this.FTL = FTL;
		this.FG = FG;
		this.FGL = FGL;
		this.TPM = TPM;
		this.TPL = TPL;
		this.Foul = Foul;
		this.OfnReb = OfnReb;
		this.DefReb = DefReb;
		this.TurnOver = TurnOver;
		this.Steal = Steal;
		this.Block = Block;
		this.Assist = Assist;
	}

	public int getGameDataID() {
		return gameDataID;
	}

	public void setGameDataID(int gameDataID) {
		this.gameDataID = gameDataID;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getFT() {
		return FT;
	}

	public void setFT(int FT) {
		this.FT = FT;
	}

	public int getFTL() {
		return FTL;
	}

	public void setFTL(int FTL) {
		this.FTL = FTL;
	}

	public int getFG() {
		return FG;
	}

	public void setFG(int FG) {
		this.FG = FG;
	}

	public int getFGL() {
		return FGL;
	}

	public void setFGL(int FGL) {
		this.FGL = FGL;
	}

	public int getTPM() {
		return TPM;
	}

	public void setTPM(int TPM) {
		this.TPM = TPM;
	}

	public int getTPL() {
		return TPL;
	}

	public void setTPL(int TPL) {
		this.TPL = TPL;
	}

	public int getFoul() {
		return Foul;
	}

	public void setFoul(int Foul) {
		this.Foul = Foul;
	}

	public int getOfnReb() {
		return OfnReb;
	}

	public void setOfnReb(int OfnReb) {
		this.OfnReb = OfnReb;
	}

	public int getDefReb() {
		return DefReb;
	}

	public void setDefReb(int DefReb) {
		this.DefReb = DefReb;
	}

	public int getTurnOver() {
		return TurnOver;
	}

	public void setTurnOver(int TurnOver) {
		this.TurnOver = TurnOver;
	}

	public int getSteal() {
		return Steal;
	}

	public void setSteal(int Steal) {
		this.Steal = Steal;
	}

	public int getBlock() {
		return Block;
	}

	public void setBlock(int Block) {
		this.Block = Block;
	}

	public int getAssist() {
		return Assist;
	}

	public void setAssist(int Assist) {
		this.Assist = Assist;
	}

}
