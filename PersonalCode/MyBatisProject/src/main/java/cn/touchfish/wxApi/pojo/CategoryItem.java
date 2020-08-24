package cn.touchfish.wxApi.pojo;

import lombok.Data;

/**
 * @ClassName CategoryItem
 * @Description 面试题库列表Item
 * @Author Josen
 * @Create 2020/8/23 19:38
 */
@Data
public class CategoryItem {
    private Integer id;// 目录id
    private String title;// 目录名称
    private Integer allCount;// 目录下所有题目的数量
    private Integer finishedCount;// 当前用户在该目录下做过的题目数量
}
