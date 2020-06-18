package com.testFileLoader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName CopyFilesVideo
 * @Description 复制文件夹下一级目录的所有视频文件到新的文件夹
 * @Author Josen
 * @Date 2020/6/15 18:33
 * @Version 1.0
 **/
public class CopyFilesVideo {
    private static final int DirLevel = 1;//文件目录递归层级
    public static void main(String[] args) {
//        String path = "D://Josen//尚硅谷Java课程//阶段三_JavaEE必学知识//尚硅谷Spring4视频教程";
//        String path = "D://Josen//尚硅谷Java课程//阶段三_JavaEE必学知识//尚硅谷JPA视频";
//        String path = "D://Josen//尚硅谷Java课程//阶段三_JavaEE必学知识//尚硅谷SpringMVC视频教程";
        String path = "D://Josen//尚硅谷Java课程//阶段三_JavaEE必学知识//尚硅谷SSH整合案例视频教程";
        File targetDir = new File(path+"//Videos");
        try {
            CopyFilesVideo.copyVideoToNewFile(path,targetDir,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 复制所有二级文件夹目录下的视频文件到一个新的文件夹
     * @param path 当前要处理的文件夹路径
     * @param targetDir 用于存放所有视频文件的文件夹
     * @param level 当前文件夹所在层级
     * @throws IOException
     */
    private static void copyVideoToNewFile(String path,File targetDir,int level) throws IOException {
        if(level>CopyFilesVideo.DirLevel){
            level = 0;
            return;
        }
        File file = new File(path);
        File[] list = file.listFiles();

        for(int i=0;i<list.length;i++){

            if(list[i].isFile()){ // 文件
                String f_name = list[i].getName();
                // 复制后缀名wmv和avi的视频文件
                if(f_name.indexOf(".wmv")>-1 || f_name.indexOf(".avi")>-1){
                    String f_path = list[i].getPath();
                    File video_file = new File(f_path);
                    // 将视频文件复制到文件夹中
                    FileUtils.copyFileToDirectory(video_file,targetDir);
                    System.out.println("复制成功："+f_name);
                }

            }else if(list[i].isDirectory() ){ // 文件夹
                String name = list[i].getPath();
                if(name.indexOf("源码")==-1 && name.indexOf("源代码")==-1 && name.indexOf("代码")==-1){
                    CopyFilesVideo.copyVideoToNewFile(list[i].getPath(),targetDir,level+1);
                }
//                System.out.println(list[i].getPath());
            }
        }
    }
}
