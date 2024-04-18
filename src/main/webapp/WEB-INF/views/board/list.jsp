<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글 목록" name="title"/>
</jsp:include>
<h2>게시판</h2>
<c:if test="${not empty sessionScope.member}">
<a href="/board/add">게시글 등록</a>
</c:if>
<table>
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일자</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="board" items="${boards}">
	<tr>
		<td>${board.no }</td>
		<td><a href="/board/view?no=${board.no}">${board.title }</a></td>
		<td>${board.writer }</td>
		<td>${board.createDate }</td>
		<td>${board.hits }</td>
	</tr>
	</c:forEach>
</table>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
