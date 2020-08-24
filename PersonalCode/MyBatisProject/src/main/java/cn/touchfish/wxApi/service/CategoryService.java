package cn.touchfish.wxApi.service;

import cn.touchfish.mm.dao.CategoryMapper;
import cn.touchfish.mm.dao.MemberMapper;
import cn.touchfish.mm.pojo.WxMember;
import cn.touchfish.mm.utils.MybatisUtils;
import cn.touchfish.wxApi.pojo.CategoryItem;
import cn.touchfish.wxApi.pojo.CategoryParam;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryService
 * @Description Details
 * @Author Josen
 * @Create 2020/8/23 19:39
 */
public class CategoryService {
    /**
     * 处理获取面试题库列表业务逻辑
     */
    public List<CategoryItem> getCategoryItems(CategoryParam param) throws IOException {
        List<CategoryItem> list = new ArrayList<>();
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        int kind = param.getCategoryKind();
        int type = param.getCategoryType();
        // 1. 根据openId获取当前用户的course_id&member_id
        WxMember wxMember = memberMapper.queryWxMemberByOpenId(param.getOpenId());
        if(wxMember==null) {
            return null;
        }
        // 按技术点刷题（面试题库，题库列表）
        if(kind == CategoryParam.CATEGORY_KIND1 && type == CategoryParam.CATEGORY_TYPE1){
                // 根据course_id&member_id获取面试题库列表
                list = mapper.queryCategoryItems(wxMember.getId(), wxMember.getCourseId());
        }else if(kind == CategoryParam.CATEGORY_KIND2 && type == CategoryParam.CATEGORY_TYPE1){
               // 按企业（面试题库，题库列表）
                list = mapper.queryCategoryByCompany(wxMember.getId());
        }else if(kind == CategoryParam.CATEGORY_KIND3 && type == CategoryParam.CATEGORY_TYPE1){
                // 按方向（面试题库，题库列表）
            list = mapper.queryCategoryByIndustry(wxMember.getId());
        }
        MybatisUtils.commitAndClose(sqlSession);
        return list;
    }
}
