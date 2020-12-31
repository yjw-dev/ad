package com.yshyerp.vehicle.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 提货计划vehi_plan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TankListVO {
    //后端生成
    private String planNo;
    //车牌号
    private String txtVehicle;
    //挂车号
    private String txtVehicle1;
    //核定载重
    private double txtVehiW;
    //容积
    private double vehiV;
    //箱重
    private double vehiW1;
    //准牵引重量
    private double txtVehiW2;
    //挂车重量
    private double txtVehiW3;
    //箱号
    private String boxNo;
    //罐号
    private String tank;
    //罐主
    private String tankCustomer;
    //货主
    private String customer;
    //委托书号
    private String crrNo;
    //提单号
    private String doNo;
    //正本提单（1选中/0未选中）
    private String original;
    //大提单（1选中/0未选中）
    private String bdo;
    //货品名称
    private String commodity;
    //打印名称
    private String comm1;
    //预计到达日期
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date estimateDate;
    //有效天数
    private int effectiveDays;
    //送货地点
    private String delivered;
    //提货数量（公斤）
    private double quantity;
    //司机身份证
    private String idno;
    //提单传真号
    private String faxno;
    //备注
    private String remarks;
    //“TTDC”
    private String planType;
    //合同号
    private String contractI;
    //合同号隐藏字段
    private int billid;
}
