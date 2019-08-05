package cn.niyulu.serviceimpl;

import java.util.Vector;


import cn.niyulu.dbc.DatabaseConnection;
import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;
import cn.niyulu.entity.Teacher;
import cn.niyulu.factory.DAOFactory;
import cn.niyulu.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	private DatabaseConnection dbc = new DatabaseConnection(); 
	@Override
	public boolean teacherLogin(Teacher teacher) {
		try {
			return DAOFactory.getTeacherDAOInstance(this.dbc.getConn()).teacherLogin(teacher);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}	
	}

	@Override
	public boolean teacherReg(Teacher teacher) {
		try {
			return DAOFactory.getTeacherDAOInstance(this.dbc.getConn()).teacherReg(teacher);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}	
	}

	@Override
	public boolean updatePassword(Teacher teacher) {
		try {
			return DAOFactory.getTeacherDAOInstance(this.dbc.getConn()).updatePassword(teacher);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}	
	}

	@Override
	public boolean updateStuScore(Score score) {
		try {
			return DAOFactory.getTeacherDAOInstance(this.dbc.getConn()).updateStuScore(score);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}	
	}

	@Override
	public Score showStudentScore(Student student) {
		try {
			return DAOFactory.getTeacherDAOInstance(this.dbc.getConn()).showStudentScore(student);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}	
	}

	@Override
	public Vector<Student> showAllStudent() {
		try {
			return DAOFactory.getTeacherDAOInstance(this.dbc.getConn()).showAllStudent();
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}	
	}

	@Override
	public boolean deleteStuInfo(Student student) {
		try {
			return DAOFactory.getTeacherDAOInstance(this.dbc.getConn()).deleteStuInfo(student);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}	
	}

}
