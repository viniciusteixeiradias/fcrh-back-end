package br.gov.prodemge.seplag.fcrhprova.seguranca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
public class LogFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        String contentType = request.getContentType();
        logger.info("SEC: Request URL path : {}, Request content type: {} - {}", path, contentType, getClientIP(request));
        filterChain.doFilter(request, response);
    }
    
    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
