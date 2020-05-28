package Dao;

import java.sql.*;

/**
 * @author 无
 */
public class jdbcUtil {

    /**
     * 连接数据库参数
     */
    private static String username = "";
    private static String password = "";
    private static String driver = "";
    private static String url = "";
    private static Connection connection = null;
    /**
     * 加载驱动，只需一次
     */
    static {    //静态代码块最先执行
        try {
            username = "root";
            password = "123456";
            driver = "com.mysql.cj.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/housekeeper?serverTimezone=GMT";
            Class.forName(driver);
        } catch (Exception e) {
            System.err.println("连接失败，请检查连接参数");
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return 返回Connection
     */
    protected static Connection getConnection() {
        try {
               connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.err.println("获取连接失败");
            e.printStackTrace();
        }
        return connection;
    }
}
