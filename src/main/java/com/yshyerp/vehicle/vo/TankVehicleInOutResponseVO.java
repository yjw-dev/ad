package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class TankVehicleInOutResponseVO {
    //车牌号
    private String vehicle;
    //挂车号
    private String vehicle1;
    //预计入仓日期
    private Timestamp date0;
    //罐主
    private String tankCustomer;
    //货主
    private String customer;
    //货品名称
    private String commodity;
    //委托书号
    private String crrNo;
    //提单号
    private String doNo;
    //罐号
    private String tank;
    //流程状态1：提货计划 2：已入仓 3出仓
    private String tempStatus;
    //身份证号
    private String idNo;
    //实际入仓日期
    private Timestamp date1;
    //实际入仓时间
    private String time1;
    //车台号
    private String station;
    private String planNo;
}
