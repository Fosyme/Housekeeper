package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;


/**
 * @author 李建强
 */
public class MethodOfOperation {
    /**
     * 在大多情况下，使用PreparedStatement 来代替Statement,防止sql注入
     */

    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    protected final static Connection connection = jdbcUtil.getConnection();

    /**
     * 返回数据库table中最大的id值
     */
    protected static String queryMaxId(String table) {
        String id = null;
        resultSet = null;
        String sqlAboutId = table + "_id";
        String sql = "select max(`" + sqlAboutId + "`)  from  `" + table + "`";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
            resultSet = null;
            preparedStatement.clearParameters();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
