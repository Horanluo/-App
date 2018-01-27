package org.xiajinsuo.epay.sdk;

import java.util.Map;

import io.bestpay.framework.base.CodeException;

/**
 * 业务接口抽象
 */
public interface BusinessServiceExecute {
    boolean support(String transType);

    Map execute(RequestDataWrapper requestDataWrapper) throws CodeException;
}