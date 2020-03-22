package com.wkcto.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * <p>Description: 实体类 </p>
 * <p>Company: http://www.bjpowernode.com
 */
@Data
public class StudentModel extends BaseRowModel {

    @ExcelProperty(value = "序号",index = 0)
    private Integer id;

    @ExcelProperty(value = "姓名",index = 1)
    private String name;

    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;
}
