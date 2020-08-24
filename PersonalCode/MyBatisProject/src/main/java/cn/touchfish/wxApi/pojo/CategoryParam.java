package cn.touchfish.wxApi.pojo;

import lombok.Data;

/**
 * @ClassName CategoryParam
 * @Description 试题分类列表请求参数bean
 * @Author Josen
 * @Create 2020/8/23 19:27
 */
@Data
public class CategoryParam {
    public static final int CATEGORY_KIND1 = 1;
    public static final int CATEGORY_KIND2 = 2;
    public static final int CATEGORY_KIND3 = 3;

    public static final int CATEGORY_TYPE1 = 101;
    public static final int CATEGORY_TYPE2 = 201;
    public static final int CATEGORY_TYPE3 = 202;
    public static final int CATEGORY_TYPE4 = 203;

    // categoryKind：1 = TAG，按技术点（按目录）
    // categoryKind：2 = 企业，按企业
    // categoryKind：3 = 方向，按方向
    private Integer categoryKind;

    // categoryType：101 = 刷题（面试题库，题库列表）
    // categoryType：201 = 错题本
    // categoryType：202 = 我的练习
    // categoryType：203 = 收藏题库
    private Integer categoryType;

    // 微信用户唯一标识
    private String openId;
}
