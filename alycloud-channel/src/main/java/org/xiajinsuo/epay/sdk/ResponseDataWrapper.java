package org.xiajinsuo.epay.sdk;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
//import org.codehaus.jackson.map.ObjectMapper;
import org.mortbay.log.Log;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.framework.exception.SignInValidException;
import io.bestpay.sdk.sign.DataEncryptable;
import io.bestpay.sdk.sign.RsaDataEncrypt;
import io.bestpay.sdk.sign.SignUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseDataWrapper extends RRParams {
    private Map responseData;

    public ResponseDataWrapper() {
    }

    public Map getResponseData() {
        return this.responseData;
    }

    public void setResponseData(Map responseData) {
        this.responseData = responseData;
    }

    /**
     * 解析响应数据
     *
     * @param response
     * @param dataEncrypt
     * @return
     * @throws CodeException
     */
    public static ResponseDataWrapper parse(String response, RsaDataEncrypt dataEncrypt) throws CodeException {
        ObjectMapper om = new ObjectMapper();
        Map map;
        try {
            map = om.readValue(response, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("解析json格式出错", e);
        }
        ResponseDataWrapper rd = new ResponseDataWrapper();
        SpecificRecordUtils.parse(rd, map);
        String serverTransId = rd.getServerTransId();
        log.info("验签:"+rd.getSign());
        RRParams data = RRParams.newBuilder(rd).setSign("").setServerTransId("").build();
        log.info("加签参数:"+SignUtils.getSortString(data));
        if (!"000000".equals(data.getRespCode()) || StringUtils.isEmpty(data.getData())) {//请求失败、或者响应为空不验签
            return rd;
        }
        byte[] signData = SignUtils.getSortString(data);
        boolean signResult = false;
        try {
            signResult = dataEncrypt.verify(signData, rd.getSign());
        } catch (CodeException e) {
            throw e;
        } catch (Exception e) {
            throw new SignInValidException("签名验证错误");
        }
        if (!signResult) {
            throw new SignInValidException();
        } else {
            if (null != rd.getData() && !"".equals(rd.getData())) {
                Map result = JSON.parseObject(rd.getData(), Map.class);
                rd.setResponseData(result);
            }
            rd.setServerTransId(serverTransId);
            return rd;
        }
    }

    public static RRParams createResponse(Builder builder, Map respData, DataEncryptable<SpecificRecordBase> dataEncrypt) {
        try {
            if (MapUtils.isNotEmpty(respData)) {
                builder.setData(JSON.toJSONString(respData));
            }
            String serverTransId = builder.getServerTransId();
            RRParams e1 = builder.setSign("").setServerTransId("").build();
            byte[] signData = SignUtils.getSortString(e1);
            String str = dataEncrypt.sign(signData);
            return RRParams.newBuilder(e1).setSign(str).setServerTransId(serverTransId).build();
        } catch (CodeException e) {
            String msg = e.getErrMsg();
            if (!StringUtils.isEmpty(e.getMessage())) {
                msg = msg + ":" + e.getMessage();
            }
            return RRParams.newBuilder(new RRParams()).setRespCode(e.getErrCode()).setRespMsg(msg).setTransTimestamp(System.currentTimeMillis()).build();
        } catch (Exception e) {
            return RRParams.newBuilder(new RRParams()).setRespCode("unkonw").setRespMsg("系统未知错误").setTransTimestamp(System.currentTimeMillis()).build();
        }
    }
}
