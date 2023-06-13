public class txb_Findpoor {
    public int ispoor;

    public txb_Findpoor(int ispoor) {
        this.ispoor = ispoor;
    }

    public void txb_ispoor() {
        if (ispoor == 1) {
            System.out.println("按照以下格式输入信息");
        } else {
            System.out.println("认证失败");
            System.out.println("不是贫困生无法申请组学金哦！");
        }
    }
}
