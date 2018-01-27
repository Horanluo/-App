package com.alycloud.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.alycloud.account.api.IMerchUserService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.modules.entity.MerchUser;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ShareController {

	@Autowired
	private IMerchUserService merchUserService;
	
	@RequestMapping(value="/share",method=RequestMethod.GET)
	@SystemControllerLog(description = "分享接口")
	public String share(@RequestParam(name="loginName") String loginName,ModelMap map) throws Exception{
		log.info("请求参数:"+loginName);
		MerchUser merchUser = merchUserService.queryMerchUser(loginName);
		map.addAttribute("refer", merchUser.getTrueName());
		return "index";
	}
	
//	@RequestMapping(value="/weixin/share")
//	@SystemControllerLog(description = "跳转微信分享接口")
//	@ResponseBody
//    public Map<String, Object> share(HttpServletRequest request) {
//        String urlTemp = "http://" + request.getServerName() + request.getContextPath();
//        String urlpath = "http://" + request.getServerName();
//        String appUrl = request.getParameter("url");
//        if (request.getParameter("code") != null) {
//            appUrl += "&code=" + request.getParameter("code");
//        }
//        if (request.getParameter("state") != null) {
//            appUrl += "&state=" + request.getParameter("state");
//        }
//        //return WxConfigUtil.getSignature(appUrl, ContentValues.APPID, ContentValues.SECRET, urlTemp, urlpath);
//        return null;
//    }
}
