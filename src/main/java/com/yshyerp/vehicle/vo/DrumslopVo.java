package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DrumslopVo {

    //    TANK  罐号
     private String tank;
    //    C_CUSTOMER  客户名称
    private String CCustomer;
    // COMMODITY  货品名称
    private String commodity;
    //NEW  新旧
    private String n;
    //COLOR         颜色
    private String color;
    //TARE  皮重
    private BigDecimal tare;
    //BALANCE  桶数
    private Integer balance;
    //QUANTITY  总重量
    private Integer quantity;
//  DATE  更新日期,
    private Date date;
//(cover,'億升','污水厂')
    private Boolean cover;
   // 存放地,
    private String code1;
    private String code2;
    //type 废油类型
    private String type;



}
