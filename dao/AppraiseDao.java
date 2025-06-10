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

    public static int add(Appraise appraise) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "INSERT INTO t_appraise (food_id, comment, star) VALUES (?, ?, ?)";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, appraise.getFoodId());
            pstmt.setString(2, appraise.getComment());
            pstmt.setInt(3, appraise.getStar());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }
}