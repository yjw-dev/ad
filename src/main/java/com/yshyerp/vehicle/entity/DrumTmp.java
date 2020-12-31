package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 桶装出货临时记录表
 */
@Data
public class DrumTmp {

    //数量
    private BigDecimal quanDrum;
    //数据条数
    private Integer coun;
}
