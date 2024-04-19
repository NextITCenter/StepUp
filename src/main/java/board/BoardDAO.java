package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class BoardDAO {
	private final SqlSession session;
	
	public BoardDAO(SqlSession session) {
		this.session = session;
	}
	public List<BoardVO> getBoardList() {
		return session.selectList("board.BoardDAO.getBoardList");
	}
	public BoardVO getBoard(int searchNo) {
		return session.selectOne("board.BoardDAO.getBoard", searchNo);
	}
	public int insertBoard(BoardVO vo) {
		return session.insert("board.BoardDAO.insertBoard", vo);
	}
	public int updateBoard(BoardVO vo) {
		return session.update("board.BoardDAO.updateBoard", vo);
	}
	public int deleteBoard(int deleteNo) {
		return session.delete("board.BoardDAO.deleteBoard", deleteNo);
	}
}
