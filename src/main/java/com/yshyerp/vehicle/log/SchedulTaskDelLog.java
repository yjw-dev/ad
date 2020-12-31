package com.yshyerp.vehicle.log;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ：tao
 * @date ：Created in 2020/12/17 14:32
 * 定时清除mongodb日志，用于给磁盘预留空间，避免磁盘消耗太快
 * @version: version
 */
@Log4j2
@Component
public class SchedulTaskDelLog {
    private final
    MongoTemplate mongoTemplate;

    /**
     * 定期删除过期日志的集合，写在spring容器中，用注入属性的方法初始化
     * 便于profile环境的切换，以及日志删除业务的解耦
     */
    @Value("${mongoDb.crcExceptionCollection}")
    private String collectionName;

    @Autowired
    public SchedulTaskDelLog(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 每月一清除
     * 定时任务，cron表达式每个月的20号的1点10分30秒删除数据
     */
    @Scheduled(cron = "30 10 1 20 * ?")
    public void delLog()
    {
//       log.info("开始删除过期日志");
//        Query query = new Query();
//        mongoTemplate.remove(query, collectionName);
    }

}
