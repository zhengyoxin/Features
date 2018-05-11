package com.duowan.zhengyongxin.abtestfeatures.feature

import com.duowan.mobile.main.annotation.IntSetting
import com.duowan.mobile.main.feature.Feature

/**
 * Created by zhengyongxin on 2018/5/3.
 */
@IntSetting(defValue = 2, alias = "IntTest的昵称", settingPath = "IntTest")
abstract class IntTest : Feature {
    abstract fun getText(): String
}