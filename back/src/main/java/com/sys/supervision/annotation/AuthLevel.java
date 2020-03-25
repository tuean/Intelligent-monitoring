package com.sys.supervision.annotation;

import com.sys.supervision.enums.RoleEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthLevel {

    RoleEnum[] value();


}
