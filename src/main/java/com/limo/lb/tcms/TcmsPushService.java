package com.limo.lb.tcms;

import java.util.Map;

/**
 * The interface Tcms service.
 *
 * @param <T> the type parameter
 * @param <K> the type parameter
 * @param <V> the type parameter
 * @description:
 * @author: libiao
 * @time: 2021 /10/20 16:59
 */
public interface TcmsPushService<T, K, V> {

    /**
     * Operation.
     *
     * @param t the t
     * @param k the k
     */
    void operation(T t, K k);

    /**
     * Push.
     *
     * @param k the k
     * @return the response json
     */
    void push(K k);

    /**
     * To map map.
     *
     * @param v the v
     * @return the map
     */
    Map toMap(V v);
}
