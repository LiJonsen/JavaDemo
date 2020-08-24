package cn.touchfish.mm.service;

import cn.touchfish.mm.constants.Constants;
import cn.touchfish.mm.dao.QuestionMapper;
import cn.touchfish.mm.dao.TagMapper;
import cn.touchfish.mm.entity.PageResult;
import cn.touchfish.mm.entity.QueryPageBean;
import cn.touchfish.mm.pojo.*;
import cn.touchfish.mm.utils.DateUtils;
import cn.touchfish.mm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName QuestionService
 * @Description 基础题库Service
 * @Author Josen
 * @Create 2020/8/19 15:35
 */

public class QuestionService {
    /**
     * 处理分页查询基础题库列表业务逻辑
     * @param qpb
     * @return
     * @throws IOException
     */
    public PageResult getQuestionListByPage(QueryPageBean qpb) throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
        int current = qpb.getCurrentPage();
        qpb.setOffset((current-1)*qpb.getPageSize());
        long total = mapper.getQuestionRecordTotal(qpb);
        List<Question> questions = mapper.queryQuestionListByPage(qpb);

        MybatisUtils.commitAndClose(sqlSession);
        return new PageResult(total,questions);
    }

    /**
     * 获取城市列表
     */
    public List<Dict> getDictList() throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        List<Dict> dictList = sqlSession.getMapper(QuestionMapper.class).getDictList();
        MybatisUtils.commitAndClose(sqlSession);
        return dictList;
    }

    /**
     * 获取企业列表以及企业对应的方向
     */
    public List<Company> getCompanyList() throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        List<Company> companyList = sqlSession.getMapper(QuestionMapper.class).getCompanyList();
        MybatisUtils.commitAndClose(sqlSession);
        return companyList;
    }

    /**
     * 处理添加一条基础试题到数据库
     * @return
     */
    public boolean addOneQuestion(Question question) {
        SqlSession sqlSession = null;
        try {
            // 创建日期
            question.setCreateDate(DateUtils.parseDate2String(new Date()));
            // 默认题目状态
            question.setStatus(Constants.QUESTION_PRE_PUBLISH);
            // 默认题目审核状态
            question.setReviewStatus(Constants.QUESTION_PRE_REVIEW);

            sqlSession = MybatisUtils.openSqlSession();
            // 1. 执行新增试题SQL，获取最新主键值
            QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
            mapper.insertOneQuestion(question);
            int primaryKey = question.getId();
             System.out.println("primaryKey="+primaryKey);
            if(primaryKey!=0){
                // 2. 根据主键添加标签列表（tagList）
                List<Tag> tagList = question.getTagList();
                if(tagList!=null && tagList.size()>0){
                    TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);
                    for (Tag tag : tagList) {
                        // 2.1 执行添加SQL操作
                        tag.setQuestionId(primaryKey);
                    }
                    boolean b = tagMapper.insertBatchQuestionTag(tagList);
                    System.out.println("insert batch tags："+b);
                    if(!b){
                        throw new RuntimeException("批量新增标签失败，事务回滚！");
                    }
                }
                // 3. 根据主键添加题目选项列表（questionItemList）
                List<QuestionItem> questionItemList = question.getQuestionItemList();
                if(questionItemList!=null && questionItemList.size()>0){
                    for (QuestionItem questionItem : questionItemList) {
                        String content = questionItem.getContent();
                        if(content==null || "".equals(content)){
                            continue;
                        }
                        questionItem.setQuestionId(primaryKey);
                        // 3.1 执行添加SQL操作
                    }
                    boolean b = mapper.insertBatchQuestionItem(questionItemList);
                    System.out.println("insert batch question item："+b);

                    if(!b){
                        throw new RuntimeException("批量新增选项失败，事务回滚！");
                    }
                }
            }
            // 提交事务
            MybatisUtils.commitAndClose(sqlSession);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            MybatisUtils.rollbackAndClose(sqlSession);
            return false;
        }

    }

    /**
     * 将questionId的基础试题修改为精选试题
     * @param reviewLog
     * @return
     */
    public boolean addToClassic(ReviewLog reviewLog) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
            // 更新题目状态为精选
            boolean status = mapper.updateClassic(reviewLog.getQuestionId(),Constants.QUESTION_REVIEWED);
            // 新增一条审核记录
            reviewLog.setStatus(Constants.QUESTION_PRE_REVIEW);
            reviewLog.setCreateDate(DateUtils.parseDate2String(new Date()));
            boolean s2 = mapper.insertOneReviewLogRecord(reviewLog);
            if(!status || !s2){
                MybatisUtils.rollbackAndClose(sqlSession);
                return false;
            }
            MybatisUtils.commitAndClose(sqlSession);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            MybatisUtils.rollbackAndClose(sqlSession);
            return false;
        }
    }

    /**
     * 处理精选试题审核
     * @param question
     * @return
     */
    public boolean reviewQuestion(Question question) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
            int reviewStatus = question.getReviewStatus();
            question.setStatus(reviewStatus);
            // 审核不通过
            if(reviewStatus == Constants.QUESTION_REJECT_REVIEW){
                // 修改为基础试题
                mapper.updateClassic(question.getId(), Constants.QUESTION_PRE_REVIEW);
            }
            boolean status = mapper.updateReviewStatus(question);

            if(status){
                // 更新t_review_log中的状态
                status = mapper.updateReviewLogStatusByQid(question);
            }
            if(status){
                MybatisUtils.commitAndClose(sqlSession);
                return true;
            }
            MybatisUtils.rollbackAndClose(sqlSession);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            MybatisUtils.rollbackAndClose(sqlSession);
            return false;
        }
    }
}
