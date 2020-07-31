package com.josen.beans;

/**
 * @ClassName User
 * @Description Details
 * @Author Josen
 * @Create 2020/7/31 12:43
 */
public class User {
        private String name;
        private String skill;

    public User() {
    }

    public User(String name, String skill) {
        this.name = name;
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
