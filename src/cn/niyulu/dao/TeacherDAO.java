package cn.niyulu.dao;


import java.util.Vector;

import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;
import cn.niyulu.entity.Teacher;

public interface TeacherDAO {
	/**
	 * 教师的登录
	 * @param teacher 执行登录的教师对象
	 * @return 成功返回true,失败返回false
	 */
	public boolean teacherLogin(Teacher teacher);
	/**
	 * 教师注册
	 * @param teacher 执行注册的教师对象
	 * @return 成功返回true,失败返回false
	 */
	 public boolean teacherReg(Teacher teacher);
	 /**
	  * 更改密码
	  * @param teacher 执行更改密码的教师对象
	  * @return 成功返回true,失败返回false
	  */
	 public boolean updatePassword(Teacher teacher);
	 /**
	  * 修改学生成绩
	  * @param score
	  * @return
	  */
	 public boolean updateStuScore(Score score);
	 /**
	  * 删除指定学生信息
	  * @param student 执行删除的学生对象
	  * @return 成功返回true ,失败返回false
	  */
	 public boolean deleteStuInfo(Student student);
	 /**
	  * 查询指定学生成绩
	  * @param student 执行查询的学生对象
	  * @return 返回学生成绩对象
	  */
	 public Score showStudentScore(Student student);
	 /**
	  * 查找所有学生
	  * @return 所有学生对象
	  */
	 public Vector<Student> showAllStudent();
	 /**
	  * 找到所有老师    
	  * @return 返回所有教师对象
	  */
	 public Vector<Teacher> findAllTeacher();
}
