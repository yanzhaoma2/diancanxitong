package com.study.shop.po;

public class Appraise {
    private int foodId;
    private String comment;
    private int star;

    public Appraise(int foodId, String comment, int star) {
        this.foodId = foodId;
        this.comment = comment;
        this.star = star;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Appraise{foodId=" + foodId + ", comment='" + comment + "', star=" + star + "}";
    }
}