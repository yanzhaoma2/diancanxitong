// 声明该类所属的包，表明这是一个持久化对象（PO）类，属于 com.study.shop.po 包
package com.study.shop.po;

// 定义一个公共的 Appraise 类，用于表示评价信息，通常作为持久化对象使用
public class Appraise {
    // 定义一个私有整型变量，用于存储被评价菜品的 ID
    private int foodId;
    // 定义一个私有字符串变量，用于存储评价的具体内容
    private String comment;
    // 定义一个私有整型变量，用于存储评价的星级
    private int star;

    // 定义一个公共的构造函数，用于初始化 Appraise 对象
    // @param foodId 被评价菜品的 ID
    // @param comment 评价的具体内容
    // @param star 评价的星级
    public Appraise(int foodId, String comment, int star) {
        // 将传入的 foodId 参数赋值给当前对象的 foodId 成员变量
        // 将传入的 foodId 参数赋值给当前对象的 foodId 成员变量
        this.foodId = foodId;
        // 将传入的 comment 参数赋值给当前对象的 comment 成员变量
        // 将传入的 comment 参数赋值给当前对象的 comment 成员变量
        this.comment = comment;
        // 将传入的 star 参数赋值给当前对象的 star 成员变量
        // 将传入的 star 参数赋值给当前对象的 star 成员变量
        this.star = star;
    }

    // 定义一个公共的 getter 方法，用于获取被评价菜品的 ID
    // @return 被评价菜品的 ID
    public int getFoodId() {
        // 返回当前对象的 foodId 成员变量的值
        return foodId;
    }

    // 定义一个公共的 setter 方法，用于设置被评价菜品的 ID
    // @param foodId 被评价菜品的新 ID
    public void setFoodId(int foodId) {
        // 将传入的 foodId 参数赋值给当前对象的 foodId 成员变量
        // 将传入的 foodId 参数赋值给当前对象的 foodId 成员变量
        this.foodId = foodId;
    }

    // 定义一个公共的 getter 方法，用于获取评价的具体内容
    // @return 评价的具体内容
    public String getComment() {
        // 返回当前对象的 comment 成员变量的值
        return comment;
    }

    // 定义一个公共的 setter 方法，用于设置评价的具体内容
    // @param comment 评价的新内容
    public void setComment(String comment) {
        // 将传入的 comment 参数赋值给当前对象的 comment 成员变量
        // 将传入的 comment 参数赋值给当前对象的 comment 成员变量
        this.comment = comment;
    }

    // 定义一个公共的 getter 方法，用于获取评价的星级
    // @return 评价的星级
    public int getStar() {
        // 返回当前对象的 star 成员变量的值
        return star;
    }

    // 定义一个公共的 setter 方法，用于设置评价的星级
    // @param star 评价的新星级
    public void setStar(int star) {
        // 将传入的 star 参数赋值给当前对象的 star 成员变量
        // 将传入的 star 参数赋值给当前对象的 star 成员变量
        this.star = star;
    }

    // 重写 Object 类的 toString 方法，用于返回对象的字符串表示形式
    @Override
    public String toString() {
        // 拼接并返回包含菜品 ID、评价内容和星级的字符串
        return "Appraise{foodId=" + foodId + ", comment='" + comment + "', star=" + star + "}";
    }
}