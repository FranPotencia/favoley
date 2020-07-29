package com.mkyong.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.model.Arbitro;
import com.mkyong.model.ArbitroActualizado;
import com.mkyong.model.Equipo;
import com.mkyong.model.EquipoActualizado;
import com.mkyong.service.ArbitroActualizadoService;
import com.mkyong.service.ArbitroService;
import com.mkyong.service.EquipoActualizadoService;
import com.mkyong.service.EquipoService;
import com.mkyong.service.TokenService;

@Controller
@RequestMapping("/admin")
public class PrivateController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private ArbitroService arbitroService;

	@Autowired
	private ArbitroActualizadoService arbitroActualizadoService;

	@Autowired
	private EquipoActualizadoService equipoActualizadoService;

	/**
	 * Cuando se valida el usuario directamente viene aquí. En funcion de la
	 * informacion tipo que tenga el token mostraremos la pantalla usuarios(ALL) y
	 * editar equipo(EQU).
	 * 
	 * A las vistas le paso, según qué usuario es, un modelo donde le insertamos
	 * valores a variables. Las variables administrador, equipo y arbitro las usamos
	 * en el header para mostrar o no los botones del nav. Las variables token e
	 * idEquipo son para hacer peticiones con el token y para acceder informacion a
	 * la que se quiera acceder como la del idEquipo respectivamente.
	 * 
	 * Echa un vistazo a los valores que tienen cuando es un tipo de usuario u otro.
	 * 
	 * Falta desarrollar la vista miEquipo(Seria crear un formulario para editar un
	 * equipoConfirmado) Falta desarrollar una vista para editarUsuario(para arbitro
	 * por ejemplo)
	 * 
	 * @param model
	 * @param token
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView usuarios(Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			List<Equipo> listaEquipos = equipoService.getAllEquipos();
			if (!CollectionUtils.isEmpty(listaEquipos)) {
				model.put("hayEquipos", true);
			} else {
				model.put("hayEquipos", false);
			}
			List<Arbitro> listaArbitros = arbitroService.getAllArbitros();
			if (!CollectionUtils.isEmpty(listaArbitros)) {
				model.put("hayArbitros", true);
			} else {
				model.put("hayArbitros", false);
			}
			List<ArbitroActualizado> listaArbitrosActualizados = arbitroActualizadoService.getAllArbitros();
			if (!CollectionUtils.isEmpty(listaArbitrosActualizados)) {
				model.put("hayArbitrosActualizado", true);
			} else {
				model.put("hayArbitrosActualizado", false);
			}
			List<EquipoActualizado> listaEquiposActualizados = equipoActualizadoService.getAllEquipos();
			if (!CollectionUtils.isEmpty(listaEquiposActualizados)) {
				model.put("hayEquiposActualizado", true);
			} else {
				model.put("hayEquiposActualizado", false);
			}
			model.put("administrador", true);
			model.put("token", token);
			return new ModelAndView("usuarios", model);
		} else if (tokenService.getTipoToken(token).equals("EQU")) {
			model.put("administrador", false);
			model.put("equipo", true);
			model.put("arbitro", false);
			model.put("token", token);
			model.put("idEquipo", tokenService.getIdAccesoToken(token));
			return new ModelAndView("usuarioEquipo", model);
		} else {
			// cambiar a editarse a si mismo si es arbitro
			model.put("administrador", false);
			model.put("equipo", false);
			model.put("arbitro", true);
			model.put("token", token);
			model.put("idArbitro", tokenService.getIdAccesoToken(token));
			return new ModelAndView("arbitro", model);
		}

	}

	/**
	 * A esta vista solo podrá acceder un administrador para ver los equipos que hay
	 * que confirmar
	 * 
	 * @param model
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/equipos", method = RequestMethod.GET)
	public ModelAndView equipos(Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("token", token);
		} else {
			return new ModelAndView("error", model);
		}
		return new ModelAndView("equipos", model);
	}

	/**
	 * Esto hay que retocarlo, de momento esta para que el administrador acepte o
	 * rechace un equipo solamente. un usuario que sea tipo EQU hay que desarrollar
	 * eso.
	 * 
	 * @param id
	 * @param model
	 * @param token
	 * @return
	 */

	// Esto es para ver los detalles de un equipo NO CONFIRMADO
	@RequestMapping(value = "/equipo/{id}", method = RequestMethod.GET)
	public ModelAndView editarEquipo(@PathVariable("id") Long id, Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		// ojo, aquí el id pertenece a la table de equipos por confirmar
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("idEquipo", id);
			model.put("token", token);
			return new ModelAndView("detalleEquipo", model);
			// aquí el id pertenece a la tabla de equipos confirmados
		} else {
			model.put("administrador", false);
			model.put("equipo", true);
			model.put("idEquipo", id);
			model.put("token", token);
			// la idea es que se vaya a una vista que sea editar equipo(otro jsp)
			// porque detalleEquipo es el formulario del administrador para aceptar o
			// rechazar un equipo
			return new ModelAndView("detalleEquipo", model);
		}

	}

	// Esto es para ver los detalles de un equipo CONFIRMADO
	@RequestMapping(value = "/equipoConfirmado/{id}", method = RequestMethod.GET)
	public ModelAndView verMiEquipo(@PathVariable("id") Long id, Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		// ojo, aquí el id pertenece a la tabla de equipos por confirmar
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("idEquipo", id);
			model.put("token", token);
			return new ModelAndView("formularioEquipo", model);
			// aquí el id pertenece a la tabla de equipos confirmados
		} else {
			model.put("administrador", false);
			model.put("equipo", true);
			model.put("idEquipo", id);
			model.put("token", token);
			// la idea es que se vaya a una vista que sea editar equipo(otro jsp)
			// porque detalleEquipo es el formulario del administrador para aceptar o
			// rechazar un equipo
			return new ModelAndView("formularioEquipo", model);
		}
	}

	@RequestMapping(value = "/equipoConfirmado", method = RequestMethod.GET)
	public ModelAndView verMiEquipoSinId(Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		// ojo, aquí el id pertenece a la tabla de equipos por confirmar
		if (tokenService.getTipoToken(token).equals("EQU")) {
			model.put("administrador", false);
			model.put("equipo", true);
			model.put("idEquipo", tokenService.getIdAccesoToken(token));
			model.put("token", token);
			// la idea es que se vaya a una vista que sea editar equipo(otro jsp)
			// porque detalleEquipo es el formulario del administrador para aceptar o
			// rechazar un equipo
			return new ModelAndView("formularioEquipo", model);
		} else {
			return new ModelAndView("error", model);
		}
	}

	// Árbitros NO CONFIRMADOS (PRIVADO)
	@RequestMapping(value = "/listaArbitros", method = RequestMethod.GET)
	public ModelAndView arbitros(Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("token", token);
			return new ModelAndView("arbitros", model);
		} else {
			return new ModelAndView("error", model);
		}

	}

	// Árbitros CONFIRMADOS (PRIVADO)
	@RequestMapping(value = "/arbitrosConfirmados", method = RequestMethod.GET)
	public ModelAndView arbitrosConfirmados(Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("token", token);
			return new ModelAndView("arbitrosConfirmados", model);
		} else if (tokenService.getTipoToken(token).equals("ARB")) {
			model.put("administrador", false);
			model.put("equipo", false);
			model.put("arbitro", true);
			model.put("token", token);
			model.put("idArbitro", tokenService.getIdAccesoToken(token)); // ¿PARA QUÉ MIERDA ES?
			return new ModelAndView("arbitrosConfirmados", model);
		} else {
			return new ModelAndView("error", model);
		}
	}

	// Información árbitro individual (PRIVADO)
	@RequestMapping(value = "/arbitro/{id}", method = RequestMethod.GET)
	public ModelAndView detallesArbitro(@PathVariable("id") Long id, Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("idArbitro", id);
			model.put("token", token);
			return new ModelAndView("detalleArbitro", model);
		} else if (tokenService.getTipoToken(token).equals("ARB")) {
			model.put("administrador", false);
			model.put("arbitro", true);
			model.put("idArbitro", id);
			model.put("token", token);
			return new ModelAndView("detalleArbitro", model);
		} else {
			return new ModelAndView("error", model);
		}
	}

	// Información árbitro Confirmado individual (PRIVADO)
	@RequestMapping(value = "/arbitroConfirmado/{id}", method = RequestMethod.GET)
	public ModelAndView detallesEArbitroConfirmado(@PathVariable("id") Long id, Map<String, Object> model,
			@RequestParam(required = true, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("idArbitro", id);
			model.put("token", token);
			return new ModelAndView("arbitro", model);
		} else if (tokenService.getTipoToken(token).equals("ARB")) {
			model.put("administrador", false);
			model.put("arbitro", true);
			model.put("idArbitro", id);
			model.put("token", token);
			return new ModelAndView("arbitro", model);
		} else {
			return new ModelAndView("error", model);
		}
	}

	// PRUEBA LISTA DE ÁRBITROS PARA ACTUALIZAR
	@RequestMapping(value = "/arbitrosParaActualizar", method = RequestMethod.GET)
	public ModelAndView arbitrosParaActualizar(Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("token", token);
			return new ModelAndView("arbitrosParaActualizar", model);
		} else {
			return new ModelAndView("error", model);
		}

	}

	// PRUEBA DE VENTANA ACTUALIZAR ÁRBITRO (PÚBLICO)
	@RequestMapping(value = "/actualizarArbitro/{id}", method = RequestMethod.GET)
	public ModelAndView ActualizarArbitro(@PathVariable("id") Long id, Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("idArbitro", id);
			model.put("token", token);
			return new ModelAndView("detalleArbitroActualizar", model);
		} else {
			return new ModelAndView("error", model);
		}
	}

	// PRUEBA DE VENTANA ACTUALIZAR EQUIPO (PÚBLICO)
	@RequestMapping(value = "/actualizarEquipo/{id}", method = RequestMethod.GET)
	public ModelAndView actualizarEquipo(@PathVariable("id") Long id, Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("idEquipo", id);
			model.put("token", token);
			return new ModelAndView("detalleEquipoActualizar", model);
		} else {
			return new ModelAndView("error", model);
		}
	}

	// PRUEBA LISTA DE ÁRBITROS PARA CONFIRMAR
	@RequestMapping(value = "/equiposParaActualizar", method = RequestMethod.GET)
	public ModelAndView equiposParaActualizar(Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		model.put("tokenPublic", "?token=" + token);
		if (tokenService.getTipoToken(token).equals("ALL")) {
			model.put("administrador", true);
			model.put("token", token);
			return new ModelAndView("equiposParaActualizar", model);
		} else {
			return new ModelAndView("error", model);
		}
		
	}
}
