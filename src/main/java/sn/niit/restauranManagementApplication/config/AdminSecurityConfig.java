package sn.niit.restauranManagementApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import sn.niit.restauranManagementApplication.serviceImpl.CustomUserDetailsService;
 
@Configuration
public class AdminSecurityConfig {
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider1() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
 
        return authProvider;
    }
 
    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider1());
 
        String[] resources = new String[]{
                "/", "/menu", "/about-us", "/contact", "/categorie/**", "/detail/**",
                "/loginprocess", "/shoppingCart", "/addToCart", "/updateShoppingCart",
                "/removeCartItem/**", "/clearShoppingCart", "/removeCartItem/**", "/register**", "/save**",
                "/resources/**", "/**/appli/**", "/static/**", "/css/**", "/fonts/**", "/bat/**",
                "/icons/**", "/images/**","/js/**","/**/webjars/**"
          		};
         http.authorizeRequests()
         .antMatchers(resources).permitAll();
          
        http.authorizeRequests()
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.antMatchers("/profile").hasAuthority("USER")
		.antMatchers("/checkout").hasAuthority("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
          .loginPage("/login")
              .usernameParameter("email")
              .loginProcessingUrl("/loginprocess")
              .defaultSuccessUrl("/")
              .permitAll()
          .and()
              .logout()
                  .logoutUrl("/logout")
                  .logoutSuccessUrl("/")
                  .permitAll()
				  .and()
				   .exceptionHandling().accessDeniedPage("/403" )
					;

      	return http.build();
    }
    
 
}

