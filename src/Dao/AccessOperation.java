package Dao;

import java.sql.ResultSet;

public class AccessOperation {
    //添加数据, 返回添加的id
    public static String addData(String table, String... data) {

        return "";
    }

    //删除数据, 返回删除结果
    public static boolean deleteData(String table, String id) {

        return false;
    }

    //修改数据, 返回修改结果
    public static boolean alterData(String table, String id, String... newData) {

        return false;
    }

    //查询数据, 返回数据集
    public static ResultSet queryData(String table, String id) {

        return null;
    }

    //用户身份确认, 返回ID
    public static String confirmData(String table, String... data) {

        return "";
    }
}
