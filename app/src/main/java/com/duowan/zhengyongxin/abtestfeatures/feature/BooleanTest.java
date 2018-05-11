package com.duowan.zhengyongxin.abtestfeatures.feature;

import com.duowan.mobile.main.annotation.BooleanSetting;
import com.duowan.mobile.main.feature.Feature;

/**
 * Created by zhengyongxin on 2018/5/2.
 */
@BooleanSetting(defValue = true, alias = "BooleanTest的昵称", settingPath = "BooleanTest")
public abstract class BooleanTest implements Feature {
    public abstract String getText();
}
