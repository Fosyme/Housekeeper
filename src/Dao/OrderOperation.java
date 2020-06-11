package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderOperation {
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static final Connection CONNECTION = MethodOfOperation.connection;

    /**
     * 添加账单,返回账单id。
     * @param data 字符串数组（账本id、账单名、金额、支付方式、收支模式、时间、分类、描述、图片）
     * @return orderId，账单id
     **/
    static String addOrder(String[] data) {
        String id = null;
        String sql = "insert into `order`(`book_id`,`order_name`,`order_price`,`order_way`,`order_mod`,`order_time`,`order_cate`,`order_desc`,`order_image_src`)" +
                "values(?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(data[0]));
            preparedStatement.setString(2, data[1]);
            preparedStatement.setString(3, data[2]);
            preparedStatement.setString(4, data[3]);
            preparedStatement.setString(5, data[4]);
            preparedStatement.setString(6, data[5]);
            preparedStatement.setString(7, data[6]);
            preparedStatement.setString(8, data[7]);
            preparedStatement.setString(9, data[8]);
            preparedStatement.execute();
            preparedStatement.clearParameters();
            id = MethodOfOperation.queryMaxId("order");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 删除账单，
     * 删除成功返回true，
     * 删除失败返回false。
     * @param id 账单id
     * @return boolean
     * */
    static boolean deleteOrder(String id) {
        String sql ="delete from `order` where `order_id`=" + id;
        int existence = 0;
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            existence = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (existence !=0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 修改账单信息，修改成功时返回true，修改失败时返回false
     * @param id 账单id
     * @param newData 字符串数组（账单名、金额、支付方式、收支模式、时间、分类、描述、图片）
     * @return boolean
     * */

    static boolean changeOrderInfo(String id, String[] newData) {
        String sql = null;
        boolean returnValue = false;
        int existence = 0;
        sql = "update  `order` set `order_name`=?,`order_price`=?,`order_way`=?,`order_mod`=?,`order_time`=?,`order_cate`=?," +
                "`order_desc`=?,`order_image_src`=? where `order_id`=?";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newData[0]);
            preparedStatement.setString(2, newData[1]);
            preparedStatement.setString(3, newData[2]);
            preparedStatement.setString(4, newData[3]);
            preparedStatement.setString(5, newData[4]);
            preparedStatement.setString(6, newData[5]);
            preparedStatement.setString(7, newData[6]);
            preparedStatement.setString(8, newData[7]);
            preparedStatement.setInt(9, Integer.parseInt(id));
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
     * 账本账单查询，
     * 返回账单信息集合。
     * @param BookId 账本id，
     * @return resultSet
     * */
    public static ResultSet queryOrderMsg(String BookId)
    {
        resultSet = null;
        String sql = "select * from `order` where `book_id`=?";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, BookId);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
