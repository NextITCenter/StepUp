package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.SearchVO;

public class BoardService {
	private final BoardMapper mapper;
	public BoardService(SqlSession session) {
		this.mapper = session.getMapper(BoardMapper.class);
	}
	public int getBoardListCount(SearchVO vo) {
		return mapper.getBoardListCount(vo);
	}
	public List<BoardVO> getBoardList(SearchVO vo) {
		return mapper.getBoardList(vo);
	}
	public BoardVO getBoard(int searchNo) {
		return mapper.getBoard(searchNo);
	}
	public int insertBoard(BoardVO vo) {
		return mapper.insertBoard(vo);
	}
	public int updateBoard(BoardVO vo) {
		return mapper.updateBoard(vo);
	}
	public int deleteBoard(int deleteNo) {
		return mapper.deleteBoard(deleteNo);
	}
}
