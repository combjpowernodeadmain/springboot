package com.wkcto.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.wkcto.domain.Student;
import com.wkcto.model.PageInfo;
import com.wkcto.model.StudentModel;
import com.wkcto.model.ViewStudentModel;
import com.wkcto.service.StudentService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceJdbc extends AnalysisEventListener implements StudentService {
    @Override
    public List<Student> allList() {
        return null;
    }

    @Override
    public Integer addBatchData(List<Student> list) {
        return 0;
    }

    @Override
    public Integer deleteBatchData(String ids) {
        return 0;
    }

    @Override
    public Integer updateBatchData(List<Student> list) {
        return null;
    }

    //注入SqlSessionFactory对象
    @Autowired
    private SqlSessionFactory factory;


    private Connection conn = null;
    private PreparedStatement pst = null;
    //计数器
    private int i =0;


    @Override
    public void invoke(Object object, AnalysisContext context) {

        i++;
        try{
            //读到excel的每行数据，执行一次
            StudentModel sm  = (StudentModel)object;
            addBatchExcelToDb(sm);

            //每 10 做一次批量数据提交， 事务提交
            if( i%10 ==0){
                //批量执行数据
                pst.executeBatch();
                //提交事务
                conn.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //在excel读取完毕后执行的
        try{
            //批量执行数据
            pst.executeBatch();
            //提交事务
            conn.commit();

            //改变自动提交
            conn.setAutoCommit(true);
            conn.close();
            pst.close();
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public StudentServiceJdbc() {
//        try{
//            init();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    //初始方法
    @PostConstruct
    public void init() throws SQLException {
        //获取连接Connection
        conn = factory.openSession().getConnection();
        //设置关闭自动提交
        conn.setAutoCommit(false);
        //创建PreparedStatment对象
        String sql = "insert into student(id,name,email,age) values(?,?,?,?)";
        pst = conn.prepareStatement(sql);
    }

    //使用jdbc访问数据库
    public void addBatchExcelToDb(StudentModel student) throws SQLException {

        System.out.println("====方法执行addBatchExcelToDb====");
        //指定sql语句需要的参数值
        pst.setInt(1,student.getId());
        pst.setString(2,student.getName());
        pst.setInt(4,student.getAge());
        //添加到batch中
        pst.addBatch();


    }

    @Override
    public void addStudents(Student student) {

        //无用
    }

    @Override
    public void readExcel(File file) {
        try{
            // 使用easyexel读取excel文件
            InputStream in = new FileInputStream(file);

            //创建EasyExcel的 ExcelReader
            ExcelReader reader = new ExcelReader(in,null,this);
            //创建Sheet sheetNo参数表示第几个 ，从 1 开始
            Sheet sheet = new Sheet(1,1, StudentModel.class);
            //读取Sheet
            reader.read(sheet);

        }catch (Exception e){
            e.printStackTrace();
            //重新抛出异常
            throw new RuntimeException("入库有异常！！！");
        }
    }

    @Override
    public List<ViewStudentModel> queryStudents(PageInfo pageInfo) {
        return null;
    }

}
