package com.its.crawlingtest.config;

import com.its.crawlingtest.config.auth.CustomOAuth2UserService;
import com.its.crawlingtest.config.auth.dto.OAuth2Provider;
import com.its.crawlingtest.config.auth.service.UserService;
import com.its.crawlingtest.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  {

//    private final UserService userService;
//    @Resource
//    private Environment env;
    private final CustomOAuth2UserService customOAuth2UserService;

//    private static String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
//    private static List<String> clients = Arrays.asList("google", "facebook", "github", "kakao", "naver");

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/profile").permitAll()
//                .antMatchers("/login").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
//                .loginPage("/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();

    }

//    @Bean
//    public OAuth2AuthorizedClientService authorizedClientService() {
//        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
//    }
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        List<ClientRegistration> registrations = clients.stream()
//                .map(c -> getRegistration(c))
//                .filter(registration -> registration != null)
//                .collect(Collectors.toList());
//        return new InMemoryClientRegistrationRepository(registrations);
//    }
//
//    private ClientRegistration getRegistration(String client) {
//        String clientId = env.getProperty(
//                CLIENT_PROPERTY_KEY + client + ".client-id");
//
//        // API Client Id 값이 존재하는지 확인하기
//        if (clientId == null) {
//            return null;
//        }
//        String clientSecret = env.getProperty(
//                CLIENT_PROPERTY_KEY + client + ".client-secret");
//
//        if (client.equals("google")) {
//            return OAuth2Provider.GOOGLE.getBuilder(client)
//                    .clientId(clientId)
//                    .clientSecret(clientSecret)
//                    .build();
//
//        }
//        return null;
//    }
}
