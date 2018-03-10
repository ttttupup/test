package com.example.hugy.test36.account.model;

/**
 * 账户服务
 * Created by hugy on 2018/3/8.
 */

public interface IAccountManager {
    //注册
    void register();
    //登陆
    void login();
    //发送验证码
    void sendSMSCode();
    //校验验证码
    void checkSMSCode();
}
