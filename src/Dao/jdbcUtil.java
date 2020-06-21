package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 李建强
 * @date 2020-6-11
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

    //加载驱动
    static {
        try {
            username = "root";
            password = "123456";
            driver = "com.mysql.cj.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/house_keeper?serverTimezone=GMT";
        } catch (Exception e) {
            System.err.println("连接失败，请检查连接参数");
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 返回Connection
     */
    protected static Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.err.println("获取连接失败");
            e.printStackTrace();
        }
        return connection;
    }
}
