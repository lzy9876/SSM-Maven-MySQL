package cn.lzy.entity;

import java.io.Serializable;

/**
 * @ClassName ConsoleData
 * @Author:Liziy
 * @Date 2019/12/17 20:27
 * @Description: console页面统计数据实体类
 **/
public class ConsoleData implements Serializable {
    private static final long serialVersionUID = 19919785809144565L;

    //普通用户总数量
    private String userConuntRole2 ;
    //管理员总数量
    private  String userConuntRole1;
    //入库总快件数量
    private String expressConunt;
    //快件总待取数量
    private  String expressConunteStatus1;
    //普通用户日注册数量
    private String userConuntRole2Date;
    //管理员户日注册数量
    private String userConuntRole1Date;
    //快件日取件
    private  String expressConunteStatus2Data;
    //日新增快件总数量
    private  String expressConunteStatus1Data;

    public ConsoleData(String userConuntRole2, String userConuntRole1, String expressConunt, String expressConunteStatus1, String userConuntRole2Date, String userConuntRole1Date, String expressConunteStatus2Data, String expressConunteStatus1Data) {
        this.userConuntRole2 = userConuntRole2;
        this.userConuntRole1 = userConuntRole1;
        this.expressConunt = expressConunt;
        this.expressConunteStatus1 = expressConunteStatus1;
        this.userConuntRole2Date = userConuntRole2Date;
        this.userConuntRole1Date = userConuntRole1Date;
        this.expressConunteStatus2Data = expressConunteStatus2Data;
        this.expressConunteStatus1Data = expressConunteStatus1Data;
    }

    public ConsoleData() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserConuntRole2() {
        return userConuntRole2;
    }

    public String getUserConuntRole1() {
        return userConuntRole1;
    }

    public String getExpressConunt() {
        return expressConunt;
    }

    public String getExpressConunteStatus1() {
        return expressConunteStatus1;
    }

    public String getUserConuntRole2Date() {
        return userConuntRole2Date;
    }

    public String getUserConuntRole1Date() {
        return userConuntRole1Date;
    }

    public String getExpressConunteStatus2Data() {
        return expressConunteStatus2Data;
    }

    public String getExpressConunteStatus1Data() {
        return expressConunteStatus1Data;
    }

    public void setUserConuntRole2(String userConuntRole2) {
        this.userConuntRole2 = userConuntRole2;
    }

    public void setUserConuntRole1(String userConuntRole1) {
        this.userConuntRole1 = userConuntRole1;
    }

    public void setExpressConunt(String expressConunt) {
        this.expressConunt = expressConunt;
    }

    public void setExpressConunteStatus1(String expressConunteStatus1) {
        this.expressConunteStatus1 = expressConunteStatus1;
    }

    public void setUserConuntRole2Date(String userConuntRole2Date) {
        this.userConuntRole2Date = userConuntRole2Date;
    }

    public void setUserConuntRole1Date(String userConuntRole1Date) {
        this.userConuntRole1Date = userConuntRole1Date;
    }

    public void setExpressConunteStatus2Data(String expressConunteStatus2Data) {
        this.expressConunteStatus2Data = expressConunteStatus2Data;
    }

    public void setExpressConunteStatus1Data(String expressConunteStatus1Data) {
        this.expressConunteStatus1Data = expressConunteStatus1Data;
    }
}
