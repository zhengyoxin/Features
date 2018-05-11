package com.duowan.mobile.main.feature.wrapper;

import com.duowan.mobile.main.annotation.BooleanValue;
import com.duowan.mobile.main.feature.Feature;
import com.duowan.mobile.main.feature.FeatureStorage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link BooleanValue}
 *
 * @param <T>
 */
public abstract class BooleanFeatureWrapper<T extends Feature> extends AbstractFeatureWrapper<T, Boolean> {
    private final T[] mInstance;
    private final boolean mEnableDebug;

    public BooleanFeatureWrapper(FeatureStorage storage, String storageKey,
                                 boolean dfValue, Class<T> clz,
                                 String alias, String group) {
        this(storage, storageKey, dfValue, clz, alias, group, false);
    }

    public BooleanFeatureWrapper(FeatureStorage storage, String storageKey,
                                 boolean dfValue, Class<T> clz,
                                 String alias, String group, boolean enableDebug) {
        super(storage, storageKey, dfValue, alias, group);
        mInstance = (T[]) Array.newInstance(clz, 2);
        this.mEnableDebug = enableDebug;
    }

    @Override
    public final T instance() {
        boolean value = storageValue();
        return instanceOfValue(value);
    }

    private T instanceOfValue(boolean value) {
        int index = value ? 1 : 0;
        if (mInstance[index] == null) {
            mInstance[index] = createInstance(value);
        }

        return mInstance[index];
    }

    @Override
    public final Class type() {
        return Boolean.TYPE;
    }

    @Override
    public final Boolean storageValue() {
        return (mEnableDebug && mStorage.isDebug())
                || mStorage.getBoolean(storageKey(), mDefaultValue);
    }

    @Override
    public final void store(Boolean value) {
        boolean old = storageValue();
        if (old != value) {
            mStorage.putBoolean(storageKey(), value);
        }
    }

    public final void store(Integer value) {
        store(value != 0);
    }

    @Override
    public final void storeInstance(T instance) {
        if (instance == mInstance[0]) {
            store(true);
        } else if (instance == mInstance[1]) {
            store(false);
        }
    }

    @Override
    public boolean enableForDebug() {
        return mEnableDebug;
    }

    protected abstract T createInstance(boolean value);

    @Override
    public List<Feature> getAllFeaturesInstance() {
        List<Feature> result = new ArrayList<>();
        result.add(instanceOfValue(true));
        result.add(instanceOfValue(false));
        return result;
    }

    @Override
    public Boolean getValueByIndex(int index) {
        if (index < 0) {
            throw new RuntimeException("index must not small 0");
        }
        return index == 0;
    }
}
