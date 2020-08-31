package com.josen.beans;

/**
 * @ClassName Member
 * @Description
 * @Author Josen
 * @Create 2020/8/26 20:32
 */
public class Member {
    private int id;
    private String nickName;
    private String avatarUrl;
    private String city;

    public Member() {
    }

    public Member(int id, String nickName, String avatarUrl, String city) {
        this.id = id;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
