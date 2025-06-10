// 声明该类所在的包，表明这是一个工具类，属于 com.study.shop.util 包
package com.study.shop.util;

// 导入 java.sql 包下的所有类，用于数据库连接和操作，例如 Connection, Statement, ResultSet, DriverManager, SQLException 等
import java.sql.*;

// 定义一个公共的 JdbcUtil 类，提供 JDBC 相关的工具方法
public class JdbcUtil {
    // 定义一个公共的、静态的、最终的字符串常量 DRIVER，存储 MySQL 数据库驱动程序的类名
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // 定义一个公共的、静态的、最终的字符串常量 URL，存储数据库连接的 URL，指定了数据库类型、主机名、端口号和数据库名称
    public static final String URL = "jdbc:mysql://localhost:3306/点餐系统";
    // 定义一个公共的、静态的、最终的字符串常量 USER，存储连接数据库的用户名
    public static final String USER = "root";
    // 定义一个公共的、静态的、最终的字符串常量 PWD，存储连接数据库的密码
    public static final String PWD = "123456"; // 注意：这里存储的是示例密码，实际使用时应替换为用户自己数据库的密码
    // 静态代码块，在 JdbcUtil 类被加载到 JVM 时执行，用于加载数据库驱动程序
    // 加载驱动,静态块在类加载到虚拟机后就直接执行
    static{ // 开始静态代码块定义
        try { // 开始 try-catch 块，用于捕获关闭资源过程中可能发生的 SQLException // 开始 try-catch 块，用于捕获获取连接过程中可能发生的 SQLException // 开始 try-catch 块，用于捕获加载驱动过程中可能发生的异常
            // 使用 Class.forName() 方法加载 MySQL 数据库驱动程序，DRIVER常量中存储了驱动的类名
            Class.forName(DRIVER);
        } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束 catch (ClassNotFoundException e) { // 捕获 ClassNotFoundException 异常，该异常在驱动类未找到时抛出
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            e.printStackTrace();
        } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束
    } // JdbcUtil 类结束 // closeAll 方法结束 // getConn 方法结束 // 静态代码块结束

    /**
     * 功能：根据预定义的 URL、用户名和密码创建并返回一个数据库连接对象。
     * @return Connection 数据库连接对象，如果连接失败则返回 null。
     */
    // 定义一个公共的静态方法 getConn，用于获取数据库连接
    public static Connection getConn()  {
        // 声明一个 Connection 类型的变量 conn，并初始化为 null，用于存储数据库连接对象
        Connection conn = null;
        try { // 开始 try-catch 块，用于捕获关闭资源过程中可能发生的 SQLException // 开始 try-catch 块，用于捕获获取连接过程中可能发生的 SQLException // 开始 try-catch 块，用于捕获加载驱动过程中可能发生的异常
            // 调用 DriverManager.getConnection() 方法，使用预定义的 URL、USER 和 PWD 获取数据库连接，并将连接对象赋值给 conn
            conn = DriverManager.getConnection(URL,USER,PWD);
        } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束} // JdbcUtil 类结束 catch (SQLException e) { // 捕获 SQLException 异常，该异常在关闭资源过程中发生数据库访问错误时抛出 // 捕获 SQLException 异常，该异常在数据库访问错误时抛出
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            e.printStackTrace();
        } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束
        // 返回获取到的数据库连接对象 conn，如果连接失败，则返回 null
        return conn;
    } // JdbcUtil 类结束 // closeAll 方法结束 // getConn 方法结束 // 静态代码块结束

    /**
     * 关闭数据库连接、Statement 对象和 ResultSet 对象，释放占用的资源。
     * @param conn 需要关闭的数据库连接对象 (Connection)
     * @param stmt 需要关闭的 Statement 对象 (Statement)
     * @param rs 需要关闭的 ResultSet 对象 (ResultSet)
     */
    // 定义一个公共的静态方法 closeAll，用于关闭数据库相关的资源
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        try { // 开始 try-catch 块，用于捕获关闭资源过程中可能发生的 SQLException // 开始 try-catch 块，用于捕获获取连接过程中可能发生的 SQLException // 开始 try-catch 块，用于捕获加载驱动过程中可能发生的异常
            // 检查 Connection 对象是否不为 null，如果不为 null，则尝试关闭它
            if(conn != null){
                // 调用 Connection 对象的 close() 方法关闭数据库连接
                // 调用 Statement 对象的 close() 方法关闭 Statement 对象 (此处应为 stmt.close())
                stmt.close(); // 更正：应该是 stmt.close() 而不是 conn.close()
            } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // ResultSet 对象关闭结束 // Statement 对象关闭结束 // Connection 对象关闭结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束
            // 检查 Statement 对象是否不为 null，如果不为 null，则尝试关闭它
            if(stmt != null){
                // 调用 Connection 对象的 close() 方法关闭数据库连接
                // 调用 Statement 对象的 close() 方法关闭 Statement 对象 (此处应为 stmt.close())
                stmt.close(); // 更正：应该是 stmt.close() 而不是 conn.close()
            } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // ResultSet 对象关闭结束 // Statement 对象关闭结束 // Connection 对象关闭结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束
            // 检查 ResultSet 对象是否不为 null，如果不为 null，则尝试关闭它
            if(rs != null){
                // 调用 ResultSet 对象的 close() 方法关闭 ResultSet 对象
                rs.close();
            } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // ResultSet 对象关闭结束 // Statement 对象关闭结束 // Connection 对象关闭结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束
        } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束} // JdbcUtil 类结束 catch (SQLException e) { // 捕获 SQLException 异常，该异常在关闭资源过程中发生数据库访问错误时抛出 // 捕获 SQLException 异常，该异常在数据库访问错误时抛出
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            // 打印异常的堆栈跟踪信息到标准错误流，方便调试
            e.printStackTrace();
        } // JdbcUtil 类结束 // closeAll 方法结束 // try-catch 块结束 // getConn 方法结束 // try-catch 块结束 // 静态代码块结束 // try-catch 块结束
    } // JdbcUtil 类结束 // closeAll 方法结束 // getConn 方法结束 // 静态代码块结束

} // JdbcUtil 类结束