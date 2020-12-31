package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.Tank;
import com.yshyerp.vehicle.entity.TtdcTemp;
import com.yshyerp.vehicle.mapper.TankMapper;
import com.yshyerp.vehicle.mapper.TtdcTempMapper;
import com.yshyerp.vehicle.service.TankService;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.TankInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 储罐业务类
 */
@Service
public class TankServiceImpl implements TankService {

    @Autowired
    TankMapper tankMapper;

    @Autowired
    TtdcTempMapper ttdcTempMapper;

    /**
     * 查询所有储罐信息
     * @return
     */
    @Override
    public List<TankInfoVO> queryTankInfo() throws Exception {
        List<TankInfoVO> tankInfoList = new ArrayList<TankInfoVO>();
        //查询所有储罐信息
        List<Tank> tankList = tankMapper.queryTankInfo();
        //将查询结果转换成VO
        for (Tank tank : tankList) {
            TankInfoVO tankInfoVo = new TankInfoVO();
            tankInfoVo = (TankInfoVO) ToolUtil.doCastVo(tank, tankInfoVo);
            tankInfoList.add(tankInfoVo);
        }
        return tankInfoList;
    }

    /**
     * 校验储罐是否可用
     * @param tank 罐号
     * @param quantity 提货数量
     * @return
     */
    public String checkTank(String tank, double quantity){
        Tank thisTank = tankMapper.queryTankInfoByTank(tank);
        if(thisTank.getOil() || thisTank.getOilO()) {
            //该货品不能从此程序入仓
            return ConstantUtil.MESSAGE_TANK_MSG1;
            //return Response.success(ConstantUtil.MESSAGE_TANK_MSG1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TANK_CODE0);
        }else if(thisTank.getCrc() && thisTank.getCrc1()) {
            //正在对储罐计量，不能出货
            return ConstantUtil.MESSAGE_TANK_MSG2;
            //return Response.success(ConstantUtil.MESSAGE_TANK_MSG2, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TANK_CODE0);
        }else if(thisTank.getCrc() && !thisTank.getCrc1() && quantity>0) {
            return ConstantUtil.MESSAGE_TANK_MSG3;
            //正在对储罐计量，只能进货不能出货。如需进货请将提货数量设为负数。
            //return Response.success(ConstantUtil.MESSAGE_TANK_MSG3, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TANK_CODE0);
        }else if(thisTank.getCoa() && quantity>0) {
            return ConstantUtil.MESSAGE_TANK_MSG4;
            //储罐没有COA证书，只能进货不能出货。如需进货请将提货数量设为负数。
            //return Response.success(ConstantUtil.MESSAGE_TANK_MSG4, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TANK_CODE0);
        }
        return ConstantUtil.CHECK_SUCCESS;
        //return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 出仓时储罐校验
     * @param tank
     * @return
     */
    public String checkTank(String tank){
        Tank thisTank = tankMapper.queryTankInfoByTank(tank);
        if(thisTank.getOil() || thisTank.getOilO()) {
            //该货品不能从此程序入仓
            return ConstantUtil.MESSAGE_TANK_MSG1;
        }
        return ConstantUtil.CHECK_SUCCESS;
    }

    /**
     * 根据委托书号查询储罐出货临时表
     * @param crrNo 委托书号
     * @return
     */
    public TtdcTemp queryQuanBulkByCrrNo(String crrNo) {
        return ttdcTempMapper.queryQuanBulkByCrrNo(crrNo);
    }

    /**
     * 根据提单号查询储罐出货临时表
     * @param doNo 提单号
     * @return
     */
    public TtdcTemp queryQuanBulkByDoNo(String doNo) {
        return ttdcTempMapper.queryQuanBulkByDoNo(doNo);
    }

    /**
     * 根据罐号查询储罐明细
     * @param tank
     * @return
     */
    public Tank queryTankInfoByTank(String tank) {
        return tankMapper.queryTankInfoByTank(tank);
    }

}
