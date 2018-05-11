package com.duowan.zhengyongxin.abtestfeatures

import android.content.Context
import android.content.SharedPreferences
import com.duowan.featuresActivity.SettingActivityStorage

import com.duowan.mobile.main.feature.FeatureStorage
import com.duowan.mobile.main.feature.Features
import com.duowan.mobile.main.feature.FeaturesFactory

/**
 * Created by ericwu on 2017/9/26.
 */

object FeatureManager {
    private val TAG = "FeatureManager"

    fun init() {
        val storage = SettingActivityStorage(MyApplication.getContext(), SPStorage())
        Features.init(storage, FeaturesFactory())
        Features.addFeatureMap(*BuildConfig.FEATURE_INJECT_NAME_ARRAY)
    }

    class SPStorage : FeatureStorage {

        private fun storage(): SharedPreferences {
            return MyApplication.getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE)
        }

        override fun getInt(key: String, defaultValue: Int): Int {
            return try {
                storage().getInt(key, defaultValue)
            } catch (e: Throwable) {
                clearKey(key)
                defaultValue
            }

        }

        override fun getString(key: String, defaultValue: String): String? {
            return try {
                storage().getString(key, defaultValue)
            } catch (e: Throwable) {
                clearKey(key)
                defaultValue
            }

        }

        override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
            return try {
                storage().getBoolean(key, defaultValue)
            } catch (e: Throwable) {
                clearKey(key)
                defaultValue
            }

        }

        override fun putInt(key: String, value: Int) {
            storage().edit().putInt(key, value).apply()
        }

        override fun putString(key: String, value: String) {
            storage().edit().putString(key, value).apply()
        }

        override fun putBoolean(key: String, value: Boolean) {
            storage().edit().putBoolean(key, value).apply()
        }

        override fun isDebug(): Boolean {
            return false
        }

        /**
         * key的类型定义改变时会导致读取转型失败,因此失败后需要清除旧设置的值,恢复到默认值。
         *
         * @param key
         */
        private fun clearKey(key: String) {
            storage().edit().remove(key).apply()
        }
    }
}
