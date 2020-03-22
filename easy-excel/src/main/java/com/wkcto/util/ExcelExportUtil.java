package com.wkcto.util;


import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExportUtil {

    /**
     * 导出execl
     * @param dataList 模型数据集合
     * @param fileName 文件名称
     * @param sheetName sheet名称
     * @param object 模型对象
     * @param response   响应参数
     * @param request  请求参数
     * @throws IOException
     */
    public static void export(List<? extends BaseRowModel> dataList, String fileName, String sheetName,
                              BaseRowModel object, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //设置响应参数返回输出对象
        ServletOutputStream out =getOut(fileName,response,request);
        //使用easy-excel
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);
        //创建Sheet
        Sheet sheet = new Sheet(1,0, object.getClass());
        //sheet命名
        sheet.setSheetName(sheetName);
        writer.write(dataList,sheet);
        writer.finish();
        out.close();
    }

    /**
     *  设置响应参数返回输出对象
     * @param fileName 文件名称
     * @param response  响应对象
     * @param request  请求对象
     * @return
     */
    private static ServletOutputStream getOut(String fileName, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xlsx" );
        } else {
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xlsx");
        }
        return response.getOutputStream();
    }
}
