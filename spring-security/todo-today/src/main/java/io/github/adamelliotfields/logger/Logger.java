package io.github.adamelliotfields.logger;

import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Log
public class Logger extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String message = String.format(
        "%s %s %s",
        request.getMethod(),
        request.getRequestURI(),
        response.getStatus()
    );

    log.log(Level.INFO, message);

    return true;
  }
}
