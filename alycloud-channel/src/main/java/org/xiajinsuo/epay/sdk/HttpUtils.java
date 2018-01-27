package org.xiajinsuo.epay.sdk;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.sdk.http.HttpClientFactory;
import io.bestpay.sdk.sign.RsaDataEncrypt;
import io.bestpay.sdk.sign.SignUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by tums on 2017/7/28.
 */
@Slf4j
public class HttpUtils {
    private static final String CHARSET_UTF_8 = "utf-8";

    public HttpUtils() {
    }

    public static String postJson(String url, Map<String, String> map) {
        return post(url, map, ContentType.APPLICATION_JSON);
    }

    public static String post(String url, Map<String, String> map, ContentType contentType) {
        CloseableHttpClient client = HttpClientFactory.getHttpClient();

        String sl;
        try {
            RequestBuilder e = RequestBuilder.post(url);
            if (!contentType.equals(ContentType.APPLICATION_FORM_URLENCODED)) {
                if (!contentType.equals(ContentType.APPLICATION_JSON)) {
                    throw new UnsupportedOperationException("不支持的协议类型:" + contentType.getMimeType());
                }

                e.setEntity(new StringEntity((new ObjectMapper()).writeValueAsString(map), CHARSET_UTF_8));
            } else {
                ArrayList post = new ArrayList();
                Iterator resp = map.keySet().iterator();

                while (resp.hasNext()) {
                    sl = (String) resp.next();
                    Object value = map.get(sl);
                    if (null != value) {
                        post.add(new BasicNameValuePair(sl, String.valueOf(value)));
                    }
                }

                e.setEntity(new UrlEncodedFormEntity(post, CHARSET_UTF_8));
            }

            HttpUriRequest post1 = e.build();
            CloseableHttpResponse resp1 = client.execute(post1);
            if (null != resp1.getStatusLine()) {
                StatusLine sl1 = resp1.getStatusLine();
                if (sl1.getStatusCode() < 200 || sl1.getStatusCode() >= 300) {
                    throw new HttpException(String.format("%s:%s", new Object[]{Integer.valueOf(sl1.getStatusCode()), sl1.getReasonPhrase()}));
                }
            }

            sl = EntityUtils.toString(resp1.getEntity(), CHARSET_UTF_8);
        } catch (Exception var12) {
            throw new RuntimeException(var12);
        } finally {
            IOUtils.closeQuietly(client);
        }

        return sl;
    }

    public static String get(String url, Map<String, String> map) {
        CloseableHttpClient client = HttpClientFactory.getHttpClient();

        String sl2;
        try {
            RequestBuilder e = RequestBuilder.get(url);
            ArrayList names = new ArrayList();
            Iterator request = map.keySet().iterator();

            while (request.hasNext()) {
                String resp = (String) request.next();
                Object sl = map.get(resp);
                if (null != sl) {
                    names.add(new BasicNameValuePair(resp, String.valueOf(sl)));
                }
            }

            e.setEntity(new UrlEncodedFormEntity(names, CHARSET_UTF_8));
            HttpUriRequest request1 = e.build();
            CloseableHttpResponse resp1 = client.execute(request1);
            if (null != resp1.getStatusLine()) {
                StatusLine sl1 = resp1.getStatusLine();
                if (sl1.getStatusCode() < 200 || sl1.getStatusCode() >= 300) {
                    throw new HttpException(String.format("%s:%s", new Object[]{Integer.valueOf(sl1.getStatusCode()), sl1.getReasonPhrase()}));
                }
            }

            sl2 = EntityUtils.toString(resp1.getEntity(), CHARSET_UTF_8);
        } catch (Exception var11) {
            throw new RuntimeException(var11);
        } finally {
            IOUtils.closeQuietly(client);
        }

        return sl2;
    }

    public static Map<String, ?> getAsMap(String url, Map<String, String> map) {
        String response = get(url, map);
        ObjectMapper om = new ObjectMapper();

        try {
            return (Map) om.readValue(response, Map.class);
        } catch (Exception var5) {
            throw new RuntimeException("解析json错误", var5);
        }
    }

    public static String post(String url, SpecificRecordBase record) {
        CloseableHttpClient client = HttpClientFactory.getHttpClient();
        String result;
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(record.toString(), Charset.forName(CHARSET_UTF_8));
            entity.setContentEncoding(CHARSET_UTF_8);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), CHARSET_UTF_8);
        } catch (Exception var11) {
            throw new RuntimeException(var11);
        } finally {
            IOUtils.closeQuietly(client);
        }

        return result;
    }

    public static String post(String url, RRParams requestParams, Map<String, String> data, DataSignable<Map<String, String>> signable) {
        String sign = signable.sign(requestParams, data);
        data.put("sign", sign);
        return post(url, data, ContentType.APPLICATION_FORM_URLENCODED);
    }

    public static String post(String url, RRParams requestParams, SpecificRecordBase record, DataSignable<SpecificRecordBase> signable) {
        Map map = SpecificRecordUtils.toMap(record);
        Map baseMap = SpecificRecordUtils.toMap(requestParams);
        map.putAll(baseMap);
        String sign = signable.sign(requestParams, record);
        map.put("sign", sign);
        return post(url, map, ContentType.APPLICATION_FORM_URLENCODED);
    }

    public static ResponseDataWrapper post(String url, RRParams requestData, Map data, RsaDataEncrypt requestDataEncrypt, RsaDataEncrypt responseDataVerify) {
        RRParams.Builder builder = RRParams.newBuilder(requestData);
        RRParams tmp = builder.setSign("").setData("").setServerTransId("").build();
        try {
            byte[] e;
            if (null != data) {
                tmp.put("data", JSON.toJSONString(data));
            }
            e = SignUtils.getSortString(tmp);
            String sign = requestDataEncrypt.sign(e);
            tmp.put("sign", sign);
            log.info("汇享天成请求参数:"+tmp);
            String response = post(url, tmp);
            return ResponseDataWrapper.parse(response, responseDataVerify);
        } catch (Exception var10) {
            throw new RuntimeException(var10);
        }
    }
}

