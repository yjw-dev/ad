package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Document;

import java.sql.Timestamp;
import java.util.List;

/**
 * 扫描文件
 */
public interface DocumentService {

    /**
     * 生成扫描文件Code
     * @param date1
     * @param time1
     * @return
     */
    public String createDocumentCode(Timestamp date1, String time1);

    /**
     * 根据code查询Document
     * @param code
     * @return
     */
    public List<Document> queryDocumentByCode(String code);
}
