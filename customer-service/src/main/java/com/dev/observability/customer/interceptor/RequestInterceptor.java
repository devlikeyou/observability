package com.dev.observability.customer.interceptor;


import com.dev.observability.customer.constants.ApiConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Log4j2
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

        final String requestTraceId = request.getHeader(ApiConstants.REQUEST_TRACE_ID_HEADER);
        //verifyIfExists(requestTraceId, "Request received without trace");

        //MDC.put(ApiConstants.REQUEST_TRACE_ID_HEADER, requestTraceId);
        log.info("Request received with trace: {} ", requestTraceId);
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, HttpServletResponse response, final Object handler, @Nullable final Exception ex) throws Exception {
        MDC.clear();
    }

}
