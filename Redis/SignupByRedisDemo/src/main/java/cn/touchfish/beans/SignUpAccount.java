package cn.touchfish.beans;

/**
 * @ClassName User
 * @Description 注册用户bean
 * @Author Josen
 * @Create 2020/8/11 11:17
 */
public class SignUpAccount {
    private String username;
    private String password;
    private String email;
    private int is_active = 0;
    public SignUpAccount() {
    }

    public SignUpAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "SignUpAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", is_active=" + is_active +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
}
