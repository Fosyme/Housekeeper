package Core;

public class User {
    private String userID;
    private String userName;            //用户名
    private String password;            //密码
    private boolean rememberPassword;   //记住密码 (默认否)
    private boolean autoLogin;          //自动登录 (默认否)
    private String[] bookID;
    private String[] orderID;
    private UserLogin userLogin;

    {
        userID = null;
        userName = null;
        password = null;
        rememberPassword = false;
        autoLogin = false;
        bookID = null;
        orderID = null;
        userLogin = null;
    }

    public User(String userId) {
        this.userID = userId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberPassword() {
        return rememberPassword;
    }

    public void setRememberPassword(boolean rememberPassword) {
        this.rememberPassword = rememberPassword;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String[] getBookID() {
        return bookID;
    }

    public void setBookID(String[] bookID) {
        this.bookID = bookID;
    }

    public String[] getOrderID() {
        return orderID;
    }

    public void setOrderID(String[] orderID) {
        this.orderID = orderID;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
