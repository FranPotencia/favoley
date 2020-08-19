package com.mkyong.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkyong.model.Arbitro;
import com.mkyong.model.ArbitroActualizado;
import com.mkyong.model.ArbitroConfirmado;
import com.mkyong.model.Equipo;
import com.mkyong.model.EquipoActualizado;
import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.JornadaDTO;
import com.mkyong.model.Usuario;
import com.mkyong.service.ArbitroActualizadoService;
import com.mkyong.service.ArbitroConfirmadoService;
import com.mkyong.service.ArbitroService;
import com.mkyong.service.EquipoActualizadoService;
import com.mkyong.service.EquipoConfirmadoService;
import com.mkyong.service.EquipoService;
import com.mkyong.service.GestionFederativaService;
import com.mkyong.service.JornadaService;
import com.mkyong.service.JugadorService;
import com.mkyong.service.TokenService;
import com.mkyong.service.UsuarioService;

@RestController
@RequestMapping("/admin")
public class PrivateRestController {

	@Autowired
	private EquipoService equipoService;
	@Autowired
	private EquipoActualizadoService equipoActualizadoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private JugadorService jugadorService;
	@Autowired
	private JornadaService jornadaService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private EquipoConfirmadoService equipoConfirmadoService;
	@Autowired
	private ArbitroConfirmadoService arbitroConfirmadoService;
	@Autowired
	private ArbitroActualizadoService arbitroActualizadoService;
	@Autowired
	private GestionFederativaService gestionFederativaService;
	@Autowired
	private ArbitroService arbitroService;

	// método privado/securizado
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> getUsuarios(@RequestParam(required = true, name = "token") String token) {

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				listaUsuarios = usuarioService.getAllUsuarios();
				return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.BAD_REQUEST);
		}
	}

	// ------------------
	// ACTUALIZAR ÁRBITROS
	// ------------------
	@RequestMapping(value = "/peticionActualizarEquipo", method = RequestMethod.POST)
	public ResponseEntity addEquipo(@Valid @RequestBody EquipoActualizado equipoActualizado,
			@RequestParam(required = true, name = "token") String token) {
		try {
			if (tokenService.getTipoToken(token).equals("EQU")) {
				equipoActualizadoService.guardarEquipo(equipoActualizado);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// ------------------
	// ACTUALIZAR ÁRBITROS
	// ------------------
	@RequestMapping(value = "/peticionActualizarArbitro", method = RequestMethod.POST)
	public ResponseEntity addArbitro(@Valid @RequestBody ArbitroActualizado arbitroActualizado,
			@RequestParam(required = true, name = "token") String token) {
		try {
			if (tokenService.getTipoToken(token).equals("ARB")) {
				arbitroActualizadoService.guardarArbitro(arbitroActualizado);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// CONFIRMAR ACTUALIZACION DE ÁRBITRO POR PARTE DE LA FEDERACIÓN
	@RequestMapping(value = "/aceptarArbitroActualizado/{id}", method = RequestMethod.POST)
	public ResponseEntity aceptarArbitroActualizado(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {

		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				gestionFederativaService.actualizarArbitroYNotificar(id);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// DEVOLVER ÁRBITRO ACTUALIZADO POR ID
	@RequestMapping(value = "/getArbitroActualizado/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArbitroActualizado> getArbitroActualizadoById(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		ArbitroActualizado arbitroActualizado = new ArbitroActualizado();
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				arbitroActualizado = arbitroActualizadoService.getArbitroById(id);
				return new ResponseEntity<ArbitroActualizado>(arbitroActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<ArbitroActualizado>(arbitroActualizado, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<ArbitroActualizado>(arbitroActualizado, HttpStatus.BAD_REQUEST);
		}
	}

	// DEVOLVER LISTA DE ÁRBITROS PARA ACTUALIZAR
	@RequestMapping(value = "/getArbitrosActualizar", method = RequestMethod.GET)
	public ResponseEntity<List<ArbitroActualizado>> getArbitrosActualizar(
			@RequestParam(required = true, name = "token") String token) {

		List<ArbitroActualizado> listaArbitrosActualizados = new ArrayList<ArbitroActualizado>();
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				listaArbitrosActualizados = arbitroActualizadoService.getAllArbitros();
				return new ResponseEntity<List<ArbitroActualizado>>(listaArbitrosActualizados, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<ArbitroActualizado>>(listaArbitrosActualizados, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<List<ArbitroActualizado>>(listaArbitrosActualizados, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/aceptarEquipo/{id}", method = RequestMethod.POST)
	public ResponseEntity aceptarEquipo(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {

		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				gestionFederativaService.validarEquipoYNotificar(id);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/aceptarEquipoActualizado/{id}", method = RequestMethod.POST)
	public ResponseEntity aceptarEquipoActualizado(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {

		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				gestionFederativaService.actualizarEquipoYNotificar(id);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@RequestParam(required = true, name = "token") String token) {
		Usuario usuario = new Usuario();
		try {
			String username = tokenService.getUsuarioToken(token);
			usuario = usuarioService.getUsuario(username);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/cambiarPassword", method = RequestMethod.POST)
	public ResponseEntity<Usuario> modificarUsuario(@Valid @RequestBody Usuario usuario) {
		try {
			usuario = usuarioService.modificarContraseña(usuario.getUsername(), usuario.getPassword());
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/guardarJornada", method = RequestMethod.POST)
	public ResponseEntity guardarJornada(@Valid @RequestBody JornadaDTO jornadaDTO,
			@RequestParam(required = true, name = "token") String token) {
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				jornadaService.guardarJornadaPartidos(jornadaDTO);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/crearUsuario", method = RequestMethod.POST)
	public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario,
			@RequestParam(required = true, name = "token") String token) {
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				usuario = usuarioService.createUser(usuario);
				return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
			} else {
				return new ResponseEntity<Usuario>(usuario, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteUsuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteUsuario(@PathVariable("id") String id,
			@RequestParam(required = true, name = "token") String token) {
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				usuarioService.deleteUsuario(id);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// Consigue un equipo no confirmado
	@RequestMapping(value = "/getEquipo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Equipo> getEquipoById(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		Equipo equipo = new Equipo();
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				equipo = equipoService.getEquipoById(id);
				return new ResponseEntity<Equipo>(equipo, HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<Equipo>(equipo, HttpStatus.BAD_REQUEST);
		}
	}

	// Consigue un equipo confirmado
	@RequestMapping(value = "/getEquipoConfirmado/{id}", method = RequestMethod.GET)
	public ResponseEntity<EquipoConfirmado> getEquipoConfirmadoById(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		EquipoConfirmado equipo = new EquipoConfirmado();
		try {
			if (tokenService.getTipoToken(token).equals("EQU") || tokenService.getTipoToken(token).equals("ALL")) {
				equipo = equipoConfirmadoService.getEquipoById(id);
				return new ResponseEntity<EquipoConfirmado>(equipo, HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity<EquipoConfirmado>(equipo, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getEquipos", method = RequestMethod.GET)
	public ResponseEntity<List<Equipo>> getEquipos(@RequestParam(required = true, name = "token") String token) {

		List<Equipo> listaEquipo = new ArrayList<Equipo>();
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				listaEquipo = equipoService.getAllEquipos();
				return new ResponseEntity<List<Equipo>>(listaEquipo, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Equipo>>(listaEquipo, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<List<Equipo>>(listaEquipo, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteJugador/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteJugador(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				jugadorService.deleteJugador(id);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteEquipo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteEquipo(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				equipoService.deleteEquipo(id);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// ÁRBITROS NO CONFIRMADOS
	@RequestMapping(value = "/getArbitros", method = RequestMethod.GET)
	public ResponseEntity<List<Arbitro>> getArbitros(@RequestParam(required = true, name = "token") String token) {

		List<Arbitro> listaArbitros = new ArrayList<Arbitro>();
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				listaArbitros = arbitroService.getAllArbitros();
				return new ResponseEntity<List<Arbitro>>(listaArbitros, HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<List<Arbitro>>(listaArbitros, HttpStatus.BAD_REQUEST);
		}
	}

	// ÁRBITROS CONFIRMADOS
	@RequestMapping(value = "/getArbitrosConfirmados", method = RequestMethod.GET)
	public ResponseEntity<List<ArbitroConfirmado>> getArbitrosConfirmados(
			@RequestParam(required = true, name = "token") String token) {

		List<ArbitroConfirmado> listaArbitros = new ArrayList<ArbitroConfirmado>();
		try {
			if (tokenService.getTipoToken(token).equals("ALL") || tokenService.getTipoToken(token).equals("ARB")) {
				listaArbitros = arbitroConfirmadoService.getAllArbitros();
				return new ResponseEntity<List<ArbitroConfirmado>>(listaArbitros, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<ArbitroConfirmado>>(listaArbitros, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<List<ArbitroConfirmado>>(listaArbitros, HttpStatus.BAD_REQUEST);
		}
	}

	// CONFIRMAR ÁRBITRO POR PARTE DE LA FEDERACIÓN
	@RequestMapping(value = "/aceptarArbitro/{id}", method = RequestMethod.POST)
	public ResponseEntity aceptarArbitro(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {

		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				gestionFederativaService.validarArbitroYNotificar(id);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// DEVOLVER ÁRBITRO NO CONFIRMADO POR ID
	@RequestMapping(value = "/getArbitro/{id}", method = RequestMethod.GET)
	public ResponseEntity<Arbitro> getArbitroById(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		Arbitro arbitro = new Arbitro();
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				arbitro = arbitroService.getArbitroById(id);
				return new ResponseEntity<Arbitro>(arbitro, HttpStatus.OK);
			} else {
				return new ResponseEntity<Arbitro>(arbitro, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<Arbitro>(arbitro, HttpStatus.BAD_REQUEST);
		}
	}

	// BORRAR ÁRBITRO NO CONFIRMADO
	@RequestMapping(value = "/deleteArbitro/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteArbitro(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				arbitroService.deleteArbitro(id);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// DEVOLVER ÁRBITRO CONFIRMADO POR ID
	@RequestMapping(value = "/getArbitroConfirmado/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArbitroConfirmado> getArbitroConfirmadoById(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		ArbitroConfirmado arbitro = new ArbitroConfirmado();
		try {
			if (tokenService.getTipoToken(token).equals("ALL") || tokenService.getTipoToken(token).equals("ARB")) {
				arbitro = arbitroConfirmadoService.getArbitroById(id);
				return new ResponseEntity<ArbitroConfirmado>(arbitro, HttpStatus.OK);
			} else {
				return new ResponseEntity<ArbitroConfirmado>(arbitro, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<ArbitroConfirmado>(arbitro, HttpStatus.BAD_REQUEST);
		}
	}

	// DEVOLVER EQUIPO ACTUALIZADO POR ID
	@RequestMapping(value = "/getEquipoActualizado/{id}", method = RequestMethod.GET)
	public ResponseEntity<EquipoActualizado> getEquipoActualizadoById(@PathVariable("id") Long id,
			@RequestParam(required = true, name = "token") String token) {
		EquipoActualizado equipoActualizado = new EquipoActualizado();
		try {
			equipoActualizado = equipoActualizadoService.getEquipoById(id);
			return new ResponseEntity<EquipoActualizado>(equipoActualizado, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<EquipoActualizado>(equipoActualizado, HttpStatus.BAD_REQUEST);
		}
	}

	// DEVOLVER LISTA DE ÁRBITROS PARA ACTUALIZAR
	@RequestMapping(value = "/getEquiposActualizar", method = RequestMethod.GET)
	public ResponseEntity<List<EquipoActualizado>> getEquiposActualizar(
			@RequestParam(required = true, name = "token") String token) {

		List<EquipoActualizado> listaEquiposActualizados = new ArrayList<EquipoActualizado>();
		try {
			if (tokenService.getTipoToken(token).equals("ALL")) {
				listaEquiposActualizados = equipoActualizadoService.getAllEquipos();
				return new ResponseEntity<List<EquipoActualizado>>(listaEquiposActualizados, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<EquipoActualizado>>(listaEquiposActualizados, HttpStatus.FORBIDDEN);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<List<EquipoActualizado>>(listaEquiposActualizados, HttpStatus.BAD_REQUEST);
		}
	}
}
