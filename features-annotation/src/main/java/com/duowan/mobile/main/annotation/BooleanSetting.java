package com.duowan.mobile.main.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 张宇 on 2017/9/23.
 * E-mail: zhangyu4@yy.com
 * YY: 909017428
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Inherited
@Setting(BooleanValue.class)
public @interface BooleanSetting {
    boolean defValue();

    String alias();

    String settingPath();
}
