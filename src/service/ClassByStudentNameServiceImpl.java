package service;

import entity.Student;

import java.util.List;


public class ClassByStudentNameServiceImpl {
    private List<Student> studentList;

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
    public List<Student> getStudentList() {
        return studentList;
    }

}
