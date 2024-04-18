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
 * Servlet implementation class MemberViewServlet
 */
@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터로 id값을 받아와야 한다.
		String searchId = request.getParameter("id");
		// id에 해당하는 데이터를 조회하여 가져온다.
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "java", "oracle21c");
			String sql = "select id, name, password, email from member where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, searchId);
			resultSet = statement.executeQuery();
			MemberVO vo = null;
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");
				String email = resultSet.getString("email");
				vo = new MemberVO(id, name, password, email);
			}
			// request에 회원목록 데이터를 보관한다.
			request.setAttribute("member", vo);
			
			request.getRequestDispatcher("/WEB-INF/views/member/view.jsp").forward(request, response);
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
