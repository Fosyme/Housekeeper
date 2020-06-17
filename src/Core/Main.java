package Core;

public class Main {
    public static void main(String[] args) {
        User user = new User("1");
        BookInterface bookInterface = new BookInterface(user);
        bookInterface.deleteBook(2);
    }
}
