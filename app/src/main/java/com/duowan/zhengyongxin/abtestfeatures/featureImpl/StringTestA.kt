package com.duowan.zhengyongxin.abtestfeatures.featureImpl

import com.duowan.mobile.main.annotation.StringValue
import com.duowan.zhengyongxin.abtestfeatures.feature.StringTest

/**
 * Created by zhengyongxin on 2018/4/27.
 */
@StringValue("1")
class StringTestA : StringTest() {
    override fun getName(): String {
        return "方案A"
    }

    override fun getText(): String {
        return "StringTestA"
    }
}