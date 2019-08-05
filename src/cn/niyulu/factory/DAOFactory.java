package cn.niyulu.factory;

import java.sql.Connection;


import cn.niyulu.dao.StudentDAO;
import cn.niyulu.dao.TeacherDAO;
import cn.niyulu.daoimpl.StudentDAOImpl;
import cn.niyulu.daoimpl.TeacherDAOImpl;


public class DAOFactory {
	public static StudentDAO getStudentDAOInstance(Connection conn) {
		return new StudentDAOImpl(conn);
	}
	public static TeacherDAO getTeacherDAOInstance(Connection conn){
        return new TeacherDAOImpl(conn);
    } 
}
