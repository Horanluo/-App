package com.alycloud.modules.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述>
 * 活体识别 请求 实体类
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月24日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivingIdentifyVO {
    
	/**
     * 活体照下载地址
     */
	@NotBlank(message="活体照地址不能为空")
    private String livingPhotoUrl;
	
	/**
     * 视频下载地址
     */
	@NotBlank(message="视频下载地址不能为空")
    private String videoUrl;
}
