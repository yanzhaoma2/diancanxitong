package com.study.shop.dao;

import com.study.shop.po.Appraise;
import com.study.shop.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppraiseDao {

    /**
     * 添加评价信息到数据库。
     * 此方法接收一个Appraise对象，将其包含的评价数据持久化到数据库的t_appraise表中。
     *
     * @param appraise 包含待添加评价信息的Appraise对象。此对象应包含food_id, comment, 和 star属性。
     * @return 返回一个整数，表示数据库操作影响的行数。如果插入成功，通常返回1；如果因任何原因失败，则返回0。
     */
    public static int add(Appraise appraise) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        int result = 0; // 初始化结果变量，用于存储SQL执行影响的行数，默认为0
        // 定义插入评价数据的SQL语句，使用占位符(?)来防止SQL注入
        String sql = "INSERT INTO t_appraise (food_id, comment, star) VALUES (?, ?, ?)";
        try {
            // 通过JdbcUtil工具类获取数据库连接
            conn = JdbcUtil.getConn();
            // 使用获取到的连接预编译SQL语句
            pstmt = conn.prepareStatement(sql);
            // 设置SQL语句中的第一个占位符参数，即食品ID
            pstmt.setInt(1, appraise.getFoodId());
            // 设置SQL语句中的第二个占位符参数，即评价内容
            pstmt.setString(2, appraise.getComment());
            // 设置SQL语句中的第三个占位符参数，即星级评分
            pstmt.setInt(3, appraise.getStar());
            // 执行SQL更新操作（插入数据），并将影响的行数赋值给result变量
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // 捕获SQL执行过程中可能发生的SQLException异常
            e.printStackTrace(); // 打印异常堆栈信息，方便调试
        } finally {
            // finally块确保无论是否发生异常，都会执行资源释放操作
            // 通过JdbcUtil工具类关闭数据库连接和PreparedStatement对象，防止资源泄露
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result; // 返回SQL操作影响的行数
    }
}