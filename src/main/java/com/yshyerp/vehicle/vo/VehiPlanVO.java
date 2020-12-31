package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 *
 */
@Data
public class VehiPlanVO {
    //车牌号
    private String vehicle;
    //储罐号
    private String tank;
    //罐主
    private String tankCustomer;
    //货主
    private String customer;
    //货品名称
    private String commodity;
    //入仓状态：0计划入仓 1已入仓
    private String tempStatus;
    //预计提货日期start
    private Timestamp dDate;
    //委托书号
    private String crrNo;
    //提单号
    private String doNo;

    private String planNo;


}
