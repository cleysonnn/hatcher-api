package org.ayty.hatcher.api;

import org.ayty.hatcher.JWT.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HatcherApiApplication {
	
	@Bean
	public FilterRegistrationBean<TokenFilter> filterJwt() {
		FilterRegistrationBean<TokenFilter> filterRB = new FilterRegistrationBean<TokenFilter>();
		filterRB.setFilter(new TokenFilter());
		filterRB.addUrlPatterns("/api/produtos", "/auth/usuarios");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(HatcherApiApplication.class, args);
	}

}