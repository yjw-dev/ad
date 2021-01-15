package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Contract;
import com.yshyerp.vehicle.mapper.ContractMapper;
import com.yshyerp.vehicle.service.Contract1Service;
import com.yshyerp.vehicle.vo.ContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Contract1ServiceImpl implements Contract1Service {

    @Autowired
    ContractMapper contractMapper;
    /**
     * 根据罐主名称查询合同信息
     * @param customer 罐主名称
     * @return
     */
    public List<Contract> queryContractListByCustomer(String customer){
        List<Contract> contractList=contractMapper.queryContractListByCustomer(customer);
        if (contractList!=null){
            return contractList;
        }
            return null;
    }

}
