package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderOperation {
    private static final Connection CONNECTION = DBLeader.connection;
    private static PreparedStatement preparedStatement = null;

    /**
     * 添加账单,返回账单id。
     *
     * @param data 字符串数组（账本id、账单名、金额、支付方式、收支模式、时间、分类、描述、图片）
     * @return orderId，账单id
     **/
    public static String add(String[] data) {
        String sql;
        try {
            sql = "insert into `order` values (0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(data[0]));
            preparedStatement.setString(2, data[1]);
            preparedStatement.setDouble(3, Double.parseDouble(data[2]));
            preparedStatement.setString(4, data[3]);
            preparedStatement.setString(5, data[4]);
            preparedStatement.setString(6, data[5]);
            preparedStatement.setString(7, data[6]);
            preparedStatement.setString(8, data[7]);
            preparedStatement.setString(9, data[8]);
            preparedStatement.execute();
            return DBLeader.getIDAfterAdd("order");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除账单，
     * 删除成功返回true，
     * 删除失败返回false。
     *
     * @param id 账单id
     * @return boolean
     */
    public static boolean delete(String id) {
        String sql;
        try {
            sql = "delete from `order` where `order_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改账单信息，修改成功时返回true，修改失败时返回false
     *
     * @param id      账单id
     * @param newData 字符串数组（账本id(待用)、账单名、金额、支付方式、收支模式、时间、分类、描述、图片）
     * @return boolean
     */

    public static boolean changeInfo(String id, String[] newData) {
        String sql;
        try {
            sql = "update `order` set `order_name` = ?, `order_price` = ?, `order_way` = ?, `order_mod` = ?, " +
                    "`order_time` = ?, `order_cate` = ?, `order_desc` = ?, `order_image_src` = ? where `order_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newData[1]);
            preparedStatement.setDouble(2, Double.parseDouble(newData[2]));
            preparedStatement.setString(3, newData[3]);
            preparedStatement.setString(4, newData[4]);
            preparedStatement.setString(5, newData[5]);
            preparedStatement.setString(6, newData[6]);
            preparedStatement.setString(7, newData[7]);
            preparedStatement.setString(8, newData[8]);
            preparedStatement.setInt(9, Integer.parseInt(id));
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 账本账单查询，
     * 返回账单信息集合。
     *
     * @param BookId 账本id，
     * @return resultSet
     */
    public static ResultSet queryInfo(String BookId) {
        String sql;
        try {
            sql = "select * from `order` where `book_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, BookId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 模糊查询
     * @param bookId 账本id，
     * @param keyWord 要查寻的关键字（在描述和账单名内查找）
     * @return ResultSet 该账本订单名和订单描述包含keyWord的所有订单
     * */
    public static ResultSet fuzzyQueryInfo(String bookId, String keyWord) {
        String sql;
        try {
            sql = "select * from `order` where `book_id` = ? and `order_name` like ? or `order_desc` like ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(bookId));
            preparedStatement.setString(2, "%" + keyWord + "%");
            preparedStatement.setString(3, "%" + keyWord + "%");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * 查询时间间隔内的账单，
     * @param bookId 账本id
     * @param startTime 时间段的开始时间
     * @param endTime 时间段的结束时间
     * @return ResultSet 该账本时间段内的所有订单
    * */
    public static ResultSet queryTimePeriod(String bookId, long startTime, long endTime) {
        String sql;
        try {
            sql = "select * from `order` where `book_id` = ? and `order_time` between ? and ? ";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(bookId));
            preparedStatement.setLong(2, startTime);
            preparedStatement.setLong(3, endTime);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
