package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    //连接数据库参数
    private static final String url;
    private static final String username;
    private static final String password;
    private static final String driver;

    //加载驱动
    static {
        username = "root";
        password = "123456";
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/house_keeper?serverTimezone=GMT";
    }

    /**
     * 获取数据库连接
     *
     * @return 返回Connection
     */
    protected static Connection getConn() {
        Connection connection = null;
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
