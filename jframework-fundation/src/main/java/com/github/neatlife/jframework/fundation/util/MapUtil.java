package com.github.neatlife.jframework.fundation.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 更少的代码封装参数
 *
 * @author suxiaolin
 * @date 2019-03-28 10:57
 */
public class MapUtil {
    public static <K, V> Map<K, V> of() {
        HashMap<K, V> map = new HashMap<>();
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3,
                                      K k4, V v4) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3,
                                      K k4, V v4, K k5, V v5) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3,
                                      K k4, V v4, K k5, V v5, K k6, V v6) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3,
                                      K k4, V v4, K k5, V v5, K k6, V v6,
                                      K k7, V v7) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3,
                                      K k4, V v4, K k5, V v5, K k6, V v6,
                                      K k7, V v7, K k8, V v8) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3,
                                      K k4, V v4, K k5, V v5, K k6, V v6,
                                      K k7, V v7, K k8, V v8, K k9, V v9) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        map.put(k9, v9);
        return map;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3,
                                      K k4, V v4, K k5, V v5, K k6, V v6,
                                      K k7, V v7, K k8, V v8, K k9, V v9,
                                      K k10, V v10) {
        HashMap<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        map.put(k9, v9);
        map.put(k10, v10);
        return map;
    }
}
