package cn.lzy.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Express
 * @Author:Liziy
 * @Date 2019/12/2 20:51
 * @Description:
 **/
public class Express implements Serializable {

    private static final long serialVersionUID = -2743940685278234285L;
    //编号
    private int id;

    //快递单号
    private int eNum;

    //快递公司
    private String company;

    //收件人手机号码
    private String uPhone;

    //收件人姓名
    private String username;

    //入库时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date inTime;

    //快件状态
    private int eStatus;

    //取件时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date outTime;

    //快递员手机号
    private String cPhone;

    //取件码
    private int code;


    public Express() {
    }

    public int getId() {
        return id;
    }

    public int geteNum() {
        return eNum;
    }

    public String getCompany() {
        return company;
    }

    public String getuPhone() {
        return uPhone;
    }

    public String getUsername() {
        return username;
    }

    public Date getInTime() {
        return inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public int geteStatus() {
        return eStatus;
    }


    public String getcPhone() {
        return cPhone;
    }

    public int getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void seteNum(int eNum) {
        this.eNum = eNum;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void seteStatus(int eStatus) {
        this.eStatus = eStatus;
    }

    public Express(Date inTime, Date outTime) {
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Express{" +
                "id=" + id +
                ", eNum=" + eNum +
                ", company='" + company + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", username='" + username + '\'' +
                ", inTime=" + inTime +
                ", eStatus=" + eStatus +
                ", outTime=" + outTime +
                ", cPhone='" + cPhone + '\'' +
                ", code=" + code +
                '}';
    }



}
