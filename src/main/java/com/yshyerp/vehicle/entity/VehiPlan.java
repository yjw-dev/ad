package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class VehiPlan {

    //id
    private Integer id;
    //删除标志
    private String d;
    private String planType;
    private String planNo;
    //合同id
    private Integer billid;
    //合同号
    private String contractI;
    //车牌号
    private String vehicle;
    //挂车号
    private String vehicle1;
    //容积
    private BigDecimal vehiV;
    //核定载重
    private BigDecimal vehiW;
    //箱重
    private BigDecimal vehiW1;
    //准牵引重量
    private BigDecimal vehiW2;
    //挂车重量
    private BigDecimal vehiW3;
    //箱号
    private String boxNo;
    //司机身份证
    private String idNo;
    //系统日期
    private Timestamp rDate;
    //预计入仓日期
    private Timestamp dDate;
    //有效天数
    private BigDecimal vDate;
    //正本提单（1选中/0未选中）
    private Boolean original;
    //大提单（1选中/0未选中）
    private Boolean bdo;
    //传真号
    private String faxNo;
    //罐主
    private String tankCustomer;
    //货主
    private String customer;
    //小客户
    private String smallCustomer;
    //货品名称
    private String commodity;
    //货品打印名称
    private String commodity1;
    //罐号
    private String tank;
    //委托书号
    private String crrNo;
    //提单号
    private String doNo;
    //提货数量
    private BigDecimal quantity;
    //备注
    private String remarks;
    //送货地点
    private String delivered;
    private String tno1;
    private String no1;
    private BigDecimal drums;
    private BigDecimal packing;
    private BigDecimal netweight;
    private String oldNew;
    private String color;
    private BigDecimal tare;
    private Boolean cover;
    private String batch;
    private String come;
    private Boolean slop;
    private String type;
    private String status;
    private String code;
    private Boolean pBar;
    private String state;
    //入仓单号
    private String jobNo;
    //入仓状态：0计划入仓 1已入仓
    private String tempStatus;
    private String cNo;





















}
