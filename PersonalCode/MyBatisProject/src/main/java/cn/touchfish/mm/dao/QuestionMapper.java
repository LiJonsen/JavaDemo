package cn.touchfish.mm.dao;

import cn.touchfish.mm.entity.QueryPageBean;
import cn.touchfish.mm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName QuestionMapper
 * @Description 基础题库Mapper
 * @Author Josen
 * @Create 2020/8/19 14:33
 */
public interface QuestionMapper {
    /**
     * 分页查询基础题库列表
     */
    List<Question> queryQuestionListByPage(QueryPageBean qpb);

    /**
     * 获取基础题库列表总记录数
     */
    long getQuestionRecordTotal(QueryPageBean qpb);

    /**
     * 获取城市列表
     */
    List<Dict> getDictList();

    /**
     * 获取企业列表以及企业对应的方向
     */
    List<Company> getCompanyList();

    /**
     * 插入一条基础试题记录
     * 注意：如果设置int返回值，会覆盖新增的记录主键返回值
     * @param question
     * @return 主键值
     */
    void insertOneQuestion(Question question);
    /**
     * 插入指定基础试题id的选项
     */
    boolean insertBatchQuestionItem(@Param("items") List<QuestionItem> items);
    /**
     * 更新精选 or 基础试题类型
     */
    boolean updateClassic(@Param("questionId") int questionId,@Param("isClassic") int isClassic);
    /**
     * 更新审核状态
     */
    boolean updateReviewStatus(Question question);
    /**
     * 根据questionId修改ReviewLog状态
     */
    boolean updateReviewLogStatusByQid(Question question);

    /**
     * 新增一条审核记录
     */
    boolean insertOneReviewLogRecord(ReviewLog reviewLog);
}
