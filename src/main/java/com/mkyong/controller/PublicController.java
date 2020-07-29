package com.mkyong.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.service.TokenService;

@Controller
public class PublicController {

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView inicio(Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		devuelveTokens(model, token);
		return new ModelAndView("inicio", model);
	}

	@RequestMapping(value = "/acceso", method = RequestMethod.GET)
	public ModelAndView login(Map<String, Object> model, @RequestParam(required = false, name = "token") String token) {
		devuelveTokens(model, token);
		return new ModelAndView("login", model);
	}

	@RequestMapping(value = "/crearEquipo", method = RequestMethod.GET)
	public ModelAndView crearEquipo(Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) { // EXPLICAR EL MODEL ESTE
		devuelveTokensSinEquipo(model, token);
		return new ModelAndView("formularioEquipo", model);
	}

	@RequestMapping(value = "/equiposConfirmados", method = RequestMethod.GET)
	public ModelAndView equiposConfirmados(Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		devuelveTokens(model, token);
		return new ModelAndView("equiposConfirmados", model);
	}

	// Formulario Nuevo árbitro (PÚBLICO)
	@RequestMapping(value = "/altaArbitro", method = RequestMethod.GET)
	public ModelAndView altaArbitro(Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		devuelveTokens(model, token);
		return new ModelAndView("formularioArbitro", model);
	}

	// PRUEBA DE JORNADA (PÚBLICO)
	@RequestMapping(value = "/jornadas", method = RequestMethod.GET)
	public ModelAndView jornadas(Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		devuelveTokens(model, token);
		return new ModelAndView("jornadas", model);
	}

	// PRUEBA DE FORMULARIO DE JORNADAS (PÚBLICO)
	@RequestMapping(value = "/formularioJornadas", method = RequestMethod.GET)
	public ModelAndView formularioJornada(Map<String, Object> model,
			@RequestParam(required = false, name = "token") String token) {
		devuelveTokens(model, token);
		return new ModelAndView("formularioJornadas", model);
	}

	// TOKENS
	private void devuelveTokensSinEquipo(Map<String, Object> model, String token) {
		if (!StringUtils.isEmpty(token)) {
			model.put("token", token);
			model.put("tokenPublic", "?token=" + token);
			if (tokenService.getTipoToken(token).equals("ALL")) {
				model.put("administrador", true);
			} else if (tokenService.getTipoToken(token).equals("EQU")) {
				model.put("administrador", false);
				model.put("equipo", true);
				model.put("arbitro", false);
			} else {
				model.put("administrador", false);
				model.put("equipo", false);
				model.put("arbitro", true);
				model.put("idArbitro", tokenService.getIdAccesoToken(token));
			}
		}
	}

	// TOKENS
	private void devuelveTokens(Map<String, Object> model, String token) {
		if (!StringUtils.isEmpty(token)) {
			model.put("token", token);
			model.put("tokenPublic", "?token=" + token);
			if (tokenService.getTipoToken(token).equals("ALL")) {
				model.put("administrador", true);
			} else if (tokenService.getTipoToken(token).equals("EQU")) {
				model.put("administrador", false);
				model.put("equipo", true);
				model.put("arbitro", false);
				model.put("idEquipo", tokenService.getIdAccesoToken(token));
			} else {
				model.put("administrador", false);
				model.put("equipo", false);
				model.put("arbitro", true);
				model.put("idArbitro", tokenService.getIdAccesoToken(token));
			}
		}
	}

}