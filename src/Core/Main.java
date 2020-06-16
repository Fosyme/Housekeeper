package Core;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        User user = new User("1");
        ArrayList<ArrayList<Order>> orders = user.getOrders();
        orders.forEach(orders1 -> orders1.forEach(System.out::println));
    }
}
