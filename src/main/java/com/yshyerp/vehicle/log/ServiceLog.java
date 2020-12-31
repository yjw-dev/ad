package com.yshyerp.vehicle.log;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ：tao
 * @date ：Created in 2020/12/8 17:25
 * mongodDB的写日志业务
 * @modified By：
 * @version: version
 */
@Log4j2
@Component
public class ServiceLog {
    private  final
    MongoTemplate mongoTemplate;

    /**
     * 使用异步方式写日志业务，避免日志业务阻塞影响主业务的相应
     */
    @Resource(name="defaultThreadPool")
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 二个mongodb的集合，exception用于存储异常，service用于存储基本的重要逻辑
     */
    @Value("${mongoDb.crcExceptionCollection}")
    private String crcExceptionCollection;

    @Value("${mongoDb.crcServiceCollection}")
    private String crcServiceCollection;
    @Autowired
    public ServiceLog(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 异常日志插入
     * @param e generator exception这里不做过滤，拦截所有异常
     */
    public  void exception(Object e)
    {
        threadPoolTaskExecutor.execute(
                ()-> mongoTemplate.insert(e,crcExceptionCollection)
        );
        log.info("插入异常数据");
    }

    /**
     * 插入业务Bson数据
     * @param object 所有插入的数据都必须是Bson格式，也就是所有object要有field
     */
    public  void service(Object object)
    {
        threadPoolTaskExecutor.execute(
                ()-> mongoTemplate.insert(object,crcServiceCollection)
        );
    log.info("插入业务");
    }


}
