package com.wkcto.service;

import com.wkcto.domain.Student;
import com.wkcto.model.PageInfo;
import com.wkcto.model.ViewStudentModel;

import java.io.File;
import java.util.List;

public interface StudentService {

    void addStudents(Student student);

    void readExcel(File file);

    List<ViewStudentModel> queryStudents(PageInfo pageInfo);

    List<Student> allList();

    Integer addBatchData(List<Student> list);

    Integer deleteBatchData(String ids);

    Integer updateBatchData(List<Student> list);
}
