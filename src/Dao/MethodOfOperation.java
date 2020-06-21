package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * @author 李建强
 * @date 2020-6-11
 */
public class MethodOfOperation {
    protected final static Connection connection = jdbcUtil.getConnection();

    /**
     * 返回数据库table中最大的id值
     */
    protected static String queryMaxId(String table) {
        String id = null;
        ResultSet resultSet;
        String sqlAboutId = table + "_id";
        String sql = "select max(`" + sqlAboutId + "`)  from  `" + table + "`";
        try {
            //在大多情况下，使用PreparedStatement来代替Statement,防止sql注入
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
            preparedStatement.clearParameters();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
