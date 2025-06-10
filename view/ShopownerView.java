package com.study.shop.view;

import java.util.Scanner;

import com.study.shop.dao.ShopownerDao;

public class ShopownerView {
    // 创建dao
    private static ShopownerDao shopownerDao = new ShopownerDao();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (login()) {
            // 如果登录成功打开管理菜单
            mainMenu();
        }
    }

    /**
     * 店主登录
     */
    private static boolean login() {

        while (true) {
            System.out.println("请输入手机号：");
            String phone = sc.next();
            System.out.println("请输入密码：");
            String password = sc.next();
            // 调用dao
            boolean result = shopownerDao.login(phone, password);
            if (result) {
                return true;
            } else {
                System.out.println("手机号或密码错误，请重新输入");
            }
        }
    }

    static void mainMenu() {
        while (true) {
            System.out.println("-------------------------------------");
            System.out.println("\t1. 类目管理");
            System.out.println("\t2. 餐品管理");
            System.out.println("\t3. 订单管理");
            System.out.println("\t4. 统计管理");
            System.out.println("\t0. 退出系统");
            System.out.println("-------------------------------------");
            System.out.println("请选择业务：");
            String option = sc.next();
            switch (option) {
                case "1":
                    CategoryManager.categoryMenu();
                    break;
                case "2":
                    FoodManager.foodMenu();
                    break;
                case "3":
                    OrderManager.orderMenu();
                    break;
                case "4":
                    CountManager.countMenu();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("没有改选修信息，请重新输入！");
            }
        }
    }

}