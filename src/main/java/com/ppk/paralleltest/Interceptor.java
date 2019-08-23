package com.ppk.paralleltest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Interceptor extends HandlerInterceptorAdapter {

  private static Logger logger = LoggerFactory.getLogger(Interceptor.class);

  private static final String START_TIME_ATTR = "start_time";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    request.setAttribute(START_TIME_ATTR, System.currentTimeMillis());
    return super.preHandle(request, response, handler);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    long startTime = (long) request.getAttribute(START_TIME_ATTR);
    logger.info("Process Time : [{}] ms", System.currentTimeMillis() - startTime);
  }
}
