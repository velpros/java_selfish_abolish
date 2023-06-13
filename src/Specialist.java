import java.util.*;
import java.util.Scanner;

//
public class Specialist extends Student {
    public String category;         // 定义一个人员类

    public Specialist(String txb_sex, String name, int  ID, int txb_classes, String category) {
        super(txb_sex, name, ID, txb_classes);
        this.category = category;
    }

    // 用来判断普通人的方法
    public void txb_identity() {
        System.out.println("普通贫困学生");
    }

    // 用来判断特别人员的方法
    public void txb_identity(int poor) {                // 定义一个带有参数的函数方法
        System.out.println("贫困生且特优生");
        System.out.println("你获得免评判资格,恭喜完成助学金申请");
    }
}
