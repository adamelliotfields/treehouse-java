package io.github.adamelliotfields.config;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
  // Support sending up to 10MB through forms
  @Bean
  MultipartConfigElement multipartConfig() {
    MultipartConfigFactory multipartConfig = new MultipartConfigFactory();

    multipartConfig.setMaxFileSize("10MB");
    multipartConfig.setMaxRequestSize("10MB");

    return multipartConfig.createMultipartConfig();
  }
}
