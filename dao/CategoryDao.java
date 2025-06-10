package com.study.shop.dao; // 定义包名，表示该类属于com.study.shop.dao包

import java.sql.Connection; // 导入数据库连接相关的类
import java.sql.PreparedStatement; // 导入预编译SQL语句相关的类
import java.sql.ResultSet; // 导入结果集相关的类
import java.util.ArrayList; // 导入ArrayList类，用于创建列表
import java.util.List; // 导入List接口

import com.study.shop.po.Category; // 导入Category持久化对象类
import com.study.shop.util.JdbcUtil; // 导入JDBC工具类，用于获取连接和关闭资源

/**
 * CategoryDao类负责与数据库中的t_category表进行交互，
 * 提供对商品类目数据的增删改查操作。
 */
public class CategoryDao { // 定义CategoryDao类
    /**
     * 增加一个新的商品类目到数据库中。
     * 
     * @param category 包含新类目名称的Category对象。
     * @return 返回一个整数，表示数据库操作影响的行数。如果插入成功，通常返回1；如果失败，则返回0。
     */
    public int add(Category category) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        String sql = "insert into t_category(category_name) values(?)"; // 定义插入类目名称的SQL语句
        int result = 0; // 初始化结果变量，用于存储SQL执行影响的行数，默认为0
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            pstmt.setString(1, category.getCategoryName()); // 设置SQL语句中的第一个占位符参数，即类目名称
            result = pstmt.executeUpdate(); // 执行SQL更新操作（插入数据），并将影响的行数赋值给result变量
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            // finally块确保无论是否发生异常，都会执行资源释放操作
            JdbcUtil.closeAll(conn, pstmt, null); // 通过JdbcUtil工具类关闭数据库连接和PreparedStatement对象
        }
        return result; // 返回SQL操作影响的行数
    }

    /**
     * 查询数据库中所有未被逻辑删除的商品类目。
     * 
     * @return 返回一个包含所有Category对象的List集合。如果查询不到数据或发生异常，则返回空List。
     */
    public List<Category> findAll() {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        ResultSet rs = null; // 声明结果集对象，初始为null
        List<Category> list = new ArrayList<>(); // 创建一个ArrayList用于存储查询到的Category对象
        String sql = "select * from t_category where is_deleted = 0"; // 定义查询所有未删除类目的SQL语句
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            rs = pstmt.executeQuery(); // 执行SQL查询操作，并获取结果集
            while (rs.next()) { // 遍历结果集中的每一行数据
                Category category = new Category(); // 创建一个新的Category对象
                category.setCategoryId(rs.getInt(1)); // 从结果集中获取第一列（category_id）的值并设置到Category对象中
                category.setCategoryName(rs.getString(2)); // 从结果集中获取第二列（category_name）的值并设置到Category对象中
                list.add(category); // 将填充好数据的Category对象添加到List中
            }
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs); // 通过JdbcUtil工具类关闭数据库连接、PreparedStatement对象和ResultSet对象
        }
        return list; // 返回包含查询结果的List集合
    }

    /**
     * 根据提供的关键字模糊查询商品类目名称。
     * 
     * @param key 用于模糊匹配类目名称的关键字字符串。
     * @return 返回一个包含匹配到的Category对象的List集合。如果查询不到数据或发生异常，则返回空List。
     */
    public List<Category> findByKey(String key) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        ResultSet rs = null; // 声明结果集对象，初始为null
        List<Category> list = new ArrayList<>(); // 创建一个ArrayList用于存储查询到的Category对象
        String sql = "select * from t_category where is_deleted = 0 and category_name like ?"; // 定义根据关键字模糊查询未删除类目的SQL语句
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            // 设置SQL语句中的第一个占位符参数，即模糊查询的关键字，前后加上%表示任意字符匹配
            pstmt.setString(1, "%" + key + "%");
            rs = pstmt.executeQuery(); // 执行SQL查询操作，并获取结果集
            while (rs.next()) { // 遍历结果集中的每一行数据
                Category category = new Category(); // 创建一个新的Category对象
                category.setCategoryId(rs.getInt(1)); // 从结果集中获取第一列（category_id）的值并设置到Category对象中
                category.setCategoryName(rs.getString(2)); // 从结果集中获取第二列（category_name）的值并设置到Category对象中
                list.add(category); // 将填充好数据的Category对象添加到List中
            }
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs); // 通过JdbcUtil工具类关闭数据库连接、PreparedStatement对象和ResultSet对象
        }
        return list; // 返回包含查询结果的List集合
    }

    /**
     * 根据商品类目的主键ID查询单个类目信息。
     * 
     * @param categoryId 要查询的商品类目的ID。
     * @return 返回一个Category对象，包含查询到的类目信息。如果未找到对应ID的类目或发生异常，则返回null。
     */
    public Category findById(int categoryId) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        ResultSet rs = null; // 声明结果集对象，初始为null
        Category category = null; // 声明Category对象，用于存储查询结果，初始为null
        String sql = "select * from t_category where is_deleted = 0 and category_id = ?"; // 定义根据ID查询未删除类目的SQL语句
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            // 设置SQL语句中的第一个占位符参数，即类目ID
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery(); // 执行SQL查询操作，并获取结果集
            if (rs.next()) { // 如果结果集中有数据（即查询到匹配的类目）
                category = new Category(); // 创建一个新的Category对象
                category.setCategoryId(rs.getInt(1)); // 从结果集中获取第一列（category_id）的值并设置到Category对象中
                category.setCategoryName(rs.getString(2)); // 从结果集中获取第二列（category_name）的值并设置到Category对象中
            }
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs); // 通过JdbcUtil工具类关闭数据库连接、PreparedStatement对象和ResultSet对象
        }
        return category; // 返回查询到的Category对象，如果未找到则返回null
    }

    /**
     * 更新数据库中已存在的商品类目信息。
     * 
     * @param category 包含更新后类目信息的Category对象，其中categoryId用于定位要更新的记录，categoryName为新的类目名称。
     * @return 返回一个整数，表示数据库操作影响的行数。如果更新成功，通常返回1；如果未找到对应记录或更新失败，则返回0。
     */
    public int update(Category category) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        String sql = "update t_category set category_name=? where category_id=?"; // 定义更新类目名称的SQL语句
        int result = 0; // 初始化结果变量，用于存储SQL执行影响的行数，默认为0
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            pstmt.setString(1, category.getCategoryName()); // 设置SQL语句中的第一个占位符参数，即新的类目名称
            pstmt.setInt(2, category.getCategoryId()); // 设置SQL语句中的第二个占位符参数，即要更新的类目的ID
            result = pstmt.executeUpdate(); // 执行SQL更新操作，并将影响的行数赋值给result变量
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            // finally块确保无论是否发生异常，都会执行资源释放操作
            JdbcUtil.closeAll(conn, pstmt, null); // 通过JdbcUtil工具类关闭数据库连接和PreparedStatement对象
        }
        return result; // 返回SQL操作影响的行数
    }

    /**
     * 根据商品类目的主键ID逻辑删除一个类目。
     * 逻辑删除通常是将记录的某个状态字段（如is_deleted）标记为已删除，而不是直接从数据库中移除记录。
     * 
     * @param categoryId 要逻辑删除的商品类目的ID。
     * @return 返回一个整数，表示数据库操作影响的行数。如果逻辑删除成功，通常返回1；如果未找到对应记录或操作失败，则返回0。
     */
    public int delete(int categoryId) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        // 定义逻辑删除类目的SQL语句，将is_deleted字段更新为1
        String sql = "update t_category set is_deleted = 1 where category_id = ?";
        int result = 0; // 初始化结果变量，用于存储SQL执行影响的行数，默认为0
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            pstmt.setInt(1, categoryId); // 设置SQL语句中的第一个占位符参数，即要逻辑删除的类目的ID
            result = pstmt.executeUpdate(); // 执行SQL更新操作（逻辑删除），并将影响的行数赋值给result变量
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            // finally块确保无论是否发生异常，都会执行资源释放操作
            JdbcUtil.closeAll(conn, pstmt, null); // 通过JdbcUtil工具类关闭数据库连接和PreparedStatement对象
        }
        return result; // 返回SQL操作影响的行数
    }
} // CategoryDao类结束