package member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberDAO {
	private final SqlSession session;
	public MemberDAO(SqlSession session) {
		this.session = session;
	}
	
	public List<MemberVO> getMemberList() {
		return session.selectList("member.MemberDAO.getMemberList");
	}
	public MemberVO getMember(String id) {
		return session.selectOne("member.MemberDAO.getMember", id);
	}
	public int insertMember(MemberVO vo) {
		return session.insert("member.MemberDAO.insertMember", vo);
	}
	public int updateMember(MemberVO vo) {
		return session.update("member.MemberDAO.updateMember", vo);
	}
	public int deleteMember(String id) {
		return session.delete("member.MemberDAO.deleteMember", id);
	}
	public MemberVO currentPassword(MemberVO vo) {
		return session.selectOne("member.MemberDAO.currentPassword", vo);
	}
	public int changePassword(MemberVO vo) {
		return session.update("member.MemberDAO.changePassword", vo);
	}
}
