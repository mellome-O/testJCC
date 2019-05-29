package com.jhtacybercampus.web.dao.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jhtacybercampus.dao.MynoteDao;
import com.jhtacybercampus.web.entity.Mynote;
import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;

public class OracleMynoteDao implements MynoteDao {


	@Override
	public Mynote get(int id) throws ClassNotFoundException, SQLException {

		Mynote mynote = null;

		String sql = "select * from notice where id=" + id;

		String url = "jdbc:oracle:thin:@192.168.0.4:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"JCC\"", "1234");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql); //꺼내오기

		if (rs.next()) {
			mynote = new Mynote(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getString("writer_id"),
					rs.getDate("regdate"),
					rs.getInt("hit")
					);
		}

		rs.close();
		st.close();
		con.close();

		return mynote;

	}


	@Override
	public int insert(Mynote notice) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		
		String sql = "INSERT INTO NOTICE(id, title, content, writer_id)" 
		+ "values(NOTICE_SEQ.NEXTVAL, ? ,?,'mello')";
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());

		result = st.executeUpdate(); //삽입,삭제
		
		st.close();
		con.close();

		return result;
	}

	@Override
	public int update(Mynote notice) throws ClassNotFoundException, SQLException {
		 int result = 0;

//	      String sql = String.format("update notice set id=%d, title='%s', writer_id = '%s' where id = 12345",
//	            notice.getId(), notice.getTitle(), notice.getWriterId());

		 String sql = "UPDATE NOTICE SET title=?, content=? WHERE ID =?";
					
	      String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
//	      Statement st = con.createStatement();
//	      /*
//	       * ResultSet a = st.executeQuery(sql); //만들어진 테이블 조회해서 가져오기
//	       */
//	       result = st.executeUpdate(sql); 연동된 DB에 입력하기
	      
	    PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setInt(3, notice.getId());

			result = st.executeUpdate(); //삽입,삭제
	      
		    st.close();
		    con.close();
	
		    return result;
	   }

	

	@Override
	public int delete(int id) throws SQLException, ClassNotFoundException {
		 int result = 0;

//	      String sql = String.format("delete from notice where id=%d", id);
	      String sql="delete notice where id=?";

	      String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
	     // Statement st = con.createStatement();
	      /*
	       * ResultSet a = st.executeQuery(sql); //만들어진 테이블 조회해서 가져오기
	       */
	      PreparedStatement st = con.prepareStatement(sql);
	      st.setInt(1, id);
	      result = st.executeUpdate();// 연동된 DB에 입력하기
	      
	      st.close();
	      con.close();

	      return result;

	}


	@Override
	public Object getList() {
		// TODO Auto-generated method stub
		return null;
	}

}