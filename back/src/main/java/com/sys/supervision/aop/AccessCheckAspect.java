package com.sys.supervision.aop;


import com.sys.supervision.annotation.AuthLevel;
import com.sys.supervision.annotation.NoAccess;
import com.sys.supervision.config.Constant;
import com.sys.supervision.dao.AccountMapper;
import com.sys.supervision.entity.db.Account;
import com.sys.supervision.enums.AccountStatus;
import com.sys.supervision.enums.RoleEnum;
import com.sys.supervision.helper.AccountHelper;
import com.sys.supervision.helper.TokenCacheHelper;
import com.sys.supervision.model.BaseListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static com.sys.supervision.config.Constant.*;


@Aspect
@Component
@Slf4j
@Order(1)
public class AccessCheckAspect {

    @Autowired
    private AccountMapper accountMapper;


    @Pointcut("execution(public * com.sys.supervision.web.*.*(..))")
    private void pointcut(){}


    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader(Constant.TOKEN_HEADER);


        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        boolean needAccess = true;
        NoAccess noAccess = method.getAnnotation(NoAccess.class);
        AuthLevel authLevel = method.getAnnotation(AuthLevel.class);
        if (noAccess != null && authLevel == null) {
            needAccess = false;
        }

        if (StringUtils.isBlank(token) && needAccess) return BaseListResponse.error(AccessWrongMessage);

        if (needAccess) {
            Account account = TokenCacheHelper.valid(token);
            if (account == null) account = accountMapper.selectByToken(token);
            if (account == null) return BaseListResponse.error(RE_LOGIN, AccessTimeOutMessage);
            if (!AccountStatus.Normal.getCode().equals(account.getStatus())) {
                return BaseListResponse.error(AccountExceptionMessage);
            }
            if (authLevel != null) {
                if (!RoleEnum.check(account.getRole())) return BaseListResponse.error(AuthErrorMessage);
                if (RoleEnum.check(account.getRole(), authLevel.value())) return BaseListResponse.error(AuthErrorMessage);
            }
            AccountHelper.put(account);
        }

        String fullClassName = signature.getDeclaringTypeName();
        String[] classes = fullClassName.split("\\.");
        String name = classes[classes.length - 1].toLowerCase();

//        AccountHelper.put(Account.builder().account("zxt").role(0).build());

        Object object = null;
        try {
            object = point.proceed(point.getArgs());
            return object;
        } catch (Throwable var) {
            throw var;
        } finally {
            // todo
        }
    }

}
