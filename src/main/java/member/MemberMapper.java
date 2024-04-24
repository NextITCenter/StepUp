package member;

import java.util.List;

import common.SearchVO;

public interface MemberMapper {
	List<MemberVO> getMemberList(SearchVO vo);
	int getMembersTotalCount();
	MemberVO getMember(String id);
	int insertMember(MemberVO vo);
	int updateMember(MemberVO vo);
	int deleteMember(String id);
	MemberVO currentPassword(MemberVO vo);
	int changePassword(MemberVO vo);
}
