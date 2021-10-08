package br.gov.prodemge.seplag.fcrhprova.seguranca;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationConfig {
	
//	private final String PATH_ACTUATOR = "/actuator";
	private final String PATH_AUDIT = "/actuator/auditevents";
	private final String PATH_BEANS = "/actuator/beans";
	private final String PATH_CACHES = "/actuator/caches/*";
	private final String PATH_HEALTH = "/actuator/health/*";
	private final String PATH_CONDITIONS = "/actuator/conditions";
	private final String PATH_SHUTDOWN = "/actuator/shutdown";
	private final String PATH_PROPS = "/actuator/configprops";
	private final String PATH_ENV = "/actuator/env/*";
	private final String PATH_INFO = "/actuator/info";
	private final String PATH_LOGGERS = "/actuator/loggers/8";
	private final String PATH_HEADDUMP = "/actuator/heapdump";
	private final String PATH_TREADHDUMP = "/actuator/threaddump";
	private final String PATH_PROMETHEUS = "/actuator/prometheus";
	private final String PATH_SCHEDULED = "/actuator/scheduledtasks";
	private final String PATH_HTTPTRACE = "/actuator/httptrace";
	private final String PATH_MAPPINGS = "/actuator/mappings";
	private final String PATH_SincGen = "/rest/sinc/avro/gen";
	
    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns(PATH_HEALTH, PATH_SHUTDOWN, PATH_SincGen);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ShutdownValidatorFilter> shutdownValidatorFilter() {
        FilterRegistrationBean<ShutdownValidatorFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ShutdownValidatorFilter());
        registrationBean.addUrlPatterns(PATH_SHUTDOWN);
        return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<ActuatorValidatorFilter> actuatorValidatorFilter() {
        FilterRegistrationBean<ActuatorValidatorFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ActuatorValidatorFilter());
        registrationBean.addUrlPatterns(PATH_AUDIT);
        registrationBean.addUrlPatterns(PATH_BEANS);
        registrationBean.addUrlPatterns(PATH_CACHES);
        registrationBean.addUrlPatterns(PATH_HEALTH);
        registrationBean.addUrlPatterns(PATH_CONDITIONS);
        registrationBean.addUrlPatterns(PATH_PROPS);
        registrationBean.addUrlPatterns(PATH_ENV);
        registrationBean.addUrlPatterns(PATH_INFO);
        registrationBean.addUrlPatterns(PATH_LOGGERS);
        registrationBean.addUrlPatterns(PATH_HEADDUMP);
        registrationBean.addUrlPatterns(PATH_TREADHDUMP);
        registrationBean.addUrlPatterns(PATH_PROMETHEUS);
        registrationBean.addUrlPatterns(PATH_SCHEDULED);
        registrationBean.addUrlPatterns(PATH_HTTPTRACE);
        registrationBean.addUrlPatterns(PATH_MAPPINGS);
        return registrationBean;
    }
}
