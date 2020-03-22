package com.wkcto.dao;

import com.wkcto.domain.Student;
import com.wkcto.model.PageInfo;
import com.wkcto.model.StudentModel;
import com.wkcto.model.ViewStudentModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//创建Dao对象
@Repository
public interface StudentDao {

    int insertStudent(Student student);

    int insertStudentModel(StudentModel sm);

    int insertBatch(List<StudentModel> list);


    List<ViewStudentModel> selectStudents(PageInfo pageInfo);

    List<Student> allList();

    Integer addBatchData(@Param("list") List<Student> list);

    Integer deleteBatchData(@Param("ids") String ids);

    Integer updateBatchData(@Param("list") List<Student> list);
}
