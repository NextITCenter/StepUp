package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

/**
 * Servlet implementation class MemberPasswordServlet
 */
@WebServlet("/member/password")
public class MemberPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		SqlSession session = (SqlSession) servletContext.getAttribute("sqlSession");
		this.service = new MemberService(session);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteId = request.getParameter("id");
		request.setAttribute("deleteId", deleteId);
		request.getRequestDispatcher("/WEB-INF/views/member/password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPassword = request.getParameter("currentPassword");
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		// id와 currentPassword에 해당하는 member를 조회하여 가져온다.
		MemberVO vo = service.currentPassword(new MemberVO(id, currentPassword));
		if (vo != null) {
			vo.setPassword(password);
			int executeUpdate = service.changePassword(vo);
			if (executeUpdate > 0) {
				response.sendRedirect("/member/list");
			}
		} else {
			// 비밀번호 변경페이지로 그대로 이동
			request.setAttribute("deleteId", id);
			request.getRequestDispatcher("/WEB-INF/views/member/password.jsp").forward(request, response);
		}
	}

}
