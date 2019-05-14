package manager;

public interface ManagerDao {
	int createTeam(String teamInfo, String userAccount);
	
	int joinTeam(String teamInfo, String userAccount);
	
	int quitTeam(String userAccount);
	
	int deleteTeam(String teamInfo);
	
	int teamExists(String teamInfo);
}
