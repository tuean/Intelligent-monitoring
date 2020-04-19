package com.sys.supervision.annotation;

import java.lang.annotation.*;

/**
 * 标注该方法不需要进行权限校验
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAccess {

}
