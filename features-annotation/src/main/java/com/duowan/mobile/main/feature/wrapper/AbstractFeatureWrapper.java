package com.duowan.mobile.main.feature.wrapper;


import com.duowan.mobile.main.feature.Feature;
import com.duowan.mobile.main.feature.FeatureStorage;

abstract class AbstractFeatureWrapper<T extends Feature, TYPE> implements FeatureWrapper<T, TYPE> {
    protected final FeatureStorage mStorage;
    protected final String mStorageKey;
    protected final TYPE mDefaultValue;
    private final String mAlias;
    private final String mGroup;

    protected AbstractFeatureWrapper(FeatureStorage storage, String storageKey,
                                     TYPE dfValue, String alias, String group) {
        mStorage = storage;
        mStorageKey = storageKey;
        mDefaultValue = dfValue;
        mAlias = alias;
        mGroup = group;
    }

    @Override
    public final String storageKey() {
        return mStorageKey;
    }

    @Override
    public final String alias() {
        return mAlias;
    }

    @Override
    public final String group() {
        return mGroup;
    }

    @Override
    public boolean enableForDebug() {
        return false;
    }
}