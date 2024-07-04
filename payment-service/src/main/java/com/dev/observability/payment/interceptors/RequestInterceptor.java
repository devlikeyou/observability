package com.dev.observability.payment.interceptors;

import com.dev.observability.payment.exceptions.RequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private final String HEADER_REQUEST_TRACE_ID = "requestTraceId";

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
            throw new RequestException("The 'requestTraceId' header not found in request !");
        }
    }

}
