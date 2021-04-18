package ma.enset.tp1_jpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Qualifier("myUsersDetailService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        System.out.println(passwordEncoder.encode("1234"));
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery
//                ("select username as principal, password as credentials, active from users where username = ?")
//                .authoritiesByUsernameQuery
//                ("select username as principal, role as role from users_roles where username = ?")
//                .rolePrefix("ROLE_")
//                .passwordEncoder(passwordEncoder);

        /*
        auth.inMemoryAuthentication().withUser("user1")
                .password(passwordEncoder.encode("1234")).roles("USER");
//        auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");
        // {noop} pour ne pas crypter le password " NO Oncoder Password "
        auth.inMemoryAuthentication().withUser("user2")
                .password(passwordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin")
                .password(passwordEncoder.encode("1234")).roles("USER","ADMIN");

         */
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin(); // utiliser le formulaire d'authentification par defaut
        http.formLogin().loginPage("/loginPage"); // view pour la page de login
//        http.httpBasic();   // Login form with basic window
        http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
            // CAD : tous les requettes http commancant par "save" and "delete"
            // (exemple savePatient/***) doit etre ADMIN
        http.authorizeRequests().antMatchers("/patient**/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/loginPage","/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
             // CAD : tous les requettes nececisite une autentification
//        http.csrf(); // activer le mechanisme contre les attaques csrf
        // Pour le desactiver :
//        http.csrf().disable();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ return  new BCryptPasswordEncoder(); }
}
