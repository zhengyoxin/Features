package com.duowan.mobile.main.feature.wrapper;

import com.duowan.mobile.main.annotation.IntValue;
import com.duowan.mobile.main.feature.Feature;
import com.duowan.mobile.main.feature.FeatureStorage;

/**
 * {@link IntValue}
 *
 * @param <T>
 */
public abstract class IntFeatureWrapper<T extends Feature> extends MixedFeatureWrapper<T, Integer> {
    public IntFeatureWrapper(FeatureStorage storage, String storageKey,
                             Integer dfValue, Class<T> clz, int valueCount,
                             String alias, String group) {
        super(storage, storageKey, dfValue, clz, valueCount, alias, group);
    }

    @Override
    public final Integer storageValue() {
        return mStorage.getInt(storageKey(), mDefaultValue);
    }

    @Override
    protected final void storeValue(Integer value) {
        mStorage.putInt(storageKey(), value);
    }

    @Override
    public final Class type() {
        return Integer.TYPE;
    }
}