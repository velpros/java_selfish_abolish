import java.sql.*;

public class TestJavaJDBC {
    public Connection conn = null;
    public PreparedStatement stmt = null;
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/lasttest";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    public void txb_nb() throws ClassNotFoundException {

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void txb_show() {
        String sql;
        sql = "SELECT * FROM student";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // 展开结果集数据库
            while (rs.next())

            {
                // 通过字段检索
                int id = rs.getInt("ID");
                String name = rs.getString("name");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 名字: " + name);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // 什么都不做
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        //System.out.println("Goodbye!");
    }

    public void txb_insert(int ID, String name) {
        String SQL_Command = "insert into Student (ID,name) values (?,?)";
        try {
            stmt = conn.prepareStatement(SQL_Command);
            stmt.setInt(1, ID); // 数据项：ID
            stmt.setString(2, name); // 数据项：studentName
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}