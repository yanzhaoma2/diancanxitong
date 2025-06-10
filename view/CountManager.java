/**
 * 声明该类所属的包，此包为项目的视图层包，用于存放与界面展示和用户交互相关的类。
 */
package com.study.shop.view;

// 导入列表集合类，用于存储和操作多个对象
import java.util.List;
// 导入控制台输入类，用于获取用户在控制台输入的信息
import java.util.Scanner;

import com.study.shop.dao.OrderDao;
// 导入餐品实体类，用于封装餐品相关的属性和方法
import com.study.shop.po.Food;

public class CountManager {
    // 创建控制台输入扫描器的静态实例，用于获取用户在控制台输入的信息
public static Scanner sc = new Scanner(System.in);
    public static OrderDao orderDao = new OrderDao();

    public static void countMenu() {
        // 打印统计管理菜单的标题
System.out.println("-------------统计管理----------");
        System.out.println("\t1.统计销量前3的餐品");
        System.out.println("\t2.统计销售额前3的餐品");
        System.out.println("\t3.返回上一菜单");
        System.out.println("\t0.退出系统");
        System.out.println("------------------------------");
        System.out.println("请选择业务：");
        // 从控制台获取用户输入的选项
String option = sc.next();
        switch (option) {
            // 当用户选择1时，调用统计销量前3餐品的方法
case "1":
                countSalesTop3();
                break;
            // 当用户选择2时，调用统计销售额前3餐品的方法
case "2":
                countRevenueTop3();
                break;
            // 当用户选择3时，返回店主主菜单
case "3":
                ShopownerView.mainMenu();
                break;
            // 当用户选择0时，退出系统
case "0":
                System.exit(0);
                break;
            default:
                System.out.println("无效选项，请重新选择。");
        }
    }

    private static void countSalesTop3() {
        // 调用OrderDao的方法来获取销量前3的餐品
        List<Food> top3Sales = orderDao.getTop3Sales();
        if (top3Sales != null && !top3Sales.isEmpty()) {
            System.out.println("销量前3的餐品：");
            for (Food food : top3Sales) {
                System.out.println("餐品名称：" + food.getFoodName() + ", 销量：" + food.getSalesVolume());
            }
        } else {
            System.out.println("没有销量数据。");
        }
    }

    private static void countRevenueTop3() {
        // 调用OrderDao的方法来获取销售额前3的餐品
        List<Food> top3Revenue = orderDao.getTop3Revenue();
        if (top3Revenue != null && !top3Revenue.isEmpty()) {
            System.out.println("销售额前3的餐品：");
            for (Food food : top3Revenue) {
                System.out.println("餐品名称：" + food.getFoodName() + ", 销售额：" + food.getRevenue());
            }
        } else {
            System.out.println("没有销售额数据。");
        }
    }
}