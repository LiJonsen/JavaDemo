package com.josen.pojo;

import java.io.Serializable;

/**
 * @ClassName SubmitOrder
 * @Description 提交体检预约请求参数Bean
 * @Author Josen
 * @Create 2020/9/11 17:21
 */
public class SubmitOrder implements Serializable {
    private Integer setmealId; // 套餐ID
    private String name; // 体检人
    private Integer sex; //性别
    private String telephone; //手机号
    private String validateCode; // 短信验证码
    private String idCard; //身份证号码
    private String orderDate; //体检日期

    public SubmitOrder() {
    }

    public SubmitOrder(Integer setmealId, String name, Integer sex, String telephone, String validateCode, String idCard, String orderDate) {
        this.setmealId = setmealId;
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
        this.validateCode = validateCode;
        this.idCard = idCard;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "SubmitOrder{" +
                "setmealId=" + setmealId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", telephone='" + telephone + '\'' +
                ", validateCode='" + validateCode + '\'' +
                ", idCard='" + idCard + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }

    public Integer getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Integer setmealId) {
        this.setmealId = setmealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
