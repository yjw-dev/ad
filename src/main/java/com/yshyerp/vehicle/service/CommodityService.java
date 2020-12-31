package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Commodity;
import com.yshyerp.vehicle.vo.TankCVO;

import java.util.List;

/**
 * 货品业务类
 */
public interface CommodityService {

    /**
     * 根据罐号查询打印货品
     * @param tank 罐号
     * @return
     */
    public List<TankCVO> queryTankCByTank(String tank)throws Exception;

    /**
     * 查询货品
     * @param commodity 货品名称
     * @return
     */
    public List<Commodity> queryCommodityByCommodity(String commodity);

    /**
     * EXXON提货限制，授权码校验
     * @param pw 授权密码
     * @return
     */
    public boolean checkQuantityPw(String pw);

    /**
     * 校验提货数量
     * @param tank 罐号
     * @return
     */
    public String checkQuantity(String tank, double vehiV, double vehiW, double pageQuantity, String tCustomer,
                                double vehiW2, double vehiW3, double vehiW1, String status, String vehicle, int netWeight);
}
