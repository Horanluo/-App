package com.alycloud.schedules.yufu.task;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.exception.ServiceException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.ChangeMerchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.vo.DownLoadImgVO;
import com.alycloud.schedules.feign.ChangeMerchInfoFeign;
import com.alycloud.schedules.feign.MerchInfoFeign;
import com.alycloud.schedules.feign.UtilFeign;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoadFileTask {

	@Autowired
	private MerchInfoFeign merchInfoFeign;
	@Autowired
	private UtilFeign utilFeign;
	@Autowired
	private ChangeMerchInfoFeign changeMerchInfoFeign;
	
	/**
	 * 定时批量下载文件
	 * @author 罗根恒
	 * @date 2017年11月27日
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 1 * * ?")
	public void process() throws ServiceException {
		MerchInfo merchInfo = new MerchInfo();
		merchInfo.setRealNameAuthStatus(3);
		RequestBean<MerchInfo> feignData = new RequestBean<MerchInfo>();
		feignData.setData(merchInfo);
		
		ResultBean<List<MerchInfo>> merchList = merchInfoFeign.getByMerchInfo(feignData);
		log.info("找到{}个待批量下载文件的商户", merchList.getData().size());
		@SuppressWarnings("unused")
		int num = 0;
		for (MerchInfo mi: merchList.getData()) {
			num++;
			log.info("开始批量下载商户[{}]的文件", mi.getId());
			if(!loadFile(mi)){
				continue;
			}
		}
	}
	
	/**
	 * @author Horanluo
	 * @date 2017年11月28日
	 * @param Horanluo
	 * @throws Exception
	 */
	@Transactional
	private boolean loadFile(MerchInfo mi) throws ServiceException {
		if(StringUtils.isEmpty(mi.getJsonStr())){
			return false;
		}
		JSONObject json = JSONObject.parseObject(mi.getJsonStr());
		String frontUrl = json.getString("frontUrl");
		String backUrl = json.getString("backUrl");
		String livingPhotoUrl = json.getString("livingPhotoUrl");
		String videoUrl = json.getString("videoUrl");
		log.info("批量下载文件链接:"+json);
		log.info("身份证正面照下载链接:"+frontUrl);
		log.info("身份证反面照下载链接:"+backUrl);
		log.info("身份证活体照下载链接:"+livingPhotoUrl);
		log.info("身份证活体视频下载链接:"+videoUrl);
		
		RequestBean<DownLoadImgVO> reqData = new RequestBean<DownLoadImgVO>();
		DownLoadImgVO dliVO=new DownLoadImgVO();
		dliVO.setFrontUrl(frontUrl);
		dliVO.setBackUrl(backUrl);
		dliVO.setLivingPhotoUrl(livingPhotoUrl);
		dliVO.setVideoUrl(videoUrl);
		reqData.setData(dliVO);
		reqData.setMerchno(mi.getMerchno());
		
		ResultBean<ChangeMerchInfo> changeMerchInforesult;
		ChangeMerchInfo cmi;
		try {
			Map<Object,Object> result = utilFeign.loadImg(reqData);
			log.info("返回结果:"+result);
			if((boolean)result.get("hasOneLoaded")){
				//下载成功   更新存储图片的表信息
				changeMerchInforesult = changeMerchInfoFeign.list(mi);
				cmi=changeMerchInforesult.getData();
				log.info(cmi.toString());
				
				cmi.setImgIdCard1((String)result.get("front"));
				cmi.setImgIdCard2((String)result.get("back"));
				changeMerchInfoFeign.update(cmi);
				
				mi.setIdentity1Img((String)result.get("front"));
				mi.setIdentity2Img((String)result.get("back"));
				mi.setCredit1Img((String)result.get("livingPhoto"));
				mi.setCredit2Img((String)result.get("videourl"));
				
				RequestBean<MerchInfo> feignData = new RequestBean<MerchInfo>();
				feignData.setData(mi);
				merchInfoFeign.mod(feignData);
				return true;
			}
		} catch (Exception e) {
			log.info("异步下载身份认证图片失败:",e);
			e.printStackTrace();
			throw new ServiceException("异步下载身份认证图片失败");
		}
		return false;
	}
}
