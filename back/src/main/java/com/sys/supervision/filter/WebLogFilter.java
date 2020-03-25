package com.sys.supervision.filter;

import com.sys.supervision.annotation.NoResponseLog;
import com.sys.supervision.entity.db.Account;
import com.sys.supervision.helper.AccountHelper;
import com.sys.supervision.helper.http.SpringRequestWrapper;
import com.sys.supervision.helper.http.SpringResponseWrapper;
import com.sys.supervision.utils.HttpUtil;
import com.sys.supervision.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static com.sys.supervision.utils.HttpUtil.getResponseBody;


@Component
@Slf4j
@Order(2)
public class WebLogFilter implements Filter {

    @Autowired
    private List<HandlerMapping> handlerMappingList;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String ip = IpUtils.getLocalIp(httpServletRequest);

        String requestUrl = httpServletRequest.getRequestURI();



        boolean toLogResponse = true;
        String url = null;
        AntPathMatcher apm = new AntPathMatcher();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            for (String urlPattern : item.getKey().getPatternsCondition().getPatterns()) {
                urlPattern = httpServletRequest.getContextPath() + urlPattern;
                if (apm.match(urlPattern, requestUrl)) {
                    url = urlPattern;

                    HandlerMethod handlerMethod = item.getValue();
                    Method method = handlerMethod.getMethod();
                    toLogResponse = method.getAnnotation(NoResponseLog.class) == null;
                    break;
                }
            }
        }
        if (url == null) {
            url = httpServletRequest.getRequestURI();
        }


        SpringResponseWrapper response = null;
        final SpringRequestWrapper requestWrapper = new SpringRequestWrapper(httpServletRequest);
        String params = HttpUtil.getBodyString(requestWrapper);
//        JSONObject object = JSONObject.parseObject(params);


        try {
            if (toLogResponse) {
                final SpringResponseWrapper responseWrapper = new SpringResponseWrapper(httpServletResponse);
                response = responseWrapper;
                filterChain.doFilter(requestWrapper, responseWrapper);
            } else {
                filterChain.doFilter(requestWrapper, httpServletResponse);
            }

        } catch (Exception var) {
            var.printStackTrace();
        } finally {

            String result = toLogResponse ? getResponseBody(response) : "";

            Account account = AccountHelper.get();
            String user = account == null ? "" : account.getAccount();
            stopWatch.stop();

            log.info("ip:{} user:{} url:{} request:{} response:{} timeCost:{}",
                    ip,
                    user,
                    url,
                    params,
                    result,
                    stopWatch.getTotalTimeMillis() + "ms"
            );
        }
    }

    @Override
    public void destroy() {

    }
}
