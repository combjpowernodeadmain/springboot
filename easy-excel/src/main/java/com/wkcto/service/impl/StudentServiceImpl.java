package com.wkcto.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.wkcto.dao.StudentDao;
import com.wkcto.domain.Student;
import com.wkcto.listeners.ReadExcelListener;
import com.wkcto.model.PageInfo;
import com.wkcto.model.StudentModel;
import com.wkcto.model.ViewStudentModel;
import com.wkcto.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//创建业务层对象
@Service("useStudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Override
    public void addStudents(Student student) {
        int nums = studentDao.insertStudent(student);
    }




    @Override
    public void readExcel(File file) {
        try{
            // 使用easyexel读取excel文件
            InputStream in = new FileInputStream(file);
            //创建自定义的Listener对象
            ReadExcelListener listener = new ReadExcelListener();
            //创建EasyExcel的 ExcelReader
            ExcelReader reader = new ExcelReader(in,null,listener);
            //创建Sheet sheetNo参数表示第几个 ，从 1 开始
            Sheet sheet = new Sheet(1,1, StudentModel.class);
            //读取Sheet
            reader.read(sheet);

            //获取读取到的excel数据， 调用dao方法完成添加到mysql
            List<StudentModel> dataList = listener.getData();

            //处理List数据
            //saveExcelList(dataList);

            //使用mysql的批量操作
            saveBatchExcelList(dataList);



        }catch (Exception e){
            e.printStackTrace();
            //重新抛出异常
            throw new RuntimeException("入库有异常！！！");
        }
    }

    @Override
    public List<ViewStudentModel> queryStudents(PageInfo pageInfo) {
        return studentDao.selectStudents(pageInfo);
    }

    @Override
    public List<Student> allList() {
        return studentDao.allList();
    }

    @Override
    public Integer addBatchData(List<Student> list) {
        return studentDao.addBatchData(list);
    }

    @Override
    public Integer deleteBatchData(String ids) {
        return studentDao.deleteBatchData(ids);
    }

    @Override
    public Integer updateBatchData(List<Student> list) {
        return studentDao.updateBatchData(list);
    }


    public void saveExcelList(List<StudentModel> dataList){
        //调用Dao的insertStudentModel方法
        //小数据量， excel 几千行数据
        for(StudentModel sm : dataList){
            studentDao.insertStudentModel(sm);
            //每次insert 后提交事务， 效率不高。
        }
    }


    public void saveBatchExcelList(List<StudentModel> dataList){
        List<StudentModel> temp = new ArrayList<>();//待提交数据

        for(int i=0,len = dataList.size();i<len;i++){
            temp.add(dataList.get(i));
            if( i % 10 == 0){
                //调用Dao的insertStudentModel方法
                studentDao.insertBatch(temp);
                //重新初始
                temp = new ArrayList<>();
            }
        }

    }


}
