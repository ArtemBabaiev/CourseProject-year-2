package edu.chnu.library.config;

import edu.chnu.library.security.DisabledPasswordEncoder;
import edu.chnu.library.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 26.09.2022 01:35
 * @class WebSecurityConfig
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DisabledPasswordEncoder passwordEncoder() {
        return new DisabledPasswordEncoder();
    }

    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(webSecurityDebug);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/ui/users/show/**").hasAnyAuthority("ADMIN", "OWNER")
                .antMatchers("/ui/users/create").hasAnyAuthority("ADMIN", "OWNER")
                .antMatchers("/ui/records/show/**").authenticated()
                .antMatchers("/ui/records/create/**").hasAnyAuthority("OPERATOR")
                .antMatchers("/ui/records/return/**").hasAnyAuthority("OPERATOR")
                .antMatchers("/ui/records/delete/**").hasAnyAuthority("OPERATOR")
                .antMatchers("/ui/writtenOffs/show/**").hasAnyAuthority("OPERATOR")
                .antMatchers("/ui/exemplars/show/for/**").hasAnyAuthority("OPERATOR")
                .antMatchers("/ui/query/**").authenticated()
                .antMatchers("/ui/control/**").authenticated()
                .antMatchers("/ui/libraries/show/**").authenticated()
                .antMatchers("/ui/readingRooms/show/**").authenticated()
                .antMatchers("/ui/bookCases/show/**").authenticated()
                .antMatchers("/ui/shelves/show/**").authenticated()
                .antMatchers("/ui/*/create/**").hasAnyAuthority("OPERATOR")
                .antMatchers("/ui/*/edit/**").hasAnyAuthority("OPERATOR")
                .antMatchers("/ui/*/delete/**").hasAnyAuthority("OPERATOR")

                .and()
                .formLogin().loginPage("/login").permitAll().failureUrl("/login-error")
                .and()
                .logout().logoutSuccessUrl("/ui/home").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access/denied")
                .and().csrf().ignoringAntMatchers("/api/**");
        ;
    }
}
