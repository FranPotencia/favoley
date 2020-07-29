package com.mkyong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mkyong.config.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	/**
	 * Componente para codificar contraseñas, a la hora de insertar usuarios codificará la contraseña. A la hora de validar 
	 * usuarios hay una funcion que valida si la contraseña puesta en el login coincide con la contraseña codificada(matches()). Se hará uso
	 * de eso en UsuarioService
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Spring-security tiene una configuracion por defecto en WebSecurityConfigurerAdapter y aquí se sobreescribe para decirle
	 * a la app qué debe estar securizado, qué no y como debe estarlo. Empezando por las peticiones publicas que son el acceso a los recursos,
	 * y las llamadas cuyo patrón coincide con el raiz (/**). A continuación se se restringirá las urls que empiecen por /admin/**.
	 * Siempre serán pedidas a autenticar y no se hará uso de cookies. Para integrar el uso de JWT se añadirá al final el filtro. 
	 * Desactivamos los cors para poder recibir peticiones desde fuera.
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
        .exceptionHandling().and()
        .anonymous().and()
        .servletApi().and()
        .authorizeRequests()

        // Allow anonymous resource requests
        .antMatchers("/**").permitAll()
        .antMatchers("/css/*.css").permitAll()
        .antMatchers("/images/*.png").permitAll()
        .antMatchers("/webjars/**").permitAll()

        // All other request need to be authenticated
        .antMatchers("/admin/**").authenticated().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
		.cors().and() // JSON WEB TOKEN JWT
		.csrf().disable()
        // Custom Token based authentication based on the header previously given to the client
		.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}

	/**
	 * El manager que genera la autenticacion guarda un usuario admin que lo usará para decir que un token es válido. Después se gestionará
	 * en base a la informacion del token los roles o privilegios.
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
	}

	/**
	 * Lo necesitamos para poder configurar y desactivar los cors
	 * @return
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
