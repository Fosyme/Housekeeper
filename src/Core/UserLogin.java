package Core;

import Dao.UserOperation;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 此类是一个登录界面调用类，包括了登录时所含的所有参数
 * 此类有登录、注册、找回密码等方法
 *
 * @author Fosyme(向世杰)
 */

public class UserLogin {
    private User user;  //用户信息

    public UserLogin() {

    }

    public User getUser() {
        return user;
    }

    /**
     * 登录方法, 用于用户登录认证
     *
     * @param userName 用户名, 不能为空
     * @param userPassword 用户输入密码(明文)
     * @return 登录结果
     * */
    public boolean signIn(@NotNull String userName, String userPassword) {
        String userID;
        String encryptedPassword = getMD5String(userPassword);
        userID = UserOperation.loginAuthentication(userName, encryptedPassword);
        if (userID == null) {
            return false;
        }
        user = new User(userID);
        return true;
    }

    /**
     * 注册方法, 用于用户注册账户
     *
     * @param userMsg 从前端接受来的用户信息数组
     *                包含: 用户名, 用户密码(明文), 用户密保问题,
     *                用户密保答案(明文), 性别, 年龄, 手机号, 地址, 头像
     * @return 注册结果
     */
    public boolean signUp(@NotNull String[] userMsg) {
        final int RECEIVE_USER_INFORMATION_LENGTH = 9;    //从前端接受用户数据长度
        final int TRANSMIT_USER_INFORMATION_LENGTH = 10;  //向数据库传输用户信息长度
        final int USER_PASSWORD = 1;                      //用户密码索引
        final int USER_ENCRYPTED_ANSWER = 3;              //用户密保索引
        final int USER_REG_TIME = 4;                      //用户注册时间索引

        String[] userInfo = new String[TRANSMIT_USER_INFORMATION_LENGTH];
        if (userMsg.length == RECEIVE_USER_INFORMATION_LENGTH) {
            boolean verifyId = UserOperation.checkUserExist(userMsg[0]);
            if (verifyId) {
                return false;
            }
            for (int i = 0, j = 0; j < RECEIVE_USER_INFORMATION_LENGTH; i++) {
                if (i == USER_PASSWORD || i == USER_ENCRYPTED_ANSWER) {
                    userInfo[i] = getMD5String(userMsg[j++]);
                } else if (i == USER_REG_TIME) {
                    userInfo[i] = String.valueOf((System.currentTimeMillis() / 1000));
                } else {
                    userInfo[i] = userMsg[j++];
                }
            }
            return UserOperation.regAccount(userInfo);
        }
        return false;
    }

    /**
     * 登出方法, 用于用户登出账号或切换账号
     *
     * @return 账号登出结果
     * */
    public boolean signOut() {
        return UserOperation.setUserLastTime(user.getUserID(), String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 查询用户密保问题
     *
     * @param userName 用户名
     * @return 用户密保问题, 返回{@code null}标识无此用户
     * */
    public String queryUserEncrypted(String userName) {
        boolean verify = UserOperation.checkUserExist(userName);
        return verify ? UserOperation.queryEncryptedQuestion(userName) : null;
    }

    /**
     * 根据密保问题及答案, 找回用户密码
     *
     * @param userName 用户名
     * @param userEncryptedAnswer 用户密码答案
     * @param newPassword 用户新密码
     * @return 返回找回密码结果
     * */
    public boolean recoverPassword(String userName, String userEncryptedAnswer, String newPassword) {
        String userID = UserOperation.userEncryptedVerify(userName, getMD5String(userEncryptedAnswer));
        if (userID == null) {
            return false;
        }
        return UserOperation.changeUserPassWord(userID, getMD5String(newPassword));
    }

    //对字符串进行MD5加密
    public static String getMD5String(String plainText) {
        String cipherText = "";
        try {
            //生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //计算md5函数
            md.update(plainText.getBytes());
            //digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            //BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            cipherText = new BigInteger(1, md.digest()).toString(16);
            return fillMD5(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    //对16位MD5码进行32位转换
    private static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }
}