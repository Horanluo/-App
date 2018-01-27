package com.alycloud.modules.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年11月6日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadVO {
	
    private String base64Img;
    private String isIdImg;
}
