package com.yshyerp.vehicle.commons;

import org.springframework.stereotype.Component;

@Component
public class ConstantUtil {

    //操作成功提示
    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MESSAGE = "操作成功";

    //校验成功
    public static final String CHECK_SUCCESS = "SUCCESS";

    //客户端请求异常
    public static final String CLIENT_ERROR_CODE = "400";

    //服务端操作失败提示
    public static final String SERVICE_ERROR_CODE = "500";
    public static final String SERVICE_ERROR_MESSAGE = "系统异常，请与管理员联系";

    //请求参数为空
    public static final String REQUEST_NULL_CODE = "ERR_5001";
    public static final String REQUEST_NULL_MESSAGE = "请求参数为空！";
    public static final String REQUEST_NULL_MESSAGE2 = "司机身份证号不能为空";
    public static final String REQUEST_NULL_MESSAGE3 = "车牌号码不能为空";
    public static final String REQUEST_NULL_MESSAGE4 = "货主不能为空";
    public static final String REQUEST_NULL_MESSAGE5 = "提单号不能为空";
    public static final String REQUEST_NULL_MESSAGE6 = "委托书号不能为空";
    public static final String REQUEST_NULL_MESSAGE7 = "EXXON客户提货请输入箱重";
    //public static final String REQUEST_NULL_MESSAGE8 = "罐号不能为空";
    //public static final String REQUEST_NULL_MESSAGE9 = "货品名称不能为空";
    public static final String REQUEST_NULL_MESSAGE10 = "授权密码为空！";
    public static final String REQUEST_NULL_MESSAGE11 = "罐主不能为空";
    public static final String REQUEST_NULL_MESSAGE12 = "合同号不能为空";
    public static final String REQUEST_NULL_MESSAGE13 = "挂车号不能为空";
    public static final String REQUEST_NULL_MESSAGE14 = "地磅称重异常";
    public static final String REQUEST_NULL_MESSAGE15 = "操作员1不能为空";
    public static final String REQUEST_NULL_MESSAGE16 = "封条号不能为空";
    public static final String REQUEST_NULL_MESSAGE17 = "校验码不能为空";

    //do转换异常
    public static final String DOVO_ERROR_CODE = "ERR_5002";
    //保存提货计划失败
    public static final String SAVEVEHIPLAN_ERROR_CODE = "ERR_5003";
    //保存入仓信息失败
    public static final String SAVETTDCTEMP_ERROR_CODE = "ERR_5006";
    //保存出仓信息失败
    public static final String SAVETTDC_ERROR_CODE = "ERR_5007";



    //储罐数据异常
    public static final String MESSAGE_TANK_CODE0 = "MSG_T000";
    public static final String MESSAGE_TANK_MSG1 = "该货品不能从此程序入仓";
    public static final String MESSAGE_TANK_MSG2 = "正在对储罐计量，不能出货";
    public static final String MESSAGE_TANK_MSG3 = "正在对储罐计量，只能进货不能出货。如需进货请将提货数量设为负数";
    public static final String MESSAGE_TANK_MSG4 = "储罐没有COA证书，只能进货不能出货。如需进货请将提货数量设为负数";

    public static final String MESSAGE_QUANTITY_CODE1 = "MSG_N001";
    public static final String MESSAGE_QUANTITY_MSG1 = "提货数量不能为零,请重新输入";

    public static final String MESSAGE_VEHICLE_CODE1 = "MSG_V001";
    public static final String MESSAGE_VEHICLE_MSG1 = "车牌号已录入提货计划";

    public static final String MESSAGE_DONO_CODE1 = "MSG_D001";
    public static final String MESSAGE_DONO_MSG1 = "传真提单没有选择提单传真号";

    public static final String MESSAGE_CUSTOMER_CODE1 = "MSG_C001";
    public static final String MESSAGE_CUSTOMER_MSG1 = "无此货主，请查证";
    public static final String MESSAGE_CUSTOMER_CODE2 = "MSG_C002";

    //备注数据异常
    public static final String MESSAGE_REMARKS_CODE0 = "MSG_R000";
    //委托书数据异常
    public static final String MESSAGE_CRRNO_CODE0 = "MSG_W000";
    //提单号数据异常
    public static final String MESSAGE_DONO_CODE0 = "MSG_D000";
    //提货数量数据异常
    public static final String MESSAGE_QUANTITY_CODE0 = "MSG_N000";
    //货品数据异常
    public static final String MESSAGE_COMMODITY_CODE0 = "MSG_M000";

    //货品校验
    public static final String INPUT_COMMODITY_CODE0 = "INPUT_M000";
    public static final String INPUT_COMMODITY_MSG0 = "提醒：本车辆装载危险货品，稍后请不要忘记扫描证件。请输入运输公司名称（全称），验证危险品运输企业：";
    //该车辆xxx装载危险货品，还没有进行证件扫描，是否继续？
    public static final String INPUT_COMMODITY_CODE1 = "INPUT_M001";
    //EXXON规定提货数量控制在安全装货范围之内，如要放行，请输入授权密码：
    public static final String INPUT_QUANTITY_CODE0 = "INPUT_Q000";

    public static final String MESSAGE_COMMODITY_CODE1 = "MSG_M001";
    public static final String MESSAGE_COMMODITY_MSG1 = "该企业未在允许的危险品运输企业中";
    public static final String MESSAGE_COMMODITY_MSG0 = "无此货品";



    //合同信息查询异常
    public static final String MESSAGE_CONTRACT_CODE1 = "MSG_H001";
    public static final String MESSAGE_CONTRACT_MSG1 = "未查询到与该储罐关联的合同，请与管理员联系";

    public static final String WEIGHBRIDGE_ERROR_CODE = "ERR_5004";
    public static final String WEIGHBRIDGE_ERROR_MESSAGE = "地磅称重异常，请与管理员联系";
    //仓内地磅与门口地磅重量相差50Kg
    public static final String WEIGHBRIDGE_MESSAGE_CODE = "MSG_E000";

    public static final String LCSTATION_ERROR_CODE = "ERR_5005";
    public static final String LCSTATION_ERROR_MESSAGE = "罐记录中灌车台安排出错, 请查证";
    //1号地磅IP
    public static final String WB1 = "10.208.2.105";
    //2号地磅IP
    public static final String WB2 = "10.208.2.106";

    public static final String INPUT_TTDCTEMP_CODE0 = "INPUT_T000";
    public static final String INPUT_TTDCTEMP_MSG0 = "该车辆没有完成正常条码扫描流程，放行吗？";

    public static final String MESSAGE_TTDCTEMP_CODE0 = "MSG_C000";
    public static final String MESSAGE_TTDCTEMP_MSG0 = "该记录入仓时未在此地磅上称重，请查证!";

    public static final String MESSAGE_TTDCTEMP_CODE1 = "MSG_C001";
    public static final String MESSAGE_TTDCTEMP_MSG1 = "校验码输入错误，请检查车辆是否选择错误!";













}
