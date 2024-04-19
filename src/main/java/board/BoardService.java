package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class BoardService {
	private final BoardDAO dao;
	public BoardService(SqlSession session) {
		this.dao = new BoardDAO(session);
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
