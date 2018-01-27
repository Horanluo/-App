package org.xiajinsuo.epay.sdk;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiajinsuo.epay.exception.OtherErrorException;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.framework.exception.NotSupportServiceException;
import io.bestpay.sdk.sign.RsaDataEncrypt;

/**
 * 请求执行抽象处理类
 */
public abstract class AbstractEncryptServiceExecute implements ServiceExecute<RRParams> {
    private static Logger LOG = LoggerFactory.getLogger(AbstractEncryptServiceExecute.class);
    private static final String IP_LAST = getIPLast();

    public AbstractEncryptServiceExecute() {
    }

    private void copyRequestData(RRParams req, RRParams.Builder builder, String serverTransId) {
        builder.setAppId(req.getAppId()).setClientTransId(req.getClientTransId()).setTransTimestamp(req.getTransTimestamp()).setServerTransId(serverTransId);
    }

    protected String generateServerTransId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(new Date());
        String nanoTime = System.nanoTime() + "";
        String transType = "";
        String ipLast = IP_LAST;
        return String.format("%s%s%s%s", new Object[]{ipLast, transType, time, nanoTime.substring(nanoTime.length() - 6)});
    }

    private static String getIPLast() {
        String ip = "000";
        try {
            InetAddress e = InetAddress.getLocalHost();
            if (null != e) {
                String host = e.getHostAddress();
                String[] arr = host.split("\\.");
                String last = arr[arr.length - 1];
                for (int i = last.length(); i < 3; ++i) {
                    last = "0" + last;
                }
                ip = last;
            }
        } catch (UnknownHostException var6) {
            var6.printStackTrace();
        }
        return ip;
    }

    public RRParams execute(Map<String, Object> parameters) {
        String server_trans_id = generateServerTransId();
        RRParams.Builder builder = RRParams.newBuilder();
        RsaDataEncrypt serverRsaDataEncrypt = null;
        try {
            RRParams e = SpecificRecordUtils.parse(new RRParams(), parameters);
            this.checkParams(e);
            e.put("server_trans_id", server_trans_id);
            this.copyRequestData(e, builder, server_trans_id);
            serverRsaDataEncrypt = this.getServerDataEncryptable(e);
            BusinessServiceExecute businessServiceExecute = this.getBusinessServiceExecute(e);
            if (null == businessServiceExecute) {
                throw new NotSupportServiceException(String.format("不支持的服务类型[%s]", new Object[]{e.getTransType()}));
            }
            RsaDataEncrypt rsaDataEncrypt = this.getDataEncryptable(e);
            RequestDataWrapper requestDataWrapper = this.getRequestDataWrapper(e, rsaDataEncrypt);
            Map result = businessServiceExecute.execute(requestDataWrapper);
            builder.setRespCode("000000").setRespMsg("请求成功");
            return ResponseDataWrapper.createResponse(builder, result, serverRsaDataEncrypt);
        } catch (CodeException e) {
            String msg = e.getErrMsg();
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            builder.setRespMsg(msg).setRespCode(e.getErrCode());
            return ResponseDataWrapper.createResponse(builder, null, serverRsaDataEncrypt);
        } catch (Exception e) {
            LOG.error("请求异常:" + e.getMessage(), e);
            builder.setRespMsg("系统未知异常").setRespCode("-1");
            return ResponseDataWrapper.createResponse(builder, null, serverRsaDataEncrypt);
        }
    }

    /**
     * 检查必填参数
     *
     * @param e
     * @throws CodeException
     */
    private void checkParams(RRParams e) throws CodeException {
        if (StringUtils.isEmpty(e.getAppId())) {
            throw new OtherErrorException("app_id 不能为空");
        }
        if (StringUtils.isEmpty(e.getClientTransId())) {
            throw new OtherErrorException("client_trans_id 不能为空");
        }
        if (e.getTransTimestamp() == null) {
            throw new OtherErrorException("trans_timestamp 不能为空");
        }
        if (StringUtils.isEmpty(e.getTransType())) {
            throw new OtherErrorException("trans_type 不能为空");
        }
        if (StringUtils.isEmpty(e.getSign())) {
            throw new OtherErrorException("sign 不能为空");
        }
    }

    private RsaDataEncrypt getDataEncryptable(RRParams requestData) throws CodeException {
        return new RsaDataEncrypt(Map.class, null, this.getPublicKey(requestData));
    }

    private RsaDataEncrypt getServerDataEncryptable(RRParams requestData) throws CodeException {
        return new RsaDataEncrypt(Map.class, this.getPrivateKey(requestData), null);
    }

    private RequestDataWrapper getRequestDataWrapper(RRParams requestData, RsaDataEncrypt rde) throws IOException, KeyStoreException {
        return RequestDataWrapper.parse(requestData, rde);
    }

    public abstract byte[] getPublicKey(RRParams params) throws CodeException;

    public abstract PrivateKey getPrivateKey(RRParams params) throws CodeException;

    public abstract BusinessServiceExecute getBusinessServiceExecute(RRParams params);
}
