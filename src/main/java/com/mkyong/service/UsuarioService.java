package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mkyong.model.Usuario;
import com.mkyong.repository.UsuarioDAO;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Este metodo tiene truco, si no existe ningun usuario y el que se intenta logar es admin se creará uno de cero
	 * en caso contrario validará las credenciales y creará un token en funcion del tipo de usuario que sea y 
	 * lo de volverá. Retornará null en caso de que ese usuario no exista o sea contraseña errónea
	 * @param usuario
	 * @return
	 */
	public String validaUsuario(Usuario usuario) {

		Usuario realUsuario = usuarioDAO.findOne(usuario.getUsername());
		if (usuario.getUsername().equals("admin") && realUsuario == null) {
			realUsuario = createAdmin();
			String token = tokenService.createToken(realUsuario);
			return token;
		} else if (realUsuario != null) {
			if (bCryptPasswordEncoder.matches(usuario.getPassword(), realUsuario.getPassword())) {
				String token = tokenService.createToken(realUsuario);
				return token;
			} else {
				return null;
			}
		}
		
		return null;

	}

	public Usuario modificarContraseña(String usuario,String password) {
		Usuario usuarioReal=getUsuario(usuario);
		usuarioReal.setPassword(bCryptPasswordEncoder.encode(password));
		usuarioDAO.save(usuarioReal);
		return usuarioReal;
	}
	
	public Usuario getUsuario(String usuario) {
		return usuarioDAO.findOne(usuario);
	}
	
	public void deleteUsuario(String id) {
		usuarioDAO.delete(id);
	}

	public List<Usuario> getAllUsuarios() {
		return usuarioDAO.findAll();
	}

	private Usuario createAdmin() {
		Usuario usuario = new Usuario();
		usuario.setPassword(bCryptPasswordEncoder.encode("admin123456"));
		usuario.setUsername("admin");
		usuario.setTipo("ALL");
		usuario.setIdAcceso("ALL");
		return usuarioDAO.save(usuario);
	}

	public Usuario createUser(Usuario usuario) {
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		return usuarioDAO.save(usuario);
	}
}
