package com.sys.supervision.annotation;

import com.sys.supervision.enums.RoleEnum;

import java.lang.annotation.*;

/**
 * 标注当前方法的调用权限
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthLevel {

    RoleEnum[] value();


}
