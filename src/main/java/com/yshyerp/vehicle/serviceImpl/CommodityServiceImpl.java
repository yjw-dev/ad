package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.*;
import com.yshyerp.vehicle.service.CommodityService;
import com.yshyerp.vehicle.vo.TankCVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 货品业务类
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    //打印货品Mapper
    @Autowired
    TankCMapper tankCMapper;

    //货品Mapper
    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    TtdcTempMapper ttdcTempMapper;

    @Autowired
    DjobMapper djobMapper;

    @Autowired
    TankMapper tankMapper;

    /**
     * 根据罐号查询打印货品名称
     * @param tank 罐号
     * @return
     */
    @Override
    public List<TankCVO> queryTankCByTank(String tank) throws Exception {
        List<TankCVO> tankCVOList = new ArrayList<TankCVO>();
        //查询打印货品名称
        List<TankC> tankCList = tankCMapper.queryTankCListByTank(tank);
        //将查询结果转换成VO
        for(TankC tankC : tankCList) {
            TankCVO tankCVo = new TankCVO();
            tankCVo = (TankCVO) ToolUtil.doCastVo(tankC, tankCVo);
            tankCVo.setKey(tankCVo.getComm1());
            tankCVo.setLabel(tankCVo.getComm1());
            tankCVOList.add(tankCVo);
        }
        return tankCVOList;
    }

    /**
     * 查询货品
     * @param commodity 货品名称
     * @return
     */
    @Override
    public List<Commodity> queryCommodityByCommodity(String commodity) {
        return commodityMapper.queryCommodityByCommodity(commodity);
    }

    /**
     * 校验提货数量
     * @param tank 罐号
     * @param vehiV 容积
     * @param  vehiW 核定载重
     * @param  pageQuantity 提货数量
     * @param  tCustomer 罐主
     * @param  vehiW2 牵引重量
     * @param  vehiW3 挂车重量
     * @param  vehiW1 箱重
     * @param  status 流程状态（1提货计划 2入仓 3出仓）
     * @param vehicle 车牌号（出货时使用）
     * @param  netWeight 净重量（出货时使用）
     * @return
     */
    @Override
    public String checkQuantity(String tank, double vehiV, double vehiW, double pageQuantity, String tCustomer,
                                double vehiW2, double vehiW3, double vehiW1, String status, String vehicle, int netWeight) {
        TtdcTemp ttdcTemp = new TtdcTemp();
        if("1".equals(status) || "2".equals(status)) {
            ttdcTemp = ttdcTempMapper.queryQuanInByTank(tank);
        }else if("3".equals(status)) {
            Map map = new HashMap();
            map.put("vehicle", vehicle);
            map.put("tank", tank);
            ttdcTemp = ttdcTempMapper.queryQuanInByTank2(map);
        }
        //数量总和
        double ttdcTempQuanIn = 0.0d;
        //工作余额*每桶净重/1000
        double djobQuanD = 0.0d;
        //密度
        double tankDensity = 0.0d;
        if(null!=ttdcTemp.getCoun() && ttdcTemp.getCoun()!=0) {
            ttdcTempQuanIn = ttdcTemp.getQuanBulk().doubleValue();
        }
        Djob djob = djobMapper.queryDjobByTank(tank);
        if(null!=djob.getCoun() && 0!=djob.getQuanD()) {
            djobQuanD = djob.getQuanD();
        }

        Tank thisTank = tankMapper.queryTankInfoByTank(tank);
        double vw;
        int quan = 0;
        int q1 = 0;
        double limit = 0.0d;
        double lowest = 0.0d;
        double crrVol = 0.0;
        boolean crrValUse = false;
        double maxV1 = 0;
        double maxV2 = 0.0d;
        double maxV3 = 0.0d;
        double maxW = 0.0d;
        double minV2 = 0.0d;

        if (null != thisTank) {
            if (null != thisTank.getDensity() && thisTank.getDensity().doubleValue() > 0) {
                tankDensity = thisTank.getDensity().doubleValue();
            } else if (null != thisTank.getCommodity()) {
                List<Commodity> commodityList = commodityMapper.queryCommodityByCommodity(thisTank.getCommodity());
                if (null != commodityList && commodityList.size() > 0 && commodityList.get(0).getDensity().doubleValue() > 0) {
                    tankDensity = commodityList.get(0).getDensity().doubleValue();
                } else {
                    String dens15C2 = tankMapper.queryDensityByCrcTable(thisTank.getCommodity());
                    if (!StringUtils.isEmpty(dens15C2)) {
                        tankDensity = Double.valueOf(dens15C2.substring(0, 6));
                    }
                }
                if (tankDensity > 0.0) {
                    //更新最近一次密度数据
                    Map map = new HashMap();
                    map.put("tankDensity", tankDensity);
                    map.put("tank", tank);
                    commodityMapper.updTankDensity(map);
                }
            }
            quan = thisTank.getQuantity();
            q1 = thisTank.getQ1() / 1000;
            limit = thisTank.getLimitVol().doubleValue();
            //最低发油量
            lowest = thisTank.getLowestVol().doubleValue();
            crrVol = thisTank.getCrrVol().doubleValue();
            crrValUse = thisTank.getCrrVolUse();

            if ("I".equals(thisTank.getTank().substring(0, 1))) {
                maxV1 = 200 * tankDensity;
            } else {
                maxV1 = Double.valueOf(thisTank.getSafetyV()) * tankDensity;
            }
            //容积>0
            if (vehiV > 0.0) {
                maxV2 = vehiV * tankDensity * 0.95;
                maxV3 = vehiV * tankDensity * 0.97;
                minV2 = vehiV * tankDensity * 0.8;
                maxW = vehiW;
            } else {
                maxV2 = vehiW;
                maxV3 = vehiW;
            }
            if (thisTank.getCrc() && !thisTank.getCrc1() && pageQuantity > 0) {
                return "正在对储罐计量，只能进货不能出货。如需进货请将提货数量设为负数";
            }
            if (thisTank.getCoa() && pageQuantity > 0) {
                return "储罐没有COA证书，只能进货不能出货。如需进货请将提货数量设为负数";
            }
        }
        if(tankDensity>0) {
            if(pageQuantity<0 && "T".equals(tank.substring(0,1))) {
                if((quan/1000-ttdcTempQuanIn-pageQuantity)>maxV1) {
                    return "该槽车进货后TANK中货物量将可能超过安全体积，请立即通知操作工程师查证";
                }
            }
            if("2".equals(status)) {
                if("EXXON".equals("tCustomer")) {
                    //核定重量>=牵引重量+挂车重量
                    if(vehiW2>=vehiW+vehiW3) {
                        vw = vehiW;
                    }else {
                        //牵引重量-挂车重量
                        vw = vehiW2-vehiW3;
                    }
                    if(pageQuantity>vw-vehiW1) {
                        return "EXXON规定提货数量不得超过车辆核定载重数量-箱重";
                    }
                    //输入授权密码操作
                    if(pageQuantity<minV2 || pageQuantity>maxV2) {
                        return "EXXON规定提货数量控制在安全装货范围之内，如要放行，请输入授权密码：";
                    }
                }else {
                    if(pageQuantity>maxV3) {
                        return "装货数量可能超过该槽车的安全装货限制";
                    }else if(pageQuantity>maxV2) {
                        return "装货数量可能超过该槽车的安全装货警示";
                    }
                }
            }
        }else {
            return "系统没有该货品的密度资料，将影响安全装货判断，请及时通知账目员完善资料";
        }
        //if(pageQuantity>maxW) {
        if(pageQuantity>vehiW) {
            return "装货数量可能超过该槽车的核定载重，已超载";
        }

        if(!"I".equals(tank.substring(0,1))) {
            if("3".equals(status)) {
                pageQuantity = netWeight/1000;
            }
            double tempQ = quan/1000-ttdcTempQuanIn-djobQuanD-pageQuantity;
            if(lowest>=0) {
                if(q1!=0 &&tempQ<q1) {
                    return "提货后TANK中货物量将少于可出货数量";
                }else if(tempQ<lowest) {
                    return "提货后TANK中货物量将少于最低发货量";
                }
            }
            if(limit!=0 && tempQ<limit) {
                return "提货后TANK中货物量将少于限制发货量";
            }
            if(crrVol!=0 && crrValUse && tempQ<crrVol) {
                return "提货后TANK中货物量将少于海关控制发货量";
            }
        }
//        if("C1214".equals(thisTank.getCommodity())
//            && quan/1000-ttdcTempQuanIn-djobQuanD-pageQuantity<30) {
//            return "该货品要求储罐库存数量低于30吨时需多辆槽车同时出货，继续吗?";
//        }
        return null;
    }

    /**
     * EXXON提货限制，授权码校验
     * @param pw 授权密码
     * @return
     */
    @Override
    public boolean checkQuantityPw(String pw) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //当前日期是星期几
        long whichDay1 = cal.get(Calendar.DAY_OF_WEEK)-1;
        if(whichDay1==0) {
            whichDay1 = 7;
        }
        //当前月份的第一个英文字母
        String monthEng = Calendar.getInstance().getTime().toString().substring(4, 5);
        //当前日期
        String dd = Calendar.getInstance().getTime().toString().substring(8, 10);
        String tempPw = whichDay1 + monthEng + dd;
        if(tempPw.equals(pw)) {
            return true;
        }
        return false;
    }
}
