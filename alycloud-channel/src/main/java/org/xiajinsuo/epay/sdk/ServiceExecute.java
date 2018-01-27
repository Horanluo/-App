package org.xiajinsuo.epay.sdk;

import java.util.Map;

/**
 * Created by tums on 2017/7/28.
 */
public interface ServiceExecute<RESP> {
    RESP execute(Map<String, Object> paramers);
}