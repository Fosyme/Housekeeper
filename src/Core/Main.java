package Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        User user = new User("1");
        HashMap<String, ArrayList<String>> map;
        ArrayList<String>[] list;
        map = user.getBooksID();
        list = user.getOrdersID();

        System.out.println(list[0]);

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                System.out.println(list[i].get(j));
            }
        }
    }
}
