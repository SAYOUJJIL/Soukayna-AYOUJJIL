package ma.enset.gestionetudiant.security;

import ma.enset.gestionetudiant.security.service.UserDetaileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

// classe de config et instancier au debut
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetaileServiceImpl userDetaileService2;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetaileService2) ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index").permitAll();

        http.authorizeRequests().antMatchers("/delete").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/edit").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/Admin/etudiantForm").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/save").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/Admin/editEtudiant").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/webjars/**", "/js/**", "/images/**","/resources/**", "/static/css/**", "/css/**").permitAll();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/index") ;
        http.csrf().disable();
        //http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
