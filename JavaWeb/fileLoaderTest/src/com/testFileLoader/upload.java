package com.testFileLoader;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName upload
 * @Description 处理客户端上传的文件保存到磁盘
 * @Author Josen
 * @Date 2020/6/8 16:11
 * @Version 1.0
 **/
public class upload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        // 判断请求是否为文件类型
        if(ServletFileUpload.isMultipartContent(req)){
            // 1. 创建 FileItemFactory 工厂实现类
            DiskFileItemFactory dfif = new DiskFileItemFactory();
            // 2. 解析上传的数据，得到每一个表单项 FileItem
            ServletFileUpload sfu = new ServletFileUpload(dfif);

            try {
                // 解析上传的数据，得到每一个表单项 FileItem
                List<FileItem> list = sfu.parseRequest(req);

                // 遍历所有字段
                for(FileItem item : list){
                    // 普通表单项
                    if(item.isFormField()){
                        System.out.println(" 表单项的 name  属性值：" + item.getFieldName());
                        // 参数 UTF-8. 解决乱码问题
                        System.out.println(" 表单项的 value  属性值：" + item.getString("UTF-8"));
                    }else{ // 文件类型
                        System.out.println(" 表单项的 name  属性值：" + item.getFieldName());
                        System.out.println(" 上传的文件名：" + item.getName());
                    }
                }


            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
}
