package com.duowan.featuresActivity

import android.content.Context
import com.duowan.featuresActivity.util.SettingStorageUtil
import com.duowan.mobile.main.feature.FeatureStorage

/**
 * Created by zhengyongxin on 2018/5/4.
 */
class SettingActivityStorage(val context: Context, storage: FeatureStorage) : DecoratorFeatureStorage(storage) {

    init {
        SettingStorageUtil.init(context)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return if (SettingStorageUtil.isCustomValueWithKey(key)) {
            SettingStorageUtil.getCustomValueWithKey(key) as Int
        } else {
            storage.getInt(key, defaultValue)
        }
    }

    override fun getString(key: String, defaultValue: String?): String {
        return if (SettingStorageUtil.isCustomValueWithKey(key)) {
            SettingStorageUtil.getCustomValueWithKey(key) as String
        } else {
            storage.getString(key, defaultValue)
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return if (SettingStorageUtil.isCustomValueWithKey(key)) {
            SettingStorageUtil.getCustomValueWithKey(key) as Boolean
        } else {
            storage.getBoolean(key, defaultValue)
        }
    }

    override fun putInt(key: String, value: Int) {
        storage.putInt(key, value)
    }

    override fun putString(key: String, value: String?) {
        storage.putString(key, value)
    }

    override fun putBoolean(key: String?, value: Boolean) {
        storage.putBoolean(key, value)
    }

    override fun isDebug(): Boolean {
        return storage.isDebug
    }
}