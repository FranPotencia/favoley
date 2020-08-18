package com.mkyong.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkyong.model.Arbitro;
import com.mkyong.model.Equipo;
import com.mkyong.model.EquipoClasificacion;
import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.Jornada;
import com.mkyong.model.Usuario;
import com.mkyong.service.ArbitroService;
import com.mkyong.service.ClasificacionService;
import com.mkyong.service.EquipoConfirmadoService;
import com.mkyong.service.EquipoService;
import com.mkyong.service.GeneradorJornadasService;
import com.mkyong.service.JornadaService;
import com.mkyong.service.UsuarioService;

@RestController
public class PublicRestController {

	@Autowired
	private EquipoService equipoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private JornadaService jornadaService;
	@Autowired
	private EquipoConfirmadoService equipoConfirmadoService;
	@Autowired
	private ArbitroService arbitroService;
	
	@Autowired
	private ClasificacionService clasificacionService;
	
	@Autowired
	private GeneradorJornadasService generadorJornadasService;

	@RequestMapping(value = "/generaJornadas", method = RequestMethod.POST)
	public ResponseEntity generaJornadas() {
		try {
			generadorJornadasService.generador();
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getJornadas", method = RequestMethod.GET)
	public ResponseEntity<List<Jornada>> getJornadas() {

		List<Jornada> listaJornada = new ArrayList<Jornada>();
		try {
			listaJornada = generadorJornadasService.getAllJorndas();
			return new ResponseEntity<List<Jornada>>(listaJornada, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Jornada>>(listaJornada, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getClasificacion", method = RequestMethod.GET)
	public ResponseEntity<List<EquipoClasificacion>> getClasificacion() {

		List<EquipoClasificacion> listaClasificacion = new ArrayList<EquipoClasificacion>();
		try {
			listaClasificacion = clasificacionService.getClasificacion();
			return new ResponseEntity<List<EquipoClasificacion>>(listaClasificacion, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<EquipoClasificacion>>(listaClasificacion, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@RequestMapping(value = "/guardarEquipo", method = RequestMethod.POST)
	public ResponseEntity addEquipo(@Valid @RequestBody Equipo equipo) {
		try {
			equipoService.guardarEquipo(equipo);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getEquiposConfirmados", method = RequestMethod.GET)
	public ResponseEntity<List<EquipoConfirmado>> getEquiposConfirmados() {

		List<EquipoConfirmado> listaEquipo = new ArrayList<EquipoConfirmado>();
		try {
			listaEquipo = equipoConfirmadoService.getAllEquipos();
			return new ResponseEntity<List<EquipoConfirmado>>(listaEquipo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<EquipoConfirmado>>(listaEquipo, HttpStatus.BAD_REQUEST);
		}
	}

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@Valid @RequestBody Usuario usuario) {
		try {
			String token = usuarioService.validaUsuario(usuario);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
	}

	// ------------------
	// MÉTODOS DE ÁRBITRO
	// ------------------
	@RequestMapping(value = "/guardarArbitro", method = RequestMethod.POST)
	public ResponseEntity addArbitro(@Valid @RequestBody Arbitro arbitro) {
		try {
			arbitroService.guardarArbitro(arbitro);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// ------------------
	// MÉTODOS DE JORNADAS
	// ------------------
//	@RequestMapping(value = "/guardarJornadas", method = RequestMethod.POST)
//	public ResponseEntity addJornadas(@Valid @RequestBody List<Jornada> listaJornadas) {
//		try {
//			jornadaService.guardarJornadas(listaJornadas);
//			return new ResponseEntity(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
//	}

}
