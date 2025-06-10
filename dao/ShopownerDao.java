// 声明该类所在的包，表明这是一个数据访问对象（DAO）类，属于 com.study.shop.dao 包
package com.study.shop.dao;

// 导入 java.sql 包中的 Connection 类，用于建立与数据库的连接
import java.sql.Connection;
// 导入 java.sql 包中的 PreparedStatement 类，用于执行预编译的 SQL 语句
import java.sql.PreparedStatement;
// 导入 java.sql 包中的 ResultSet 类，用于存储数据库查询结果
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 导入自定义的 JdbcUtil 工具类，用于管理数据库连接和资源关闭
import com.study.shop.util.JdbcUtil;

// 定义一个公共的 ShopownerDao 类，用于处理店主相关的数据库操作
public class ShopownerDao {
    /**
     * 获取当前营业状态
     */
    public String getShopStatus() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            String sql = "SELECT status FROM shopowner LIMIT 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, ps, rs);
        }
        return "1"; // 默认返回营业中
    }

    /**
     * 切换营业状态
     */
    public void toggleShopStatus() {
        System.out.println("请输入您要修改的营业状态：");
        Scanner scanner = new Scanner(System.in);
        String newStatus = scanner.next();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            String sql = "UPDATE shopowner SET status = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, newStatus);
            ps.executeUpdate();
            System.out.println("修改成功");
        } catch (SQLException e) {
            System.out.println("\u001b[31m状态更新失败：" + e.getMessage() + "\u001b[0m");
        } finally {
            JdbcUtil.closeAll(conn, ps, rs);
        }
    }
    /**
     * 店主登录
     * @param phone 电话
     * @param password 密码
     * @return 成功 true 失败 false
     */
    public boolean login(String phone, String password) {
        // 声明一个 Connection 类型的变量，用于存储与数据库的连接，初始化为 null
    Connection conn = null;
        // 声明一个 PreparedStatement 类型的变量，用于执行预编译的 SQL 语句，初始化为 null
    PreparedStatement pstmt = null;
        // 声明一个 ResultSet 类型的变量，用于存储数据库查询结果，初始化为 null
    ResultSet rs = null;
        // 定义一个 SQL 查询语句，用于查询 t_shopowner 表中指定电话和密码的店主记录
    String sql = "select * from t_shopowner where phone=? and password = ?";
        // 声明一个布尔类型的变量，用于存储登录结果，初始化为 false，表示登录失败
    boolean result = false;
        try {
            // 调用 JdbcUtil 工具类的 getConn 方法，获取与数据库的连接，并赋值给 conn 变量
    conn = JdbcUtil.getConn();
            // 使用 conn 对象的 prepareStatement 方法，将 SQL 语句预编译为 PreparedStatement 对象，并赋值给 pstmt 变量
    pstmt = conn.prepareStatement(sql);
            // 为预编译的 SQL 语句中的第一个占位符（?）设置值，即传入的电话参数
    pstmt.setString(1, phone);
            // 为预编译的 SQL 语句中的第二个占位符（?）设置值，即传入的密码参数
    pstmt.setString(2, password);
            // 调用 pstmt 对象的 executeQuery 方法，执行 SQL 查询，并将查询结果存储在 rs 变量中
    rs = pstmt.executeQuery();
            // 调用 rs 对象的 next 方法，判断结果集中是否有下一条记录，如果有则进入 if 语句块
    if (rs.next()) {
                // 如果查询到数据
                // 如果查询到数据，将登录结果设置为 true，表示登录成功
    result = true;
            }
        } catch (Exception e) {
            // 打印异常堆栈信息，方便调试和定位问题
    e.printStackTrace();
        } finally {
            // 关闭资源
            // 调用 JdbcUtil 工具类的 closeAll 方法，关闭数据库连接、PreparedStatement 对象和 ResultSet 对象，释放资源
    JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return result;
    }
}