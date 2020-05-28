package Core;

public interface ResultStatus {
    int NONE = 0;                       //系统未知结果

    int SIGN_IN_SUCCESS = 1;            //登录成功
    int SIGN_UP_SUCCESS = 2;            //注册成功
    int RECOVER_PASSWORD_SUCCESS = 3;   //找回密码成功
    int SIGN_OUT_SUCCESS = 4;           //登出成功

    int PASSWORD_ERROR = -1;            //登录，密码错误
    int USER_NOT_EXIST = -2;            //登录或找回密码，用户名不存在
    int USER_IS_EXIST = -3;             //注册，用户已存在
    int INFO_AGAINST_RULES = -4;        //注册，用户信息不符合规范
    int USER_PHONE_ERROR = -5;          //找回密码，用户手机号错误
    int ENCRYPTED_QUESTION_ERROR = -6;  //找回密码，密保问题错误
    int ENCRYPTED_ANSWER_ERROR = -7;    //找回密码，密保答案错误
    int DATA_NOT_SAVED = -8;            //登出，数据未保存
    int INCORRECT_WAY = -9;             //登出，非正常方式
}
