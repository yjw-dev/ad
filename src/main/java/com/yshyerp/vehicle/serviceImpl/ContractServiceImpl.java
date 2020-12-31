package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.Contract;
import com.yshyerp.vehicle.mapper.ContractMapper;
import com.yshyerp.vehicle.service.ContractService;
import com.yshyerp.vehicle.vo.ContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 合同业务类
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractMapper contractMapper;

    /**
     * 根据罐主名称查询合同信息
     * @param tankCustomer 罐主名称
     * @param tank 罐号
     * @return
     */
    @Override
    public List<ContractVO> queryContractListByCustomer(String tankCustomer, String tank) throws Exception{
        List<ContractVO> contractVoList = new ArrayList<ContractVO>();
        //查询合同数据
        List<Contract> contractList = contractMapper.queryContractListByCustomer(tankCustomer);
        //将查询结果转换成VO
        for(Contract contract : contractList) {
            ContractVO contractVo = new ContractVO();
            contractVo = (ContractVO) ToolUtil.doCastVo(contract, contractVo);
            contractVo.setKey(contractVo.getContractI());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            contractVo.setLable(contractVo.getContractI() + "  " + tank + "  " + simpleDateFormat.format(contractVo.getEnDate()));
            contractVoList.add(contractVo);
        }
        return contractVoList;
    }
}
