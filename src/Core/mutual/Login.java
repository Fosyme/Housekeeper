package Core.mutual;

import Core.model.User;
import Dao.UserOperation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Properties;

/**
 * 此类是一个登录界面调用类，包括了登录时所含的所有参数
 * 此类有登录、注册、找回密码等方法
 */
public class Login {
    public Login() {
    }

    /**
     * passwordType密码类型
     * */
    public static final int plain = 0;  //明文
    public static final int cipher = 1; //密文

    /**
     * 登录方法, 用于用户登录认证
     *
     * @param userName     用户名, 不能为空
     * @param userPassword 用户输入密码
     * @param passwordType 密码方式
     * @return 用户对象
     */
    public User signIn(String userName, String userPassword, int passwordType) {
        String userID;
        if (passwordType == plain) {
            userID = UserOperation.authentication(userName, getMD5String(userPassword));
        } else {
            userID = UserOperation.authentication(userName, userPassword);
        }
        if (userID != null) {
            Data data = new Data();
            return data.loadUser(userID);
        }
        return null;
    }

    /**
     * 注册方法, 用于用户注册账户
     *
     * @param userMsg 从前端接受来的用户信息数组
     *                包含: 用户名, 用户密码(明文), 用户密保问题,
     *                用户密保答案(明文), 性别, 年龄, 手机号, 地址, 头像
     * @return 注册结果
     */
    public boolean signUp(String[] userMsg) {
        String[] userInfo = new String[10];
        boolean verifyId = UserOperation.isExist(userMsg[0]);
        if (verifyId) {
            return false;
        }
        userInfo[0] = userMsg[0];
        userInfo[1] = getMD5String(userMsg[1]);
        userInfo[2] = userMsg[2];
        userInfo[3] = getMD5String(userMsg[3]);
        userInfo[4] = String.valueOf(System.currentTimeMillis() / 1000);
        System.arraycopy(userMsg, 4, userInfo, 5, 5);
        return UserOperation.add(userInfo);
    }

    /**
     * 登出方法, 用于用户登出账号或切换账号
     *
     * @param userID 用户ID
     */
    public void signOut(String userID) {
        UserOperation.setLastTime(userID, String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 查询用户密保问题
     *
     * @param userName 用户名
     * @return 用户密保问题, 返回{@code null}标识无此用户
     */
    public String queryUserEncrypted(String userName) {
        boolean verify = UserOperation.isExist(userName);
        return verify ? UserOperation.queryEncryptedQuestion(userName) : null;
    }

    /**
     * 根据密保问题及答案, 找回用户密码
     *
     * @param userName            用户名
     * @param userEncryptedAnswer 用户密保答案
     * @param newPassword         用户新密码
     * @return 返回找回密码结果
     */
    public boolean recoverPassword(String userName, String userEncryptedAnswer, String newPassword) {
        String userID = UserOperation.verifyEncrypted(userName, getMD5String(userEncryptedAnswer));
        if (userID == null) {
            return false;
        }
        return UserOperation.changePassword(userID, getMD5String(newPassword));
    }

    /**
     * 将登录信息写入配置文件
     *
     * @param userName         用户名
     * @param userPassword     用户密码(明文)
     * @param autoSignIn       自动登录标识
     * @param rememberPassword 记住密码标识
     */
    public void writeConfig(String userName, String userPassword, boolean autoSignIn, boolean rememberPassword) {
        try {
            FileOutputStream fos = new FileOutputStream("src/config");
            Properties prop = new Properties();
            prop.put("auto_sign_in", String.valueOf(autoSignIn));
            prop.put("remember_password", String.valueOf(rememberPassword));
            prop.put("user_name", userName);
            if (rememberPassword) {
                prop.put("user_password", userPassword);
            }
            prop.store(fos, "Config");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //对字符串进行MD5加密
    private static String getMD5String(String plainText) {
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