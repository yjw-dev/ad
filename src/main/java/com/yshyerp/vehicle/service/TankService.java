package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Tank;
import com.yshyerp.vehicle.entity.TtdcTemp;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.TankInfoVO;

import java.util.List;

/**
 * 储罐业务类
 */
public interface TankService {

    /**
     * 查询所有储罐信息
     * @return
     * @throws Exception
     */
    public List<TankInfoVO> queryTankInfo() throws Exception;

    //校验储罐是否可进行入仓操作
    public String checkTank(String tank, double quantity);

    //校验储罐是否可进行出仓操作
    public String checkTank(String tank);

    /**
     * 根据委托书号查询储罐出货临时表
     * @param crrNo 委托书号
     * @return
     */
    public TtdcTemp queryQuanBulkByCrrNo(String crrNo);

    /**
     * 根据提单号查询储罐出货临时表
     * @param doNo 提单号
     * @return
     */
    public TtdcTemp queryQuanBulkByDoNo(String doNo);

    /**
     * 根据罐号查询储罐明细
     * @param tank
     * @return
     */
    public Tank queryTankInfoByTank(String tank);
}
