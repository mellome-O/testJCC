package com.jhtacybercampus.web.controller.mynote;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhtacybercampus.dao.MynoteDao;
import com.jhtacybercampus.web.dao.oracle.OracleMynoteDao;
import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.oracle.OracleNoticeDao;
import com.newlecture.web.entity.Notice;

//메소드가없으면 405?
@WebServlet("/mynote/del")
public class delController extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    
    Integer id = Integer.parseInt(request.getParameter("id"));
    MynoteDao mynoteDao  = new OracleMynoteDao();

    try {
		mynoteDao.delete(id);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    response.sendRedirect("../mynote/list");
   
 }
}