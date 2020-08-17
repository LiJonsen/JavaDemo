package cn.touchfish.beans;

/**
 * @ClassName HomeUser
 * @Description 首頁列表bean
 * @Author Josen
 * @Create 2020/8/14 21:38
 */
public class HomeUser {
    private int uid;
    private String username;
    private String email;
    private int isActive;
    private int loginCount;
    private int accessCount;
    public HomeUser() {
    }
    @Override
    public String toString() {
        return "HomeUser{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", loginCount=" + loginCount +
                ", accessCount=" + accessCount +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
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
