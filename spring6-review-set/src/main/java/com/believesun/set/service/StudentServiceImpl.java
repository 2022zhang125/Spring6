package com.believesun.set.service;

import com.believesun.set.dao.StudentDaoImpl;

public class StudentServiceImpl {
    // 创建DAO层对象
    private StudentDaoImpl studentDao;

    public StudentDaoImpl getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDaoImpl studentDao) {
        this.studentDao = studentDao;
    }

    // 创建一个方法,调用其action方法
    public void getAction(){
        studentDao.action();
    }
}
