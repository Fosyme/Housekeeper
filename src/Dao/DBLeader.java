package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * 构建数据库连接
 */
public class DBLeader {
    protected final static Connection connection = DBUtil.getConnection();

    /**
     * 返回数据库table中最大的id值
     * 用于每次book, order添加后获取id
     */
    protected static String getIDAfterAdd(String table) {
        String sql;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            sql = "select max(?) from ?";
            //在大多情况下，使用PreparedStatement来代替Statement,防止sql注入
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, table + "_id");
            preparedStatement.setString(2, table);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
