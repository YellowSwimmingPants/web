package server.games;

import java.util.List;

import PlayerInfo.server.GameDataCount;
import PlayerInfo.server.Player;

public interface GameDataDao {
	int insert(GameDataCount gameData);
	
	int insert(int gameId, List<GameDataCount> gameDatas);

	int update(GameDataCount gameData);

	int delete(int id);

	GameDataCount findByGameData(GameDataCount gameData);

//	List<GameDataCount> getAll();

	List<Player> getGamePlayer(int gameID);
	
	GameDataCount getSingleGameData(int playerId, int gameID);
	
	List<Integer> gamePlayers(int gameID);
	
	List<Player> getPlayerEachPeriod(int gameID, int period);
	
	GameDataCount getSinglePeriodData(int playerID, int gameID, int period);

}
