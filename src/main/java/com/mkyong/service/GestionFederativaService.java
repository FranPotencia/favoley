package com.mkyong.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.model.Arbitro;
import com.mkyong.model.ArbitroActualizado;
import com.mkyong.model.ArbitroConfirmado;
import com.mkyong.model.Equipo;
import com.mkyong.model.EquipoActualizado;
import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.Jugador;
import com.mkyong.model.JugadorActualizado;
import com.mkyong.model.JugadorConfirmado;
import com.mkyong.model.Usuario;

@Service
public class GestionFederativaService {

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private EquipoConfirmadoService equipoConfirmadoService;
	
	@Autowired
	private EquipoActualizadoService equipoActualizadoService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ArbitroService arbitroService;

	@Autowired
	private ArbitroConfirmadoService arbitroConfirmadoService;
	
	@Autowired
	private ArbitroActualizadoService arbitroActualizadoService;
	
	@Transactional
	public void validarEquipoYNotificar(Long id) throws Exception {
		Equipo equipo;
		EquipoConfirmado equipoConfirmado = new EquipoConfirmado();
		equipo = equipoService.getEquipoById(id);
		// tengo que hacer la transformación de equipo a equipo confirmado
		equipoConfirmado.setNombreEquipo(equipo.getNombreEquipo());
		equipoConfirmado.setDireccion(equipo.getDireccion());
		equipoConfirmado.setEmail(equipo.getEmail());
		equipoConfirmado.setFechaAlta(equipo.getFechaAlta());
		equipoConfirmado.setLocalidad(equipo.getLocalidad());
		equipoConfirmado.setMovil(equipo.getMovil());
		equipoConfirmado.setPabellon(equipo.getPabellon());
		equipoConfirmado.setProvincia(equipo.getProvincia());
		equipoConfirmado.setPrimerEntrenador(equipo.getPrimerEntrenador());
		equipoConfirmado.setPhoto(equipo.getPhoto());

		List<JugadorConfirmado> jugadores = new ArrayList<JugadorConfirmado>();
		// 1ºClase/Tipo 2ºNombre variable Tipo 3ºLista que voy a recorrer
		for (Jugador jugador : equipo.getJugadores()) {
			JugadorConfirmado jugadorConfirmado = new JugadorConfirmado();
			jugadorConfirmado.setNombre(jugador.getNombre());
			jugadorConfirmado.setApellidos(jugador.getApellidos());
			jugadorConfirmado.setDni(jugador.getDni());
			jugadorConfirmado.setFechaNacimiento(jugador.getFechaNacimiento());

			jugadores.add(jugadorConfirmado);
		}
		equipoConfirmado.setJugadores(jugadores);

		// Guardo el equipo Confirmado en tabla equipo confirmado
		equipoConfirmado = equipoConfirmadoService.guardarEquipo(equipoConfirmado);

		//EXPLICAR ESTO
		Usuario usuario=new Usuario();
		usuario.setIdAcceso(equipoConfirmado.getId().toString());
		usuario.setPassword(equipoConfirmado.getEmail());
		usuario.setTipo("EQU");
		usuario.setUsername(equipoConfirmado.getEmail());
		usuario = usuarioService.createUser(usuario);

		// Enviamos email de confirmacion
		//emailSenderService.sendEmailEquipo(usuario);

		// Borro equipo de la tabla de equipos pendientes
		equipoService.deleteEquipo(id);
	}
 
	//CONFIRMACIÓN DE LA ACTUALIZACIÓN DATOS EQUIPO
	@Transactional
	public void actualizarEquipoYNotificar(Long id) throws Exception {
		EquipoActualizado equipoActualizado;
		EquipoConfirmado equipoConfirmado = new EquipoConfirmado();
		equipoActualizado = equipoActualizadoService.getEquipoById(id);
		// tengo que hacer la transformación de equipo a equipo confirmado
		equipoConfirmado.setId(equipoActualizado.getId());
		equipoConfirmado.setNombreEquipo(equipoActualizado.getNombreEquipo());
		equipoConfirmado.setDireccion(equipoActualizado.getDireccion());
		equipoConfirmado.setEmail(equipoActualizado.getEmail());
		equipoConfirmado.setFechaAlta(equipoActualizado.getFechaAlta());
		equipoConfirmado.setLocalidad(equipoActualizado.getLocalidad());
		equipoConfirmado.setMovil(equipoActualizado.getMovil());
		equipoConfirmado.setPabellon(equipoActualizado.getPabellon());
		equipoConfirmado.setProvincia(equipoActualizado.getProvincia());
		equipoConfirmado.setPrimerEntrenador(equipoActualizado.getPrimerEntrenador());
		equipoConfirmado.setPhoto(equipoActualizado.getPhoto());

		List<JugadorConfirmado> jugadores = new ArrayList<JugadorConfirmado>();
		// 1ºClase/Tipo 2ºNombre variable Tipo 3ºLista que voy a recorrer
		for (JugadorActualizado jugadorActualizado : equipoActualizado.getJugadores()) {
			JugadorConfirmado jugadorConfirmado = new JugadorConfirmado();
			jugadorConfirmado.setNombre(jugadorActualizado.getNombre());
			jugadorConfirmado.setApellidos(jugadorActualizado.getApellidos());
			jugadorConfirmado.setDni(jugadorActualizado.getDni());
			jugadorConfirmado.setFechaNacimiento(jugadorActualizado.getFechaNacimiento());
			jugadorConfirmado.setId(jugadorActualizado.getId());
			jugadores.add(jugadorConfirmado);
		}
		equipoConfirmado.setJugadores(jugadores);

		// Guardo el equipo Confirmado en tabla equipo confirmado
		equipoConfirmado = equipoConfirmadoService.guardarEquipo(equipoConfirmado);


		// Enviamos email de confirmacion
		//emailSenderService.sendEmailEquipo(usuario);

		// Borro equipo de la tabla de equipos pendientes
		equipoActualizadoService.deleteEquipo(id);
	}
	
	@Transactional
	public void validarArbitroYNotificar(Long id) throws Exception {
		Arbitro arbitro;
		ArbitroConfirmado arbitroConfirmado = new ArbitroConfirmado();
		arbitro = arbitroService.getArbitroById(id);
		// tengo que hacer la transformación de equipo a equipo confirmado
		arbitroConfirmado.setNombre(arbitro.getNombre());
		arbitroConfirmado.setApellidos(arbitro.getApellidos());
		arbitroConfirmado.setDni(arbitro.getDni());
		arbitroConfirmado.setFechaNacimiento(arbitro.getFechaNacimiento());
		arbitroConfirmado.setLocalidad(arbitro.getLocalidad());
		arbitroConfirmado.setMovil(arbitro.getMovil());
		arbitroConfirmado.setEmail(arbitro.getEmail());
		arbitroConfirmado.setDelegacion(arbitro.getDelegacion());
		arbitroConfirmado.setProvincia(arbitro.getProvincia());
		arbitroConfirmado.setLicencia(arbitro.getLicencia());
		arbitroConfirmado.setNumeroLicencia(arbitro.getNumeroLicencia());

	

		// Guardo el arbitro Confirmado en tabla arbitro confirmado
		arbitroConfirmado = arbitroConfirmadoService.guardarArbitro(arbitroConfirmado);

		//EXPLICAR ESTO
		Usuario usuario=new Usuario();
		usuario.setIdAcceso(arbitroConfirmado.getId().toString());
		usuario.setPassword(arbitroConfirmado.getEmail());
		usuario.setTipo("ARB");
		usuario.setUsername(arbitroConfirmado.getEmail());
		usuario = usuarioService.createUser(usuario);

		// Enviamos email de confirmacion
		//emailSenderService.sendEmailArbitro(usuario);

		// Borro equipo de la tabla de equipos pendientes
		arbitroService.deleteArbitro(id);
	}
	
	@Transactional
	public void actualizarArbitroYNotificar(Long id) throws Exception {
		
		ArbitroConfirmado arbitroConfirmado= arbitroConfirmadoService.getArbitroById(id);
		ArbitroActualizado arbitroActualizado = arbitroActualizadoService.getArbitroById(id);
		// tengo que hacer la transformación de equipo a equipo confirmado
		arbitroConfirmado.setNombre(arbitroActualizado.getNombre());
		arbitroConfirmado.setApellidos(arbitroActualizado.getApellidos());
		arbitroConfirmado.setDni(arbitroActualizado.getDni());
		arbitroConfirmado.setFechaNacimiento(arbitroActualizado.getFechaNacimiento());
		arbitroConfirmado.setLocalidad(arbitroActualizado.getLocalidad());
		arbitroConfirmado.setMovil(arbitroActualizado.getMovil());
		arbitroConfirmado.setEmail(arbitroActualizado.getEmail());
		arbitroConfirmado.setDelegacion(arbitroActualizado.getDelegacion());
		arbitroConfirmado.setProvincia(arbitroActualizado.getProvincia());
		arbitroConfirmado.setLicencia(arbitroActualizado.getLicencia());
		arbitroConfirmado.setNumeroLicencia(arbitroActualizado.getNumeroLicencia());

		// Guardo el arbitro Confirmado en tabla arbitro confirmado
		arbitroConfirmado = arbitroConfirmadoService.guardarArbitro(arbitroConfirmado);

		// Enviamos email de confirmacion
		//emailSenderService.sendEmailArbitro(usuario);

		// Borro equipo de la tabla de equipos pendientes
		arbitroActualizadoService.deleteArbitro(id);
	}
}
