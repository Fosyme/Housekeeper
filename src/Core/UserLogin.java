package Core;

/**
 * 此类是一个登录界面调用类，包括了登录时所含的所有参数
 * 此类有登录、注册、找回密码等方法
 */
public class UserLogin {
    private String username;          //用户名
    private String password;          //密码
    private boolean rememberPassword; //记住密码
    private boolean autoLogin;        //自动登录

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRememberPassword(boolean rememberPassword) {
        this.rememberPassword = rememberPassword;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    //登录方法
    public int signIn() {

        return ResultStatus.NONE;
    }

    //注册方法
    public int signUp() {

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

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberPassword=" + rememberPassword +
                ", autoLogin=" + autoLogin +
                '}';
    }
}
