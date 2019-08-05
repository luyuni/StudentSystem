package cn.niyulu.daoimpl;

import java.sql.Connection;


import java.sql.ResultSet;
import java.util.Vector;

import cn.niyulu.dao.TeacherDAO;
import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;
import cn.niyulu.entity.Teacher;
import cn.niyulu.util.AbstractDAOImpl;

public class TeacherDAOImpl extends AbstractDAOImpl implements TeacherDAO {

	public TeacherDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean teacherLogin(Teacher teacher) {
		boolean flag = false;
        try {
        	String sql = "SELECT name FROM teacher WHERE id=? AND password=?";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setInt(1,teacher.getId());
	        super.pstmt.setString(2,teacher.getPassword());
	        ResultSet rs = super.pstmt.executeQuery();
	        if (rs.next()){
	        	teacher.setName(rs.getString(1));
	            flag = true;
	        }
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        return flag;
	}

	@Override
	public boolean teacherReg(Teacher teacher) {
		boolean flag = false;
        try {
        	Vector<Teacher> all = this.findAllTeacher();
        	for (Teacher tea : all) {
				if(tea.getId().equals(teacher.getId())) {
					return false;
				}
			}
        	String sql = "INSERT INTO teacher(id,name,password)VALUES(?,?,?)";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setInt(1,teacher.getId());
	        super.pstmt.setString(2,teacher.getName());
	        super.pstmt.setString(3, teacher.getPassword());
	        
	        flag = super.pstmt.executeUpdate()>0;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return flag;
	}

	@Override
	public boolean updatePassword(Teacher teacher) {
		boolean flag = false;
        try {
        	String sql = "UPDATE teacher SET password=? WHERE id=?";
	        super.pstmt = super.conn.prepareStatement(sql);
	        
	        super.pstmt.setString(1, teacher.getPassword());
	        super.pstmt.setInt(2, teacher.getId());
	        flag = super.pstmt.executeUpdate()>0;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return flag;
	}

	@Override
	public boolean updateStuScore(Score score) {
		boolean flag = false;
        try {
        	String sql = "UPDATE score SET javass=?,databasess=?,css=?,linearalgebra=? WHERE id=?";
	        super.pstmt = super.conn.prepareStatement(sql);
	       
	        super.pstmt.setDouble(1,score.getJava());
	        super.pstmt.setDouble(2,score.getDatabase());
	        super.pstmt.setDouble(3,score.getC());
	        super.pstmt.setDouble(4,score.getLinearalgebra());
	        super.pstmt.setInt(5,score.getId());
	        flag = super.pstmt.executeUpdate()>0;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return flag;
	}

	@Override
	public Score showStudentScore(Student student) {
		Score score = new Score();
		try {
        	String sql = "SELECT id,javass,databasess,css,linearalgebra FROM score WHERE student_id=?";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setInt(1, student.getId());
	        ResultSet rs = super.pstmt.executeQuery();
	        if (rs.next()){
	        	score.setId(rs.getInt(1));
	        	score.setStudent_id(student);
	        	score.setJava(rs.getDouble(2));
	        	score.setDatabase(rs.getDouble(3));
	        	score.setC(rs.getDouble(4));
	        	score.setLinearalgebra(rs.getDouble(5));
	        }
	        
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return score;
	}

	@Override
	public Vector<Student> showAllStudent() {
		Vector<Student> all = new Vector<Student>();
		try {
			String sql = "SELECT id,name,sex,department,profession,password FROM student";
			super.pstmt = super.conn.prepareStatement(sql);
			ResultSet rs = super.pstmt.executeQuery();
			while (rs.next()){
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setSex(rs.getString(3));
				student.setDepartment(rs.getString(4));
				student.setProfession(rs.getString(5));
				student.setPassword(rs.getString(6));
			    all.add(student);
			}
		} catch(Exception e){
			e.printStackTrace();
		}		
		return all;
	}

	@Override
	public boolean deleteStuInfo(Student student) {
		boolean flag = false;
        try {
        	String sql = "DELETE FROM student WHERE id=?";
	        super.pstmt = super.conn.prepareStatement(sql);
	       
	        super.pstmt.setInt(1,student.getId());
	        flag = super.pstmt.executeUpdate()>0;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return flag;
	}

	@Override
	public Vector<Teacher> findAllTeacher() {
		Vector<Teacher> all = new Vector<Teacher>();
		try {
			String sql = "SELECT id,name,password FROM teacher";
			super.pstmt = super.conn.prepareStatement(sql);
			ResultSet rs = super.pstmt.executeQuery();
			while (rs.next()){
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setName(rs.getString(2));
				teacher.setPassword(rs.getString(3));
			    all.add(teacher);
			}
		} catch(Exception e){
			e.printStackTrace();
		}		
		return all;
	}

}
