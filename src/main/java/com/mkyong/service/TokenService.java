package com.mkyong.service;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mkyong.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	public static final String SUPER_SECRET_KEY = "secreto123456654321";
	public static final long TOKEN_EXPIRATION_TIME = 60000000; // 1 dia
	
	/**
	 * Crea el token en base a un usuario, su tipo y su idAcceso
	 * @param usuario
	 * @return
	 */
	public String createToken(Usuario usuario) {
		Long tiempo=(System.currentTimeMillis()+TOKEN_EXPIRATION_TIME)/1000;
		String token = Jwts.builder().setPayload(
				"{\"iat\":1542286078,\"sub\":\""+usuario.getUsername()+"\",\"type\":\""+usuario.getTipo()+"\",\"idAcceso\":\""+usuario.getIdAcceso()+"\",\"exp\":"+tiempo+"}")
				.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
		return token;
	}
	
	/**
	 * Obtiene el nombre de usuario del token
	 * @param token
	 * @return
	 */
	public String getUsuarioToken(String token) {
		String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        //~~~~~~~~~ JWT Header ~~~~~~~//
        Base64 base64Url = new Base64(true);
        //~~~~~~~~~ JWT Body ~~~~~~~//
        String body = new String(base64Url.decode(base64EncodedBody));
        JSONObject jsonObj = new JSONObject(body);
        String usuario=jsonObj.getString("sub");
		return usuario.toUpperCase();
	}
	
	/**
	 * Obtiene el tipo del usuario del token
	 * @param token
	 * @return
	 */
	public String getTipoToken(String token) {
		String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        //~~~~~~~~~ JWT Header ~~~~~~~//
        Base64 base64Url = new Base64(true);
        //~~~~~~~~~ JWT Body ~~~~~~~//
        String body = new String(base64Url.decode(base64EncodedBody));
        JSONObject jsonObj = new JSONObject(body);
        String usuario=jsonObj.getString("type");
		return usuario.toUpperCase();
	}
	
	/**
	 * Obtiene el idAcceso del usuario del token
	 * @param token
	 * @return
	 */
	public String getIdAccesoToken(String token) {
		String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        //~~~~~~~~~ JWT Header ~~~~~~~//
        Base64 base64Url = new Base64(true);
        //~~~~~~~~~ JWT Body ~~~~~~~//
        String body = new String(base64Url.decode(base64EncodedBody));
        JSONObject jsonObj = new JSONObject(body);
        String usuario=jsonObj.getString("idAcceso");
		return usuario.toUpperCase();
	}
}
