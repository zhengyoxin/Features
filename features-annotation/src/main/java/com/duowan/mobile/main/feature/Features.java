package com.duowan.mobile.main.feature;

import com.duowan.mobile.main.feature.wrapper.FeatureWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ericwu on 2017/9/26.
 */

public class Features {
    private static FeaturesImpl instance;

    public static void init(FeatureStorage storage, FeaturesFactory factory) {
        instance = new FeaturesImpl(storage, factory);
    }

    private static <T extends Feature> FeatureWrapper<T, ?> wrapper(Class<T> clz) {
        FeatureWrapper<T, ?> wrapper = instance.wrapper(clz);

        if (wrapper == null) {
            throw new RuntimeException("Feature wrapper of class:" + clz.getSimpleName() + " missing.");
        }

        return wrapper;
    }

    public static <T extends Feature> T of(Class<T> clz) {
        if (instance == null) {
            throw new UnsupportedOperationException("call Features.init().");
        }
        return instance.of(clz);
    }

    public static <V> V get(Class<? extends Feature> clz) {
        FeatureWrapper wrapper = wrapper(clz);
        return (V) wrapper.storageValue();
    }

    public static <T extends Feature> void set(Class<? extends T> clz, Object val) {
        FeatureWrapper wrapper = wrapper(clz);
        wrapper.store(val);
    }

    public static boolean set(String key, Object val) throws ClassCastException, NumberFormatException {
        FeatureWrapper wrapper = instance.wrapper(key);

        try {
            instance.wrapper(key).store(val);
        } catch (Exception e) {
            throw new RuntimeException("Failed to restore value of key:" + key + ", value:" + val);
        }

        return true;
    }

    public static void addFeatureMap(String... moduleNameArray) {
        if (moduleNameArray != null && moduleNameArray.length > 0) {
            for (String moduleName : moduleNameArray) {
                instance.addFeatureMap(moduleName);
            }
        }
    }

//    public static List<Class<? extends Feature>> getFeatures() {
//        Set<Class<? extends Feature>> classes = instance.featureClasses();
//
//        List<Class<? extends Feature>> result = new ArrayList<>();
//        for (Class<? extends Feature> clz : classes) {
//            result.add(clz);
//        }
//        return result;
//    }

    public static List<FeatureWrapper> getFeaturesWrapper() {
        return instance.getFeaturesWrapper();
    }
}
