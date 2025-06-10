package com.study.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.shop.po.Food;
import com.study.shop.util.JdbcUtil;

public class FoodDao {
    /**
     * 增加餐品
     *
     * @param food 餐品对象
     * @return 影响的行数 1成功 0失败
     */
    public int add(Food food) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_food(food_name, category_id, price, description, is_deleted) values(?,?,?,?,?)";
        int result = 0;
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, food.getFoodName());
            pstmt.setInt(2, food.getCategoryId());
            pstmt.setInt(3, food.getPrice());
            pstmt.setString(4, food.getDescription());
            pstmt.setInt(5, 0);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    /**
     * 查询所有餐品
     *
     * @return
     */
    public static List<Food> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String sql = "SELECT t1.*, t2.category_name " + "FROM t_food t1 "
                + "JOIN t_category t2 ON t1.category_id = t2.category_id "
                + "WHERE t1.is_deleted = 0";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setCategoryId(rs.getInt("category_id"));
                food.setPrice(rs.getInt("price"));
                food.setDescription(rs.getString("description"));
                food.setDeleted(rs.getBoolean("is_deleted"));
                food.setCategoryName(rs.getString("category_name"));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 根据关键字查询餐品
     *
     * @param key 关键字
     * @return
     */
    public List<Food> findByKey(String key) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String sql = "SELECT t1.*, t2.category_name "
                + "FROM t_food t1 "
                + "JOIN t_category t2 ON t1.category_id = t2.category_id "
                + "WHERE t1.is_deleted = 0 AND CONCAT(t1.food_name, t2.category_name) LIKE ?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + key + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setCategoryId(rs.getInt("category_id"));
                food.setPrice(rs.getInt("price"));
                food.setDescription(rs.getString("description"));
                food.setDeleted(rs.getBoolean("is_deleted"));
                food.setCategoryName(rs.getString("category_name"));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 根据主键查询餐品
     *
     * @param foodId 餐品主键
     * @return 餐品对象
     */
    public Food findById(int foodId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Food food = null;
        String sql = "select t1.*,category_name from "
                + "t_food t1,t_category t2 "
                + "where t1.category_id = t2.category_id "
                + "and t1.is_deleted = 0 and food_id = ?";

        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setCategoryId(rs.getInt("category_id"));
                food.setPrice(rs.getInt("price"));
                food.setDescription(rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return food;
    }

    /**
     * 修改餐品
     *
     * @param food 餐品对象
     * @return
     */
    public int update(Food food) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_food set food_name=?,category_id=?,price=?,description=? where food_id=?";
        int result = 0;
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, food.getFoodName());
            pstmt.setInt(2, food.getCategoryId());
            pstmt.setInt(3, food.getPrice());
            pstmt.setString(4, food.getDescription());
            pstmt.setInt(5, food.getFoodId());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    /**
     * 删除餐品
     *
     * @param foodId 餐品编号
     * @return
     */
    public int delete(int foodId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_food  set is_deleted =1 where food_id =?";
        int result = 0;
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }
}