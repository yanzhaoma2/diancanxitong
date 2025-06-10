/**
 * 定义包路径，表明该类属于 com.study.shop.po 包，通常用于存放实体类。
 */
package com.study.shop.po;

/**
 * 餐品实体类，用于表示系统中的餐品信息。
 * 包含餐品的基本信息、所属类目、价格、描述、销售状态以及销售数据等。
 */
public class Food {
        /**
     * 餐品ID，用于唯一标识一个餐品。
     */
    private Integer foodId;
        /**
     * 餐品名称，用于描述餐品的具体名称。
     */
    private String foodName;
        /**
     * 类目ID，用于在更新数据时关联餐品所属的类目。
     */
    private Integer categoryId; // 更新数据用
        /**
     * 类目名称，用于在显示数据时展示餐品所属的类目名称。
     */
    private String categoryName; // 显示数据用
        /**
     * 餐品价格，以整数形式存储。
     */
    private Integer price;
        /**
     * 餐品描述，用于详细介绍餐品的特点、成分等信息。
     */
    private String description;
        /**
     * 餐品是否已被删除的标志，true 表示已删除，false 表示未删除。
     */
    private boolean deleted;
        /**
     * 餐品的销量，记录该餐品的销售数量。
     */
    private Integer salesVolume; // 销量
        /**
     * 餐品的销售额，记录该餐品的销售总收入。
     */
    private Integer revenue; // 销售额

        /**
     * 获取餐品ID。
     * 
     * @return 餐品ID
     */
    public Integer getFoodId() {
        return foodId;
    }

        /**
     * 设置餐品ID。
     * 
     * @param foodId 要设置的餐品ID
     */
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

        /**
     * 获取餐品名称。
     * 
     * @return 餐品名称
     */
    public String getFoodName() {
        return foodName;
    }

        /**
     * 设置餐品名称。
     * 
     * @param foodName 要设置的餐品名称
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

        /**
     * 获取类目ID。
     * 
     * @return 类目ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

        /**
     * 设置类目ID。
     * 
     * @param categoryId 要设置的类目ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

        /**
     * 获取类目名称。
     * 
     * @return 类目名称
     */
    public String getCategoryName() {
        return categoryName;
    }

        /**
     * 设置类目名称。
     * 
     * @param categoryName 要设置的类目名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

        /**
     * 获取餐品价格。
     * 
     * @return 餐品价格
     */
    public Integer getPrice() {
        return price;
    }

        /**
     * 设置餐品价格。
     * 
     * @param price 要设置的餐品价格
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

        /**
     * 获取餐品描述。
     * 
     * @return 餐品描述
     */
    public String getDescription() {
        return description;
    }

        /**
     * 设置餐品描述。
     * 
     * @param description 要设置的餐品描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

        /**
     * 检查餐品是否已被删除。
     * 
     * @return 如果餐品已被删除返回 true，否则返回 false
     */
    public boolean isDeleted() {
        return deleted;
    }

        /**
     * 设置餐品是否已被删除的状态。
     * 
     * @param deleted 要设置的删除状态，true 表示已删除，false 表示未删除
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

        /**
     * 获取餐品销量。
     * 
     * @return 餐品销量
     */
    public Integer getSalesVolume() {
        return salesVolume;
    }

        /**
     * 设置餐品销量。
     * 
     * @param salesVolume 要设置的餐品销量
     */
    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

        /**
     * 获取餐品销售额。
     * 
     * @return 餐品销售额
     */
    public Integer getRevenue() {
        return revenue;
    }

        /**
     * 设置餐品销售额。
     * 
     * @param revenue 要设置的餐品销售额
     */
    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

        /**
     * 重写 toString 方法，用于返回餐品对象的字符串表示形式。
     * 
     * @return 包含餐品各项信息的字符串
     */
    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                ", salesVolume=" + salesVolume +
                ", revenue=" + revenue +
                '}';
    }
}