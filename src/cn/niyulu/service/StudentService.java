package cn.niyulu.service;

import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;

public interface StudentService {
	/**
	 * 实现学生的登录
	 * @param student 要执行登录操作的学生对象
	 * @return 成功返回true,失败返回false
	 */
	public boolean studentLogin(Student student);
	/**
	 * 实现学生信息的注册
	 * @param student 要执行注册的学生对象
	 * @return 成功返回true,失败返回false
	 */
	public boolean studentReg(Student student);
	/**
	 * 实现对自身成绩的查看
	 * @param student 要查看成绩的学生对象
	 * @return 成功返回分数对象,失败返回空
	 */
	public Score showScore(Student student);
	/**
	 * 实现对自身信息的查看
	 * @param student 要进行查看的学生对象
	 * @return 成功返回学生对象,失败返回null
	 */
	public Student showInfo(Student student);
	/**
	 * 实现对学生信息的更新
	 * @param student 要执行更新的学生对象
	 * @return 成功返回true,失败返回false
	 */
	public boolean updateInfo(Student student);
}
