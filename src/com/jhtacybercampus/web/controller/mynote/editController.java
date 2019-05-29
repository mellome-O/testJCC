package com.jhtacybercampus.web.controller.mynote;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.oracle.OracleNoticeDao;
import com.newlecture.web.entity.Notice;

//메소드가없으면 405?
@WebServlet("/mynote/edit")
public class editController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Integer id = Integer.parseInt(request.getParameter("id"));
	
//페이지 넘어갈 때 2가지
//포워드는 다음 이어서 작업할 것이 있을 때 
//리드렉트는 작업이 끝나고 새로운 곳으로 가고싶을떄 사용

//출력시 한글출력이 깨지는경우
//서버에서 한글을 지원하지 않는 문자코드로인코딩한경우
//서버에서는 utf-8로 인코딩해서보냈지만 브라우저가 다른코드로잘못해석한경우
//response.setCharacterEncoding("UTF-8");
//response.setContentType("type=");
//입력할 때 깨지는 경우에는 입력받기 전에 request.setCharacterEncoding("UTF-8"); 를 넣어줘야한다

//서블릿 필터
//톰캣을 통해 서블릿들이 실행되는데
//실행할 때 중간에 조사,동일한 일 집중
//출력이나 입력에 대한 것을 미리 설정해놓는 것


//선생님 풀이
		
MynoteDao mynoteDao  = new OracleMynoteDao();
		
Mynote mynote = new Mynote();
mynote.setId(id);
mynote.setTitle(title);
mynote.setContent(content);
		
int result=0;
try {
	result = mynoteDao.update(mynote);
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
System.out.println("wkrna"+result);
if(result !=1)
	response.sendRedirect("error");
else
	response.sendRedirect("detail?id="+id);
		
	}		
		
		
//		NoticeDao noticeDao  = new OracleNoticeDao();
//		
//		try {
//			noticeDao.update(new Notice(id,title,content,"",null,0));
//			request.setAttribute("notice",noticeDao.get(id));
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		request.getRequestDispatcher("../WEB-INF/view/notice/detail.jsp").forward(request, response);
//	}
	
	
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //hello?cnt3 ->3?
    //hello? -> ""
    //hello -> null
    	
    	
      MynoteDao mynoteDao  = new OracleMynoteDao();
      
      Integer id = Integer.parseInt(request.getParameter("id"));
  
       try {
		request.setAttribute("mynote",  mynoteDao.get(id));
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       request.getRequestDispatcher("../WEB-INF/view/mynote/edit.jsp").forward(request, response);

   }
   
   
}