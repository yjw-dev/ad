package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 大提单
 */
@Data
public class Bdo {
    //id
    private int id;
    //删除标识
    private String d;
    //提单号
    private String doNo;
    //委托书号
    private String crrNo;
    //客户
    private String customer;
    //货品
    private String commodity;
    //罐号
    private String tank;
    //日期
    private Timestamp date;
    //数量
    private BigDecimal quantity;
    //结存
    private BigDecimal balance;
    //跟新日期
    private Timestamp vDate;
}
