/**
 * 类目管理模块的视图层实现
 * 提供类目的增删改查功能
 */
/**
 * 声明该类所属的包，此包为项目的视图层包，用于存放与界面展示和用户交互相关的类。
 */
package com.study.shop.view;

// 导入必要的Java类库
// 导入数据库连接类，用于与数据库建立连接
import java.sql.Connection;  // 数据库连接
// 导入列表集合类，用于存储和操作多个对象
import java.util.List;      // 列表集合
// 导入控制台输入类，用于获取用户在控制台输入的信息
import java.util.Scanner;   // 控制台输入

// 导入类目数据访问对象类，用于与数据库中的类目表进行交互
import com.study.shop.dao.CategoryDao;
// 导入类目实体类，用于封装类目相关的属性和方法
import com.study.shop.po.Category;

/**
 * 类目管理类
 * 实现类目的增删改查功能
 */
public class CategoryManager {
        // 类目数据访问对象实例
    // 创建类目数据访问对象的静态实例，方便在整个类中使用该对象进行数据库操作
public static CategoryDao categoryDao = new CategoryDao();
    
    // 控制台输入扫描器
    // 创建控制台输入扫描器的静态实例，用于获取用户在控制台输入的信息
public static Scanner sc = new Scanner(System.in);

        /**
     * 显示类目管理主菜单
     * 提供1-5的选项供用户选择
     */
    public static void categoryMenu() {
        // 打印类目管理菜单的标题
System.out.println("-------------类目管理----------");
        System.out.println("\t1.增加类目");
        System.out.println("\t2.查询类目");
        System.out.println("\t3.修改类目");
        System.out.println("\t4.删除类目");
        System.out.println("\t5.返回上一菜单");
        System.out.println("\t0.退出系统");
        System.out.println("------------------------------");
        System.out.println("请选择业务：");
                // 获取用户输入的选项
        // 从控制台获取用户输入的选项
String option = sc.next();
        switch (option) {
            // 当用户选择1时，调用添加类目方法
case "1":
                addCategory();
                break;
            // 当用户选择2时，调用查询类目方法
case "2":
                queryCategory();
                break;
            // 当用户选择3时，调用修改类目方法
case "3":
                updateCategory();
                break;
            // 当用户选择4时，调用删除类目方法
case "4":
                deleteCategory();
                break;
            // 当用户选择5时，返回店主主菜单
case "5":
                ShopownerView.mainMenu();
            // 当用户选择0时，退出系统
case "0":
                System.exit(0);
        }

    }

    // 增加类目
        /**
     * 添加新类目
     * 提示用户输入类目名称并保存到数据库
     */
    private static void addCategory() {
        // 打印增加类目功能的标题
System.out.println("------------- 增加类目 -------------");
        System.out.println("类目名称：");// 类目编号自增
        // 从控制台获取用户输入的类目名称
// 从控制台获取用户输入的新的类目名称
String categoryName = sc.next();
        // 封装对象
        // 创建类目实体类的实例，用于封装用户输入的类目信息
Category category = new Category();
        // 将用户输入的类目名称设置到类目实体类的实例中
// 将用户输入的新的类目名称设置到类目实体类的实例中
category.setCategoryName(categoryName);
        // 调用dao
        // 调用类目数据访问对象的添加方法，将类目信息添加到数据库中，并获取操作结果
int result = categoryDao.add(category);
        // 判断添加操作是否成功，如果结果大于0表示成功
// 判断更新操作是否成功，如果结果大于0表示成功
// 判断删除操作是否成功，如果结果大于0表示成功
if (result > 0) {
            // 打印添加类目成功的提示信息
System.out.println("增加类目成功！");
        } else {
            // 打印添加类目失败的提示信息
System.out.println("增加类目失败！");
        }

    }

    // 查询类目
        /**
     * 查询类目
     * 显示所有类目并可选择关键字查询
     */
    private static void queryCategory() {
        // 打印查询类目功能的标题
System.out.println("---------- 查询类目 --------");
        // 打印所有类目信息
        // 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
// 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
// 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
List<Category> list = categoryDao.findAll();
        // 调用打印类目列表方法，将获取到的类目列表进行打印
// 调用打印类目列表方法，将获取到的类目列表进行打印
// 调用打印类目列表方法，将获取到的类目列表进行打印
printCategoryList(list);
        // 提示用户是否选择关键字查询
System.out.println("是否选择关键字查询：1是 0否");
        // 从控制台获取用户输入的选择
// 从控制台获取用户输入的确认选择
int select = sc.nextInt();
        // 判断用户是否选择关键字查询，如果选择1表示是
// 判断用户是否确认删除，如果选择1表示是
if (select == 1) {
            // 提示用户输入查询关键字
System.out.println("请输入查询关键字：");
            // 从控制台获取用户输入的查询关键字
String key = sc.next();
            // 调用类目数据访问对象的按关键字查询方法，获取符合条件的类目信息并存储在列表中
List<Category> keyList = categoryDao.findByKey(key);
            // 调用打印类目列表方法，将按关键字查询到的类目列表进行打印
printCategoryList(keyList);
        }
    }

    // 打印类目集合信息
        /**
     * 打印类目列表
     * @param list 要打印的类目集合
     */
    private static void printCategoryList(List<Category> list) {
        // 遍历类目列表，获取列表中的每个类目对象
for (Category category : list) {
            // 打印当前类目对象的类目名称
System.out.println(category.getCategoryName());
        }
    }

    // 修改类目
        /**
     * 更新类目信息
     * 先显示所有类目，然后选择要修改的类目
     */
    private static void updateCategory() {
        // 打印修改类目功能的标题
System.out.println("-------------- 修改类目 --------------");
        // 打印所有类目信息
        // 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
// 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
// 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
List<Category> list = categoryDao.findAll();
        // 调用打印类目列表方法，将获取到的类目列表进行打印
// 调用打印类目列表方法，将获取到的类目列表进行打印
// 调用打印类目列表方法，将获取到的类目列表进行打印
printCategoryList(list);
        // 提示用户选择要修改的类目编号
System.out.println("选择要修改的类目编号：");
        // 从控制台获取用户输入的要修改的类目编号
// 从控制台获取用户输入的要删除的类目编号
int categoryId = sc.nextInt();
        // 调用类目数据访问对象的按编号查询方法，获取要修改的类目信息
// 调用类目数据访问对象的按编号查询方法，获取要删除的类目信息
Category category = categoryDao.findById(categoryId);
        // 判断要修改的类目信息是否存在，如果为null表示不存在
// 判断要删除的类目信息是否存在，如果为null表示不存在
if (category == null) {
            // 打印没有该类目信息的提示信息
// 打印没有该类目信息的提示信息
System.out.println("没有该类目信息");
            // 结束当前方法，返回调用处
// 结束当前方法，返回调用处
return;
        }
        // 打印要修改的类目信息
// 打印要删除的类目信息
System.out.println(category);
        // 提示用户重新输入类目信息
System.out.println("重新输入类目信息：");
        // 提示用户输入新的类目名称
System.out.println("类目名称:");
        // 从控制台获取用户输入的类目名称
// 从控制台获取用户输入的新的类目名称
String categoryName = sc.next();
        // 重新设置属性
        // 将用户输入的类目名称设置到类目实体类的实例中
// 将用户输入的新的类目名称设置到类目实体类的实例中
category.setCategoryName(categoryName);
        // 调用dao
        // 调用类目数据访问对象的更新方法，将修改后的类目信息更新到数据库中，并获取操作结果
int result = categoryDao.update(category);
        // 判断添加操作是否成功，如果结果大于0表示成功
// 判断更新操作是否成功，如果结果大于0表示成功
// 判断删除操作是否成功，如果结果大于0表示成功
if (result > 0) {
            // 打印修改类目成功的提示信息
System.out.println("修改类目成功！");
        } else {
            // 打印修改类目失败的提示信息
System.out.println("修改类目失败！");
        }
    }

    /**
     * 删除类目
     */
        /**
     * 删除类目(逻辑删除)
     * 先显示所有类目，然后选择要删除的类目
     */
    private static void deleteCategory() {
        // 打印删除类目功能的标题
System.out.println("------------- 删除类目 --------------");
        // 打印所有类目信息
        // 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
// 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
// 调用类目数据访问对象的查询所有类目方法，获取所有类目信息并存储在列表中
List<Category> list = categoryDao.findAll();
        // 调用打印类目列表方法，将获取到的类目列表进行打印
// 调用打印类目列表方法，将获取到的类目列表进行打印
// 调用打印类目列表方法，将获取到的类目列表进行打印
printCategoryList(list);
        // 提示用户选择要删除的类目编号
System.out.println("选择要删除的类目编号：");
        // 从控制台获取用户输入的要修改的类目编号
// 从控制台获取用户输入的要删除的类目编号
int categoryId = sc.nextInt();
        // 调用类目数据访问对象的按编号查询方法，获取要修改的类目信息
// 调用类目数据访问对象的按编号查询方法，获取要删除的类目信息
Category category = categoryDao.findById(categoryId);
        // 判断要修改的类目信息是否存在，如果为null表示不存在
// 判断要删除的类目信息是否存在，如果为null表示不存在
if (category == null) {
            // 打印没有该类目信息的提示信息
// 打印没有该类目信息的提示信息
System.out.println("没有该类目信息");
            // 结束当前方法，返回调用处
// 结束当前方法，返回调用处
return;
        }
        // 打印要修改的类目信息
// 打印要删除的类目信息
System.out.println(category);
        // 提示用户确认是否要删除该类目
System.out.println("确定要删除吗？1是 0否");
        // 从控制台获取用户输入的选择
// 从控制台获取用户输入的确认选择
int select = sc.nextInt();
        // 判断用户是否选择关键字查询，如果选择1表示是
// 判断用户是否确认删除，如果选择1表示是
if (select == 1) {
            // 执行删除
            // 调用类目数据访问对象的删除方法，将指定编号的类目信息从数据库中删除，并获取操作结果
int result = categoryDao.delete(categoryId);
            // 判断添加操作是否成功，如果结果大于0表示成功
// 判断更新操作是否成功，如果结果大于0表示成功
// 判断删除操作是否成功，如果结果大于0表示成功
if (result > 0) {
                // 打印删除类目成功的提示信息
System.out.println("删除类目成功！");
            } else {
                // 打印删除类目失败的提示信息
System.out.println("删除类目失败！");
            }
        }

    }
}