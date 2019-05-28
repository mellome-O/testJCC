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

@WebServlet("/notice/reg")
public class regController extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      
      NoticeDao noticeDao = new OracleNoticeDao();
      Notice notice = new Notice();
      notice.setTitle(title);
      notice.setContent(content);
      
      int result = 0;
      
      try {
         result = noticeDao.insert(notice);
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      if(result != 1)
         response.sendRedirect("../notice/error");
      else
         response.sendRedirect("../notice/list");
       
      
   }
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.getRequestDispatcher("/WEB-INF/view/notice/reg.jsp").forward(request, response);
   }
   
}