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

@WebServlet("/mynote/reg")
public class regController extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      
      MynoteDao mynoteDao = new OracleMynoteDao();
      Mynote mynote = new Mynote();
      mynote.setTitle(title);
      mynote.setContent(content);
      
      int result = 0;
      
      try {
         result = mynoteDao.insert(mynote);
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      if(result != 1)
         response.sendRedirect("../mynote/error");
      else
         response.sendRedirect("../mynote/list");
       
      
   }
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.getRequestDispatcher("/WEB-INF/view/mynote/reg.jsp").forward(request, response);
   }
   
}