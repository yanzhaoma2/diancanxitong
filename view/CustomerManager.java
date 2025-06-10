package com.study.shop.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.study.shop.dao.AppraiseDao;
import com.study.shop.dao.FoodDao;
import com.study.shop.dao.OrderDao;
import com.study.shop.po.Appraise;
import com.study.shop.po.Food;
import com.study.shop.po.Order;

public class CustomerManager {
    public static Scanner sc = new Scanner(System.in);
    public static FoodDao foodDao = new FoodDao();
    public static OrderDao orderDao = new OrderDao();

    public static void main(String[] args) {
        customerMenu();
    }
    // 顾客主界面
    public static void customerMenu() {
        System.out.println("-----------顾客界面----------");
        while (true) {
            System.out.println("\t1. 点餐");
            System.out.println("\t2. 点餐记录查询");
            System.out.println("\t0. 退出系统");
            System.out.println("请选择业务");
            String option = sc.next();
            switch (option) {
                case "1":
                    orderFood();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("您的选择有误，请重新选择！");
            }
        }
    }

    // 点餐
    private static void orderFood() {
        System.out.println("---------------  点餐  ------------");
        // 打印餐品信息
        List<Food> list = foodDao.findAll();
        // 辅助方法1：根据餐品类目排序
        printFoodByCategory(list);
        System.out.println("1.点餐  2.退出");
        int option1 = sc.nextInt();
        if (option1 == 2) {
            customerMenu(); // 回到顾客页面
        } else {
            // 点餐
            // 辅助方法2：加入购物车
            List<Order> cartList = addCart(list);
            // 辅助方法3：管理购物车-得到最终订单
            List<Order> orderList = manageCartList(cartList);
            // 打印订单
            for (Order order : orderList) {
                System.out.println(order);
            }
            System.out.println("是否提交订单：1是 0 否");
            int submit = sc.nextInt();
            if (submit == 1) {
                System.out.println("订单已提交");
            }
        }
    }

    private static void printFoodByCategory(List<Food> list) {
        Map<String, List<Food>> foodMap = new HashMap<>();
        for (Food food : list) {
            foodMap.computeIfAbsent(food.getCategoryName(), k -> new ArrayList<>()).add(food);
        }

        for (Map.Entry<String, List<Food>> entry : foodMap.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            for (Food food : entry.getValue()) {
                System.out.println("ID: " + food.getFoodId() + ", Name:" + food.getFoodName() + ", Price:" + food.getPrice());
            }
        }
    }

    private static List<Order> addCart(List<Food> list) {
        List<Order> cartList = new ArrayList<>();
        while (true) {
            System.out.println("餐品编号：");
            int foodId = sc.nextInt();
            System.out.println("桌号：");
            int deskNumber = sc.nextInt();
            System.out.println("手机号：");
            String phone = sc.next();
            System.out.println("数量：");
            int amount = sc.nextInt();
            int price = getFoodPrice(foodId, list);
            int total = price * amount;
            Food food = foodDao.findById(foodId);
            Order order = new Order();
            cartList.add(order);
            System.out.println("是否继续添加：1 是 0 否");
            int continueOrder = sc.nextInt();
            if (continueOrder == 2) {
                break;
            }
        }
        return cartList;
    }

    private static int getFoodPrice(int foodId, List<Food> list) {
        for (Food food : list) {
            if (food.getFoodId() == foodId) {
                return food.getPrice();
            }
        }
        return 0;
    }

    private static List<Order> manageCartList(List<Order> cartList) {
        System.out.println("-------------购物车------------");
        for (Order order : cartList) {
            System.out.println(order);
        }
        while (true) {
            System.out.println("1.删除  2.更改数量  3.确定");
            int option = sc.nextInt();
            if(option == 1){
                System.out.println("要删除的餐品编号：");
                int foodId = sc.nextInt();
                deleteCart(foodId,cartList);
            }else if (option == 2){
                System.out.println("更改数量的餐品编号：");
                int foodId = sc.nextInt();
                System.out.println("数量：");
                int count = sc.nextInt();
                updateCart(foodId, count, cartList);
            } else if (option == 3) {
                break;
            }
        }
        return cartList;
    }

    private static void deleteCart(int foodId, List<Order> cartList) {
        for (Order order : cartList) {
            if (order.getFoodId() == foodId) {
                cartList.remove(order);
                break;
            }
        }
    }

    private static void updateCart(int foodId, int count, List<Order> cartList) {
        for (Order order : cartList) {
            if (order.getFoodId() == foodId) {
                order.setAmount(count);
                break;
            }
        }
    }

    private static int getOrderMoney(List<Order> orderList) {
        int money = 0;
        for (Order order : orderList) {
            money += order.getTotal();
        }
        return money;
    }
}