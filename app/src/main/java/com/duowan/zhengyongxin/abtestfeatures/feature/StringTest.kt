package com.duowan.zhengyongxin.abtestfeatures.feature

import com.duowan.mobile.main.annotation.StringSetting
import com.duowan.mobile.main.feature.Feature

/**
 * Created by zhengyongxin on 2018/4/27.
 */
@StringSetting(defValue = "1", alias = "StringTest的昵称", settingPath = "SettingTest")
abstract class StringTest : Feature {
    abstract fun getText(): String
}