package com.alycloud.account.api;

import java.util.List;

import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.ChangeMerchInfo;

public interface IChangeMerchInfoService {

	/**
	 * 获取商户信息更改记录   判断是否是第一次申请待审核的操作
	 * @param loginName
	 * @param merchMobile
	 * @return
	 */
	List<ChangeMerchInfo> getChangeMerchInfoList(ChangeMerchInfo info4s);
	
	/**
	 * 新增商户待审核的信息
	 * @param info
	 * @return
	 */
	int add(ChangeMerchInfo info);
	
	/**
	 * 修改商户待审核的信息
	 * @param info
	 * @return
	 */
	int mod(ChangeMerchInfo info);
	
	/**
	 * 更新待审核商户信息
	 * @param isIdImg 图片类型  1身份证  0 银行卡
	 * @param uploadfile 文件
	 * @param imgLocalpath 保存路径
	 * @param merchno 商户号
	 * @author Horanluo
	 * @return
	 */
	SingleResult<String> updateToAuditInfo(String isIdImg,String base64Img,String imgLocalpath,String merchno)throws Exception;
	
	/**
	 * 获取待审核商户信息   查询身份证
	 * @param merchno
	 * @return
	 * @author Horanluo
	 */
	ChangeMerchInfo getChangeMerchInfo(String merchno);
}
