package com.josen.utils;


import com.google.gson.Gson;
import com.josen.entity.MessageConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

import javax.crypto.Mac;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * @ClassName QiNiuUtil
 * @Description 七牛云工具类
 * @Author Josen
 * @Create 2020/9/7 20:19
 */
public class QiNiuUtil {
    //构造一个带指定Zone对象的配置类，zone0表示华东地区（默认）
    //其他参数参考类注释
    private final static String ACCESS_KEY = "WmYGeNzkzZiZN4Unb8Rktr6K1c6L1zuhGOBkvdh7";
    private final static String SECRET_KEY = "Km4nkAniaOFD5mnFW5_WsDbJCUu7uJgdl6k6cWZO";
    // 存储区域：华南
    private final static Configuration QINIU_ZONE = new Configuration(Zone.zone2());
    // 空间名称
    private final static String BUCKET = "touchfish";
    // 存储文件目录
    private final static String QINIU_DIR = "test_dubbo/";
    // 上传文件格式校验
    public final static String[] FILE_FORMAT = new String[]{"jpg", "png", "gif", "jpeg"};

    /**
     * 上传图片到七牛云
     *
     * @param bytes    要上传的文件字节数据
     * @param fileName 文件名
     */
    public static String uploadImage(byte[] bytes, String fileName) {
        // 校验文件名后缀格式
        List<String> formats = Arrays.asList(FILE_FORMAT);
        String endName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (!formats.contains(endName)) {
            // 格式错误
            return MessageConstant.PIC_UPLOAD_FORMAT_ERROR;
        }

        // 创建上传token
        UploadManager uploadManager = new UploadManager(QINIU_ZONE);
        String key = UUID.randomUUID().toString() + "." + endName;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        try {
            // 执行上传图片
            Response response = uploadManager.put(bytes, QINIU_DIR + key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println("上传成功...");
            return key;
        } catch (QiniuException e) {
            System.out.println("上传失败...");
            e.printStackTrace();
            return MessageConstant.PIC_UPLOAD_FAIL;
        } catch (Exception e) {
            e.printStackTrace();
            return MessageConstant.PIC_UPLOAD_FAIL;
        }
    }

    /**
     * 删除七牛云图片
     * @param name
     */
    public static void deleteImg(String name) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, QINIU_ZONE);
        try {
            bucketManager.delete(BUCKET, QINIU_DIR + name);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(name + ":删除失败！！！");
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    /**
     * 获取七牛云所有图片信息
     */
    public static List<String> getBatchQiNiuImg() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, QINIU_ZONE);
        //文件名前缀
        String prefix = QINIU_DIR;
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        List<String> list = new ArrayList<>();// 存储文件名
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(BUCKET, prefix, limit, delimiter);
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                String name = item.key.replace(QINIU_DIR,"");
                list.add(name);
            }
        }

        return list;
    }
}
