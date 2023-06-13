import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;
import java.util.Locale.Category;

public class MainWin {
    public static String password; // 系统登录密码
    static boolean txb_exist = true; // 默认指针用于系统操作选择循环的判断
    public static String txb_sex; // 性别
    public static String name; // 名字
    public static int txb_classes; // 班别
    public static int ID; // 学号
    public static int ispoor; // 判别学生身份信息
    public static int account = 0; // 用做数组类下标
    public static int TXb; // 用于计数
    public static TestJavaJDBC testJavaJDBC = new TestJavaJDBC();

    public static void main(String[] args) {
        try {
            Scanner txb_sc = new Scanner(System.in);
            System.out.println("欢迎来到学生资助系统");
            System.out.println("请输入密码");
            password = txb_sc.next();
            if (password.equals("123")) {
                try {
                    testJavaJDBC.txb_nb();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                do {
                    System.out.println(
                            "欢迎进入学生资助系统:\n*****1-身份认证*****\n*****2-退出系统*****\n*****3-显示达标人数*****\n*****4-显示未达标人数*****\n*****5-以下是存储名单*****");
                    int option;
                    option = txb_sc.nextInt();
                    switch (option) {
                        case 1:
                            try {
                                System.out.println("是否属于贫困生:1-是/任意数字-不是\n");
                                ispoor = txb_sc.nextInt();
                                txb_Findpoor findpoor = new txb_Findpoor(ispoor);
                                findpoor.txb_ispoor();
                                if (ispoor != 1) {
                                    break;
                                }
                                Specialist st = new Specialist(txb_sex, name, ID, txb_classes, "人员类型");
                                System.out.println("学生类型:1-普通贫困学生;2-特困生且特优生");
                                int choice;
                                choice = txb_sc.nextInt();
                                if (choice == 1) {
                                    st.txb_identity();
                                    System.out.println("请输入你的性别:1-男;2-女");
                                    txb_sex = txb_sc.next();
                                    System.out.println("请输入你的名字");
                                    name = txb_sc.next();
                                    System.out.println("请输入你的学号");
                                    ID = txb_sc.nextInt();
                                    System.out.println("请输入你的班级");
                                    txb_classes = txb_sc.nextInt();
                                    Student txb_St = new Student(txb_sex, name, ID, txb_classes);
                                    testJavaJDBC.txb_insert(ID, name);
                                    System.out.println(txb_St.get_position());
                                    txb_St.txb_grade();
                                    txb_St.txb_getAverage();
                                    account++;
                                    break;
                                }
                                if (choice == 2) {
                                    st.txb_identity(ispoor);
                                    System.out.println("请输入你的学号");
                                    ID = txb_sc.nextInt();
                                    System.out.println("请输入你的名字");
                                    name = txb_sc.next();
                                    Student txb_St = new Student(txb_sex, name, ID, txb_classes);
                                    testJavaJDBC.txb_insert(txb_St.ID,txb_St.name);
                                    txb_St.Can_xb = true;
                                    account++;

                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("请按照正确格式输入");
                            }
                        case 2:
                            System.out.println("已退出系统");
                            // 完成后关闭
                            testJavaJDBC.rs.close();
                            testJavaJDBC.stmt.close();
                            testJavaJDBC.conn.close();
                            txb_exist = false;
                            break;
                        case 3:
                            TXb = 0;
                            Student txb_St = new Student(txb_sex, name, ID, txb_classes);
                            for (int i = 0; i < account; i++) {
                                if (txb_St.Can_xb) {
                                    TXb++;
                                }

                            }
                            System.out.println("达标人数为" + TXb);
                            break;
                        case 4:
                            TXb = 0;
                            Student txb_ST = new Student(txb_sex, name, ID, txb_classes);
                            for (int i = 0; i < account; i++) {
                                if (!txb_ST.Can_xb) {
                                    TXb++;
                                }
                            }
                            System.out.println("未达标人数为" + TXb);
                            break;
                        case 5:
                            System.out.println("该存储名单名字如下:\n");

                            testJavaJDBC.txb_show();
                        default:
                            System.out.println("按照上述操作指令输入");
                    }
                } while (txb_exist);
            } else {
                System.out.println("密码错误");
                System.exit(0);
            }
        } catch (SQLException se) {
             //TODO: handle exception
            se.printStackTrace();

        //} catch (Exception e) {
        //    System.out.println("操作错误");
        }

    }
}
