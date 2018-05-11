package com.duowan.featuresActivity.bean

import com.duowan.mobile.main.feature.Feature
import com.duowan.mobile.main.feature.wrapper.FeatureWrapper

/**
 * Created by zhengyongxin on 2018/5/10.
 */
data class SelectInfo(val wrapper: FeatureWrapper<Feature, Any>) : BaseInfo() {
    constructor(wrapper: FeatureWrapper<Feature, Any>, type: Int) : this(wrapper) {
        this.type = type
    }
}