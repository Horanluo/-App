package org.xiajinsuo.epay.sdk;

/**
 * Created by tums on 2017/7/28.
 */

public interface DataSignable<T> {
    String sign(RRParams var1, T var2);

    boolean verify(RRParams var1, T var2, String var3);
}
