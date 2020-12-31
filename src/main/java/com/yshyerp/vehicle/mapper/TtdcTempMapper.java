package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.TtdcTemp;
import com.yshyerp.vehicle.vo.WarehousingVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 槽车出货临时记录表
 */
@Mapper
@Repository
public interface TtdcTempMapper {

    //根据委托书号查询出货数量
    public TtdcTemp queryQuanBulkByCrrNo(String crrNo);

    //根据提单号查询出货数量
    public TtdcTemp queryQuanBulkByDoNo(String doNo);

    //根据提单号查询槽车出货临时记录表
    public List<TtdcTemp> queryTtdcTempByDoNo(String doNo);

    //根据罐号查询出货数量
    public TtdcTemp queryQuanInByTank(String tank);

    //根据罐号查询出货数量(出货状态时调用)
    public TtdcTemp queryQuanInByTank2(Map map);

    //查询装车台信息
    public List<TtdcTemp> queryStation(Map map);

    //保存入仓信息
    public void saveTtdcTemp(WarehousingVO vo);

    /**
     * 根据工作单号查询入仓明细
     * @param jobNo 工作单号
     * @return
     */
    public TtdcTemp queryTtdcTempDetail(String jobNo);

}
