package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DjobVo {


 //客户
 private  String  customer;
 //小客户
 private  String  scustomer;
 //新旧
 private  String  n;
 //颜色
 private  String  color;
 //皮重
 private BigDecimal tare;
 //库存
 private Integer balance;
 //灌桶需求
 private  Integer drums;
}
