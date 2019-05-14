package billBoard;

import java.util.List;

public interface BillBoardDao {

	int insertBillBoard(BillBoard billBoard, String teamInfo);
	
	int deleteBillBoard(int billBoardId);
	
	List<BillBoard> getBillBoard(String teamInfo);
}
