// 声明该类所在的包，表明这是一个工具类，属于 com.study.shop.util 包
package com.study.shop.util;

// 导入 java.text.SimpleDateFormat 类，用于格式化日期对象为指定格式的字符串，或将字符串解析为日期对象
import java.text.SimpleDateFormat;
// 导入 java.util.Date 类，用于表示特定的瞬间，精确到毫秒
import java.util.Date;

// 定义一个公共的 DateUtil 类，提供日期相关的工具方法
public class DateUtil {
    /**
     * 将日期对象格式化为 "yyyy-MM-dd HH:mm:ss" 格式的字符串
     * @param date 要格式化的日期对象
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date){ // 定义一个公共的静态方法 formatDate，接收一个 Date 类型的参数 date
        // 创建一个 SimpleDateFormat 对象，并指定日期格式为 "yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 调用 SimpleDateFormat 对象的 format 方法，将传入的日期对象格式化为字符串，并返回结果
        return sdf.format(date);
    } // DateUtil 类结束 // getCurrentToday 方法结束 // formatDate 方法结束

    /**
     * 获取当前日期的 "yyyy-MM-dd" 格式字符串
     * @return 当前日期的 "yyyy-MM-dd" 格式字符串
     */
    public static String getCurrentToday() { // 定义一个公共的静态方法 getCurrentToday，不接收参数
        // 创建一个新的 Date 对象，表示当前的日期和时间
        Date today = new Date();
        // 调用本类的 formatDate 方法，将当前日期对象格式化为 "yyyy-MM-dd HH:mm:ss" 格式的字符串
        String now = formatDate(today);
        // 截取格式化后的日期时间字符串的前11个字符（即 "yyyy-MM-dd " 部分，注意包含末尾空格），并返回结果
        return now.substring(0, 11);
    } // DateUtil 类结束 // getCurrentToday 方法结束 // formatDate 方法结束
} // DateUtil 类结束