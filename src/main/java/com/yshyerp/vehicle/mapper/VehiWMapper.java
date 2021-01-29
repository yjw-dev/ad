package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 车辆
 */
@Mapper
@Repository
public interface VehiWMapper {

    /**
     * 根据车牌号查询槽车出仓临时记录表
     * @param vehicle
     * @return
     */
    public Integer getTtdcTempVehicle(String vehicle);

    /**
     * 根据车牌号和挂车号查询核定载重
     * @param map
     * @return
     */
    public VehiW getVehicleDetailByVehicleAndVehicle1(Map map);



      List<VehiWa>  getyaajw(String vehicle);

    /**
     * 根据车牌号查询准牵引重量
     * @param vehicle
     * @return
     */
    public VehiW2 getVehiW2ByVehicle(String vehicle);


    List<VehiW2> listvehiw2(String vehicle);

    /**
     * 根据挂车号查询挂车重量
     * @param vehicle1
     * @return
     */
    public VehiW3 getVehiW3ByVehicle1(String vehicle1);
    List<VehiW3> listvehiw3(String vehicle1);
    /**
     * 根据箱号查询容积、箱重
     * @param txtcNo
     */
    public CnoV getTxtcNoDetail(String txtcNo);
}
