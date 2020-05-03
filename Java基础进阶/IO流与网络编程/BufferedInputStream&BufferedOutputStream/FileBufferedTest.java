package com.atguigu.IO_Test;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 缓冲流练习
 */
public class FileBufferedTest {
    /**
     * 使用缓冲流实现视频的读取和输出操作，并打印代码执行所需时间
     */
    @Test
    public void testing()  {
        long s = System.currentTimeMillis();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            // 1. 创建节点流、缓冲流（File可省略）
            bis = new BufferedInputStream(new FileInputStream("src/com/atguigu/IO_Test/video.avi"));
            bos = new BufferedOutputStream(new FileOutputStream("src/com/atguigu/IO_Test/videoCopy.avi"));
            // 2. 读取视频内容
            byte[] buff = new byte[1024];
            int len;
            while((len=bis.read(buff))!=-1){
                bos.write(buff,0,len);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            // 3. 资源关闭
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时（ms）："+(end - s)); // 缓冲流1199
    }

    /**
     * 获取TextCount.txt文本上，每个字符出现的次数；
     * 使用Map实现
     */
    @Test
    public void testTextCount(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try{
            // 1. 创建节点流、缓冲流
            br = new BufferedReader(new FileReader("src/com/atguigu/IO_Test/TextCount.txt"));
            bw = new BufferedWriter(new FileWriter("src/com/atguigu/IO_Test/CountResult.txt"));
            // 2. 将读取TextCount.txt得到的字符，存到HashMap中并统计次数
            HashMap<Character,Long> map = new HashMap<>();
            String str;
            while((str = br.readLine()) != null){
                char[] chars = str.toCharArray();
                for(char c : chars){
                    if(map.containsKey(c)){
                       long count = map.get(c) + 1;
                        map.put(c,count);
                    }else{
                        map.put(c,1L);
                    }
                }
            }
            // 3.遍历HashMap，将统计好的key-value输出到CountResult.txt中
            Set<Map.Entry<Character, Long>> characters = map.entrySet();
            Iterator<Map.Entry<Character, Long>> iterator = characters.iterator();
            while (iterator.hasNext()){
                Map.Entry<Character, Long> next = iterator.next();
                Character key = next.getKey();
                Long value = next.getValue();
                bw.write("当前字符："+key+"======统计次数："+value);
                bw.newLine();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
