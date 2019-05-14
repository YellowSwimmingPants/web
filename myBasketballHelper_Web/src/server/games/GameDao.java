package server.games;

import java.util.List;
import java.util.Map;
import java.util.Set;

import PlayerInfo.server.GameDataCount;
import PlayerInfo.server.Player;

public interface GameDao {
	int insert(Game game);
	
	Map<Integer, Integer> insertID(int gameID, int playerIDs[]);

	int getPlayerIDs(int gameID, Set<Integer> playersID);

	int update(Game game);

	int delete(int id);

	Game findById(int id);

	List<Game> getAll();

}
