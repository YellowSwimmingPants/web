package PlayerInfo.server;
import java.util.List;

public interface PlayerDao {
	
	int insert(Player player, byte[] image);

	int update(Player player, byte[] image);

	int delete(int playerID);

	Player findById(int playerID);

	List<Player> getAll(String teamID);

	byte[] getImage(int playerID);
	
	GameDataCount gameDataCount(String playerID);

}



