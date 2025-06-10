/**
 * 声明该类所属的包，此包为项目的视图层包，用于存放与界面展示和用户交互相关的类。
 */
package com.study.shop.view;

// 导入列表集合类，用于存储和操作多个对象
import java.util.List;
// 导入控制台输入类，用于获取用户在控制台输入的信息
import java.util.Scanner;

import com.study.shop.dao.CategoryDao;
// 导入餐品数据访问对象类，用于与数据库中的餐品表进行交互
import com.study.shop.dao.FoodDao;
import com.study.shop.po.Category;
// 导入餐品实体类，用于封装餐品相关的属性和方法
import com.study.shop.po.Food;

public class FoodManager {
    public static CategoryDao categoryDao = new CategoryDao();
    // 创建餐品数据访问对象的静态实例，方便在整个类中使用该对象进行数据库操作
public static FoodDao foodDao = new FoodDao();
    // 创建控制台输入扫描器的静态实例，用于获取用户在控制台输入的信息
public static Scanner sc = new Scanner(System.in);

    public static void foodMenu() {
        while (true) {
            System.out.println("-------------餐品管理----------");
            System.out.println("\t1.增加餐品");
            System.out.println("\t2.查询餐品");
            System.out.println("\t3.修改餐品");
            System.out.println("\t4.删除餐品");
            System.out.println("\t5.返回上一菜单");
            System.out.println("\t0.退出系统");
            System.out.println("------------------------------");
            System.out.println("请选择业务：");
            // 从控制台获取用户输入的选项
String option = sc.next();
            switch (option) {
                // 当用户选择1时，调用添加餐品方法
case "1":
                addFood();
                break;
                // 当用户选择2时，调用查询餐品方法
case "2":
                queryFood();
                break;
                // 当用户选择3时，调用修改餐品方法
case "3":
                updateFood();
                break;
                // 当用户选择4时，调用删除餐品方法
case "4":
                deleteFood();
                break;
                case "5":
                    ShopownerView.mainMenu();
                case "0":
                    System.exit(0);
            }
        }
    }

    private static void addFood() {
        System.out.println("--------------- 增加餐品 -------------");
        System.out.println("餐品名称：");
        // 从控制台获取用户输入的餐品名称
String foodName = sc.next();
        System.out.println("所属类目：");
        List<Category> categories = categoryDao.findAll();
        for (Category category : categories) {
            System.out.println(category.getCategoryId() + " : " + category.getCategoryName());
        }
        System.out.println("选择所属类目编号：");
        int categoryId = sc.nextInt();
        System.out.println("餐品价格：");
        // 从控制台获取用户输入的餐品价格
int price = sc.nextInt();
        System.out.println("餐品描述：");
        String description = sc.next();

        // 创建餐品实体类的实例，用于封装新添加的餐品信息
Food food = new Food();
        // 将用户输入的餐品名称设置到餐品实体类的实例中
food.setFoodName(foodName);
        food.setCategoryId(categoryId);
        // 将用户输入的餐品价格设置到餐品实体类的实例中
food.setPrice(price);
        food.setDescription(description);

        // 调用餐品数据访问对象的添加餐品方法，将新餐品信息添加到数据库中，并获取操作结果
int result = foodDao.add(food);
        // 判断添加餐品操作是否成功，如果结果大于0表示成功
// 判断修改餐品操作是否成功，如果结果大于0表示成功
// 判断删除餐品操作是否成功，如果结果大于0表示成功
if (result > 0) {
            // 打印添加餐品成功的提示信息
System.out.println("增加餐品成功！");
        } else {
            // 打印添加餐品失败的提示信息
System.out.println("增加餐品失败！");
        }
    }

    private static void queryFood() {
        System.out.println("------------- 查询餐品 -----------");
        List<Food> allList = foodDao.findAll();
        for (Food food : allList) {
            System.out.println(food);
        }
        System.out.println("是否按关键字查询：1 是 0 否");

        int select = sc.nextInt();
        if (select == 1) {
            System.out.println("请输入查询关键字：");
            String key = sc.next();
            List<Food> keyList = foodDao.findByKey(key);
            for (Food food : keyList) {
                System.out.println(food);
            }
        }
    }

    private static void updateFood() {
        System.out.println("-------------- 修改餐品 --------------");
        // 调用餐品数据访问对象的查询所有餐品方法，获取所有餐品信息并存储在列表中
List<Food> list = foodDao.findAll();
        // 遍历餐品列表，获取列表中的每个餐品对象
for (Food food : list) {
            System.out.println(food.getFoodId() + " " + food.getFoodName());
        }
        System.out.println("选择要修改的餐品编号：");
        // 从控制台获取用户输入的要修改的餐品编号
int foodId = sc.nextInt();
        // 调用餐品数据访问对象的根据编号查询餐品方法，获取要修改的餐品信息
Food food = foodDao.findById(foodId);
        // 判断是否成功获取到要修改的餐品信息，如果为空表示未获取到
if (food == null) {
            // 打印未找到要修改的餐品的提示信息
System.out.println("没有该餐品信息");
            return;
        }
        System.out.println(food);
        System.out.println("重新输入餐品信息：");
        System.out.println("餐品名称：");
        // 从控制台获取用户输入的餐品名称
String foodName = sc.next();
        System.out.println("所属类目：");
        List<Category> categories = categoryDao.findAll();
        for (Category category : categories) {
            System.out.println(category.getCategoryId() + " " + category.getCategoryName());
        }
        System.out.println("选择所属类目编号：");
        int categoryId = sc.nextInt();
        System.out.println("餐品价格：");
        // 从控制台获取用户输入的餐品价格
int price = sc.nextInt();
        System.out.println("餐品描述：");
        String description = sc.next();
        // 将用户输入的餐品名称设置到餐品实体类的实例中
food.setFoodName(foodName);
        food.setCategoryId(categoryId);
        // 将用户输入的餐品价格设置到餐品实体类的实例中
food.setPrice(price);
        food.setDescription(description);
        // 调用餐品数据访问对象的修改餐品方法，将修改后的餐品信息更新到数据库中，并获取操作结果
int result = foodDao.update(food);
        // 判断添加餐品操作是否成功，如果结果大于0表示成功
// 判断修改餐品操作是否成功，如果结果大于0表示成功
// 判断删除餐品操作是否成功，如果结果大于0表示成功
if (result > 0) {
            // 打印修改餐品成功的提示信息
System.out.println("修改餐品成功！");
        } else {
            // 打印修改餐品失败的提示信息
System.out.println("修改餐品失败！");
        }

    }

    private static void deleteFood() {
        System.out.println("------------- 删除餐品 --------------");
        // 调用餐品数据访问对象的查询所有餐品方法，获取所有餐品信息并存储在列表中
List<Food> list = foodDao.findAll();
        // 遍历餐品列表，获取列表中的每个餐品对象
for (Food food : list) {
            System.out.println(food);
        }
        System.out.println("选择要删除的餐品编号：");
        // 从控制台获取用户输入的要修改的餐品编号
int foodId = sc.nextInt();
        // 调用餐品数据访问对象的根据编号查询餐品方法，获取要修改的餐品信息
Food food = foodDao.findById(foodId);
        // 判断是否成功获取到要修改的餐品信息，如果为空表示未获取到
if (food == null) {
            // 打印未找到要修改的餐品的提示信息
System.out.println("没有该餐品信息");
            return;
        }
        System.out.println("确定要删除吗？1 是 0 否");
        int select = sc.nextInt();
        if (select == 1) {
            // 调用餐品数据访问对象的删除餐品方法，将指定编号的餐品从数据库中删除，并获取操作结果
int result = foodDao.delete(foodId);
            // 判断添加餐品操作是否成功，如果结果大于0表示成功
// 判断修改餐品操作是否成功，如果结果大于0表示成功
// 判断删除餐品操作是否成功，如果结果大于0表示成功
if (result > 0) {
                // 打印删除餐品成功的提示信息
System.out.println("删除餐品成功！");
            } else {
                // 打印删除餐品失败的提示信息
System.out.println("删除餐品失败！");
            }
        }
    }
}