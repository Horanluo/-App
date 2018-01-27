/*
 * 类文件名:  SystemLogAspect.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.log.service.ISystemErrorLogService;
import com.alycloud.core.log.service.ISystemServiceLogService;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.modules.log.SystemErrorLogBean;
import com.alycloud.modules.log.SystemServiceLogBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 业务日志和异常日志记录到数据中
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月4日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
@Aspect
@Component
public class SystemLogAspect
{
    @Autowired
    private ISystemServiceLogService<SystemServiceLogBean> systemServiceLogService;
    
    @Autowired
    private ISystemErrorLogService<SystemErrorLogBean> systemErrorLogService;
    
    /** 
     * 定义拦截规则：拦截com.alycloud包下面的所有类中，有@ServiceLogAnnotation注解的方法。 
     */
    //@Pointcut("execution(* com.alycloud..*(..)) and @annotation(com.alycloud.core.annotation.ServiceLogAnnotation)")
    @Pointcut("@annotation(com.alycloud.core.annotation.ServiceLogAnnotation)")
    public void serviceAspect(){};
    
  //Controller层切点  
    @Pointcut("@annotation(com.alycloud.core.annotation.SystemControllerLog)")  
    public void controllerAspect(){   }  
    
 // result of return  
    @AfterReturning(pointcut = "serviceAspect()", returning = "retVal")  
    public void after(JoinPoint joinPoint, Object retVal) {  
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
//      HttpSession session = request.getSession();  
      //读取session中的用户  
//      User user = (User) session.getAttribute(WebConstants.CURRENT_USER);  
      //请求的IP  
      String ip = request.getRemoteAddr();
      String url = request.getRequestURI();
      String params = "";  
      try {  
          if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {  
              for (int i = 0; i < joinPoint.getArgs().length; i++) {  
                  params += JSONUtils.obj2json(joinPoint.getArgs()[i]) + ";";  
              }  
          } 
          //*========控制台输出=========*//  
          System.out.println("=====前置通知开始=====");  
          System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
          System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));  
          System.out.println("请求IP:" + ip);  
          SystemServiceLogBean serviceLogBean = new SystemServiceLogBean();
          serviceLogBean.setIpAddress(ip);
          serviceLogBean.setCreateDate(new Date());
          serviceLogBean.setModuleName(getServiceMthodDescription(joinPoint));
          serviceLogBean.setInputParams(params);
          serviceLogBean.setOutputParams(JSONUtils.obj2json(retVal));
          serviceLogBean.setServicerl(url);
          systemServiceLogService.insert(serviceLogBean);
          //*========数据库日志=========*//  
          System.out.println("=====前置通知结束=====");  
      } catch (Exception e) {  
          //记录本地异常日志  
          log.error("==后置通知异常==");  
          log.error("异常信息:{}", e.getMessage());  
      } 
    } 
    
    /** 
     * 前置通知 用于拦截Controller层记录用户的操作 
     * 
     * @param joinPoint 切点 
     */  
    @Before("controllerAspect()")  
    public void doBefore(JoinPoint joinPoint) {  

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
//        HttpSession session = request.getSession();  
        //读取session中的用户  
//        User user = (User) session.getAttribute(WebConstants.CURRENT_USER);  
        //请求的IP  
        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        String params = "";  
        try {  
          //*========控制台输出=========*//  
            System.out.println("=====前置通知开始=====");  
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {  
                for (int i = 0; i < joinPoint.getArgs().length; i++) {  
                    params += JSONUtils.obj2json(joinPoint.getArgs()[i]) + ";";  
                }  
            } 
            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));  
//            System.out.println("请求人:" + user.getName());  
            System.out.println("请求IP:" + ip);  
            SystemServiceLogBean serviceLogBean = new SystemServiceLogBean();
            serviceLogBean.setIpAddress(ip);
            serviceLogBean.setCreateDate(new Date());
            serviceLogBean.setModuleName(getControllerMethodDescription(joinPoint));
            serviceLogBean.setInputParams(params);
            serviceLogBean.setServicerl(url);
            systemServiceLogService.insert(serviceLogBean);
            //*========数据库日志=========*//  
            System.out.println("=====前置通知结束=====");  
        } catch (Exception e) { 
            //记录本地异常日志  
            log.error("==前置通知异常==");  
            log.error("异常信息:{}", e.getMessage());  
        }  
    }  

    /** 
     * 异常通知 用于拦截service层记录异常日志 
     * 
     * @param joinPoint 
     * @param e 
     */  
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")  
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {  
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
//        HttpSession session = request.getSession();  
        //读取session中的用户  
//        User user = (User) session.getAttribute(WebConstants.CURRENT_USER);  
        //获取请求ip  
        String ip = request.getRemoteAddr();  
        //获取用户请求方法的参数并序列化为JSON格式字符串  
        String params = "";  
        
        try {  
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {  
                for (int i = 0; i < joinPoint.getArgs().length; i++) {  
                    System.out.println("入参------>>>>>" + joinPoint.getArgs()[i]);
                    params += JSONUtils.obj2json(joinPoint.getArgs()[i]) + ";";
//                    if(joinPoint.getArgs()[i] instanceof RequestBean)
//                    {
//                        params += JSONUtils.obj2json(joinPoint.getArgs()[i]) + ";";
                        
//                        RequestBean bean = (RequestBean)joinPoint.getArgs()[i];
//                        if(bean.getData() != null)
//                        {
//                            String value = "requestBean: "+"data: " + JSONUtils.obj2json(bean.getData()); 
//                            bean.setData(null);
//                            value = "requestBean: "+JSONUtils.obj2json(bean) + value  + ";"; 
//                             params += value;
//                        }
//                         
//                    }else
//                    {
//                        params += JSONUtils.obj2json(joinPoint.getArgs()[i]) + ";";  
//                    }
                    
                }  
            } 
            
              /*========控制台输出=========*/  
            System.out.println("=====异常通知开始=====");  
            System.out.println("异常代码:" + e.getClass().getName());  
            System.out.println("异常信息:" + e.getMessage());  
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));  
//            System.out.println("请求人:" + user.getName());  
            System.out.println("请求IP:" + ip);  
            System.out.println("请求参数:" + params);  
               /*==========数据库日志=========*/  
            SystemErrorLogBean errorLogBean = new SystemErrorLogBean();
            errorLogBean.setIpAddress(ip);
            errorLogBean.setCreateDate(new Date());
            errorLogBean.setModuleName(getServiceMthodDescription(joinPoint));
            errorLogBean.setErrorMessage(e.getClass().getName() +"; " + e.getMessage());
            errorLogBean.setInputParams(params);
            errorLogBean.setServicerl((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            systemErrorLogService.insert(errorLogBean);
            System.out.println("=====异常通知结束=====");  
        } catch (Exception ex) {  
            //记录本地异常日志  
            log.error("==异常通知异常==");  
            log.error("异常信息:{}", ex.getMessage());  
        }  
         /*==========记录本地异常日志==========*/  
        log.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);  

    }  


    /** 
     * 获取注解中对方法的描述信息 用于service层注解 
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
    public static String getServiceMthodDescription(JoinPoint joinPoint)  
            throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(ServiceLogAnnotation.class).moduleName();  
                    break;  
                }  
            }  
        }  
        return description;  
    }  

    /** 
     * 获取注解中对方法的描述信息 用于Controller层注解 
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(SystemControllerLog.class).description();  
                    break;  
                }  
            }  
        }  
        return description;  
    } 
    
//    @AfterThrowing(pointcut = "methodPointcut()", returning = "retVal")  
//    public void afterException(JoinPoint joinPoint, Object retVal) {  
//        System.out.println(retVal);  
//    }
}
