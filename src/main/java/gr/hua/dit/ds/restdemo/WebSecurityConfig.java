package gr.hua.dit.ds.restdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, authority from authorities where username=?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().cors().and().authorizeRequests() // /*
				.antMatchers("/Users/**").hasRole("ADMIN").antMatchers("/Manager/**").hasRole("MANAGER")
				.antMatchers("/Dayoffs/**").hasRole("SUPERVISOR").antMatchers("/Payroll/**").hasRole("PAYROLL")
				.antMatchers("/User/**").hasRole("USER") // when complete enable this */
				//.antMatchers(HttpMethod.OPTIONS, "/User").permitAll()
				.anyRequest().authenticated().and()
				.csrf().disable().headers().frameOptions().disable();

	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}