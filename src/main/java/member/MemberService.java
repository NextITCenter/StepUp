package member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberService {
	private MemberDAO dao;
	public MemberService(SqlSession session) {
		this.dao = new MemberDAO(session);
	}
	public List<MemberVO> getMemberList() {
		return dao.getMemberList();
	}
	public MemberVO getMember(String id) {
		return dao.getMember(id);
	}
	public int insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}
	public int updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}
	public int deleteMember(String id) {
		return dao.deleteMember(id);
	}
	public MemberVO currentPassword(MemberVO vo) {
		return dao.currentPassword(vo);
	}
	public int changePassword(MemberVO vo) {
		return dao.changePassword(vo);
	}
}
