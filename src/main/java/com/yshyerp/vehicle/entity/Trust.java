package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Trust implements Serializable {

    private Integer id;

    //删除标志
    private String d;

    //罐主
    private String tCustomer;

    //货主
    private String customer;

    //委托书号
    private String crrNo;

    private Timestamp date1;

    private String time1;

    //提货单位
    private String iCustomer;

    //出仓方式
    private String outWay;

    //罐号
    private String tank;

    //货品
    private String commodity;

    private String packing;

    //报关数量
    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal prtQty;

    //结 //结存MT
       private double balance;

    private Boolean trust;

    private String custNo;

    private String inwardNo;

    private String outwardNo;

    //通关日期
    private Timestamp date2;

    private String time2;

    private Timestamp prnDate;

    private String prnTime;

    //报关日期
    private Timestamp passDate;

    private String passTime;

    private Boolean done;

    private Boolean transfer;

    private String remarks;

    private Boolean btd;

    private BigDecimal chQty;

    private String tradeP;

    private Boolean tax;

    private BigDecimal drums;

    private BigDecimal dTare;

    private String newOld;

    private BigDecimal tare;

    private String tradeScms;

    private String authNo;

    private String authNo0;

    private BigDecimal check;

    private BigDecimal doBal;

    private BigDecimal zzs;

    private BigDecimal gs;

    private Boolean lock;


    private static final long serialVersionUID = 1L;
}
