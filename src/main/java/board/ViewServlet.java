package board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

@WebServlet("/board/view")
public class ViewServlet extends HttpServlet {
    private BoardService service;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        SqlSession sqlSession = (SqlSession) context.getAttribute("sqlSession");
        service = new BoardService(sqlSession);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파라미터로 id값을 받아와야 한다.
        int searchNo = Integer.parseInt(request.getParameter("no"));
        BoardVO vo = service.getBoard(searchNo);
        request.setAttribute("board", vo);
        request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);
    }
}
