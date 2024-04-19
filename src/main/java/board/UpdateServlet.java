package board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

@WebServlet("/board/update")
public class UpdateServlet extends HttpServlet {
    private BoardService service;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        SqlSession sqlSession = (SqlSession) context.getAttribute("sqlSession");
        service = new BoardService(sqlSession);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int searchNo = Integer.parseInt(request.getParameter("no"));
        // id에 해당하는 데이터를 조회하여 가져온다.
        // request에 회원목록 데이터를 보관한다.

        BoardVO vo = service.getBoard(searchNo);

        request.setAttribute("board", vo);

        request.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int no = Integer.parseInt(request.getParameter("no"));
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        int updatebo = service.updateBoard(new BoardVO(no, writer, title, content));
        if (updatebo > 0) {
            // 등록 성공
            response.sendRedirect("/board/list");
        } else {
            // 등록 실패
            request.setAttribute("msg", "수정 실패");
            request.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(request, response);
        }
    }
}
