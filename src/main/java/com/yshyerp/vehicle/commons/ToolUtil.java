package com.yshyerp.vehicle.commons;

import com.yshyerp.vehicle.entity.SysData;
import com.yshyerp.vehicle.service.SysDataService;
import com.yshyerp.vehicle.serviceImpl.SysDataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 */
@Slf4j
@Component
public class ToolUtil {

    //将do转换成vo
    public static Object doCastVo(Object domainObject, Object valueObject) throws Exception{
        Class valueObjectClass = valueObject.getClass();
        Class domainObjectClass = domainObject.getClass();
        Field[] field = valueObjectClass.getDeclaredFields();
        Method doGetMethod;
        Method voSetMethod;
        for (int i = 0; i < field.length; i++) {
            //获得VO中的成员变量名称
            String upStt = StringUtils.capitalize(field[i].getName());
            doGetMethod = domainObjectClass.getMethod("get" + upStt, null);
            voSetMethod = valueObjectClass.getMethod("set" + upStt, doGetMethod.getReturnType());
            //调用do中的get方法获取参数值
            Object getParam = doGetMethod.invoke(domainObject, null);
            if(getParam instanceof String) {
                getParam = ((String) getParam).trim();
            }
            //将参数值写到VO中
            voSetMethod.invoke(valueObject, getParam);
        }
        return valueObject;
    }

    /**
     * 获取访问主机Ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        return ipAddress;
    }

    /**
     * 解析remark中的crr_no
     * @param remark
     * @return
     */
    public static ArrayList<String> analysisRemark(String remark) {
        ArrayList<String> remarkList = new ArrayList<String>();
        remark = remark.substring(0, remark.lastIndexOf("MT")+2);
        String[] sArray = remark.split("MT");
        for(int i=0;i<sArray.length;i++) {
            if(sArray[i].indexOf(";")!=-1 && sArray[i].indexOf(":")!=-1) {
                sArray[i] = sArray[i].substring(sArray[i].lastIndexOf(";")+1, sArray[i].length());
                remarkList.add(sArray[i]);
            }
        }
        return remarkList;
    }
}
