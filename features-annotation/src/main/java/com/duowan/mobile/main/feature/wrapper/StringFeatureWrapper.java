package com.duowan.mobile.main.feature.wrapper;

import com.duowan.mobile.main.annotation.StringValue;
import com.duowan.mobile.main.feature.Feature;
import com.duowan.mobile.main.feature.FeatureStorage;

/**
 * {@link StringValue}
 *
 * @param <T>
 */
public abstract class StringFeatureWrapper<T extends Feature> extends MixedFeatureWrapper<T, String> {
    public StringFeatureWrapper(FeatureStorage storage, String storageKey,
                                String dfValue, Class<T> clz, int valueCount,
                                String alias, String group) {
        super(storage, storageKey, dfValue, clz, valueCount, alias, group);
    }

    @Override
    public final String storageValue() {
        return mStorage.getString(storageKey(), mDefaultValue);
    }

    @Override
    protected final void storeValue(String value) {
        mStorage.putString(storageKey(), value);
    }

    @Override
    public final Class type() {
        return String.class;
    }
}