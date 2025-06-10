package com.study.shop.dao; // 定义包名，表示该类属于com.study.shop.dao包

import java.sql.Connection; // 导入数据库连接相关的类
import java.sql.PreparedStatement; // 导入预编译SQL语句相关的类
import java.sql.ResultSet; // 导入结果集相关的类
import java.sql.SQLException; // 导入SQL异常类
import java.util.ArrayList; // 导入ArrayList类，用于创建列表
import java.util.List; // 导入List接口

import com.study.shop.po.Food; // 导入Food持久化对象类
import com.study.shop.util.JdbcUtil; // 导入JDBC工具类，用于获取连接和关闭资源

/**
 * FoodDao类负责与数据库中的t_food表进行交互，
 * 提供对餐品数据的增删改查操作。
 */
public class FoodDao { // 定义FoodDao类
    /**
     * 增加一个新的餐品到数据库中。
     *
     * @param food 包含新餐品信息的Food对象。
     * @return 返回一个整数，表示数据库操作影响的行数。如果插入成功，通常返回1；如果失败，则返回0。
     */
    public int add(Food food) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        String sql = "insert into t_food(food_name, category_id, price, description, is_deleted) values(?,?,?,?,?)"; // 定义插入餐品信息的SQL语句
        int result = 0; // 初始化结果变量，用于存储SQL执行影响的行数，默认为0
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            pstmt.setString(1, food.getFoodName()); // 设置SQL语句中的第一个占位符参数，即餐品名称
            pstmt.setInt(2, food.getCategoryId()); // 设置SQL语句中的第二个占位符参数，即餐品分类ID
            pstmt.setInt(3, food.getPrice()); // 设置SQL语句中的第三个占位符参数，即餐品价格
            pstmt.setString(4, food.getDescription()); // 设置SQL语句中的第四个占位符参数，即餐品描述
            pstmt.setInt(5, 0); // 设置SQL语句中的第五个占位符参数，即is_deleted字段，默认为0（未删除）
            result = pstmt.executeUpdate(); // 执行SQL更新操作（插入数据），并将影响的行数赋值给result变量
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null); // 通过JdbcUtil工具类关闭数据库连接和PreparedStatement对象
        }
        return result; // 返回SQL操作影响的行数
    }

    /**
     * 查询数据库中所有未被逻辑删除的餐品，并关联查询其所属的分类名称。
     *
     * @return 返回一个包含所有Food对象的List集合。如果查询不到数据或发生异常，则返回空List。
     */
    public static List<Food> findAll() {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        ResultSet rs = null; // 声明结果集对象，初始为null
        List<Food> list = new ArrayList<>(); // 创建一个ArrayList用于存储查询到的Food对象
        // 定义查询所有未删除餐品及其分类名称的SQL语句，使用JOIN连接t_food和t_category表
        String sql = "SELECT t1.*, t2.category_name " + "FROM t_food t1 "
                + "JOIN t_category t2 ON t1.category_id = t2.category_id "
                + "WHERE t1.is_deleted = 0";
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            rs = pstmt.executeQuery(); // 执行SQL查询操作，并获取结果集
            while (rs.next()) { // 遍历结果集中的每一行数据
                Food food = new Food(); // 创建一个新的Food对象
                food.setFoodId(rs.getInt("food_id")); // 从结果集中获取"food_id"列的值并设置到Food对象中
                food.setFoodName(rs.getString("food_name")); // 从结果集中获取"food_name"列的值并设置到Food对象中
                food.setCategoryId(rs.getInt("category_id")); // 从结果集中获取"category_id"列的值并设置到Food对象中
                food.setPrice(rs.getInt("price")); // 从结果集中获取"price"列的值并设置到Food对象中
                food.setDescription(rs.getString("description")); // 从结果集中获取"description"列的值并设置到Food对象中
                food.setDeleted(rs.getBoolean("is_deleted")); // 从结果集中获取"is_deleted"列的值并设置到Food对象中
                food.setCategoryName(rs.getString("category_name")); // 从结果集中获取"category_name"列的值并设置到Food对象中
                list.add(food); // 将填充好数据的Food对象添加到List中
            }
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs); // 通过JdbcUtil工具类关闭数据库连接、PreparedStatement对象和ResultSet对象
        }
        return list; // 返回包含查询结果的List集合
    }

    /**
     * 根据提供的关键字模糊查询餐品名称或其所属分类名称。
     *
     * @param key 用于模糊匹配餐品名称或分类名称的关键字字符串。
     * @return 返回一个包含匹配到的Food对象的List集合。如果查询不到数据或发生异常，则返回空List。
     */
    public List<Food> findByKey(String key) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        ResultSet rs = null; // 声明结果集对象，初始为null
        List<Food> list = new ArrayList<>(); // 创建一个ArrayList用于存储查询到的Food对象
        // 定义根据关键字模糊查询未删除餐品及其分类名称的SQL语句，使用CONCAT函数连接food_name和category_name进行模糊匹配
        String sql = "SELECT t1.*, t2.category_name "
                + "FROM t_food t1 "
                + "JOIN t_category t2 ON t1.category_id = t2.category_id "
                + "WHERE t1.is_deleted = 0 AND CONCAT(t1.food_name, t2.category_name) LIKE ?";
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            pstmt.setString(1, "%" + key + "%"); // 设置SQL语句中的第一个占位符参数，即模糊查询的关键字，前后加上%表示任意字符匹配
            rs = pstmt.executeQuery(); // 执行SQL查询操作，并获取结果集
            while (rs.next()) { // 遍历结果集中的每一行数据
                Food food = new Food(); // 创建一个新的Food对象
                food.setFoodId(rs.getInt("food_id")); // 从结果集中获取"food_id"列的值并设置到Food对象中
                food.setFoodName(rs.getString("food_name")); // 从结果集中获取"food_name"列的值并设置到Food对象中
                food.setCategoryId(rs.getInt("category_id")); // 从结果集中获取"category_id"列的值并设置到Food对象中
                food.setPrice(rs.getInt("price")); // 从结果集中获取"price"列的值并设置到Food对象中
                food.setDescription(rs.getString("description")); // 从结果集中获取"description"列的值并设置到Food对象中
                food.setDeleted(rs.getBoolean("is_deleted")); // 从结果集中获取"is_deleted"列的值并设置到Food对象中
                food.setCategoryName(rs.getString("category_name")); // 从结果集中获取"category_name"列的值并设置到Food对象中
                list.add(food); // 将填充好数据的Food对象添加到List中
            }
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs); // 通过JdbcUtil工具类关闭数据库连接、PreparedStatement对象和ResultSet对象
        }
        return list; // 返回包含查询结果的List集合
    }

    /**
     * 根据餐品的主键ID查询单个餐品信息，并关联查询其所属的分类名称。
     *
     * @param foodId 要查询的餐品ID。
     * @return 返回一个Food对象，包含查询到的餐品信息。如果未找到对应ID的餐品或发生异常，则返回null。
     */
    public Food findById(int foodId) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        ResultSet rs = null; // 声明结果集对象，初始为null
        Food food = null; // 声明Food对象，用于存储查询结果，初始为null
        // 定义根据ID查询未删除餐品及其分类名称的SQL语句，使用JOIN连接t_food和t_category表
        String sql = "select t1.*,category_name from "
                + "t_food t1,t_category t2 "
                + "where t1.category_id = t2.category_id "
                + "and t1.is_deleted = 0 and food_id = ?";

        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            pstmt.setInt(1, foodId); // 设置SQL语句中的第一个占位符参数，即餐品ID
            rs = pstmt.executeQuery(); // 执行SQL查询操作，并获取结果集
            if (rs.next()) { // 如果结果集中有数据（即查询到匹配的餐品）
                food = new Food(); // 创建一个新的Food对象
                food.setFoodId(rs.getInt("food_id")); // 从结果集中获取"food_id"列的值并设置到Food对象中
                food.setFoodName(rs.getString("food_name")); // 从结果集中获取"food_name"列的值并设置到Food对象中
                food.setCategoryId(rs.getInt("category_id")); // 从结果集中获取"category_id"列的值并设置到Food对象中
                food.setPrice(rs.getInt("price")); // 从结果集中获取"price"列的值并设置到Food对象中
                food.setDescription(rs.getString("description")); // 从结果集中获取"description"列的值并设置到Food对象中
            }
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs); // 通过JdbcUtil工具类关闭数据库连接、PreparedStatement对象和ResultSet对象
        }
        return food; // 返回查询到的Food对象，如果未找到则返回null
    }

    /**
     * 更新数据库中已存在的餐品信息。
     *
     * @param food 包含更新后餐品信息的Food对象，其中foodId用于定位要更新的记录，其他字段为新的餐品信息。
     * @return 返回一个整数，表示数据库操作影响的行数。如果更新成功，通常返回1；如果未找到对应记录或更新失败，则返回0。
     */
    public int update(Food food) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        String sql = "update t_food set food_name=?,category_id=?,price=?,description=? where food_id=?"; // 定义更新餐品信息的SQL语句
        int result = 0; // 初始化结果变量，用于存储SQL执行影响的行数，默认为0
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            pstmt.setString(1, food.getFoodName()); // 设置SQL语句中的第一个占位符参数，即新的餐品名称
            pstmt.setInt(2, food.getCategoryId()); // 设置SQL语句中的第二个占位符参数，即新的餐品分类ID
            pstmt.setInt(3, food.getPrice()); // 设置SQL语句中的第三个占位符参数，即新的餐品价格
            pstmt.setString(4, food.getDescription()); // 设置SQL语句中的第四个占位符参数，即新的餐品描述
            pstmt.setInt(5, food.getFoodId()); // 设置SQL语句中的第五个占位符参数，即要更新的餐品的ID
            result = pstmt.executeUpdate(); // 执行SQL更新操作，并将影响的行数赋值给result变量
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null); // 通过JdbcUtil工具类关闭数据库连接和PreparedStatement对象
        }
        return result; // 返回SQL操作影响的行数
    }

    /**
     * 根据餐品的主键ID逻辑删除一个餐品。
     * 逻辑删除通常是将记录的某个状态字段（如is_deleted）标记为已删除，而不是直接从数据库中移除记录。
     *
     * @param foodId 要逻辑删除的餐品ID。
     * @return 返回一个整数，表示数据库操作影响的行数。如果逻辑删除成功，通常返回1；如果未找到对应记录或操作失败，则返回0。
     */
    public int delete(int foodId) {
        Connection conn = null; // 声明数据库连接对象，初始为null
        PreparedStatement pstmt = null; // 声明预编译的SQL语句对象，初始为null
        String sql = "update t_food  set is_deleted =1 where food_id =?"; // 定义逻辑删除餐品的SQL语句，将is_deleted字段更新为1
        int result = 0; // 初始化结果变量，用于存储SQL执行影响的行数，默认为0
        try {
            conn = JdbcUtil.getConn(); // 通过JdbcUtil工具类获取数据库连接
            pstmt = conn.prepareStatement(sql); // 使用获取到的连接预编译SQL语句
            pstmt.setInt(1, foodId); // 设置SQL语句中的第一个占位符参数，即要逻辑删除的餐品的ID
            result = pstmt.executeUpdate(); // 执行SQL更新操作（逻辑删除），并将影响的行数赋值给result变量
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印执行过程中可能发生的任何异常的堆栈信息
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null); // 通过JdbcUtil工具类关闭数据库连接和PreparedStatement对象
        }
        return result; // 返回SQL操作影响的行数
    }
} // FoodDao类结束