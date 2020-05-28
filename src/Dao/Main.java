package Dao;

import java.sql.ResultSet;
import java.util.Date;

/**
 * @author 李建强
 */
public class Main {
    public static void main(String[] args) {
/*      //检测添加用户方法的测试方法
      String[] string = new String[11];
        String user = "user";
        String id;
        for (int i = 0; i < 11; i++) {
            if (i == 6) {
                string[i] = "男";
            }
            else if (i==4||i==5||i==7)
            {
                string[i]="11";
            } else if (i == 10) {
                string[i]="111111111111111111111111111111111111111111111111111111111111111111111111";
            } else {
                string[i] = "string" + i;
            }
        }
        id = AccessOperation.addData(user, string);
        System.out.println(id);*/


/*        //测试添加账本的测试方法
        String[] data=new String[5];
        String book="book";
        String id;
        data[0]="7";
        data[1]="book1";
        data[2]="";
        data[3]="1231123";
        data[4]="123123";
        id=AccessOperation.addData(book,data);
        System.out.println(id);*/

/*
        //测试添加账单的测试方法
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
        id = AccessOperation.addData(order,data);
        System.out.println(id);
*/

/*        //检测删除用户的方法
        String table="user";
        String id="4";
        boolean R=false;
        R=AccessOperation.deleteData(table,id);
        System.out.println(R);*/


/*        //测试删除账本的方法
        String table="book";
        String id="1";
        boolean R=false;
        R=AccessOperation.deleteData(table,id);
        System.out.println(R);*/

/*        //测试删除账单单的方法
        String table="order";
        String id="3";
        boolean R=false;
        R=AccessOperation.deleteData(table,id);
        System.out.println(R);*/

/*        //测试 alterUserInfo方法1。
        String[] st=new String[5];
        String id = "5";
        String table="user";
        st[0]="保密";st[1]="0"; st[2]="0";
        Boolean b= AccessOperation.changeData(table,id,st);
        System.out.println(b);*/
        //2
/*        String[] st = new String[1];
        String id = "5";
        String table="user";
        st[0]="1111212312";
        Boolean b= AccessOperation.changeData(table,id,st);
        System.out.println(b);*/

/*        //测试changePasswordAndEncrypted方法和changePwdAndEncrypted方法
        String id="5";
        String[] strings=new String[1];
        strings[0]="123456";
        boolean b=false;
        b= AccessOperation.changePwdAndEncrypted(id, strings);
        System.out.println(b);*/

/*        //测试changeBookInfo方法
        String id="3";
        String table ="book";
        String[] strings=new String[3];
        strings[0]="000";
        strings[2]="111";
        boolean b=false;
        b = AccessOperation.changeData(table, id, strings);
        System.out.println(b);*/

/*        //测试changeOrderInfo方法
        String id = "5";
        String table ="order";
        String[] strings=new String[8];
        strings[0]="name";
        strings[4]="4";
        strings[5]="5";
        boolean b = AccessOperation.changeData(table, id, strings);
        System.out.println(b);*/

 /*       //测试loginAuthentications方法
        String[] strings=new String[2];
        strings[0]="1";
        strings[1]="123456";
        String table = "user";
        String ID=null;
        ID=AccessOperation.loginAuthentication(table,strings);
        System.out.println(ID);*/


/*        //测试queryData方法
        String table = "order";
        String id = "5";
        ResultSet resultSet = AccessOperation.quiry(table, id);
        System.out.println(resultSet.toString());*/

    }
}
