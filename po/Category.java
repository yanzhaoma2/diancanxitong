/**
 * 定义包路径，该类属于 com.study.shop.po 包，通常用于存放实体类。
 */
package com.study.shop.po;

/**
 * 类目实体类，用于表示系统中的类目信息。
 * 包含类目ID、类目名称以及是否删除的状态。
 */
public class Category {
        /**
     * 类目ID，用于唯一标识一个类目。
     */
    private Integer categoryId;
        /**
     * 类目名称，用于描述类目。
     */
    private String categoryName;
        /**
     * 类目是否已被删除的标志，true 表示已删除，false 表示未删除。
     */
    private boolean deleted;

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
     * 检查类目是否已被删除。
     * 
     * @return 如果类目已被删除返回 true，否则返回 false
     */
    public boolean isDeleted() {
        return deleted;
    }

        /**
     * 设置类目是否已被删除的状态。
     * 
     * @param deleted 要设置的删除状态，true 表示已删除，false 表示未删除
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

        /**
     * 重写 toString 方法，用于返回类目对象的字符串表示形式。
     * 
     * @return 包含类目ID、类目名称和删除状态的字符串
     */
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}