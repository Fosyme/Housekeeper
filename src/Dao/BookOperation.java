package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookOperation {
    private static final Connection CONNECTION = DBLeader.connection;
    private static PreparedStatement preparedStatement = null;

    /**
     * 添加账本
     *
     * @param data 字符串数组（用户id、账本名、账本、描述、添加时间、最后修改时间）
     * @return 账本id
     */
    public static String add(String[] data) {
        String sql;
        try {
            sql = "insert into `book` values(0, ?, ?, ?, ?)";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(data[0]));
            preparedStatement.setString(2, data[1]);
            preparedStatement.setString(3, data[2]);
            preparedStatement.setString(4, data[3]);
            preparedStatement.execute();
            return DBLeader.getIDAfterAdd("book");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除账本，删除成功时返回true，删除失败时，返回false。
     *
     * @param id 账本id
     * @return boolean
     */
    public static boolean delete(String id) {
        String sql;
        try {
            sql = "delete from `order` where `book_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.execute();
            preparedStatement.clearParameters();
            sql = "delete from `book` where `book_id`= ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            if (preparedStatement.executeUpdate() == 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改账本信息，修改成功返回true，修改失败返回false。
     *
     * @param id      账本id
     * @param newData 字符串数组（账本名、账本描述、上次更改时间）
     * @return boolean
     */
    public static boolean changeInfo(String id, String[] newData) {
        String sql;
        try {
            sql = "update `book` set `book_name` = ?, `book_desc` = ? where book_id = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newData[0]);
            preparedStatement.setString(2, newData[1]);
            preparedStatement.setInt(3, Integer.parseInt(id));
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用户账本查询，
     * 返回用户的账本信息集合。
     *
     * @param UserId 用户id，
     * @return resultSet
     */
    public static ResultSet queryInfo(String UserId) {
        String sql;
        try {
            sql = "select * from `book` where `user_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(UserId));
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证账本是否存在，
     *
     * @param userId   用户id
     * @param bookName 账本名
     * @return 如果账本存在返回true
     */
    public static boolean isExist(String userId, String bookName) {
        String sql;
        ResultSet resultSet;
        try {
            sql = "select * from `book` where `user_id`=?  and `book_name` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(userId));
            preparedStatement.setString(2, bookName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过账本ID获得账本名
     *
     * @param bookID 账本ID
     * @return 账本名
     */
    public static String queryNameFromID(String bookID) {
        String sql;
        ResultSet resultSet;
        try {
            sql = "select `book_name` from `book` where book_id = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(bookID));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("book_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
