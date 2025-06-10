/**
 * 该包包含项目中的实体类。
 */
package com.study.shop.po;

/**
 * 店主实体类，用于表示系统中的店主信息。
 */
public class Shopowner {
        /**
     * 店铺的唯一标识符。
     */
    private Integer shopId;
        /**
     * 店铺的地址。
     */
    private String address;
        /**
     * 店主的姓名。
     */
    private String shopowner;
        /**
     * 店主的联系电话。
     */
    private String phone;
        /**
     * 店主登录系统的密码。
     */
    private String password;

        /**
     * 获取店铺的唯一标识符。
     * 
     * @return 店铺的唯一标识符。
     */
    public Integer getShopId() {
        return shopId;
    }

        /**
     * 设置店铺的唯一标识符。
     * 
     * @param shopId 店铺的唯一标识符。
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

        /**
     * 获取店铺的地址。
     * 
     * @return 店铺的地址。
     */
    public String getAddress() {
        return address;
    }

        /**
     * 设置店铺的地址。
     * 
     * @param address 店铺的地址。
     */
    public void setAddress(String address) {
        this.address = address;
    }

        /**
     * 获取店主的姓名。
     * 
     * @return 店主的姓名。
     */
    public String getShopowner() {
        return shopowner;
    }

        /**
     * 设置店主的姓名。
     * 
     * @param shopowner 店主的姓名。
     */
    public void setShopowner(String shopowner) {
        this.shopowner = shopowner;
    }

        /**
     * 获取店主的联系电话。
     * 
     * @return 店主的联系电话。
     */
    public String getPhone() {
        return phone;
    }

        /**
     * 设置店主的联系电话。
     * 
     * @param phone 店主的联系电话。
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

        /**
     * 获取店主登录系统的密码。
     * 
     * @return 店主登录系统的密码。
     */
    public String getPassword() {
        return password;
    }

        /**
     * 设置店主登录系统的密码。
     * 
     * @param password 店主登录系统的密码。
     */
    public void setPassword(String password) {
        this.password = password;
    }

        /**
     * 重写 toString 方法，用于返回店主信息的字符串表示形式。
     * 
     * @return 包含店主信息的字符串。
     */
    @Override
    public String toString() {
        return "Shopowner{" +
                "shopId=" + shopId +
                ", address='" + address + '\'' +
                ", shopowner='" + shopowner + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}