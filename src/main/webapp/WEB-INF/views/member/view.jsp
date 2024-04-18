<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원 정보" name="title"/>
</jsp:include>
<a href="/member/update?id=${member.id}">수정</a>
<a href="javascript:deleteItem('/member/delete?id=${member.id}');">삭제</a>
<a href="/member/password?id=${member.id}">비밀번호 수정</a>
<div>
	ID: ${member.id }
</div>
<div>
	이름: ${member.name }
</div>
<div>
	이메일: ${member.email }
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />