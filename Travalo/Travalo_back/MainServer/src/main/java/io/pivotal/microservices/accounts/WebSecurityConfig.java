package io.pivotal.microservices.accounts;

import com.google.common.collect.ImmutableList;
import io.pivotal.microservices.Security.CustomWebAuthenticationDetailsSource;
import io.pivotal.microservices.Security.RestUnauthorizedEntryPoint;
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
@ComponentScan(basePackages = {"io.pivotal.microservices.Security"})
@CrossOrigin(origins = "http://127.0.0.1:9000")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String REMEMBER_ME_KEY = "rememberme_key";

    /*    @Autowired
        private AuthenticationSuccessHandler myAuthenticationSuccessHandler;*/
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private AuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    private RememberMeServices rememberMeServices;


    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

    public WebSecurityConfig() {
        super();
    }

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

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
                .antMatchers("/searchFriends").permitAll()
                .antMatchers("/flights").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/cars").permitAll()
                .antMatchers("/suggestion").permitAll()
                .antMatchers("/hotels").permitAll()
                .antMatchers("/airports").permitAll()
                .antMatchers("/regitrationConfirm").permitAll()
                .antMatchers("/v2/api-docs").hasAnyAuthority("admin")
                .antMatchers("/users/**").hasAnyAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/authenticate")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll()
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
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
 /*               <property name="host"><value>smtp.gmail.com</value></property>
        <property name="port"><value>587</value></property>
        <property name="protocol"><value>smtp</value></property>
        <property name="username"><value>${mail.username}</value></property>
        <property name="password"><value>${mail.password}</value></property>
        <property name="javaMailProperties">
            <props>
                <prop key="mail.smtp.auth">true</prop>
                <prop key="mail.smtp.starttls.enable">true</prop>
                <prop key="mail.smtp.quitwait">false</prop>
            </props>
        </property>
                mail.protocol=smtp
        mail.host=smtp.gmail.com
        mail.port=465
        mail.smtp.socketFactory.port=465
        mail.smtp.auth=true
        mail.smtp.starttls.enable=true
        mail.smtp.debug=true
        mail.smtp.starttls.required=true
        mail.smtp.socketFactory.fallback=false
        mail.from=XXX@gmail.com
                mail.username=XXX@gmail.com
                mail.password=my_password*/
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
/*        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.port", "587");
        props.put("mail.username", "travalo007@gmail.com");
        props.put("mail.smpt.username", "travalo007@gmail.com");
        props.put("mail.password", "xxqi1nkv");
        props.put("mail.smpt.password", "xxqi1nkv");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.from.email", "travalo007@gmail.com");
        javaMailSender.setJavaMailProperties(props);*/
Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername("travalo007@gmail.com");
        javaMailSender.setPassword("xxqi1nkv");
        javaMailSender.setPort(587);
javaMailSender.setJavaMailProperties(mailProperties);
        return javaMailSender;
    }


    @Bean
    public RememberMeServices rememberMeServices() {
        return new io.pivotal.microservices.Security.RememberMeServices(userDetailsService);
    }

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