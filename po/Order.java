package com.study.shop.po;

import java.util.Date;

public class Order {
    private Integer orderId;
    private Integer deskNumber;
    private String phone;
    private Integer foodId;
    private String foodName;// 显示数据用
    private Integer price; //冗余字段，方便查询统计
    private Integer amount;
    private Integer total;
    private Date createTime;
    private int state;
    private Integer deliveryType;  // 配送方式:0堂食 1外卖
    private String address;        // 外卖地址

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(Integer deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", deskNumber=" + deskNumber +
                ", phone='" + phone + '\'' +
                ", foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", total=" + total +
                ", createTime=" + createTime +
                ", state=" + state +
                '}';
    }
}