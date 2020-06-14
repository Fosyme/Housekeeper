package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 李建强
 * @date 2020-6-11
 * */
public class UserOperation {
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static final Connection CONNECTION = MethodOfOperation.connection;

    /**
     * 登录验证，验证用户密码是否正确,
     * 如果执行成功返回id，否则返回null;
     * @param userName     用户名
     * @param userPassword 用户密码
     * @return int,用户id
     */
    public static String loginAuthentication(String userName, String userPassword) {
        String id = null;
        String sql = "select `user_id` from `user` where `user_name` = ? and `user_pwd` = ?";
        resultSet = null;
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
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
     * 验证用户名是否存在
     *如果用户存在，返回boolean类型true，否则返回false。
     * @param userName 用户名
     * @return  boolean
     */
    public static boolean checkUserExist(String userName) {
        String sql = "select * from `user` where user_name = ?";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            resultSet = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 注册账号,如果注册成功，返回boolean变量 ture，否则返回false。
     * @param userMsg 字符数组(用户名、密码、密保问题、密保答案、注册世间、上次登录时间、性别、年龄、手机号、地址)
     * @return boolean
     */
    public static boolean regAccount(String[] userMsg) {
        String sql = " insert into `user` (`user_name`,`user_pwd`,`user_encrypted_question`,`user_encrypted_answer`,`user_reg_time`,`user_sex`,`user_age`,`user_phone`,`user_address`,`user_head_thumb`) " +
                "values (?,?,?,?,?,?,?,?,?,?)";
        int exist = 0;
        boolean verify = false;
        try {
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
            exist = preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
            if (exist != 0) {
                verify = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verify;
    }


    /**
     * 验证用户密保是否正确,
     * 如果验证结果为正确，返回该用户id，否则返回null。
     * * @param userName 用户名,
     * * @param userEncryptedAnswer 密保答案,
     */
    public static String userEncryptedVerify(String userName, String userEncryptedAnswer) {
        String uerId = null;
        resultSet = null;
        String sql = "select user_id from `user` where `user_name`=? and user_encrypted_answer=?";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userEncryptedAnswer);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                uerId = resultSet.getString(1);
                resultSet = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uerId;
    }

    /**
     * 删除用户,
     * 删除成功返回true，
     * 删除失败返回false。
     * @param id 用户id
     * @return boolean
    * */

    public static boolean deleteUser(String id) {
        boolean returnValue = false;
        String sql = null;
        int existence = 0;
        sql = "delete from `user` where `user_id`= "+id;
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            existence = preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (existence == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 更改用户密码，
     * 修改成功返回true，否则返回false。
     * @param userId      用户id
     * @param newPassWord 新密码
     * @return boolean
     */
    public static boolean changeUserPassWord(String userId, String newPassWord) {
        String sql = "update  `user` set `user_pwd`=?  where `user_id`=? ";
        int existence = 0;
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newPassWord);
            preparedStatement.setInt(2, Integer.parseInt(userId));
            existence = preparedStatement.executeUpdate();
            if (existence != 0) {
                return true;
            }
            preparedStatement.clearParameters();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

   /**
    * 修改密保问题和密保答案,
    * 修改成功返回true，修改失败返回false。
    * @param userId 用户id
    * @param newUserEncryptedQuestion 新的用户密保问题
    * @param newUserEncryptedAnswer 新的用户密保答案
    * @return boolean
    * */
    public static boolean changeEncrypted(String userId, String newUserEncryptedQuestion, String newUserEncryptedAnswer) {
        String sql = "update  `user` set `user_encrypted_question`=? , `user_encrypted_answer`=?  where `user_id`=? ";
        int existence=0;
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, newUserEncryptedQuestion);
            preparedStatement.setString(2, newUserEncryptedAnswer);
            preparedStatement.setInt(3, Integer.parseInt(userId));
            existence = preparedStatement.executeUpdate();
            if (existence!=0)
            {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改用户信息，修改成功返回true，修改失败返回false。
     * @param id 需要更改信息的用户id‘
     * @param newData 用户信息数组 （性别、年龄、电话、地址、头像）
     * @return boolean类型
     * */
    public static boolean changeUserInfo(String id, String[] newData) {
        int existence = 0;
        String sql ="update `user` set `user_sex`=?,`user_age`=?,`user_phone`=?,`user_address`=?,`user_head_thumb`=? where `user_id`= ?";
            try {
                preparedStatement = CONNECTION.prepareStatement(sql);
                preparedStatement.setString(1, newData[0]);
                preparedStatement.setInt(2, Integer.parseInt(newData[1]));
                preparedStatement.setString(3, newData[2]);
                preparedStatement.setString(4, newData[3]);
                preparedStatement.setString(5, newData[4]);
                preparedStatement.setInt(6, Integer.parseInt(id));
                existence = preparedStatement.executeUpdate();
                if (existence!=0)
                {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }


    /**
     * 设置用户最后登录时间，
     * 成功返回true，失败返回false。
     * @param userId 用户id，
     * @param lastTime 最后登录时间
     * @return  boolean
     * */
    public static boolean setUserLastTime(String userId, String lastTime){
        String sql = "update `user` set `user_last_time`=? where `user_id`=?";
        int existence=0;
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, lastTime);
            preparedStatement.setInt(2, Integer.parseInt(userId));
            existence = preparedStatement.executeUpdate();
            if (existence!=0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查找用户密保问题
     *
     * @param userName 用户名
     * @return 用户密保问题
     *          如果失败返回{@code null}
     * */
    public static String queryEncryptedQuestion(String userName){
        String userEncryptedQuestion=null;
        resultSet=null;
        String sql="select `user_encrypted_question` from `user` where  `user_name`=? ";
        try {
            preparedStatement=CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next())
            {
               userEncryptedQuestion=resultSet.getString(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userEncryptedQuestion;
    }


    /**
     * 查询用户信息
     * @param UserId 用户id，
     * @return resultSet
     * */
    public static ResultSet queryUserMsg(String UserId)
    {
        resultSet = null;
        String sql = "select * from `user` where `user_id`=?";
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, UserId);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
