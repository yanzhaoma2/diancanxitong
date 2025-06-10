package com.study.shop.view;

import java.util.List;
import java.util.Scanner;

import com.study.shop.dao.CategoryDao;
import com.study.shop.dao.FoodDao;
import com.study.shop.po.Category;
import com.study.shop.po.Food;

public class FoodManager {
    public static CategoryDao categoryDao = new CategoryDao();
    public static FoodDao foodDao = new FoodDao();
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
            String option = sc.next();
            switch (option) {
                case "1":
                    addFood();
                    break;
                case "2":
                    queryFood();
                    break;
                case "3":
                    updateFood();
                    break;
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
        String foodName = sc.next();
        System.out.println("所属类目：");
        List<Category> categories = categoryDao.findAll();
        for (Category category : categories) {
            System.out.println(category.getCategoryId() + " : " + category.getCategoryName());
        }
        System.out.println("选择所属类目编号：");
        int categoryId = sc.nextInt();
        System.out.println("餐品价格：");
        int price = sc.nextInt();
        System.out.println("餐品描述：");
        String description = sc.next();

        Food food = new Food();
        food.setFoodName(foodName);
        food.setCategoryId(categoryId);
        food.setPrice(price);
        food.setDescription(description);

        int result = foodDao.add(food);
        if (result > 0) {
            System.out.println("增加餐品成功！");
        } else {
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
        List<Food> list = foodDao.findAll();
        for (Food food : list) {
            System.out.println(food.getFoodId() + " " + food.getFoodName());
        }
        System.out.println("选择要修改的餐品编号：");
        int foodId = sc.nextInt();
        Food food = foodDao.findById(foodId);
        if (food == null) {
            System.out.println("没有该餐品信息");
            return;
        }
        System.out.println(food);
        System.out.println("重新输入餐品信息：");
        System.out.println("餐品名称：");
        String foodName = sc.next();
        System.out.println("所属类目：");
        List<Category> categories = categoryDao.findAll();
        for (Category category : categories) {
            System.out.println(category.getCategoryId() + " " + category.getCategoryName());
        }
        System.out.println("选择所属类目编号：");
        int categoryId = sc.nextInt();
        System.out.println("餐品价格：");
        int price = sc.nextInt();
        System.out.println("餐品描述：");
        String description = sc.next();
        food.setFoodName(foodName);
        food.setCategoryId(categoryId);
        food.setPrice(price);
        food.setDescription(description);
        int result = foodDao.update(food);
        if (result > 0) {
            System.out.println("修改餐品成功！");
        } else {
            System.out.println("修改餐品失败！");
        }

    }

    private static void deleteFood() {
        System.out.println("------------- 删除餐品 --------------");
        List<Food> list = foodDao.findAll();
        for (Food food : list) {
            System.out.println(food);
        }
        System.out.println("选择要删除的餐品编号：");
        int foodId = sc.nextInt();
        Food food = foodDao.findById(foodId);
        if (food == null) {
            System.out.println("没有该餐品信息");
            return;
        }
        System.out.println("确定要删除吗？1 是 0 否");
        int select = sc.nextInt();
        if (select == 1) {
            int result = foodDao.delete(foodId);
            if (result > 0) {
                System.out.println("删除餐品成功！");
            } else {
                System.out.println("删除餐品失败！");
            }
        }
    }
}