package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "java", "oracle21c");
			String sql = "insert into member (id, name, password, email) values (?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, name);
			statement.setString(3, password);
			statement.setString(4, email);
			// 조회(select): 결과 ResultSet이고 메소드명은 executeQuery();
			// 등록, 수정, 삭제: 결과가 int이고 성공 실패 여부만 리턴해줌 메소드명은 executeUpdate();
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				// 리다이렉트를 사용
				response.sendRedirect("/member/list");
			} else {
				request.getRequestDispatcher("/WEB-INF/views/member/add.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
