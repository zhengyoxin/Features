package com.duowan.mobile.main.feature;

import com.duowan.mobile.main.feature.wrapper.FeatureWrapper;

import java.util.Map;

/**
 * Created by ericwu on 2017/9/29.
 */

public interface FeatureMapSyringe {
    void injectFeatureInto(Map<String, Class<? extends Feature>> feature, Map<Class<? extends Feature>, Class<? extends FeatureWrapper>> wrappers);

}
