package com.duowan.mobile.main.feature;


import com.duowan.mobile.main.feature.wrapper.FeatureWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ericwu on 2017/9/26.
 */

public class FeaturesImpl {
    private final FeatureStorage mStorage;
    private final FeaturesFactory mFactory;
    private FeatureSyringeService mSyringeService;

    private final ConcurrentHashMap<Class<? extends Feature>, FeatureWrapper> mFeatures = new ConcurrentHashMap<>();

    public FeaturesImpl(FeatureStorage storage, FeaturesFactory factory) {
        this.mStorage = storage;
        this.mFactory = factory;

        mSyringeService = new FeatureSyringeService();
        mSyringeService.init();
    }

    /**
     * 获取给定feature的实例
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T extends Feature> T of(Class<T> clz) {
        return wrapper(clz).instance();
    }

    /**
     * 获取给定feature的封装。
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T extends Feature> FeatureWrapper<T, ?> wrapper(Class<T> clz) {
        FeatureWrapper<T, ?> featureWrapper = mFeatures.get(clz);

        if (featureWrapper == null) {
            featureWrapper = create(clz);

            if (featureWrapper == null) {
                throw new RuntimeException("Feature is not used properly, type:" + clz.getSimpleName());
            }
            mFeatures.put(clz, featureWrapper);
        }

        return featureWrapper;
    }

    private <T extends Feature> FeatureWrapper<T, ?> create(Class<T> clz) {
        return mFactory.create(clz, mStorage);
    }

    public FeatureWrapper wrapper(String key) {
        return wrapper(mFactory.map(key));
    }

    private Set<Class<? extends Feature>> featureClasses() {
        return mFactory.featureClasses();
    }

    public boolean addFeatureMap(String moudleName) {
        return mSyringeService.inject(moudleName, FeatureHouse.mFeatureClzs, FeatureHouse.mFeatureWrappers);
    }

    public List<FeatureWrapper> getFeaturesWrapper() {
        List<FeatureWrapper> result = new ArrayList<>();
        Set<Class<? extends Feature>> featureClasses = featureClasses();
        if (featureClasses != null && !featureClasses.isEmpty()) {
            for (Class<? extends Feature> featureClass : featureClasses) {
                result.add(wrapper(featureClass));
            }
        }
        return result;
    }
}
