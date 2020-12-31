package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 委托书出货过程
 */
@Data
public class CarGoOut {

    //id
    private BigDecimal id;
    //删除标志
    private String d;
    //相应出入仓单号
    private String no;
    //委托书号
    private String crrNo;
    //提单号
    private String doNo;
    //车牌号
    private String vehicle;
    //货主
    private String customer;
    //货品
    private String commodity;
    //日期
    private Timestamp date2;
    //罐号
    private String tank;
    //前结存
    private BigDecimal balBf;
    //发生数
    private BigDecimal crrQty;
    //后结存
    private BigDecimal balance;
    //容积
    private BigDecimal volume;
    //密度
    private BigDecimal density;
    //备注
    private String remarks;
    //提单总量
    private String totalDoNo;
}
