package cn.touchfish.beans;

/**
 * @ClassName CountForUser
 * @Description User统计信息
 * @Author Josen
 * @Create 2020/8/17 8:28
 */
public class CountForUser {
    private String username;
    private int loginCount;
    private int accessCount;

    public CountForUser() {
    }

    public CountForUser(String username, int loginCount, int accessCount) {
        this.username = username;
        this.loginCount = loginCount;
        this.accessCount = accessCount;
    }

    @Override
    public String toString() {
        return "CountForUser{" +
                "username='" + username + '\'' +
                ", loginCount=" + loginCount +
                ", accessCount=" + accessCount +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }
}
