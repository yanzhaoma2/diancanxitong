package com.study.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.shop.po.Food;
import com.study.shop.po.Order;
import com.study.shop.util.DateUtil;
import com.study.shop.util.JdbcUtil;

public class OrderDao {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/点餐系统?useSSL=false&characterEncoding=UTF-8";
    public static final String USER = "root";
    public static final String PWD = "123456";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addOrder(List<Order> orderList) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO t_order(desk_number, phone, food_id, price, amount, total) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            for (Order order : orderList) {
                pstmt.setInt(1, order.getDeskNumber());
                pstmt.setString(2, order.getPhone());
                pstmt.setInt(3, order.getFoodId());
                pstmt.setInt(4, order.getPrice());
                pstmt.setInt(5, order.getAmount());
                pstmt.setInt(6, order.getTotal());
                pstmt.addBatch();
            }
            int[] result = pstmt.executeBatch();
            for (int i : result) {
                if (i != 1) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll(conn, pstmt, null);
        }
    }

    public static List<Order> findRecord(String phone, int option) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        List<Order> list = new ArrayList<>();
        try {
            conn = JdbcUtil.getConn();

            if (option == 1) {
                String today = DateUtil.getCurrentToday();
                sql = "select t1.*,food_name from t_order t1,t_food t2 "
                        + " where t1.food_id = t2.food_id and "
                        + "phone=? and DATE(create_time)=? and state=1";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, phone);
                pstmt.setString(2, today);
            }
            if (option == 2) {
                String today = DateUtil.getCurrentToday();
                sql = "select t1.*,food_name from t_order t1,t_food t2 "
                        + " where t1.food_id = t2.food_id and "
                        + "phone=? and DATE(create_time)=? and state=2";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, phone);
                pstmt.setString(2, today);
            }
            if (option == 3) {
                sql = "select t1.*,food_name from t_order t1,t_food t2 "
                        + " where t1.food_id = t2.food_id and " + "phone=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, phone);
            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt(1));
                order.setDeskNumber(rs.getInt(2));
                order.setPhone(rs.getString(3));
                order.setFoodId(rs.getInt(4));
                order.setPrice(rs.getInt(5));
                order.setAmount(rs.getInt(6));
                order.setTotal(rs.getInt(7));
                order.setCreateTime(rs.getTimestamp(8));
                order.setState(rs.getInt(9));
                order.setFoodName(rs.getString("food_name"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 全部接单
     * @return
     */
    public int receiveAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE t_order SET state = 2 WHERE state = 1";
        int result = 0;
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, null);
        }
        return result;
    }

    /**
     * 部分接单
     * @param oids
     * @return
     */
    public int receivePart(String oids) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE t_order SET state = 2 WHERE state = 1 AND order_id IN ("
                + oids + ")";
        int result = 0;
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, null);
        }
        return result;
    }

    public int cancelOrder(String oids) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE t_order SET state = 3 WHERE order_id IN (" + oids + ")";
        int result = 0;
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, null);
        }
        return result;
    }

    public List<Order> findNoHandleRecord() {
        List<Order> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM t_order WHERE state = 1";
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setDeskNumber(rs.getInt("desk_number"));
                order.setPhone(rs.getString("phone"));
                order.setFoodId(rs.getInt("food_id"));
                order.setPrice(rs.getInt("price"));
                order.setAmount(rs.getInt("amount"));
                order.setTotal(rs.getInt("total"));
                order.setCreateTime(rs.getTimestamp("create_time"));
                order.setState(rs.getInt("state"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return list;
    }

    public List<Food> getTop3Sales() {
        List<Food> top3Sales = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT f.food_name, f.food_id, COUNT(o.food_id) AS sales_volume "
                + "FROM t_food f "
                + "JOIN t_order o ON f.food_id = o.food_id "
                + "GROUP BY f.food_id "
                + "ORDER BY sales_volume DESC "
                + "LIMIT 3";
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodName(rs.getString("food_name"));
                food.setFoodId(rs.getInt("food_id"));
                top3Sales.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return top3Sales;
    }

    public List<Food> getTop3Revenue() {
        List<Food> top3Revenue = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT f.food_name, f.food_id, SUM(o.price * o.amount) AS revenue "
                + "FROM t_food f "
                + "JOIN t_order o ON f.food_id = o.food_id "
                + "GROUP BY f.food_id " + "ORDER BY revenue DESC " + "LIMIT 3";
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodName(rs.getString("food_name"));
                food.setFoodId(rs.getInt("food_id"));
                top3Revenue.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return top3Revenue;
    }

}