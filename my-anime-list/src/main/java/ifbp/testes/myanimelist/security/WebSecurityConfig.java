package ifbp.testes.myanimelist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/anime/newAnime").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/anime/newAnime").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/anime").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/user/signUp").permitAll()
		.antMatchers(HttpMethod.GET,"/user/signUp").permitAll()
		.antMatchers(HttpMethod.GET,"/cart").hasAnyRole("USER")
		.antMatchers(HttpMethod.POST,"/cart").hasAnyRole("USER")
		.antMatchers(HttpMethod.GET,"/").hasAnyRole("ADMIN","USER")
		.anyRequest().authenticated().and().formLogin().loginPage("/entrar").permitAll().and()
		.logout().logoutSuccessUrl("/entrar?logout").permitAll();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication().withUser("Torgate").password("{noop}yaboku123").roles("ADMIN")
//		.and().withUser("Gatetor").password("{noop}dado123").roles("USER");
//		
//	}
//	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder, 
			PasswordEncoder passwordEncoder, 
			ImplementsUserDetailsService userDetails) throws Exception {
		builder
			.userDetailsService(userDetails)
			.passwordEncoder(passwordEncoder);
	} 
	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/images/**","/js/**" ,"/css/**","/templates/**","/drivers/**");
	}
	
	
	
}
