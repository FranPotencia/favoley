package com.mkyong.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.ClasificacionComparator;
import com.mkyong.model.EquipoClasificacion;
import com.mkyong.model.EquipoConfirmado;

@Service
public class ClasificacionService {

	@Autowired
	private QueryComponent queryComponent;

	@Autowired
	private EquipoConfirmadoService equipoConfirmadoService;

	public List<EquipoClasificacion> getClasificacion() {
		List<EquipoClasificacion> result = new ArrayList<EquipoClasificacion>();

		List<EquipoConfirmado> listaEquipos = equipoConfirmadoService.getAllEquipos();

		if (!CollectionUtils.isEmpty(listaEquipos)) {
			for (EquipoConfirmado equipoConfirmado : listaEquipos) {
				EquipoClasificacion equipoClasificacion = new EquipoClasificacion();
				equipoClasificacion.setNombre(equipoConfirmado.getNombreEquipo());
				equipoClasificacion
						.setPartidosJugados(queryComponent.getPartidosJugadosByEquipo(equipoClasificacion.getNombre()));
				equipoClasificacion
						.setPartidosGanados(queryComponent.getPartidosGanadosByEquipo(equipoClasificacion.getNombre()));
				equipoClasificacion.setPartidosPerdidos(
						queryComponent.getPartidosPerdidosByEquipo(equipoClasificacion.getNombre()));
				equipoClasificacion.setTanteosFavor(
						(Long.parseLong(queryComponent.getTanteosFavorLocalByEquipo(equipoClasificacion.getNombre())) + Long
								.parseLong(queryComponent.getTanteosFavorVisitanteByEquipo(equipoClasificacion.getNombre())))
								+ "");
				equipoClasificacion.setTanteosContra(
						(Long.parseLong(queryComponent.getTanteosContraLocalByEquipo(equipoClasificacion.getNombre())) + Long
								.parseLong(queryComponent.getTanteosContraVisitanteByEquipo(equipoClasificacion.getNombre())))
								+ "");
				equipoClasificacion.setPuntos(Long.parseLong(equipoClasificacion.getPartidosGanados()) * 3);
				result.add(equipoClasificacion);
			}
			Collections.sort(result, new ClasificacionComparator());
			int i=1;
			for (EquipoClasificacion equipoClasificacion : result) {
				equipoClasificacion.setPosicion(i+"");
				i++;
			}
		}

		return result;
	}
}
