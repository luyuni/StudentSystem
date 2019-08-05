package cn.niyulu.dao;

import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;

public interface StudentDAO {
	/**
	 * 实现学生注册
	 * @param student 要注册的学生信息
	 * @return 成功返回true,失败返回false
	 */
	public boolean studentReg(Student student);
	/**
	 * 实现学生登录
	 * @param student 登录的学生对象
	 * @return 成功返回true,失败返回false
	 */
	public boolean studentLogin(Student student);
	/**
	 * 实现对成绩的查询
	 * @param student 要执行查询的学生对象
	 * @return 成功返回分数对象.失败返回null
	 */
	public Score showScore(Student student);
	/**
	 * 实现对自身信息的查看
	 * @param student 要查看的学生对象
	 * @return 成功返回学生对象
	 */
	public Student showInfo(Student student);
	/**
	 * 实现对自己信息的修改
	 * @param student 要执行修改的学生对象
	 * @return 成功返回true,失败返回false
	 */
	public boolean updateInfo(Student student);
}
