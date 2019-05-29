package com.jhtacybercampus.dao;

import java.sql.SQLException;

import com.jhtacybercampus.web.entity.Mynote;

public interface MynoteDao {

	Object getList();

	int update(Mynote notice) throws ClassNotFoundException, SQLException;

	int insert(Mynote notice) throws ClassNotFoundException, SQLException;

	Mynote get(int id) throws ClassNotFoundException, SQLException;

	int delete(int id) throws SQLException, ClassNotFoundException;

}
