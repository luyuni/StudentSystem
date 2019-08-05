package cn.niyulu.serviceimpl;

import cn.niyulu.dbc.DatabaseConnection;
import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;
import cn.niyulu.factory.DAOFactory;
import cn.niyulu.service.StudentService;



public class StudentServiceImpl implements StudentService {
	private DatabaseConnection dbc = new DatabaseConnection(); 
	@Override
	public boolean studentLogin(Student student) {
		try {
			return DAOFactory.getStudentDAOInstance(this.dbc.getConn()).studentLogin(student);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}	
		
	}

	@Override
	public boolean studentReg(Student student) {
		try {
			return DAOFactory.getStudentDAOInstance(this.dbc.getConn()).studentReg(student);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
	}

	@Override
	public Score showScore(Student student) {
		try {
			return DAOFactory.getStudentDAOInstance(this.dbc.getConn()).showScore(student);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
	}

	@Override
	public Student showInfo(Student student) {
		try {
			return DAOFactory.getStudentDAOInstance(this.dbc.getConn()).showInfo(student);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
	}

	@Override
	public boolean updateInfo(Student student) {
		try {
			return DAOFactory.getStudentDAOInstance(this.dbc.getConn()).updateInfo(student);
		}catch(Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
	}
}
