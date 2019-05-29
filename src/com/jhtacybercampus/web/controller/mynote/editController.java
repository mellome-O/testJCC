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

//�޼ҵ尡������ 405?
@WebServlet("/mynote/edit")
public class editController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Integer id = Integer.parseInt(request.getParameter("id"));
	
//������ �Ѿ �� 2����
//������� ���� �̾ �۾��� ���� ���� �� 
//���巺Ʈ�� �۾��� ������ ���ο� ������ ��������� ���

//��½� �ѱ������ �����°��
//�������� �ѱ��� �������� �ʴ� �����ڵ�����ڵ��Ѱ��
//���������� utf-8�� ���ڵ��ؼ��������� �������� �ٸ��ڵ���߸��ؼ��Ѱ��
//response.setCharacterEncoding("UTF-8");
//response.setContentType("type=");
//�Է��� �� ������ ��쿡�� �Է¹ޱ� ���� request.setCharacterEncoding("UTF-8"); �� �־�����Ѵ�

//���� ����
//��Ĺ�� ���� �������� ����Ǵµ�
//������ �� �߰��� ����,������ �� ����
//����̳� �Է¿� ���� ���� �̸� �����س��� ��


//������ Ǯ��
		
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