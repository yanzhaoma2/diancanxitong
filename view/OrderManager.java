/**
 * 声明该类所属的包，此包为项目的视图层包，用于存放与界面展示和用户交互相关的类。
 */
package com.study.shop.view;

// 导入列表集合类，用于存储和操作多个对象
import java.util.List;
// 导入控制台输入类，用于获取用户在控制台输入的信息
import java.util.Scanner;

// 导入订单数据访问对象类，用于与数据库中的订单表进行交互
import com.study.shop.dao.OrderDao;
// 导入订单实体类，用于封装订单相关的属性和方法
import com.study.shop.po.Order;

public class OrderManager {
    // 创建控制台输入扫描器的静态实例，用于获取用户在控制台输入的信息
public static Scanner sc = new Scanner(System.in);
    // 引入Dao
    // 创建订单数据访问对象的静态实例，方便在整个类中使用该对象进行数据库操作
public static OrderDao orderDao = new OrderDao();

    public static void orderMenu() {
        // 打印订单管理界面菜单的标题
System.out.println("------------订单管理------------");
        while (true) {
            //循环打印未处理订单
            // 调用订单数据访问对象的查询未处理订单方法，获取所有未处理订单信息并存储在列表中
List<Order> list = orderDao.findNoHandleRecord();
            // 遍历未处理订单列表，获取列表中的每个订单对象
for (Order order : list) {
                System.out.println(order);
            }
            System.out.println("1.全部接单  2.部分接单  3.取消订单");
            // 从控制台获取用户输入的选项
String option = sc.next();
            switch (option) {
                // 当用户选择1时，调用全部接单方法
case "1":
                    receiveAll();
            }
        }

    }

    // 全部接单
    private static void receiveAll() {
        int result = orderDao.receiveAll();
        if (result > 0) {
            System.out.println("完成接单");
        } else {
            System.out.println("接单失败");
        }
    }

    // 部分接单
    private static void recivePart() {
        System.out.println("输入要接单的编号（中间用逗号间隔）：");
        String oids = sc.next();
        int result = orderDao.receivePart(oids);
        if (result > 0) {
            System.out.println("完成接单");
        } else {
            System.out.println("接单失败");
        }
    }

    // 取消订单
    private static void cancleOrder() {
        System.out.println("输入要取消的订单编号（中间用逗号间隔）：");
        String oids = sc.next();
        int result = orderDao.cancelOrder(oids);
        if (result > 0) {
            System.out.println("完成取消");
        } else {
            System.out.println("取消失败");
        }
    }
}