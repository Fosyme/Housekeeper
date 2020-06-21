package Dao;

import java.sql.ResultSet;

/**
 * @author 李建强
 */
public class Main {
    public static void main(String[] args) {
/*      //检测UserLoginOperation.regAccount方法的测试方法
      String[] string = new String[10];
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                string[i] = "男";
            }
            else if (i==6)
            {
                string[i]="12";
            } else if (i == 9) {
                string[i]="111111111111111111111111111111111111111111111111111111111111111111111111";
            } else {
                string[i] = "1234";
            }
        }
        Boolean s= UserOperation.regAccount(string);
        System.out.println(s);*/

/*        //测试 UserLoginOperation的userEncryptedVerify方法 验证用户密保是否正确
        String name="123";
        String q="123";
        String a="123";
        String id=null;
        id=UserOperation.userEncryptedVerify(name,q,a);
        System.out.println(id);*/

/*        //UserLoginOperation 的checkUserExist方法  验证用户名是否存在
        boolean exist=false;
        exist=UserOperation.checkUserExist("13");
        System.out.println(exist);*/

/*         //测试changeUserPassWord方法
        String id="4";
        String[] strings=new String[1];
        boolean b= UserOperation.changeUserPassWord(id,"123" );
        System.out.println(b);*/

/*        //userLoginOperation类changeEncrypted方法的测试方法  修改密保
        boolean b= UserOperation.changeEncrypted("0","456","456");
        System.out.println(b);*/

/*
        //测试UserLoginOperation.changeUserInfo方法1。  修改用户信息
        String[] st=new String[5];
        String id = "1";
        st[0]="保密";st[1]="0"; st[2]="0";
        Boolean b= UserOperation.changeUserInfo(id,st);
        System.out.println(b);
*/

/*        //测试UserOperation.setUserLastTime   ，设置用户最后登录时间
        String id="1";
        String time="2020-06-100000000000000000000000";
        Boolean b = UserOperation.setUserLastTime(id, time);
        System.out.println(b);*/

 /*      //测试query方法
        String id = "5";
        ResultSet resultSet = BookOperation.queryBookMsg(id);
        System.out.println(resultSet.toString());
*/

/*
        //测试添加账本的测试方法
        String[] data=new String[5];
        String id;
        data[0]="123";
        data[1]="book2";
        data[2]="";
        data[3]="1231123";
        data[4]="123123";
        id=BookOperation.addBook(data);
        System.out.println(id);
*/


/*        //测试添加账单的测试方法
        String[] data =new String[9];
        String order = "order";
        String id;
        data[0]="1";
        data[1] = "ordername";
        data[2] = "221.22";
        data[3]="现金";
        data[4]="支出";
        data[5] = "123123";
        data[6]="klk";
        data[7]="jljl";
        data[8]="11111111111111";
        id = OrderOperation.addOrder(data);
        System.out.println(id);*/

/*                //测试changeBookInfo方法
        String id="2";
        String table ="book";
        String[] strings=new String[3];
        strings[0]="000";
        strings[2]="111";
        boolean b=false;
        b = BookOperation.changeBookInfo(id, strings);
        System.out.println(b);*/

/*                //测试changeOrderInfo方法
        String id = "3";
        String table ="order";
        String[] strings=new String[8];
        strings[0]="name";
        strings[1]="11.0";
        strings[4]="4";
        strings[5]="5";
        boolean b = OrderOperation.changeOrderInfo(id, strings);
        System.out.println(b);*/

/*
        //检测删除用户的方法
        String table="user";
        String id="4";
        boolean R =UserOperation.deleteUser(id);
        System.out.println(R);
*/

/*        //测试删除账本的方法
        String id="1";
        Boolean R=BookOperation.deleteBook(id);
        System.out.println(R);*/

/*        //测试删除账单单的方法
        String table="order";
        String id="2";
        boolean R=false;
        R=OrderOperation.deleteOrder(id);
        System.out.println(R);*/

/*        //查询密保问题
        String un="1";
        String question=UserOperation.queryEncryptedQuestion(un);
        System.out.println(question);*/

/*        //验证checkBookExist方法
        Boolean b=false;
        b=BookOperation.checkBookExist("book");
        System.out.println(b);*/

/*        //验证queryTimeInterval
        ResultSet resultSet= OrderOperation.queryTimeInterval("2", "1", "1000");
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
