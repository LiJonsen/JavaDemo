package cn.touchfish.beans;

import java.math.BigDecimal;

/**
 * @ClassName SiteMessage
 * @Description 站点统计信息bean
 * @Author Josen
 * @Create 2020/8/13 18:32
 */
public class SiteMessage {
    // 访问统计
    private BigDecimal access_count;
    // 登录统计
    private BigDecimal login_count;
    // 用户统计
    private long user_count;
    // 激活统计
    private long active_count;

    public SiteMessage() {
    }

    public SiteMessage(BigDecimal access_count, BigDecimal login_count, long user_count, long active_count) {
        this.access_count = access_count;
        this.login_count = login_count;
        this.user_count = user_count;
        this.active_count = active_count;
    }

    @Override
    public String toString() {
        return "SiteMessage{" +
                "access_count=" + access_count +
                ", login_count=" + login_count +
                ", user_count=" + user_count +
                ", active_count=" + active_count +
                '}';
    }

    public BigDecimal getAccess_count() {
        return access_count;
    }

    public void setAccess_count(BigDecimal access_count) {
        this.access_count = access_count;
    }

    public BigDecimal getLogin_count() {
        return login_count;
    }

    public void setLogin_count(BigDecimal login_count) {
        this.login_count = login_count;
    }

    public long getUser_count() {
        return user_count;
    }

    public void setUser_count(long user_count) {
        this.user_count = user_count;
    }

    public long getActive_count() {
        return active_count;
    }

    public void setActive_count(long active_count) {
        this.active_count = active_count;
    }
}
