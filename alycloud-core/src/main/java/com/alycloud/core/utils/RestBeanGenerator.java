/*
 * 类文件名:  ResultBeanGenerator.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月30日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.utils;

import java.util.List;

import com.alycloud.core.modules.PageResultBean;
import com.alycloud.core.modules.ResultBean;
import com.github.pagehelper.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月30日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
public class RestBeanGenerator
{
    /**
     * normal
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultBean<T> genResult(String success, T data, String message) {
        ResultBean<T> result = ResultBean.newInstance();
        result.setRespCode(success);
        result.setData(data);
        result.setMessage(message);
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", result);
        }
        return result;
    }
    
    public static <T> ResultBean<T> genResult(String success, T data,String errorCode, String message) {
        ResultBean<T> result = ResultBean.newInstance();
        result.setRespCode(success);
        result.setErrorcode(errorCode);
        result.setData(data);
        result.setMessage(message);
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", result);
        }
        return result;
    }

    /**
     * success
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultBean<T> genSuccessResult(T data) {
        if(data == null)
        {
            return genResult("99", data, "执行成功");
        }
    	if (data instanceof List && ((List<?>) data).size() == 0) {
    		return genResult("99", data, "执行成功");
		}
        return genResult("1", data, "执行成功");
    }

    /**
     * error message
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> ResultBean<T> genErrorResult(String message) {

        return genResult("0", null, message);
    }
    
    public static <T> ResultBean<T> genErrorResult(String errorCode, String message) {

        return genResult("0", null, errorCode,message);
    }
    
    public static <T> ResultBean<T> genErrorResult(T data,String message) {

        return genResult("0", data, message);
    }

    /**
     * error
     * @param error error enum
     * @param <T>
     * @return
     */
//    public static <T> ResultBean<T> genErrorResult(ErrorCode error) {
//
//        return genErrorResult(error.getMessage());
//    }

    /**
     * success no message
     * @return
     */
    public static ResultBean<?> genSuccessResult() {
        return genSuccessResult(null);
    }
    
    /**
     * success
     * @param data
     * @param <T>
     * @return
     * @author Horanluo
     */
    public static <T> PageResultBean<T> genSuccessPageResult(T data) {

        return genPageResult("1", data, "执行成功");
    }
    
    /**
     * normal
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @author Horanluo
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> PageResultBean<T> genPageResult(String success, T data, String message) {
		Page<T> page =(Page<T>)data;
    	PageResultBean<T> result = PageResultBean.newInstance();
    	result.setPageSize(page.getPageSize());
    	result.setPages(page.getPages());
    	result.setTotal(page.getTotal());
        result.setRespCode(page.getTotal() == 0 ? "99":success);
        result.setData((T)page.getResult());
        result.setMessage(message);
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", result);
        }
        return result;
    }
    
    /**
     * normal
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> PageResultBean<T> genSuccessPageResult(String success, T data, String message) {
    	Page<T> page =(Page<T>)data;
    	PageResultBean<T> result = PageResultBean.newInstance();
    	result.setPageSize(page.getPageSize());
    	result.setPages(page.getPages());
    	result.setTotal(page.getTotal());
        result.setRespCode(page.getTotal() == 0 ? "99":success);
        result.setData((T)page.getResult());
        result.setMessage(message);
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", result);
        }
        return result;
    }
    
    public static <T> PageResultBean<T> genEmptyPageResult( String message) {
        PageResultBean<T> result = PageResultBean.newInstance();
        result.setRespCode("99");
        result.setMessage(message);
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", result);
        }
        return result;
    }
    
//    /**
//     * success
//     * @param data
//     * @param <T>
//     * @return
//     */
//    public static <T> ResultBean<T> genSuccessSignResult(T data,String sign) {
//        if(data == null)
//        {
//            return genSignResult("99", data, "执行成功",sign);
//        }
//    	if (data instanceof List && ((List<?>) data).size() == 0) {
//    		return genSignResult("99", data, "执行成功",sign);
//		}
//        return genSignResult("1", data, "执行成功",sign);
//    }
//    
//    /**
//     * normal
//     * @param success
//     * @param data
//     * @param message
//     * @param <T>
//     * @return
//     */
//    public static <T> ResultBean<T> genSignResult(String success, T data, String message,String sign) {
//        ResultBean<T> result = ResultBean.newInstance();
//        result.setRespCode(success);
//        result.setData(data);
//        result.setMessage(message);
//        result.setSign(sign);
//        if (log.isDebugEnabled()) {
//            log.debug("generate rest result:{}", result);
//        }
//        return result;
//    }
}
