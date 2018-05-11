package com.duowan.mobile.main.feature;

import com.duowan.mobile.main.feature.wrapper.FeatureWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ericwu on 2017/10/6.
 */

public class FeatureSyringeService {
    private static final String PACKAGE = "com.duowan.mobile.setting.";

    private LruCache<String, FeatureMapSyringe> classCache;
    private List<String> blackList;

    public void init() {
        classCache = new LruCache<>(6);
        blackList = new ArrayList<>();
    }

    public boolean inject(String moduleName, Map<String, Class<? extends Feature>> feature, Map<Class<? extends Feature>, Class<? extends FeatureWrapper>> wrappers) {
        String className = PACKAGE+FeatureMapSyringe.class.getSimpleName()+ "$$" + moduleName;
        try {
            if (!blackList.contains(className)) {
                FeatureMapSyringe autowiredHelper = classCache.get(className);
                if (null == autowiredHelper) {  // No cache.
                    autowiredHelper = (FeatureMapSyringe) Class.forName(className).getConstructor().newInstance();
                }
                autowiredHelper.injectFeatureInto(feature, wrappers);
                classCache.put(className, autowiredHelper);
                return true;
            }
            return false;
        } catch (Exception ex) {
            blackList.add(className);    // This instance need not autowired.
            return false;
        }
    }
}
