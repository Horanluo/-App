package com.alycloud.pay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alycloud.modules.channel.yufu.YufuChannelMerchBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.search.ChannelSubmerchInfo4Search;


/**
 * 渠道商户(新表)
 * @author Moyq5
 * @date 2017年10月20日
 */
public interface ChannelSubmerchInfoMapper {
	
	public List<ChannelSubmerchInfo> listByPage(ChannelSubmerchInfo4Search merch4s);

    /**
     * 根据商户号、支付类型查询是否已经开通快捷服务
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月20日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public List<ChannelSubmerchInfo> searchByParams(@Param("merchno") String merchno, @Param("payType") String payType);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public ChannelSubmerchInfo searchFastByGradeInfo(Map<String,String> channelParams);
    
    /**
     * 根据渠道编号查询该商户是否已经进件
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public ChannelSubmerchInfo searchFastByChannelCode(Map<String,String> channelParams);
    
    /**
     * 插入渠道信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public int addChannelSubMerchInfo(ChannelSubmerchInfo info);

    /**
     * 查询御付注册信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月22日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public YufuChannelMerchBean searchYufuSubmerchInfo(@Param("platMerchno") String merchno,@Param("phone") String mobile);
}
