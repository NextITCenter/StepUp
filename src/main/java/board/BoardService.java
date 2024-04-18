package board;

import java.util.List;

import javax.sql.DataSource;

public class BoardService {
	BoardDAO dao;
	public BoardService(DataSource dataSource) {
		this.dao = new BoardDAO(dataSource); 
	}
	public List<BoardVO> getBoardList() {
		return dao.getBoardList();
	}
	public BoardVO getBoard(int searchNo) {
		return dao.getBoard(searchNo);
	}
	public int insertBoard(BoardVO vo) {
		return dao.insertBoard(vo);
	}
	public int updateBoard(BoardVO vo) {
		return dao.updateBoard(vo);
	}
	public int deleteBoard(int deleteNo) {
		return dao.deleteBoard(deleteNo);
	}
}
