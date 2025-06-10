package com.study.shop.view;

import java.util.List;
import java.util.Scanner;

import com.study.shop.dao.OrderDao;
import com.study.shop.po.Food;

public class CountManager {
    public static Scanner sc = new Scanner(System.in);
    public static OrderDao orderDao = new OrderDao();

    public static void countMenu() {
        System.out.println("-------------统计管理----------");
        System.out.println("\t1.统计销量前3的餐品");
        System.out.println("\t2.统计销售额前3的餐品");
        System.out.println("\t3.返回上一菜单");
        System.out.println("\t0.退出系统");
        System.out.println("------------------------------");
        System.out.println("请选择业务：");
        String option = sc.next();
        switch (option) {
            case "1":
                countSalesTop3();
                break;
            case "2":
                countRevenueTop3();
                break;
            case "3":
                ShopownerView.mainMenu();
                break;
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