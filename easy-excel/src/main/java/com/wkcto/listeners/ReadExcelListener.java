package com.wkcto.listeners;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wkcto.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 实体类 </p>
 * <p>Company: http://www.bjpowernode.com
 */
public class ReadExcelListener extends AnalysisEventListener {

    //保存读取的每行数据
    private List<StudentModel> data = null;

    public ReadExcelListener() {
        super();
        //初始List
        data = new ArrayList<>();
    }

    /**
     * 读取到excel每一行，都调用invoke方法
     * @param object 行数据，StudentModel
     * @param context 上下文
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        //读取到的每行数据
        System.out.println(object);
        //把数据添加到List
        data.add( (StudentModel) object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //excel读取完毕最后执行的方法
    }

    //获取数据
    public List<StudentModel> getData() {
        return data;
    }
}
