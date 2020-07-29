package com.mkyong.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	 
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	public static final String SUPER_SECRET_KEY = "secreto123456654321";
	public static final long TOKEN_EXPIRATION_TIME = 60000000; // 1 dia
	
	/**
	 * Esta clase extiende de BasicAuthenticationFilter y debes inicializar la clase padre con un AuthenticationManger llamando a super()
	 * @param authManager
	 */
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	/**
	 * Cada petición que necesite token, ya sea pasado por parametro en la url o en la cabecera del http,
	 * se recupera con el req.getParameter o req.getHeader(funciona como un mapa, buscas por el nombre del parametro y te 
	 * devuelve el valor)y en el caso que no haya ninguna informacion de token hará un return;, es decir, te mandará a la pantalla
	 * de /error diciendote que no tienes acceso. Si recupera token te dará una autenticacion válida y accedera a la ruta indicada.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
        
		String token=TOKEN_BEARER_PREFIX;
		if(req.getParameter("token")!=null) {
			token=token.concat(req.getParameter("token"));
		} else {
			String header = req.getHeader(HEADER_AUTHORIZACION_KEY);
			if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
				chain.doFilter(req, res);
				return;
			}
			token=req.getHeader(HEADER_AUTHORIZACION_KEY);
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(req, res);
	}
	
	/**
	 * Aquí se crea la autenticación, no hace nada más
	 * @param token
	 * @return
	 */

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (token != null) {
			// Se procesa el token y se recupera el usuario.
			String user = Jwts.parser()
						.setSigningKey(SUPER_SECRET_KEY)
						.parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""))
						.getBody()
						.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}
