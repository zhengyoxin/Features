package com.duowan.mobile.main.feature.wrapper;

import com.duowan.mobile.main.annotation.IntValue;
import com.duowan.mobile.main.annotation.StringValue;
import com.duowan.mobile.main.feature.Feature;
import com.duowan.mobile.main.feature.FeatureStorage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 有不规则值的Feature，如{@link IntValue},{@link StringValue}
 *
 * @param <T>
 */
public abstract class MixedFeatureWrapper<T extends Feature, TYPE> extends AbstractFeatureWrapper<T, TYPE> {
    private final int mValueCount;
    private final T[] mInstance;
    private final Class[] mImplClasses;
    protected final Map<TYPE, Integer> mIndexMap;

    public MixedFeatureWrapper(FeatureStorage storage, String storageKey,
                               TYPE dfValue, Class<T> clz, int valueCount,
                               String alias, String group) {
        super(storage, storageKey, dfValue, alias, group);
        mValueCount = valueCount;
        mInstance = (T[]) Array.newInstance(clz, mValueCount);
        mImplClasses = (Class[]) Array.newInstance(Class.class, mValueCount);
        mIndexMap = new HashMap<>();
        initializeIndex();
    }

    @Override
    public final T instance() {
        if (mIndexMap.isEmpty()) {
            // TODO: 2018/5/9 应该抛出异常
            throw null;
        }

        TYPE value = storageValue();
        Integer index = mIndexMap.get(value);
        return instanceOfIndex(index);
    }

    private T instanceOfIndex(int index) {
        if (mInstance[index] == null) {
            mInstance[index] = createInstance(index);
        }

        return mInstance[index];
    }

    private T createInstance(int index) {
        Class clz = mImplClasses[index];
        try {
            return (T) clz.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * map value to index of instance array.
     *
     * @param value
     * @param index
     */
    protected final void mapIndex(TYPE value, int index, Class<? extends T> implClass) {
        mIndexMap.put(value, index);
        mImplClasses[index] = implClass;
    }

    @Override
    public void store(TYPE value) {
        TYPE oldValue = storageValue();
        if (value.equals(oldValue)) {
            return;
        }

        Set<TYPE> ketSet = mIndexMap.keySet();
        if (ketSet.isEmpty()) {
            //key-value feature
            storeValue(value);
        } else {
            //optional feature
            for (TYPE v : mIndexMap.keySet()) {
                if (v.equals(value)) {
                    storeValue(value);
                }
            }
        }
    }

    @Override
    public void storeInstance(T instance) {
        int index = 0;
        for (; index < mValueCount; index++) {
            if (mInstance[index] == instance) {
                break;
            }
        }

        if (index >= mValueCount) {
            return;
        }

        for (Map.Entry<TYPE, Integer> entry : mIndexMap.entrySet()) {
            if (entry.getValue() == index) {
                store(entry.getKey());
                break;
            }
        }
    }

    protected abstract void initializeIndex();

    /**
     * Store value persistent.
     *
     * @param value
     */
    protected abstract void storeValue(TYPE value);

    @Override
    public List<Feature> getAllFeaturesInstance() {
        List<Feature> result = new ArrayList<>();
        for (int i = 0; i < mValueCount; i++) {
            if (mInstance[i] != null) {
                result.add(mInstance[i]);
            } else {
                result.add(createInstance(i));
            }
        }
        return result;
    }

    @Override
    public TYPE getValueByIndex(int index) {
        if (index < 0) {
            throw new RuntimeException("index must not small 0");
        }
        int count = 0;
        for (TYPE typeValue: mIndexMap.keySet()){
            if (count == index) {
                return typeValue;
            }
            count++;
        }
        return null;
    }
}