import java.util.*;
import java.util.Scanner;

public class Student {
    public String position;         // 职位(该系统默认针对学生)
    public String txb_sex;          // 性别
    public String name;             // 名字
    public int  ID;               // 学号
    public int txb_classes;         // 班别
    public double[] grade = new double[5]; // 各科成绩
    public static double sum = 0;
    public boolean Can_xb;

    public Student(String txb_sex, String name, int ID, int txb_classes) {
        this.txb_sex = txb_sex;
        this.name = name;
        this.ID = ID;
        this.txb_classes = txb_classes;
    }

    public String get_position() {                      // 系统针对对象
        return "资助筑梦每一个需要帮助的同学";
    }

    public void txb_grade() {                           // 成绩判断
        int t;
        Scanner sc = new Scanner(System.in);
        for (t = 0; t < 5; t++) {
            System.out.println("请输入" + (t + 1) + "科目的成绩");
            grade[t] = sc.nextDouble();
            if (grade[t] < 60) {
                System.out.println("挂科是无法申请助学金的哦！");
                break;
            } else if (grade[t] > 100) {
                System.out.println("分数请按照百分制进行输出");
                break;
            } else {
                continue;
            }
        }
    }

    public void txb_getAverage() {                  // 获取平均数的方法
        double sum = 0;
        int x;
        for (x = 0; x < 5; x++) {
            sum += grade[x];
        }
        if (sum >= 400) {
            System.out.println("亲爱的" + name + "同学你已经达到申请要求");
            Can_xb = true;
        } else {
            System.out.println("未达标");
            Can_xb = false;
        }
    }

    public void txb_datebaseshow(){
        TestJavaJDBC st = new TestJavaJDBC();
        st.txb_show();
    }
}
