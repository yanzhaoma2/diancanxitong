/**
 * 该包包含项目中的实体类。
 */
package com.study.shop.po;

/**
 * 导入 Date 类，用于处理日期和时间。
 */
import java.util.Date;

/**
 * 订单实体类，用于表示系统中的订单信息。
 */
public class Order {
        /**
     * 订单的唯一标识符。
     */
    private Integer orderId;
        /**
     * 订单对应的桌号，用于堂食订单。
     */
    private Integer deskNumber;
        /**
     * 下单用户的联系电话。
     */
    private String phone;
        /**
     * 订单中食品的唯一标识符。
     */
    private Integer foodId;
        /**
     * 订单中食品的名称，用于显示数据。
     */
    private String foodName;
        /**
     * 订单中食品的单价，为冗余字段，方便查询统计。
     */
    private Integer price;
        /**
     * 订单中食品的数量。
     */
    private Integer amount;
        /**
     * 订单的总金额。
     */
    private Integer total;
        /**
     * 订单的创建时间。
     */
    private Date createTime;
        /**
     * 订单的状态。
     */
    private int state;
        /**
     * 订单的配送方式，0 表示堂食，1 表示外卖。
     */
    private Integer deliveryType;
        /**
     * 外卖订单的配送地址。
     */
    private String address;

        /**
     * 获取订单的唯一标识符。
     * 
     * @return 订单的唯一标识符。
     */
    public Integer getOrderId() {
        return orderId;
    }

        /**
     * 设置订单的唯一标识符。
     * 
     * @param orderId 订单的唯一标识符。
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

        /**
     * 获取订单对应的桌号。
     * 
     * @return 订单对应的桌号。
     */
    public Integer getDeskNumber() {
        return deskNumber;
    }

        /**
     * 设置订单对应的桌号。
     * 
     * @param deskNumber 订单对应的桌号。
     */
    public void setDeskNumber(Integer deskNumber) {
        this.deskNumber = deskNumber;
    }

        /**
     * 获取下单用户的联系电话。
     * 
     * @return 下单用户的联系电话。
     */
    public String getPhone() {
        return phone;
    }

        /**
     * 设置下单用户的联系电话。
     * 
     * @param phone 下单用户的联系电话。
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

        /**
     * 获取订单中食品的唯一标识符。
     * 
     * @return 订单中食品的唯一标识符。
     */
    public Integer getFoodId() {
        return foodId;
    }

        /**
     * 设置订单中食品的唯一标识符。
     * 
     * @param foodId 订单中食品的唯一标识符。
     */
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

        /**
     * 获取订单中食品的名称。
     * 
     * @return 订单中食品的名称。
     */
    public String getFoodName() {
        return foodName;
    }

        /**
     * 设置订单中食品的名称。
     * 
     * @param foodName 订单中食品的名称。
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

        /**
     * 获取订单中食品的单价。
     * 
     * @return 订单中食品的单价。
     */
    public Integer getPrice() {
        return price;
    }

        /**
     * 设置订单中食品的单价。
     * 
     * @param price 订单中食品的单价。
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

        /**
     * 获取订单中食品的数量。
     * 
     * @return 订单中食品的数量。
     */
    public Integer getAmount() {
        return amount;
    }

        /**
     * 设置订单中食品的数量。
     * 
     * @param amount 订单中食品的数量。
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

        /**
     * 获取订单的总金额。
     * 
     * @return 订单的总金额。
     */
    public Integer getTotal() {
        return total;
    }

        /**
     * 设置订单的总金额。
     * 
     * @param total 订单的总金额。
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

        /**
     * 获取订单的创建时间。
     * 
     * @return 订单的创建时间。
     */
    public Date getCreateTime() {
        return createTime;
    }

        /**
     * 设置订单的创建时间。
     * 
     * @param createTime 订单的创建时间。
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

        /**
     * 获取订单的状态。
     * 
     * @return 订单的状态。
     */
    public int getState() {
        return state;
    }

        /**
     * 设置订单的状态。
     * 
     * @param state 订单的状态。
     */
    public void setState(int state) {
        this.state = state;
    }

        /**
     * 获取订单的配送方式。
     * 
     * @return 订单的配送方式，0 表示堂食，1 表示外卖。
     */
    public Integer getDeliveryType() {
        return deliveryType;
    }

        /**
     * 设置订单的配送方式。
     * 
     * @param deliveryType 订单的配送方式，0 表示堂食，1 表示外卖。
     */
    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

        /**
     * 获取外卖订单的配送地址。
     * 
     * @return 外卖订单的配送地址。
     */
    public String getAddress() {
        return address;
    }

        /**
     * 设置外卖订单的配送地址。
     * 
     * @param address 外卖订单的配送地址。
     */
    public void setAddress(String address) {
        this.address = address;
    }
        /**
     * 重写 toString 方法，用于返回订单信息的字符串表示形式。
     * 
     * @return 包含订单信息的字符串。
     */
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