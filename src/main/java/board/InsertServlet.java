package board;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;

/**
 * controller 역할
 */
@MultipartConfig(location = "c:\\temp")
@WebServlet("/board/add")
public class InsertServlet extends HttpServlet {
	private BoardService service;

	@Override
	public void init() throws ServletException {
        ServletContext context = getServletContext();
        SqlSession sqlSession = (SqlSession) context.getAttribute("sqlSession");
        service = new BoardService(sqlSession);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// 모든 input 태그들은 multipart/form-data로 전송시 part에 담긴다.
		// 이때 파일을 제외한 나머지는 request.getParameter()로 가져오는게 편하고
		// 첨부파일만 getPart()를 통해 가져온다.
		Part part = request.getPart("uploadFile");
		// 업로드된 첨부파일의 파일 크기를 알고 싶으면
		long fileSize = part.getSize();
		// 업로드된 첨부파일의 이름을 알고 싶으면
		String fileName = part.getSubmittedFileName();
		System.out.println("file size: " + fileSize);
		System.out.println("file name: " + fileName);
		// 파일 쓰기
		part.write("c:\\temp\\" + fileName);
		part.delete();
		
//		String contentType = request.getContentType();
//		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
//			Collection<Part> parts = request.getParts();
//			for (Part part : parts) {
//				System.out.println("name: " + part.getName());
//				String type = part.getContentType();
//				if (part.getHeader("Content-Disposition").contains("filename=")) {
//					System.out.println("file size: " + part.getSize());
//					String fileName = part.getSubmittedFileName();
//					System.out.println("file name: " + fileName);
//					if (part.getSize() > 0) {
//						part.write("c:\\upload_temp\\" + fileName);
//						part.delete();
//					}
//				} else {
//					String value = request.getParameter(part.getName());
//					System.out.println("value: " + value);
//				}
//			}
//		}
		
		int insertBoard = service.insertBoard(new BoardVO(writer, title, content));
		if (insertBoard > 0) {
			// 등록 성공
			response.sendRedirect("/board/list");
		} else {
			// 등록 실패
			request.setAttribute("msg", "등록 실패");
			request.getRequestDispatcher("/WEB-INF/views/board/add.jsp").forward(request, response);
		}
	}

}
