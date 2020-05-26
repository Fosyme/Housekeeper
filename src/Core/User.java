package Core;

public class User {
    private String userId;
    private String[] bookId;
    private String[] orderId;
    private UserLogin userLogin;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String[] getBookId() {
        return bookId;
    }

    public void setBookId(String[] bookId) {
        this.bookId = bookId;
    }

    public String[] getOrderId() {
        return orderId;
    }

    public void setOrderId(String[] orderId) {
        this.orderId = orderId;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
