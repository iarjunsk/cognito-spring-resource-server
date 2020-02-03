package com.arjunsk.cognito.oauth.config;

import com.arjunsk.cognito.oauth.store.CognitoAccessTokenConverter;
import java.util.Collections;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerSecurityConfiguration extends ResourceServerConfigurerAdapter {

  private final ResourceServerProperties resource;

  public OAuth2ResourceServerSecurityConfiguration(ResourceServerProperties resource) {
    this.resource = resource;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors();

    http.csrf().disable();

    http.authorizeRequests()
        .antMatchers("/api/public/**")
        .permitAll()
        .antMatchers("/actuator/health")
        .permitAll()
        .anyRequest()
        .authenticated();
  }

  @Bean
  public TokenStore jwkTokenStore() {
    return new JwkTokenStore(
        Collections.singletonList(resource.getJwk().getKeySetUri()),
        new CognitoAccessTokenConverter(),
        null);
  }
}
