package cn.niyulu.factory;

import cn.niyulu.service.StudentService;

import cn.niyulu.service.TeacherService;
import cn.niyulu.serviceimpl.StudentServiceImpl;
import cn.niyulu.serviceimpl.TeacherServiceImpl;

public class ServiceFactory {
	public static StudentService getStudentServiceInstance() {
		return new StudentServiceImpl();
	}
	public static TeacherService getTeacherServiceInstance() {
		return new TeacherServiceImpl();
	}
}
