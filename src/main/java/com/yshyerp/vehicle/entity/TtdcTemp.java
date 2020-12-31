package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 槽车出仓临时记录表
 */
@Data
public class TtdcTemp {

    //总数量
    private BigDecimal quanBulk;
    //记录条数
    private Integer coun;
    //工作单号
    private String jobNo;
    //提单号
    private String doNo;

    private String cdcNo;
    private String ttdcNo;
    private String d;
    private String authNo;
    private String planNo;
    private String vehicle;
    private String vehicle1;
    private BigDecimal vehiV;
    private BigDecimal vehiW;
    private BigDecimal vehiW1;
    private BigDecimal vehiW2;
    private BigDecimal vehiW3;
    private String boxNo;
    private String timeR;
    private Timestamp date0;
    private String time0;
    private Timestamp date1;
    private String time1;
    private String tankCustomer;
    private String customer;
    private Integer billid;
    private String crrNo;
    private String fixNo;
    private Boolean bdo;
    private String sendNo;
    private String commodity;
    private String commodity1;
    private BigDecimal weight1;
    private BigDecimal weight2;
    private Timestamp date2;
    private String time2;
    private BigDecimal netweight;
    private BigDecimal density;
    private BigDecimal volume;
    private String delivered;
    private BigDecimal quantity;
    private String sealNo;
    private String tank;
    private String station;
    private String wb;
    private String idNo;
    private Boolean original;
    private Boolean isin;
    private String remarks;
    private Boolean send1;
    private String totalDoNo;
    private String remarks1;
    private BigDecimal ccQuan;
    private String ccStation;
    private String ccGh;
    private Timestamp ccTime;
    private String status;
    private BigDecimal ssWeight;
    private Timestamp ssTime;
    private BigDecimal eeWeight;
    private Timestamp eeTime;

    private String tempStatus;
}
