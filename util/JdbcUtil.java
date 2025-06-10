package com.study.shop.util;

import java.sql.*;

public class JdbcUtil {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/点餐系统";
    public static final String USER = "root";
    public static final String PWD = "123456"; // 注意是自己数据库的密码
    // 加载驱动,静态块在类加载到虚拟机后就直接执行
    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能：根据指定的连接字符串创建连接
     * @return
     */
    public static Connection getConn()  {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接，释放资源
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if(conn != null){
                conn.close();
            }
            if(stmt != null){
                conn.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}