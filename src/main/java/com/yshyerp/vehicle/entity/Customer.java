package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 客户
 */
@Data
public class Customer {

    private String code;

    //客户简称
    private String customer;
    //客户全面
    private String fullName;
    //中文名称
    private String cName;
    //地址
    private String address;
    //邮编
    private String postCode;
    //区号
    private String areaCode;
    //电话1
    private String telePhone1;
    //电话2
    private String telePhone2;
    //传真
    private String faxno;
    //联系人
    private String contact;

    private String taxno;
    //删除标志
    private String d;

    private BigDecimal drumLock;

    private BigDecimal order1;


}
