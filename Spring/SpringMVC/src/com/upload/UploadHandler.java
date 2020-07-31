package com.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName UploadHandler
 * @Description 文件上传Handler
 * @Author Josen
 * @Create 2020/7/30 20:48
 */
@Controller
public class UploadHandler {
    /**
     * 接收浏览器上传的图片文件
     * @return
     */
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public String uploadImageHandler(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "desc",required = false) String desc,
            HttpSession session
    ) throws IOException {
        String name = file.getOriginalFilename();
//        InputStream is = file.getInputStream();
        //获取服务器端的static文件夹的真实路径。
        String realPath = session.getServletContext().getRealPath("static");
        // 注意：如果文件夹不存在不会自动创建
        File out_file = new File( realPath+ "/" + name);
        file.transferTo(out_file);
        System.out.printf("Filename=%s\tDesc=%s",name,desc);
        return "success";
    }
}
