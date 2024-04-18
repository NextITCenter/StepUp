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
 * Servlet implementation class MemberPasswordServlet
 */
@WebServlet("/member/password")
public class MemberPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteId = request.getParameter("id");
		request.setAttribute("deleteId", deleteId);
		request.getRequestDispatcher("/WEB-INF/views/member/password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPassword = request.getParameter("currentPassword");
		String password = request.getParameter("password");
		String deleteId = request.getParameter("id");
		// id와 currentPassword에 해당하는 member를 조회하여 가져온다.
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "java", "oracle21c");
			String sql = "select id, name, email from member where id = ? and password = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, deleteId);
			statement.setString(2, currentPassword);
			resultSet = statement.executeQuery();
			MemberVO vo = null;
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				vo = new MemberVO(id, name, null, email);
			}
			if (vo != null) {
				// 비밀번호 변경
				String updateSql = "update member set password = ?, modify_date = sysdate where id = ?";
				statement = connection.prepareStatement(updateSql);
				statement.setString(1, password);
				statement.setString(2, deleteId);
				
				int executeUpdate = statement.executeUpdate();
				if (executeUpdate > 0) {
					response.sendRedirect("/member/list");
				}
			} else {
				// 비밀번호 변경페이지로 그대로 이동
				request.setAttribute("deleteId", deleteId);
				request.getRequestDispatcher("/WEB-INF/views/member/password.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
