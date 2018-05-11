package com.duowan.featuresActivity.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.duowan.mobile.main.feature.Features

@SuppressLint("StaticFieldLeak")
/**
 * Created by zhengyongxin on 2018/5/9.
 */
object SettingStorageUtil {
    private const val SP_KEY = "SP_SETTING_FEATURES_ACTIVITY"
    private lateinit var context: Context
    private lateinit var settingSp: SharedPreferences


    fun init(context: Context) {
        this.context = context
        settingSp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)
    }

    fun setSelectWithKey(key: String, index: Int) {
        settingSp.edit().putInt(key, index).apply()
    }

    fun getSelectIndex(key: String): Int {
        return settingSp.getInt(key, 0)
    }

    fun getCustomValueWithKey(key: String): Any {
        val index = settingSp.getInt(key, 0) - 1
        return Features.getFeaturesWrapper().first {
            it.storageKey() == key
        }.getValueByIndex(index)
    }

    fun isCustomValueWithKey(key: String): Boolean {
        return (settingSp.getInt(key, 0)) > 0
    }
}