package com.duowan.mobile.main.feature;

import com.duowan.mobile.main.feature.wrapper.FeatureWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ericwu on 2017/9/29.
 */

public class FeatureHouse {

    public static Map<String, Class<? extends Feature>> mFeatureClzs = new HashMap<>();
    public static Map<Class<? extends Feature>, Class<? extends FeatureWrapper>> mFeatureWrappers = new HashMap<>();

    static void clear() {
        mFeatureClzs.clear();
        mFeatureWrappers.clear();
    }
}
