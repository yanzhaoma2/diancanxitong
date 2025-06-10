// 声明该类所在的包，com.study.shop.dao 通常用于存放数据访问对象相关的类
package com.study.shop.dao;

// 导入 java.sql 包下的类，用于处理数据库连接和操作
import java.sql.Connection;
// 用于管理数据库驱动和获取数据库连接
import java.sql.DriverManager;
// 用于执行预编译的 SQL 语句
import java.sql.PreparedStatement;
// 用于处理查询结果集
import java.sql.ResultSet;
// 用于处理 SQL 异常
import java.sql.SQLException;
// 用于执行静态 SQL 语句
import java.sql.Statement;
// 导入 java.util 包下的类，用于处理集合
import java.util.ArrayList;
// 定义一个列表接口
import java.util.List;

// 导入自定义的实体类，Food 表示餐品信息
import com.study.shop.po.Food;
// 导入自定义的实体类，Order 表示订单信息
import com.study.shop.po.Order;
// 导入自定义的工具类，DateUtil 用于处理日期相关操作
import com.study.shop.util.DateUtil;
// 导入自定义的工具类，JdbcUtil 用于管理数据库连接
import com.study.shop.util.JdbcUtil;

// 定义一个公共的类 OrderDao，用于处理订单相关的数据访问操作
public class OrderDao {
    // 定义数据库驱动类名，使用 MySQL 8.0 及以上版本的驱动
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // 定义数据库连接 URL，指定数据库地址、端口、数据库名和字符编码
    public static final String URL = "jdbc:mysql://localhost:3306/点餐系统?useSSL=false&characterEncoding=UTF-8";
    // 定义数据库用户名
    public static final String USER = "root";
    // 定义数据库密码
    public static final String PWD = "123456";

    // 静态代码块，在类加载时执行，用于加载数据库驱动
    static {
        // 尝试加载数据库驱动类
        try {
            // 使用反射机制加载数据库驱动类
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // 若加载失败，打印异常堆栈信息
            e.printStackTrace();
        }
    }

    // 定义一个静态方法，用于获取数据库连接
    public static Connection getConn() throws SQLException {
        // 使用 DriverManager 类的 getConnection 方法获取数据库连接
        return DriverManager.getConnection(URL, USER, PWD);
    }

    // 定义一个静态方法，用于关闭数据库连接、语句和结果集
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        // 尝试关闭数据库资源
        try {
            // 若结果集不为空，关闭结果集
            if (rs != null) {
                rs.close();
            }
            // 若语句对象不为空，关闭语句对象
            if (stmt != null) {
                stmt.close();
            }
            // 若数据库连接不为空，关闭数据库连接
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // 若关闭失败，打印异常堆栈信息
            e.printStackTrace();
        }
    }

    // 定义一个公共方法，用于批量添加订单信息
    public boolean addOrder(List<Order> orderList) {
        // 定义数据库连接对象
        Connection conn = null;
        // 定义预编译语句对象
        PreparedStatement pstmt = null;
        // 定义插入订单信息的 SQL 语句，使用占位符 ?
        String sql = "INSERT INTO t_order(desk_number, phone, food_id, price, amount, total) VALUES (?, ?, ?, ?, ?, ?)";
        // 尝试执行批量插入操作
        try {
            // 获取数据库连接
            conn = getConn();
            // 预编译 SQL 语句
            pstmt = conn.prepareStatement(sql);
            // 遍历订单列表
            for (Order order : orderList) {
                // 设置 SQL 语句中的第一个参数，桌号
                pstmt.setInt(1, order.getDeskNumber());
                // 设置 SQL 语句中的第二个参数，手机号
                pstmt.setString(2, order.getPhone());
                // 设置 SQL 语句中的第三个参数，餐品 ID
                pstmt.setInt(3, order.getFoodId());
                // 设置 SQL 语句中的第四个参数，餐品价格
                pstmt.setInt(4, order.getPrice());
                // 设置 SQL 语句中的第五个参数，餐品数量
                pstmt.setInt(5, order.getAmount());
                // 设置 SQL 语句中的第六个参数，订单总价
                pstmt.setInt(6, order.getTotal());
                // 将当前订单信息添加到批处理中
                pstmt.addBatch();
            }
            // 执行批处理操作，返回每个插入操作的结果
            int[] result = pstmt.executeBatch();
            // 遍历结果数组
            for (int i : result) {
                // 若有插入操作失败（返回值不为 1），返回 false
                if (i != 1) {
                    return false;
                }
            }
            // 所有插入操作成功，返回 true
            return true;
        } catch (SQLException e) {
            // 若执行过程中出现异常，打印异常堆栈信息并返回 false
            e.printStackTrace();
            return false;
        } finally {
            // 无论是否成功，关闭数据库连接和语句对象
            closeAll(conn, pstmt, null);
        }
    }

    // 定义一个静态方法，用于根据手机号和选项查询订单记录
    public static List<Order> findRecord(String phone, int option) {
        // 定义数据库连接对象
        Connection conn = null;
        // 定义预编译语句对象
        PreparedStatement pstmt = null;
        // 定义结果集对象
        ResultSet rs = null;
        // 定义 SQL 语句变量
        String sql = null;
        // 定义一个列表，用于存储查询到的订单记录
        List<Order> list = new ArrayList<>();
        // 尝试执行查询操作
        try {
            // 使用 JdbcUtil 工具类获取数据库连接
            conn = JdbcUtil.getConn();

            // 根据选项值构建不同的 SQL 语句
            if (option == 1) {
                // 获取当前日期
                String today = DateUtil.getCurrentToday();
                // 构建查询今天已接单订单记录的 SQL 语句
                sql = "select t1.*,food_name from t_order t1,t_food t2 "
                        + " where t1.food_id = t2.food_id and "
                        + "phone=? and DATE(create_time)=? and state=1";
                // 预编译 SQL 语句
                pstmt = conn.prepareStatement(sql);
                // 设置 SQL 语句中的第一个参数，手机号
                pstmt.setString(1, phone);
                // 设置 SQL 语句中的第二个参数，当前日期
                pstmt.setString(2, today);
            }
            if (option == 2) {
                // 获取当前日期
                String today = DateUtil.getCurrentToday();
                // 构建查询今天已完成订单记录的 SQL 语句
                sql = "select t1.*,food_name from t_order t1,t_food t2 "
                        + " where t1.food_id = t2.food_id and "
                        + "phone=? and DATE(create_time)=? and state=2";
                // 预编译 SQL 语句
                pstmt = conn.prepareStatement(sql);
                // 设置 SQL 语句中的第一个参数，手机号
                pstmt.setString(1, phone);
                // 设置 SQL 语句中的第二个参数，当前日期
                pstmt.setString(2, today);
            }
            if (option == 3) {
                // 构建查询所有订单记录的 SQL 语句
                sql = "select t1.*,food_name from t_order t1,t_food t2 "
                        + " where t1.food_id = t2.food_id and " + "phone=?";
                // 预编译 SQL 语句
                pstmt = conn.prepareStatement(sql);
                // 设置 SQL 语句中的第一个参数，手机号
                pstmt.setString(1, phone);
            }

            // 执行查询操作，获取结果集
            rs = pstmt.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                // 创建一个新的订单对象
                Order order = new Order();
                // 设置订单对象的订单 ID
                order.setOrderId(rs.getInt(1));
                // 设置订单对象的桌号
                order.setDeskNumber(rs.getInt(2));
                // 设置订单对象的手机号
                order.setPhone(rs.getString(3));
                // 设置订单对象的餐品 ID
                order.setFoodId(rs.getInt(4));
                // 设置订单对象的餐品价格
                order.setPrice(rs.getInt(5));
                // 设置订单对象的餐品数量
                order.setAmount(rs.getInt(6));
                // 设置订单对象的订单总价
                order.setTotal(rs.getInt(7));
                // 设置订单对象的创建时间
                order.setCreateTime(rs.getTimestamp(8));
                // 设置订单对象的订单状态
                order.setState(rs.getInt(9));
                // 设置订单对象的餐品名称
                order.setFoodName(rs.getString("food_name"));
                // 将订单对象添加到列表中
                list.add(order);
            }
        } catch (Exception e) {
            // 若执行过程中出现异常，打印异常堆栈信息
            e.printStackTrace();
        } finally {
            // 无论是否成功，关闭数据库连接、语句对象和结果集
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        // 返回查询到的订单记录列表
        return list;
    }

    /**
     * 全部接单，将所有状态为 1（未处理）的订单状态更新为 2（已接单）
     * @return 受影响的记录行数
     */
    // 定义一个公共方法，用于全部接单操作
    public int receiveAll() {
        // 定义数据库连接对象
        Connection conn = null;
        // 定义预编译语句对象
        PreparedStatement pstmt = null;
        // 定义更新订单状态的 SQL 语句，将所有状态为 1 的订单状态更新为 2
        String sql = "UPDATE t_order SET state = 2 WHERE state = 1";
        // 定义一个变量，用于存储受影响的记录行数
        int result = 0;
        // 尝试执行更新操作
        try {
            // 获取数据库连接
            conn = getConn();
            // 预编译 SQL 语句
            pstmt = conn.prepareStatement(sql);
            // 执行更新操作，返回受影响的记录行数
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // 若执行过程中出现异常，打印异常堆栈信息
            e.printStackTrace();
        } finally {
            // 无论是否成功，关闭数据库连接和语句对象
            closeAll(conn, pstmt, null);
        }
        // 返回受影响的记录行数
        return result;
    }

    /**
     * 部分接单，将指定订单 ID 且状态为 1（未处理）的订单状态更新为 2（已接单）
     * @param oids 订单 ID 字符串，多个 ID 用逗号分隔
     * @return 受影响的记录行数
     */
    // 定义一个公共方法，用于部分接单操作
    public int receivePart(String oids) {
        // 定义数据库连接对象
        Connection conn = null;
        // 定义预编译语句对象
        PreparedStatement pstmt = null;
        // 定义更新部分订单状态的 SQL 语句，将指定订单 ID 且状态为 1 的订单状态更新为 2
        String sql = "UPDATE t_order SET state = 2 WHERE state = 1 AND order_id IN ("
                + oids + ")";
        // 定义一个变量，用于存储受影响的记录行数
        int result = 0;
        // 尝试执行更新操作
        try {
            // 获取数据库连接
            conn = getConn();
            // 预编译 SQL 语句
            pstmt = conn.prepareStatement(sql);
            // 执行更新操作，返回受影响的记录行数
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // 若执行过程中出现异常，打印异常堆栈信息
            e.printStackTrace();
        } finally {
            // 无论是否成功，关闭数据库连接和语句对象
            closeAll(conn, pstmt, null);
        }
        // 返回受影响的记录行数
        return result;
    }

    // 定义一个公共方法，用于取消指定订单
    public int cancelOrder(String oids) {
        // 定义数据库连接对象
        Connection conn = null;
        // 定义预编译语句对象
        PreparedStatement pstmt = null;
        // 定义取消订单的 SQL 语句，将指定订单 ID 的订单状态更新为 3
        String sql = "UPDATE t_order SET state = 3 WHERE order_id IN (" + oids + ")";
        // 定义一个变量，用于存储受影响的记录行数
        int result = 0;
        // 尝试执行更新操作
        try {
            // 获取数据库连接
            conn = getConn();
            // 预编译 SQL 语句
            pstmt = conn.prepareStatement(sql);
            // 执行更新操作，返回受影响的记录行数
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // 若执行过程中出现异常，打印异常堆栈信息
            e.printStackTrace();
        } finally {
            // 无论是否成功，关闭数据库连接和语句对象
            closeAll(conn, pstmt, null);
        }
        // 返回受影响的记录行数
        return result;
    }

    // 定义一个公共方法，用于查询未处理的订单记录
    public List<Order> findNoHandleRecord() {
        // 定义一个列表，用于存储未处理的订单记录
        List<Order> list = new ArrayList<>();
        // 定义数据库连接对象
        Connection conn = null;
        // 定义预编译语句对象
        PreparedStatement pstmt = null;
        // 定义结果集对象
        ResultSet rs = null;
        // 定义查询未处理订单记录的 SQL 语句，查询状态为 1 的订单
        String sql = "SELECT * FROM t_order WHERE state = 1";
        // 尝试执行查询操作
        try {
            // 获取数据库连接
            conn = getConn();
            // 预编译 SQL 语句
            pstmt = conn.prepareStatement(sql);
            // 执行查询操作，获取结果集
            rs = pstmt.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                // 创建一个新的订单对象
                Order order = new Order();
                // 设置订单对象的订单 ID
                order.setOrderId(rs.getInt("order_id"));
                // 设置订单对象的桌号
                order.setDeskNumber(rs.getInt("desk_number"));
                // 设置订单对象的手机号
                order.setPhone(rs.getString("phone"));
                // 设置订单对象的餐品 ID
                order.setFoodId(rs.getInt("food_id"));
                // 设置订单对象的餐品价格
                order.setPrice(rs.getInt("price"));
                // 设置订单对象的餐品数量
                order.setAmount(rs.getInt("amount"));
                // 设置订单对象的订单总价
                order.setTotal(rs.getInt("total"));
                // 设置订单对象的创建时间
                order.setCreateTime(rs.getTimestamp("create_time"));
                // 设置订单对象的订单状态
                order.setState(rs.getInt("state"));
                // 将订单对象添加到列表中
                list.add(order);
            }
        } catch (SQLException e) {
            // 若执行过程中出现异常，打印异常堆栈信息
            e.printStackTrace();
        } finally {
            // 无论是否成功，关闭数据库连接、语句对象和结果集
            closeAll(conn, pstmt, rs);
        }
        // 返回未处理的订单记录列表
        return list;
    }

    // 定义一个公共方法，用于查询销量前三的餐品
    public List<Food> getTop3Sales() {
        // 定义一个列表，用于存储销量前三的餐品
        List<Food> top3Sales = new ArrayList<>();
        // 定义数据库连接对象
        Connection conn = null;
        // 定义预编译语句对象
        PreparedStatement pstmt = null;
        // 定义结果集对象
        ResultSet rs = null;
        // 定义查询销量前三餐品的 SQL 语句
        String sql = "SELECT f.food_name, f.food_id, COUNT(o.food_id) AS sales_volume "
                + "FROM t_food f "
                + "JOIN t_order o ON f.food_id = o.food_id "
                + "GROUP BY f.food_id "
                + "ORDER BY sales_volume DESC "
                + "LIMIT 3";
        // 尝试执行查询操作
        try {
            // 获取数据库连接
            conn = getConn();
            // 预编译 SQL 语句
            pstmt = conn.prepareStatement(sql);
            // 执行查询操作，获取结果集
            rs = pstmt.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                // 创建一个新的餐品对象
                Food food = new Food();
                // 设置餐品对象的餐品名称
                food.setFoodName(rs.getString("food_name"));
                // 设置餐品对象的餐品 ID
                food.setFoodId(rs.getInt("food_id"));
                // 将餐品对象添加到列表中
                top3Sales.add(food);
            }
        } catch (SQLException e) {
            // 若执行过程中出现异常，打印异常堆栈信息
            e.printStackTrace();
        } finally {
            // 无论是否成功，关闭数据库连接、语句对象和结果集
            closeAll(conn, pstmt, rs);
        }
        // 返回销量前三的餐品列表
        return top3Sales;
    }

    // 定义一个公共方法，用于查询营收前三的餐品
    public List<Food> getTop3Revenue() {
        // 定义一个列表，用于存储营收前三的餐品
        List<Food> top3Revenue = new ArrayList<>();
        // 定义数据库连接对象
        Connection conn = null;
        // 定义预编译语句对象
        PreparedStatement pstmt = null;
        // 定义结果集对象
        ResultSet rs = null;
        // 定义查询营收前三餐品的 SQL 语句
        String sql = "SELECT f.food_name, f.food_id, SUM(o.price * o.amount) AS revenue "
                + "FROM t_food f "
                + "JOIN t_order o ON f.food_id = o.food_id "
                + "GROUP BY f.food_id " + "ORDER BY revenue DESC " + "LIMIT 3";
        // 尝试执行查询操作
        try {
            // 获取数据库连接
            conn = getConn();
            // 预编译 SQL 语句
            pstmt = conn.prepareStatement(sql);
            // 执行查询操作，获取结果集
            rs = pstmt.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                // 创建一个新的餐品对象
                Food food = new Food();
                // 设置餐品对象的餐品名称
                food.setFoodName(rs.getString("food_name"));
                // 设置餐品对象的餐品 ID
                food.setFoodId(rs.getInt("food_id"));
                // 将餐品对象添加到列表中
                top3Revenue.add(food);
            }
        } catch (SQLException e) {
            // 若执行过程中出现异常，打印异常堆栈信息
            e.printStackTrace();
        } finally {
            // 无论是否成功，关闭数据库连接、语句对象和结果集
            closeAll(conn, pstmt, rs);
        }
        // 返回营收前三的餐品列表
        return top3Revenue;
    }

}