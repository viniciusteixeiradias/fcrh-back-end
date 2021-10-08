package br.gov.prodemge.seplag.fcrhprova.seguranca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
public class ShutdownValidatorFilter extends OncePerRequestFilter {
	
	private final Logger logger = LoggerFactory.getLogger(ShutdownValidatorFilter.class);
	
    @Override
    protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException,
      IOException {
    	String yourIP = request.getRemoteAddr();
    	String forwardProxy = request.getHeader("X-Forwarded-For");
    	logger.info("SEC: proxy " + forwardProxy + " - remote " + yourIP + " - test " + getClientIP(request));
    	
    	if((forwardProxy == null)) {
    		if ((!matches(yourIP, "127.0.0.1"))) { // local
            	String path = request.getRequestURI();
                String contentType = request.getContentType();
                logger.info("Acesso a partir desse IP nao permitido - Request URL path : {}, Request content type: {} - {}", path, contentType, yourIP);
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Acesso a partir desse IP nao permitido: " + yourIP);
                return;
            }
    	} else {
    		if ((!matches(getClientIP(request), "10.199.208.0/20"))) { // (VPNDESENV)
            	String path = request.getRequestURI();
                String contentType = request.getContentType();
                logger.info("Acesso a partir desse IP nao permitido - Request URL path : {}, Request content type: {} - {}", path, contentType, yourIP);
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Acesso a partir desse IP nao permitido: " + yourIP);
                return;
            }
    	}
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/actuator/demge".equals(path);
    }
    
    private boolean matches(String ip, String subnet) {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(subnet);
        return ipAddressMatcher.matches(ip);
    }
    
    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
