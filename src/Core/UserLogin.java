package Core;

import Dao.AccessOperation;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 此类是一个登录界面调用类，包括了登录时所含的所有参数
 * 此类有登录、注册、找回密码等方法
 *
 * @author Fosyme
 * @version 1.0.0
 */

public class UserLogin {
    private User user;  //用户信息

    public static final int RECEIVE_USER_INFORMATION_LENGTH = 9;    //从前端接受用户数据长度
    public static final int TRANSMIT_USER_INFORMATION_LENGTH = 11;  //向数据库传输用户信息长度

    public UserLogin() {

    }

    public User getUser() {
        return user;
    }

    /**
     * 登录方法, 用于用户登录认证
     * @param userName 用户名, 不能为空
     * @param userPassword 用户输入密码(明文)
     * @return 登录结果
     * */
    public ResultStatus signIn(@NotNull String userName, String userPassword) {
        String userID;
        userID = AccessOperation.loginAuthentication(userName, getMD5String(userPassword));
        if (userID.isEmpty()) {
            return ResultStatus.USERNAME_OR_PASSWORD_ERROR;
        }
        user = new User(userID);
        return ResultStatus.SIGN_IN_SUCCESS;
    }

    /**
     * 注册方法, 用于用户注册账户
     * @param userMsg 从前端接受来的用户信息数组
     *                包含: 用户名, 用户密码(明文), 用户密保问题,
     *                用户密保答案(明文), 性别, 年龄, 手机号, 地址, 头像
     * @return 注册结果
     */
    public ResultStatus signUp(@NotNull String[] userMsg) {
        final int userPassword = 1;         //用户密码索引
        final int userEncryptedAnswer = 3;  //用户密保索引
        final int userRegTime = 4;          //用户注册时间索引
        final int userLastTime = 5;         //用户最后登录时间索引

        String[] userInfo = new String[TRANSMIT_USER_INFORMATION_LENGTH];
        if (userMsg.length == RECEIVE_USER_INFORMATION_LENGTH) {
            boolean verifyId = AccessOperation.checkUserExist(userMsg[0]);
            if (!verifyId) {
                return ResultStatus.USER_IS_EXIST;
            }
            for (int i = 0, j = 0; j < RECEIVE_USER_INFORMATION_LENGTH; i++) {
                if (i == userPassword || i == userEncryptedAnswer) {
                    userInfo[i] = getMD5String(userMsg[j++]);
                } else if (i == userRegTime || i == userLastTime) {
                    userInfo[i] = String.valueOf((System.currentTimeMillis() / 1000));
                } else {
                    userInfo[i] = userMsg[j++];
                    j++;
                }
            }
            boolean regResult = AccessOperation.regAccount(userInfo);
            System.out.println(regResult);
            if (regResult) {
                return ResultStatus.SIGN_UP_SUCCESS;
            } else {
                return ResultStatus.INFO_AGAINST_RULES;
            }
        }
        return ResultStatus.DATA_ERROR;
    }

    //登出方法
    public ResultStatus signOut() {
        if (setUserLastTime(user.getUserID(), System.currentTimeMillis())) {
            return ResultStatus.SIGN_OUT_SUCCESS;
        }
        return ResultStatus.INCORRECT_WAY;
    }

    //找回密码
    public ResultStatus recoverPassword(String userName, String userEncryptedQuestion, String userEncryptedAnswer, String newPassword) {
        boolean verifyId = AccessOperation.checkUserExist(userName);
        if (verifyId) {
           String userID = AccessOperation
                   .userEncryptedVerify(userName, userEncryptedQuestion, getMD5String(userEncryptedAnswer));
            if (userID.isEmpty()) {
                return ResultStatus.ENCRYPTED_ERROR;
            }
            if (AccessOperation.changeUserPassword(userID, newPassword)) {
                return ResultStatus.RECOVER_PASSWORD_SUCCESS;
            } else {
                return ResultStatus.UNKNOWN_ERROR;
            }
        }
        return ResultStatus.USER_NOT_EXIST;
    }

    //MD5加密方法
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
            System.out.println(cipherText);
            return fillMD5(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    private static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }
}