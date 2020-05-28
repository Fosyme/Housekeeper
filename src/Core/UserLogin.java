package Core;

import Dao.AccessOperation;
import com.sun.javafx.image.IntPixelGetter;

/**
 * 此类是一个登录界面调用类，包括了登录时所含的所有参数
 * 此类有登录、注册、找回密码等方法
 *
 * @author Fosyme
 * @date 2020/5/7
 * @version 1.0.0
 */
public class UserLogin {
    private User user;                  //用户信息

    public UserLogin() {

    }

    public User getUser() {
        return user;
    }

    //登录方法
    public int signIn() {

        return ResultStatus.NONE;
    }

    //注册方法
    public int signUp(String[] userMsg) {
        if (userMsg.length == 11) {
            String verifyId = AccessOperation.loginAuthentication("user", userMsg[0]);
            if (verifyId.length() != 0) {
                return ResultStatus.USER_IS_EXIST;
            }
            String addUserID = AccessOperation.addData("user", userMsg);

        }
        return ResultStatus.NONE;
    }

    //登出方法
    public int signOut() {

        return ResultStatus.NONE;
    }

    //找回密码
    public int recoverPassword() {

        return ResultStatus.NONE;
    }
}
