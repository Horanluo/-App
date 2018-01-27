package org.xiajinsuo.epay.sdk;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.framework.exception.SignInValidException;
import io.bestpay.sdk.sign.RsaDataEncrypt;
import io.bestpay.sdk.sign.SignUtils;

/**
 * Created by tums on 2017/7/28.
 */
public class RequestDataWrapper extends RRParams {
    private Map requestData;

    public RequestDataWrapper() {
    }

    public Map getRequestData() {
        return this.requestData;
    }

    public void setRequestData(Map requestData) {
        this.requestData = requestData;
    }

    /**
     * 解密请求数据
     * 1 验签
     * 2 解密加密的数据
     *
     * @param requestData
     * @param dataEncrypt
     * @return
     * @throws CodeException
     */
    public static RequestDataWrapper parse(RRParams requestData, RsaDataEncrypt dataEncrypt) throws CodeException, UnsupportedEncodingException {
        String serverTransId = requestData.getServerTransId();
        RRParams rrParams = newBuilder(requestData).setSign("").setServerTransId("").build();
        byte[] signData = SignUtils.getSortString(rrParams);
        try {
            dataEncrypt.verify(signData, requestData.getSign());
        } catch (CodeException e) {
            throw e;
        } catch (Exception e) {
            throw new SignInValidException("签名验证错误");
        }
        Map data = null;
        try {
            data = JSON.parseObject(requestData.getData(), Map.class);
        } catch (Exception e) {
            throw new CodeException("数据格式异常");
        }
        RequestDataWrapper rdw = new RequestDataWrapper();
        SpecificRecordUtils.copy(requestData, rdw);
        rdw.setServerTransId(serverTransId);
        rdw.setRequestData(data);
        return rdw;
    }
}
