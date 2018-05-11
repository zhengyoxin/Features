package com.duowan.featuresActivity.bean

/**
 * Created by zhengyongxin on 2018/5/10.
 */
data class TitleInfo(val titleName: String) : BaseInfo() {
    constructor(titleName: String, type: Int) : this(titleName) {
        this.type = type
    }
}