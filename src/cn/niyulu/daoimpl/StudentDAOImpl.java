package cn.niyulu.daoimpl;

import java.sql.Connection;

import java.sql.ResultSet;
import java.util.Vector;

import cn.niyulu.dao.StudentDAO;
import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;
import cn.niyulu.factory.ServiceFactory;
import cn.niyulu.util.AbstractDAOImpl;

public class StudentDAOImpl extends AbstractDAOImpl implements StudentDAO {

	public StudentDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean studentReg(Student student) {
		boolean flag = false;
        try {
        	Vector<Student> all = ServiceFactory.getTeacherServiceInstance().showAllStudent();
        	for (Student stu : all) {
				if(stu.getId().equals(student.getId())) {
					return false;
				}
			}
        	String sql = "INSERT INTO student(id,name,sex,department,profession,password)VALUES(?,?,?,?,?,?)";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setInt(1,student.getId());
	        super.pstmt.setString(2,student.getName());
	        super.pstmt.setString(3, student.getSex());
	        super.pstmt.setString(4, student.getDepartment());
	        super.pstmt.setString(5, student.getProfession());
	        super.pstmt.setString(6, student.getPassword());
	        
	        flag = super.pstmt.executeUpdate()>0;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return flag;
	}

	@Override
	public boolean studentLogin(Student student) {
		boolean flag = false;
        try {
        	String sql = "SELECT name FROM student WHERE id=? AND password=?";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setInt(1,student.getId());
	        super.pstmt.setString(2,student.getPassword());
	        ResultSet rs = super.pstmt.executeQuery();
	        if (rs.next()){
	        	student.setName(rs.getString(1));
	            flag = true;
	        }
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        return flag;
	}

	@Override
	public Score showScore(Student student) {
		Score score = new Score();
		try {
			String sql = "SELECT javass,databasess,css,linearalgebra FROM score WHERE student_id=?";
			super.pstmt = super.conn.prepareStatement(sql);
			super.pstmt.setInt(1, student.getId());
			ResultSet rs = super.pstmt.executeQuery();
			if(rs.next()) {
				score.setJava(rs.getDouble(1));
				score.setDatabase(rs.getDouble(2));
				score.setC(rs.getDouble(3));
				score.setLinearalgebra(rs.getDouble(4));
			}
		} catch(Exception e) {
        	e.printStackTrace();
        }
		
		return score;
	}

	@Override
	public Student showInfo(Student student) {
		Student studentInfo = new Student();
		try {
			String sql = "SELECT name,sex,department,profession,password FROM student WHERE id=?";
			super.pstmt = super.conn.prepareStatement(sql);
			super.pstmt.setInt(1, student.getId());
			ResultSet rs = super.pstmt.executeQuery();
			if(rs.next()) {
				studentInfo.setId(student.getId());
				studentInfo.setName(rs.getString(1));
				studentInfo.setSex(rs.getString(2));
				studentInfo.setDepartment(rs.getString(3));
				studentInfo.setProfession(rs.getString(4));
				studentInfo.setPassword(rs.getString(5));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentInfo;
	}

	@Override
	public boolean updateInfo(Student student) {
		boolean flag = false;
        try {
        	String sql = "UPDATE student SET name=?,sex=?,department=?,profession=?,password=? WHERE id=?";
	        super.pstmt = super.conn.prepareStatement(sql);
	       
	        super.pstmt.setString(1,student.getName());
	        super.pstmt.setString(2, student.getSex());
	        super.pstmt.setString(3, student.getDepartment());
	        super.pstmt.setString(4, student.getProfession());
	        super.pstmt.setString(5, student.getPassword());
	        super.pstmt.setInt(6,student.getId());
	        flag = super.pstmt.executeUpdate()>0;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return flag;
	}

}
