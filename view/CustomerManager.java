/**
 * 声明该类所属的包，此包为项目的视图层包，用于存放与界面展示和用户交互相关的类。
 */
/**
 * 声明该类所属的包，此包为项目的视图层包，用于存放与界面展示和用户交互相关的类。
 */
package com.study.shop.view;

// 导入动态数组类，用于存储和操作多个对象，可自动调整大小
import java.util.ArrayList;
// 导入集合工具类，提供了一系列静态方法用于操作集合，如排序、查找等
import java.util.Collections;
// 导入哈希表类，用于存储键值对，可根据键快速查找对应的值
import java.util.HashMap;
// 导入列表接口，定义了有序集合的操作规范，可存储重复元素
import java.util.List;
// 导入映射接口，定义了键值对的存储和操作规范，键具有唯一性
import java.util.Map;
// 导入扫描器类，用于从控制台读取用户输入的各种数据类型
import java.util.Scanner;

// 导入评价数据访问对象类，用于与数据库中的评价表进行交互
import com.study.shop.dao.AppraiseDao;
// 导入餐品数据访问对象类，封装了与数据库中餐品表的增删改查操作
import com.study.shop.dao.FoodDao;
// 导入订单数据访问对象类，封装了与数据库中订单表的增删改查操作
import com.study.shop.dao.OrderDao;
// 导入评价实体类，用于封装评价相关的属性和方法，与数据库表字段对应
import com.study.shop.po.Appraise;
// 导入餐品实体类，用于封装餐品的各种属性，如名称、价格、类目等
import com.study.shop.po.Food;
// 导入订单实体类，用于封装订单的各种属性，如餐品ID、桌号、数量等
import com.study.shop.po.Order;

public class CustomerManager {
    // 创建一个静态的扫描器对象，用于从控制台读取用户输入的信息，方便在整个类中使用
public static Scanner sc = new Scanner(System.in);
    // 创建一个静态的餐品数据访问对象，用于与数据库中的餐品表进行交互，方便类内方法调用
public static FoodDao foodDao = new FoodDao();
    // 创建一个静态的订单数据访问对象，用于与数据库中的订单表进行交互，方便类内方法调用
public static OrderDao orderDao = new OrderDao();

    public static void main(String[] args) {
        customerMenu();
    }
    // 顾客主界面方法，提供顾客操作的菜单选项，循环等待用户输入
public static void customerMenu() {
        // 打印顾客界面菜单的标题
System.out.println("-----------顾客界面----------");
        while (true) {
            System.out.println("\t1. 点餐");
            System.out.println("\t2. 点餐记录查询");
            System.out.println("\t0. 退出系统");
            System.out.println("请选择业务");
            // 从控制台读取用户输入的字符串，作为菜单选项
String option = sc.next();
            switch (option) {
                // 当用户输入的选项为1时，调用点餐方法，进入点餐流程
case "1":
                orderFood();
                break;
                // 当用户输入的选项为0时，调用系统退出方法，终止程序运行
case "0":
                System.exit(0);
                default:
                    System.out.println("您的选择有误，请重新选择！");
            }
        }
    }

    // 点餐方法，实现顾客点餐的完整流程，包括展示餐品、加入购物车、管理订单等
private static void orderFood() {
        System.out.println("---------------  点餐  ------------");
        // 打印餐品信息
        // 调用餐品数据访问对象的 `findAll` 方法，从数据库中查询所有餐品信息，并存储在列表中
List<Food> list = foodDao.findAll();
        // 调用辅助方法 `printFoodByCategory`，将餐品按类目分类并打印出来
printFoodByCategory(list);
        System.out.println("1.点餐  2.退出");
        int option1 = sc.nextInt();
        if (option1 == 2) {
            customerMenu(); // 回到顾客页面
        } else {
            // 点餐
            // 调用辅助方法 `addCart`，将用户选择的餐品加入购物车，并返回购物车列表
List<Order> cartList = addCart(list);
            // 调用辅助方法 `manageCartList`，对购物车中的订单进行管理，如删除、修改数量等，返回最终订单列表
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

    // 辅助方法：根据餐品类目对餐品列表进行分类，并打印每个类目的餐品信息
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

    // 辅助方法：将用户选择的餐品信息添加到购物车列表中，并返回购物车列表
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

    // 辅助方法：根据餐品ID在餐品列表中查找对应的餐品，并返回其价格
private static int getFoodPrice(int foodId, List<Food> list) {
        for (Food food : list) {
            if (food.getFoodId() == foodId) {
                return food.getPrice();
            }
        }
        return 0;
    }

    // 辅助方法：管理购物车中的订单，提供删除、修改数量等操作，返回最终的订单列表
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

    // 辅助方法：根据餐品ID从购物车列表中删除对应的订单
private static void deleteCart(int foodId, List<Order> cartList) {
        for (Order order : cartList) {
            if (order.getFoodId() == foodId) {
                cartList.remove(order);
                break;
            }
        }
    }

    // 辅助方法：根据餐品ID修改购物车列表中对应订单的数量
private static void updateCart(int foodId, int count, List<Order> cartList) {
        for (Order order : cartList) {
            if (order.getFoodId() == foodId) {
                order.setAmount(count);
                break;
            }
        }
    }

    // 辅助方法：计算订单列表中所有订单的总金额
private static int getOrderMoney(List<Order> orderList) {
        int money = 0;
        for (Order order : orderList) {
            money += order.getTotal();
        }
        return money;
    }
}