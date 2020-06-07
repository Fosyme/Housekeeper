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
    private static Connection connection = jdbcUtil.getConnection();
    private static final int FINAL_INT_ONE = 1;
    private static final int FINAL_INT_Five = 5;
    private static final int FINAL_INT_TWO = 2;

    /**
     * 添加用户的方法
     * 参数table为需要添加数据的表名,返回用户id信息
     */
    static String addUserMethod(String table, String[] data) {
        String sql = " insert into `" + table + "` (`user_name`,`user_pwd`,`user_encrypted_question`,`user_encrypted_answer`,`user_reg_time`,`user_last_time`,`user_sex`,`user_age`,`user_phone`,`user_address`,`user_head_thumb`) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
        String id = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, data[0]);
            preparedStatement.setString(2, data[1]);
            preparedStatement.setString(3, data[2]);
            preparedStatement.setString(4, data[3]);
            preparedStatement.setString(5, data[4]);
            preparedStatement.setString(6, data[5]);
            preparedStatement.setString(7, data[6]);
            preparedStatement.setInt(8, Integer.parseInt(data[7]));
            preparedStatement.setString(9, data[8]);
            preparedStatement.setString(10, data[9]);
            preparedStatement.setString(11, data[10]);
            preparedStatement.execute();
            preparedStatement.clearParameters();
            id = MethodOfOperation.queryMaxId(connection, table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }


    /**
     * 添加账本的方法
     * 参数为string类型,返回账本id信息
     */
    static String addBookMethod(String table, String[] data) {
        String id = null;
        String sql = "insert into `" + table + "` ( `user_id`, `book_name`, `book_desc`, `book_add_time`, `book_last_time`) " +
                "values(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(data[0]));
            preparedStatement.setString(2, data[1]);
            preparedStatement.setString(3, data[2]);
            preparedStatement.setString(4, data[3]);
            preparedStatement.setString(5, data[4]);
            preparedStatement.execute();
            preparedStatement.clearParameters();
            id = MethodOfOperation.queryMaxId(connection, table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 添加账单的方法
     * 参数为string类型,返回账单id信息
     **/
    static String addOrderMethod(String table, String[] data) {
        String id = null;
        String sql = "insert into `" + table + "`(`book_id`,`order_name`,`order_price`,`order_way`,`order_mod`,`order_time`,`order_cate`,`order_desc`,`order_image_src`)" +
                "values(?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
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
            id = queryMaxId(connection, table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 返回数据库table中最大的id值
     */
    private static String queryMaxId(Connection connection, String table) {
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


    /**
     * 删除数据的方法，参数table为表名，
     * 参数id需要删除的列的id值
     */
    static boolean delete(String table, String id) {
        boolean returnValue = false;
        String sql = null;
        int existence = 0;
        sql = "delete from `" + table + "` where `" + table + "_id`=" + id;
        try {
            preparedStatement = connection.prepareStatement(sql);
            existence = preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (existence == 1) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    /**
     * 修改用户信息的方法，参数id为需要修改的数据的id值，
     * 参数newData为更改的新值的字符串数组；当该数组长度为1时，修改最后登录时间，
     * 当该数组长度为5时，修改的是用户基本信息。
     */
    static boolean changeUserInfo(String id, String[] newData) {
        boolean returnValue = false;
        int existence = 0;
        String sql = null;
        if (newData.length == FINAL_INT_ONE) {
            sql = "update `user` set `user_last_time`=? where `user_id`=?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newData[0]);
                preparedStatement.setInt(2, Integer.parseInt(id));
                existence = preparedStatement.executeUpdate();
                preparedStatement.clearParameters();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (newData.length == FINAL_INT_Five) {
            sql = "update `user` set `user_sex`=?,`user_age`=?,`user_phone`=?,`user_address`=?,`user_head_thumb`=? where `user_id`= ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newData[0]);
                preparedStatement.setInt(2, Integer.parseInt(newData[1]));
                preparedStatement.setString(3, newData[2]);
                preparedStatement.setString(4, newData[3]);
                preparedStatement.setString(5, newData[4]);
                preparedStatement.setInt(6, Integer.parseInt(id));
                existence = preparedStatement.executeUpdate();
                preparedStatement.clearParameters();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (existence == 1) {
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * id为需要修改的账本的账本id值，
     * newDate[0]为账本名，
     * newDate[1]为描述，可以为null
     * newDate[2]为上一次的修改时间，应该传入当前时间
     */
    static boolean changeBookInfo(String id, String[] newData) {
        boolean returnValue = false;
        int existence = 0;
        String sql = null;
        sql = "update  `book` set  `book_name`=?,`book_desc`=?,`book_last_time`=?  where book_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
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
     * id为需要被修改的订单的id值，
     * newData为修改后的新数据
     */
    static boolean changeOrderInfo(String id, String[] newData) {
        String sql = null;
        boolean returnValue = false;
        int existence = 0;
        sql = "update  `order` set `order_name`=?,`order_price`=?,`order_way`=?,`order_mod`=?,`order_time`=?,`order_cate`=?," +
                "`order_desc`=?,`order_image_src`=? where `order_id`=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
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
     * 修改用户密码或者修改用户的密保及密保问题，
     * id为String类型，是需要修改数据的的用户id值，
     * 参数newData为更改的新值的字符串数组；当该数组长度为1时，修改最后登录时间，
     * 当该数组长度为2时，修改的是用户基本信息。
     */
    protected static boolean changePasswordAndEncrypted(String id, String[] newData) {
        String sql = null;
        boolean returnValue = false;
        int existence = 0;
        //newDate数组长度为1时，修改密码；
        if (newData.length == FINAL_INT_ONE) {
            sql = "update  `user` set `user_pwd`=?  where `user_id`=? ";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newData[0]);
                preparedStatement.setInt(2, Integer.parseInt(id));
                existence = preparedStatement.executeUpdate();
                preparedStatement.clearParameters();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //newDate数组长度为2时，修改密保问题和密保答案；
        if (newData.length == FINAL_INT_TWO) {
            sql = "update  `user` set `user_encrypted_question`=? , `user_encrypted_answer`=?  where `user_id`=? ";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newData[0]);
                preparedStatement.setString(2, newData[1]);
                preparedStatement.setInt(3, Integer.parseInt(id));
                existence = preparedStatement.executeUpdate();
                preparedStatement.clearParameters();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (existence == 1) {
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * 登录验证，参数table 为表名，
     * 参数data为数组类型，数组第一个数据为用户名，第二个数据为密码;
     * 如果执行成功且有返回值，则返回id，否则返回null;
     */
    static String loginAuthentications(String userName, String userPassword) {
        String id = null;
        String sql = null;
        resultSet = null;
        sql = "select `user_id` from `user` where `user_name` = ? and `user_pwd` = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            resultSet = preparedStatement.executeQuery();
            preparedStatement.clearParameters();
            if (resultSet.next()) {
                id = resultSet.getString(1);
                resultSet = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }

    /**
     * 参数table为所要查询的表名，
     * 参数id为所要查询记录的id，
     * 如果执行成功，就返回该条记录的结果集，
     * 否则就返回null
     */
    static ResultSet queryData(String table, String id) {
        resultSet = null;
        String sql = "select * from `" + table + "` where `" + table + "_id`=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            preparedStatement.clearParameters();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * @param userName 用户名
     * @return 返回是否查询到此用户
     * */
    public static boolean CheckUserExists(String userName) {
        String sql = "select * from `user` where user_name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
