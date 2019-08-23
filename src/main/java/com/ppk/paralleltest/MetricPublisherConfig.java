package com.ppk.paralleltest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MetricPublisherConfig implements WebMvcConfigurer {

  private Interceptor interceptor;

  public MetricPublisherConfig(Interceptor interceptor) {
    this.interceptor = interceptor;
  }

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(interceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/**/info")
        .excludePathPatterns("/**/health");
  }

}