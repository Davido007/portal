package io.pivotal.microservices.services.web;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Properties;

/**
 * Created by DPLICHTA on 4/13/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String REMEMBER_ME_KEY = "rememberme_key";


    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/index.html", "/login.html",
                "/partials/**", "/template/**", "/", "/error/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()

                .and().cors();
/*                .and()
               .rememberMe()
              .rememberMeServices(rememberMeServices())
                .key(REMEMBER_ME_KEY)
                .and();
        */
    }

/*    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources*//**");
     }

     @Override protected void configure(final HttpSecurity http) throws Exception {
     // @formatter:off
     http
     .csrf().disable()
     .authorizeRequests()
     .antMatchers("/login*","/login*", "/logout*", "/signin*//**", "/signup*//**",
     "/user/registration*", "/registrationConfirm*", "/expiredAccount*", "/registration*",
     "/badUser*", "/user/resendRegistrationToken*" ,"/forgetPassword*", "/user/resetPassword*",
     "/user/changePassword*", "/emailError*", "/resources*/
    /**
     * ","/old/user/registration*","/successRegister*","/qrcode*").permitAll()
     * .antMatchers("/invalidSession*").anonymous()
     * .antMatchers("/user/updatePassword*","/user/savePassword*","/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
     * .anyRequest().hasAuthority("READ_PRIVILEGE")
     * .and()
     * .formLogin()
     * .loginProcessingUrl("/login")
     * //.loginPage("http://127.0.0.1:9000/login")
     * .defaultSuccessUrl("/homepage.html")
     * .failureUrl("/login?error=true")
     * .successHandler(myAuthenticationSuccessHandler)
     * .failureHandler(authenticationFailureHandler)
     * .authenticationDetailsSource(authenticationDetailsSource)
     * .permitAll()
     * .and()
     * .sessionManagement()
     * .invalidSessionUrl("/invalidSession.html")
     * .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
     * .sessionFixation().none()
     * .and()
     * .logout()
     * .logoutSuccessHandler(myLogoutSuccessHandler)
     * .invalidateHttpSession(false)
     * .logoutSuccessUrl("/logout.html?logSucc=true")
     * .deleteCookies("JSESSIONID")
     * .permitAll();
     * // @formatter:on
     * }
     */

    // beans
/*    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        System.out.println("ddddddddddddddddddddddddddddddddd");
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }*/
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }



/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/user*/

    /**
     * ").permitAll()
     * .and()
     * .formLogin()
     * .loginPage("/login")
     * .permitAll()
     * .and()
     * .logout()
     * .permitAll().and()
     * .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
     * //                .csrf().csrfTokenRepository(csrfTokenRepository());
     * }
     */

 /*   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("a").password("a").roles("ADMIN");
    }*/





    /*
        @Bean
        public UserDetailsService userDetailsService() {
            return new MyUserDetailsService();
        }
    */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}