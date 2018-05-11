package com.duowan.mobile.main.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ericwu on 2017/9/29.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Inherited
@Setting(StringValue.class)
public @interface StringSetting {
    String defValue() default "";

    String alias();

    String settingPath();
}
