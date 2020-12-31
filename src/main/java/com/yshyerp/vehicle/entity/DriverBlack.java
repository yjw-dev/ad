package com.yshyerp.vehicle.entity;

import lombok.Data;

/**
 * 司机黑名单
 */
@Data
public class DriverBlack {
    //身份证号
    private String idNo;
    //姓名
    private String name;
    //公司
    private String customer;
}
