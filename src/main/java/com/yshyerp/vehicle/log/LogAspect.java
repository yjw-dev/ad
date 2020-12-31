package com.yshyerp.vehicle.log;


import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：tao
 * @date ：Created in 2020/12/16 14:02
 * 定义切面处理异常
 * @version: version
 */
@Log4j2
@Component
@Aspect
public class LogAspect {
    /**
     * 加入写mongodb日志业务
     */
    private final
    ServiceLog serviceLog;

    @Autowired
    public LogAspect(ServiceLog serviceLog) {
        this.serviceLog = serviceLog;
    }

    /**
     * 定义的切入点为业务层
     */
    @Pointcut("execution(* com.yshyerp.vehicle.serviceImpl.*.*(..))")
    public void ponitCut()
    {
    }


    /**
     * 定义切点，实现在业务层抛出异常的时候，处理写异常业务
     * @param joinPoint
     * @param e 抛出的异常为generator
     */
    @AfterThrowing(value = "ponitCut()",throwing = "e")
    public void handelThrowing(JoinPoint joinPoint, Exception e)
    {
      log.info("开始记录异常日志");
      if (e instanceof RuntimeException)
      {
          serviceLog.exception(e);
      }
//      if (e instanceof ParseTimeException)
//      {
//          ParseTimeException parseTimeException=
//                  new ParseTimeException("时间格式错误");
//          serviceLog.exception(parseTimeException);
//      }
//      if (e instanceof EmptyEntityException)
//      {
//          log.info("开始记录异常2");
//          EmptyEntityException emptyEntityException=new EmptyEntityException("出现空的数据实体");
//          serviceLog.exception(emptyEntityException);
//      }

    }
}
