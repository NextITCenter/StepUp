package member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberService {
	private final MemberMapper mapper;
	public MemberService(SqlSession session) {
		this.mapper = session.getMapper(MemberMapper.class);
	}
	public List<MemberVO> getMemberList() {
		return mapper.getMemberList();
	}
	public MemberVO getMember(String id) {
		return mapper.getMember(id);
	}
	public int insertMember(MemberVO vo) {
		return mapper.insertMember(vo);
	}
	public int updateMember(MemberVO vo) {
		return mapper.updateMember(vo);
	}
	public int deleteMember(String id) {
		return mapper.deleteMember(id);
	}
	public MemberVO currentPassword(MemberVO vo) {
		return mapper.currentPassword(vo);
	}
	public int changePassword(MemberVO vo) {
		return mapper.changePassword(vo);
	}
}
