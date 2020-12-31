package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WeighbridgeVO {
    //重量
    private BigDecimal weight;
    //地磅状态
    private String st;
}
