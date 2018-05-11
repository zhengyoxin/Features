package com.duowan.mobile.main.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengyongxin on 2018/5/3.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Inherited
@Setting(IntValue.class)
public @interface IntSetting {
    int defValue();

    String alias();

    String settingPath();
}
