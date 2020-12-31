package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.SysData;
import com.yshyerp.vehicle.mapper.SysDataMapper;
import com.yshyerp.vehicle.service.SysDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDataServiceImpl implements SysDataService {

    @Autowired
    SysDataMapper sysDataMapper;

    @Override
    public String getSysDataId(SysData sysData, String idName) throws Exception{
        if("plan_no".equals(idName)) {
            int intPlanNo = Integer.parseInt(sysData.getPlanNo().trim());
            return String.format("%06d",++intPlanNo);
        }else if("job_no".equals(idName)) {
            int intJobNo = Integer.parseInt(sysData.getJobNo().trim());
            return String.format("%06d",++intJobNo);
        }else if("cdc_no".equals(idName)) {
            int intCdcNo = Integer.parseInt(sysData.getCdcNo().trim());
            return String.format("%06d",++intCdcNo);
        }
        return null;

    }


    public SysData querySysData() {
        return sysDataMapper.querySysData();
    }

    /**
     * 更新planNo
     * @param planNo
     * @return
     */
    @Override
    public int updSysDataIdToPlanNo(String planNo) {
        return sysDataMapper.updSysDataIdToPlanNo(planNo);
    }

    @Override
    public int updSysDataIdToJobNo(String jobNo) {
        return sysDataMapper.updSysDataIdToJobNo(jobNo);
    }

}
