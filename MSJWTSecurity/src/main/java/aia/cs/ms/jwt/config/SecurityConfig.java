package aia.cs.ms.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import aia.cs.ms.jwt.models.JwtRequestFilter;

//import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtRequestFilter jwtRequestFilter;


	/*
	 @Override protected void configure(HttpSecurity http) throws Exception{
	 http.cors(); http.csrf().disable(); http.authorizeRequests()
	 .antMatchers("/admin").hasRole("ADMIN")
	 .antMatchers("/user").hasAnyRole("ADMIN","USER")
	 .antMatchers("/login").permitAll()
	 .antMatchers("/").permitAll().and().formLogin(); }
	 */
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/login","/h2-console/*").permitAll().
						anyRequest().authenticated().and().
						exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
	 
	/*
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register").permitAll().antMatchers("/welcome")
				.hasAnyRole("USER", "ADMIN").antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
				.antMatchers("/addNewEmployee").hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();

		http.csrf().disable();
	}
	 java brain
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}*/
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws  Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new CustomPasswordEncoder());
    }
	
	@Bean
	AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * CustomPasswordEncoder(); }
	 */
}
