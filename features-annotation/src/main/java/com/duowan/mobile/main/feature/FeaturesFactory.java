package com.duowan.mobile.main.feature;


import com.duowan.mobile.main.feature.wrapper.FeatureWrapper;

import java.util.Set;

/**
 * Created by ericwu on 2017/9/26.
 */

public class FeaturesFactory {

    public FeaturesFactory() {

    }

    /**
     * Initialize feature wrapper.
     *
     * @param clz
     * @param storage
     * @param <T>
     * @return
     */
    public final <T extends Feature> FeatureWrapper<T, ?> create(Class<T> clz, FeatureStorage storage) {
        Class<? extends FeatureWrapper> wrapperClass = FeatureHouse.mFeatureWrappers.get(clz);
        try {
            return wrapperClass.getDeclaredConstructor(FeatureStorage.class, Class.class)
                    .newInstance(storage, clz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get feature classes.
     *
     * @return
     */
    public Set<Class<? extends Feature>> featureClasses() {
        return FeatureHouse.mFeatureWrappers.keySet();
    }

    /**
     * Map key to Feature class.
     *
     * @param key
     * @return
     */
    public Class<? extends Feature> map(String key) {
        return FeatureHouse.mFeatureClzs.get(key);
    }
}
