package com.yshyerp.vehicle.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class WarehousingVO {
    //提货计划id
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
    //实际入仓时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date warehousingDate;
    //操作员1(出仓时录入装车操作员)
    private String operator1;
    //操作员2(出仓时录入装车操作员)
    private String operator2;
    //第一次称重重量
    private Integer weight1;
    //第二次称重重量
    private Integer weight2;
    //净重
    private Integer netWeight;
    //校验码
    private String checkCode;
    //摄像头截图（入仓截图）
    private MultipartFile cameraFile1;
    //入仓Id
    private String jobNo;
    //装车台
    private String station;
    //封条号
    private String sealNo;
    //入仓时间(用户手输)
    private String timeR;

    private Timestamp date0;

    private String time0;
    //地磅号，根据ip判断
    private String wb;
    //操作完成状态（通过扫描枪更新）
    private String status;
    //入仓日期
    private Timestamp date1;
    //入仓时间
    private String time1;

    //仓内地磅重量（装车台地磅）
    private Integer eeWeight;
    //出仓Id
    private String cdcNo;
}
