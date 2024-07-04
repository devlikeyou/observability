package com.dev.observability.order.interceptors;

import com.dev.observability.order.exceptions.RequestException;
import com.newrelic.api.agent.Trace;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static org.springframework.util.StringUtils.hasText;

@Log4j2
@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private final String HEADER_REQUEST_TRACE_ID = "requestTraceId";
    private final String NO_REQUEST_TRACE_ID = "The 'requestTraceId' header not found in request.";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestTraceIdValue = request.getHeader(HEADER_REQUEST_TRACE_ID);
        validateRequestTraceId(requestTraceIdValue);

        MDC.put(HEADER_REQUEST_TRACE_ID, requestTraceIdValue);
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }

    public void validateRequestTraceId(String requestTraceId) {
        if (!hasText(requestTraceId)) {
            log.error(NO_REQUEST_TRACE_ID);
            throw new RequestException(NO_REQUEST_TRACE_ID);
        }
    }

}
