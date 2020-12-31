package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Document;
import com.yshyerp.vehicle.mapper.DocumentMapper;
import com.yshyerp.vehicle.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 扫描文件
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentMapper documentMapper;

    /**
     * 生成扫描文件Code
     * @param date1
     * @param time1
     * @return
     */
    @Override
    public String createDocumentCode(Timestamp date1, String time1) {
        String sDate1 = date1.toString();
        sDate1 = sDate1.substring(2,4) + sDate1.substring(5,7) + sDate1.substring(8,10);
        int iTime1 = Integer.valueOf(time1.substring(0,2)) * 3600 + Integer.valueOf(time1.substring(3,5))*60 +
                Integer.valueOf(time1.substring(6,8));
        String sTime1 = String.valueOf(iTime1);
        for(int i=sTime1.length();i<5;i++) {
            sTime1 = "0" + sTime1;
        }
        return sDate1+sTime1;
    }

    /**
     * 根据Code查询Document
     * @param code
     * @return
     */
    @Override
    public List<Document> queryDocumentByCode(String code) {
        return documentMapper.queryDocumentByCode(code);
    }
}
