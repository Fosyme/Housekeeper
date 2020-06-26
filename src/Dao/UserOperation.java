package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperation {
    private static final Connection CONNECTION = DBUtil.connection;
    private static PreparedStatement preparedStatement = null;

    //TODO string -> int
    /**
     * 登录验证，验证用户密码是否正确,
     * 如果执行成功返回id，否则返回null;
     *
     * @param userName     用户名
     * @param userPassword 用户密码
     * @return int, 用户id
     */
    public static String authentication(String userName, String userPassword) {
        String sql;
        ResultSet resultSet;
        try {
            sql = "select `user_id` from `user` where `user_name` = ? and `user_pwd` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            resultSet = preparedStatement.executeQuery();
            preparedStatement.clearParameters();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证用户名是否存在
     * 如果用户存在，返回boolean类型true，否则返回false。
     *
     * @param userName 用户名
     * @return boolean
     */
    public static boolean isExist(String userName) {
        String sql;
        try {
            sql = "select * from `user` where user_name = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            if (preparedStatement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 注册账号,如果注册成功，返回boolean变量 true，否则返回false。
     *
     * @param userMsg 字符数组(用户名、密码、密保问题、密保答案、注册时间、性别、年龄、手机号、地址、头像)
     * @return boolean
     */
    public static boolean add(String[] userMsg) {
        String sql;
        try {
            sql = " insert into `user` values (0, ?, ?, ?, ?, ?, null, ?, ?, ?, ?, ?)";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, userMsg[0]);
            preparedStatement.setString(2, userMsg[1]);
            preparedStatement.setString(3, userMsg[2]);
            preparedStatement.setString(4, userMsg[3]);
            preparedStatement.setString(5, userMsg[4]);
            preparedStatement.setString(6, userMsg[5]);
            preparedStatement.setInt(7, Integer.parseInt(userMsg[6]));
            preparedStatement.setString(8, userMsg[7]);
            preparedStatement.setString(9, userMsg[8]);
            preparedStatement.setString(10, userMsg[9]);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证用户密保是否正确,
     * 如果验证结果为正确，返回该用户id，否则返回null。
     * * @param userName 用户名,
     * * @param userEncryptedAnswer 密保答案,
     */
    public static String verifyEncrypted(String userName, String userEncryptedAnswer) {
        String sql;
        ResultSet resultSet;
        try {
            sql = "select user_id from `user` where `user_name` = ? and user_encrypted_answer = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userEncryptedAnswer);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除用户,
     * 删除成功返回true，
     * 删除失败返回false。
     *
     * @param id 用户id
     * @return boolean
     */
    public static boolean delete(String id) {
        String sql;
        try {
            sql = "delete from `user` where `user_id`= ?";
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
     * 更改用户密码，
     * 修改成功返回true，否则返回false。
     *
     * @param userID      用户id
     * @param newPassWord 新密码
     * @return boolean
     */
    public static boolean changePassword(String userID, String newPassWord) {
        String sql;
        try {
            sql = "update `user` set `user_pwd` = ? where `user_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newPassWord);
            preparedStatement.setInt(2, Integer.parseInt(userID));
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改密保问题和密保答案,
     * 修改成功返回true，修改失败返回false。
     *
     * @param userID               用户id
     * @param newEncryptedQuestion 新的用户密保问题
     * @param newEncryptedAnswer   新的用户密保答案
     * @return boolean
     */
    public static boolean changeEncrypted(String userID, String newEncryptedQuestion, String newEncryptedAnswer) {
        String sql;
        try {
            sql = "update `user` set `user_encrypted_question` = ?, `user_encrypted_answer` = ? where `user_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newEncryptedQuestion);
            preparedStatement.setString(2, newEncryptedAnswer);
            preparedStatement.setInt(3, Integer.parseInt(userID));
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改用户信息，修改成功返回true，修改失败返回false。
     *
     * @param id      需要更改信息的用户id‘
     * @param newData 用户信息数组 （性别、年龄、电话、地址、头像）
     * @return boolean类型
     */
    public static boolean changeInfo(String id, String[] newData) {
        String sql;
        try {
            sql = "update `user` set `user_sex` = ?, `user_age` = ?, `user_phone` = ?, " +
                    "`user_address` = ?, `user_head_thumb` = ? where `user_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newData[0]);
            preparedStatement.setInt(2, Integer.parseInt(newData[1]));
            preparedStatement.setString(3, newData[2]);
            preparedStatement.setString(4, newData[3]);
            preparedStatement.setString(5, newData[4]);
            preparedStatement.setInt(6, Integer.parseInt(id));
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置用户最后登录时间，
     * 成功返回true，失败返回false。
     *
     * @param userID   用户id，
     * @param lastTime 最后登录时间
     * @return boolean
     */
    public static boolean setLastTime(String userID, String lastTime) {
        String sql;
        try {
            sql = "update `user` set `user_last_time` = ? where `user_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, lastTime);
            preparedStatement.setInt(2, Integer.parseInt(userID));
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查找用户密保问题
     *
     * @param userName 用户名
     * @return 用户密保问题
     * 如果失败返回{@code null}
     */
    public static String queryEncryptedQuestion(String userName) {
        String sql;
        ResultSet resultSet;
        try {
            sql = "select `user_encrypted_question` from `user` where `user_name` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询用户信息
     *
     * @param UserID 用户id，
     * @return resultSet
     */
    public static ResultSet queryInfo(String UserID) {
        String sql;
        try {
            sql = "select * from `user` where `user_id` = ?";
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, UserID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
