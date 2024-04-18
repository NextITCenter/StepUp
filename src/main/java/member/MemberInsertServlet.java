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
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/member/add")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// /member/add.jsp로 forwarding
		request.getRequestDispatcher("/WEB-INF/views/member/add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		ServletContext servletContext = request.getServletContext();
		SqlSession session = (SqlSession) servletContext.getAttribute("sqlSession");
		MemberService service = new MemberService(session);
		int executeUpdate = service.insertMember(new MemberVO(id, name, password, email));
		if (executeUpdate > 0) {
			response.sendRedirect("/member/list");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/member/add.jsp").forward(request, response);
		}
	}

}
