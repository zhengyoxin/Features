package com.duowan.mobile.main.feature.wrapper;


import com.duowan.mobile.main.annotation.IntValue;
import com.duowan.mobile.main.feature.Feature;

import java.util.List;

/**
 * Wrapper class that provide interfaces of a specified feature.
 *
 * @param <T>
 * @param <TYPE>
 */
public interface FeatureWrapper<T extends Feature, TYPE> {
    /**
     * Get instance of current feature value.
     *
     * @return
     */
    T instance();

    String storageKey();

    /**
     * Read persistent value.
     *
     * @return
     */
    TYPE storageValue();

    /**
     * Store persistent value.
     *
     * @param value
     */
    void store(TYPE value);

    /**
     * Store persistent value specified by instance.
     *
     * @param instance
     */
    void storeInstance(T instance);

    /**
     * Value type.
     *
     * @return
     */
    Class type();

    /**
     * Alias of feature.
     *
     * @return
     */
    String alias();

    /**
     * Default is true in debug build mode. Only valid for boolean features.
     *
     * @return
     */
    boolean enableForDebug();

    /**
     * group of features
     *
     * @return
     */
    String group();

    /**
     * get instance of all features
     *
     * @return
     */
    List<Feature> getAllFeaturesInstance();

    TYPE getValueByIndex(int index);
}