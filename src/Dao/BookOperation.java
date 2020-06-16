package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 李建强
 * @date 2020-6-11
* */

public class BookOperation {
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static final Connection CONNECTION = MethodOfOperation.connection;

    /**
    * 添加账本
     * @param data 字符串数组（用户id、账本名、账本、描述、添加时间、最后修改时间）
     * @return String，账本id
     */
    public static String addBook(String[] data) {
        String id = null;
        String sql = "insert into `book` ( `user_id`, `book_name`, `book_desc`, `book_add_time`, `book_last_time`) " +
                "values(?,?,?,?,?)";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(data[0]));
            preparedStatement.setString(2, data[1]);
            preparedStatement.setString(3, data[2]);
            preparedStatement.setString(4, data[3]);
            preparedStatement.setString(5, data[4]);
            preparedStatement.execute();
            id = MethodOfOperation.queryMaxId("book");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 删除账本，删除成功时返回true，删除失败时，返回false。
     * @param id 账本id
     * @return boolean
    * */
    public static boolean deleteBook(String id) {
         String sql ="delete from `order` where `book_id`=" + id;
        int existences=0;
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.clearParameters();
            sql="delete from `book` where `book_id`="+id;
            preparedStatement=CONNECTION.prepareStatement(sql);
            existences=preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (existences!=0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 修改账本信息，修改成功返回true，修改失败返回false。
     * @param id 账本id
     * @param newData 字符串数组（账本名、账本描述、上次更改时间）
     * @return boolean
     * */
    public static boolean changeBookInfo(String id, String[] newData) {
        boolean returnValue = false;
        int existence = 0;
        String sql = null;
        sql = "update  `book` set  `book_name`=?,`book_desc`=?,`book_last_time`=?  where book_id=?";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newData[0]);
            preparedStatement.setString(2, newData[1]);
            preparedStatement.setString(3, newData[2]);
            preparedStatement.setInt(4, Integer.parseInt(id));
            existence = preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (existence == 1) {
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * 用户账本查询，
     * 返回用户的账本信息集合。
     * @param UserId 用户id，
     * @return resultSet
     * */
    public static ResultSet queryBookMsg(String UserId)
    {
        resultSet = null;
        String sql = "select * from `book` where `user_id`=?";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, UserId);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 验证账本是否存在，
     * 如果账本存在返回true，反之
     * @param bookName 账本名
     * @return boolean
     * */
    public static boolean checkBookExist(String bookName)
    {
        String sql = "select * from `book` where  `book_name` = ?";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, bookName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            resultSet = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
