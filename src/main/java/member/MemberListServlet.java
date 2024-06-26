package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.PaginationInfo;
import common.SearchVO;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		SqlSession session = (SqlSession) servletContext.getAttribute("sqlSession");
		this.service = new MemberService(session);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNo = request.getParameter("currentPageNo");
		int currentPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setPageSize(5);
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setCurrentPageNo(currentPageNo);
		SearchVO vo = new SearchVO();
		vo.setFirstRecordIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastRecordIndex(paginationInfo.getLastRecordIndex());
		
		int totalCount = service.getMembersTotalCount();
		paginationInfo.setTotalRecordCount(totalCount);
		List<MemberVO> list = service.getMemberList(vo);
		
		// request에 회원목록 데이터를 보관한다.
		request.setAttribute("members", list);
		request.setAttribute("pagination", paginationInfo);
		request.getRequestDispatcher("/WEB-INF/views/member/list.jsp").forward(request, response);
	}

}






