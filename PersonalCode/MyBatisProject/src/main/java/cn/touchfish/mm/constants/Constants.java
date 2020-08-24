package cn.touchfish.mm.constants;


/**
 * 问题常量
 */
public interface Constants {
    /**
     * 题目状态
     */
    int QUESTION_PRE_PUBLISH = 0;           // 待发布 0
    int QUESTION_PUBLISHED = 1;            // 已发布 1
    int QUESTION_PUBLISHED_OFFLINE = 2;   // 已下架 2

    /**
     * 题目审核状态
     */
    int QUESTION_PRE_REVIEW = 0;            // 待审核 0
    int QUESTION_REVIEWED = 1;            // 已审核 1
    int QUESTION_REJECT_REVIEW = 2;        // 已拒绝 2


    /**
     * 接口响应信息
     */

    String SERVICE_ERROR = "服务器异常，请稍后重试！"; // 接口默认异常状态msg
    String SERVICE_SUCCESS = "response success!"; //接口默认成功状态msg

    /**
     * 缓存用户登录信息Key
     */
    String REDIS_LOGIN_KEY = "LOGIN_HASH";
}
