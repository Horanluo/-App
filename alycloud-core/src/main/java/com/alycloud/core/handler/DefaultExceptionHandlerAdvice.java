/*
 * 类文件名:  DefaultExceptionHandlerAdvice.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月30日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.handler;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alycloud.core.log.service.ISystemErrorLogService;
import com.alycloud.core.modules.ArgumentInvalidResultBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.log.SystemErrorLogBean;

/**
 * 全局异常抓取
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年9月30日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandlerAdvice
{
    @Autowired
    private ISystemErrorLogService<SystemErrorLogBean> systemErrorLogService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
        // @Override
        // public void setAsText(String text) {
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // try {
        // setValue(format.parse(text));
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        // }
        // });
    }
    
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private <T> ResultBean<T> runtimeExceptionHandler(Exception e)
    {
        log.error("---------> huge error!", e);
        HttpServletRequest request =
            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        // HttpSession session = request.getSession();
        // 读取session中的用户
        // User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        // 获取请求ip
        String ip = request.getRemoteAddr();
        // 获取用户请求方法的参数并序列化为JSON格式字符串
        /* ==========数据库日志========= */
        SystemErrorLogBean errorLogBean = new SystemErrorLogBean();
        errorLogBean.setIpAddress(ip);
        errorLogBean.setCreateDate(new Date());
        errorLogBean.setModuleName(request.getServerName());
        errorLogBean.setErrorMessage(e.getClass().getName() + "; " + e.getMessage());
        String params ="";
        try
        {
//            Enumeration<String> enume = request.getAttributeNames();
//            while(enume.hasMoreElements())
//            {
//                params +=enume.nextElement() + ";";
//            }
            params = JSONUtils.obj2json(request.getParameterMap());
        }
        catch (Exception e1)
        {
           log.error("异常信息转成JSON格式出现异常:  " , e1);
        }
//        while(request.getAttributeNames().hasMoreElements())
//        {
//           String key =  request.getAttributeNames().nextElement();
//           Object value = request.getAttribute(key);
//           params += "key: " + key +", value: "+ value + ";";
//        }
        errorLogBean.setInputParams(params);
        errorLogBean.setServicerl(request.getRequestURI());
        systemErrorLogService.insert(errorLogBean);
        return RestBeanGenerator.genErrorResult(e.getMessage());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> ResultBean<T> illegalParamsExceptionHandler(MethodArgumentNotValidException e)
    {
        log.error("---------> invalid request!", e);
        // 按需重新封装需要返回的错误信息
        List<ArgumentInvalidResultBean> invalidArguments = new ArrayList<>();
        // 解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : e.getBindingResult().getFieldErrors())
        {
            ArgumentInvalidResultBean invalidArgument = new ArgumentInvalidResultBean();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }
        return RestBeanGenerator.genErrorResult((T)invalidArguments, "参数有误");
    }
}
