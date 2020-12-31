package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Document;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DocumentMapper {

    /**
     * 根据Code查询Document
     * @param code
     * @return
     */
    public List<Document> queryDocumentByCode(String code);
}
