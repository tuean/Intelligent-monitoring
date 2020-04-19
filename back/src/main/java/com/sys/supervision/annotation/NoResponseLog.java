package com.sys.supervision.annotation;

import java.lang.annotation.*;

/**
 * 标注该方法不需要进行返回日志的打印
 *
 * 多见于文件下载
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoResponseLog {


}
