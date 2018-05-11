package com.duowan.zhengyongxin.abtestfeatures.featureImpl

import com.duowan.mobile.main.annotation.IntValue
import com.duowan.zhengyongxin.abtestfeatures.feature.IntTest

/**
 * Created by zhengyongxin on 2018/5/3.
 */
@IntValue(2)
class IntTestB : IntTest() {
    override fun getName(): String {
        return "方案B"
    }

    override fun getText(): String {
        return "IntTestB"
    }
}