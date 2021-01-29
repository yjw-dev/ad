package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.VehiWMapper;
import com.yshyerp.vehicle.service.VehicleService;
import com.yshyerp.vehicle.vo.TxtcVO;
import com.yshyerp.vehicle.vo.VehicleNoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆信息
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehiWMapper vehiWMapper;

    /**
     * 根据车牌号查询槽车出仓临时记录表
     * @param vehicle 车牌号
     * @return
     */
    public Boolean checkVehicleIsNull(String vehicle) {
        Integer vehicleNumber = vehiWMapper.getTtdcTempVehicle(vehicle);
        //数据存在
        if(vehicleNumber>0){
            return false;
        }
        return true;
    }

    @Override
    public List<VehiWa> getyaajw(String vehicle) {
        List<VehiWa> list=vehiWMapper.getyaajw(vehicle);
        return list;
    }

    /**
     * 根据车牌号、挂车号查询车辆信息
     * @param vo
     * @return
     */
    @Override
    public VehicleNoVO getVehicleDetailByVehicleAndVehicle1(VehicleNoVO vo) {
        VehicleNoVO responseVo = new VehicleNoVO();
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        //车牌号
        String vehicle = vo.getTxtVehicle();
        //挂车号
        String vehicle1= vo.getTxtVehicle1();
        queryMap.put("vehicle",vehicle);
        queryMap.put("vehicle1",vehicle1);
        //查询核定载重
        VehiW vehiW = vehiWMapper.getVehicleDetailByVehicleAndVehicle1(queryMap);
        //查询准牵引重量
        VehiW2 vehiW2 = vehiWMapper.getVehiW2ByVehicle(vehicle);
        //查询挂车重量
        VehiW3 vehiW3 = vehiWMapper.getVehiW3ByVehicle1(vehicle1);
        //将查询结果映射到VO重
        responseVo.setTxtVehicle(vehicle);
        responseVo.setTxtVehicle1(vehicle1);
        if(null!=vehiW) {
            responseVo.setTxtVehiW(vehiW.getVehiW());
        }
        if(null!=vehiW2) {
            responseVo.setTxtVehiW2(vehiW2.getVehiW2());
        }
        if(null!=vehiW3) {
            responseVo.setTxtVehiW3(vehiW3.getVehiW3());
        }
        return responseVo;
    }

    /**
     * 根据箱号查询容积、箱重
     * @param txtcNo 箱号
     * @return
     */
    @Override
    public TxtcVO getTxtcNoDetail(String txtcNo) {
        TxtcVO responseVo = new TxtcVO();
        CnoV cnoV = vehiWMapper.getTxtcNoDetail(txtcNo);
        if(null!=cnoV) {
            responseVo.setCNo(cnoV.getCNo());
            responseVo.setVehiV(cnoV.getVehiV());
            responseVo.setVehiW(cnoV.getVehiW());
        }
        return responseVo;
    }
}
