package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.DrumTmp;
import com.yshyerp.vehicle.mapper.DrumTmpMapper;
import com.yshyerp.vehicle.service.DrumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrumServiceImpl implements DrumService {

    @Autowired
    DrumTmpMapper drumTmpMapper;

    /**
     * 根据委托书号查询桶装出货临时记录表
     * @param crrNo 委托书号
     * @return
     */
    public DrumTmp queryQuanDrumByCrrNo(String crrNo) {
        return drumTmpMapper.queryQuanDrumByCrrNo(crrNo);
    }

    /**
     * 根据提单号查询桶装出货临时记录表
     * @param doNo 提单号
     * @return
     */
    public DrumTmp queryQuanDrumByDoNo(String doNo) {
        return drumTmpMapper.queryQuanDrumByDoNo(doNo);
    }
}
