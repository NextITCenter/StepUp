<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label {
		display: block;
	}
	.error {
		color: red;
	}
</style>
</head>
<body>
<form action="/member/password" method="post" id="passwordForm">
	<label>현재 비밀번호:
		<input type="password" name="currentPassword" id="currentPassword">
	</label>
	<label>변경할 비밀번호:
		<input type="password" name="password" id="password">
	</label>
	<label>비밀번호 확인:
		<input type="password" id="confirmPassword">
	</label>
	<input type="hidden" name="id" value="${deleteId}">
	<button type="submit">변경</button>
	<button type="button">취소</button>
</form>
<script>
	const passwordForm = document.querySelector("#passwordForm")
	passwordForm.addEventListener("submit", function (e) {
		e.preventDefault();
		const password = document.querySelector("#password");
		const confirmPassword = document.querySelector("#confirmPassword");
		if (password.value == confirmPassword.value) {
			this.submit();
		} else {
			// "<span class='error'>비밀번호가 일치하지 않습니다.</span>"
			const span = document.createElement("span");
			span.classList.add("error");
			span.textContent = "비밀번호가 일치하지 않습니다.";
			confirmPassword.parentNode.appendChild(span);
			confirmPassword.select();
		}
	})
	
</script>
</body>
</html>