package Dao;

import java.sql.ResultSet;

/**
 * @author 李建强
 */

/**访问操作类*/
public class AccessOperation {

    /**
    *添加数据, 返回添加的id
     **/
    public static String addData(String table, String[] data)
    {   String string=null;
        final String user="user",book="book",order="order";
        //添加用户
        if ("user".equals(table))
        {
            string= MethodOfOperation.addUserMethod(table,data);
        }
        //添加账本
        else if ("book".equals(table)) {
            string= MethodOfOperation.addBookMethod(table,data);
        }
        //添加订单
        else if ("order".equals(table)) {
            string= MethodOfOperation.addOrderMethod(table,data);
        }
        return string;
    }
    /**
     * 删除数据, 返回删除结果
     * */
    public static boolean deleteData(String table, String id) {
        boolean returnValue=false;
        returnValue = MethodOfOperation.delete(table, id);
        return returnValue;
    }

    /**
     * 修改数据, 返回修改结果
     * */
    public static boolean changeData(String table, String id, String[] newData) {
        boolean returnValue=false;
        if ("user".equals(table))
        {
            returnValue= MethodOfOperation.changeUserInfo(id,newData);
        }
        if("book".equals(table))
        {
            returnValue = MethodOfOperation.changeBookInfo(id,newData);
        }
        if ("order".equals(table)) {
            returnValue=MethodOfOperation.changeOrderInfo(id,newData);
        }
        return returnValue;
    }

    /**
     * 修改用户密码或者密保，返回结果
     * */
    public static boolean changePwdAndEncrypted(String id, String[] newData){
        boolean returnValue=false;
        returnValue=MethodOfOperation.changePasswordAndEncrypted(id,newData);
        return returnValue;
    }

    /**
     * 查询数据, 返回数据集
     * */
    public static ResultSet quiry(String table, String id) {
        ResultSet resultSet=null;
        resultSet=MethodOfOperation.queryData(table,id);
        return resultSet;
    }

    /**
     * 用户身份确认, 返回ID
     * */
    public static String loginAuthentication(String table, String[] data) {
        String ID=null;
        ID=MethodOfOperation.loginAuthentications(table,data);
        return ID;
    }
}
