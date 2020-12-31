package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.DrumTmp;

/**
 * 桶装货业务类
 */
public interface DrumService {

    public DrumTmp queryQuanDrumByCrrNo(String crrNo);

    public DrumTmp queryQuanDrumByDoNo(String doNo);



}
