package com.mkyong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.Arbitro;
import com.mkyong.model.ArbitroActualizado;
import com.mkyong.model.ArbitroConfirmado;
import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.JugadorConfirmado;
import com.mkyong.repository.ArbitroActualizadoDAO;
import com.mkyong.repository.ArbitroConfirmadoDAO;

@Service
public class ArbitroActualizadoService {

	@Autowired
	private ArbitroActualizadoDAO arbitroActualizadoDAO;

	// private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public List<ArbitroActualizado> getAllArbitros() {

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<ArbitroActualizado> listaArbitros = new ArrayList<ArbitroActualizado>();

		listaArbitros = arbitroActualizadoDAO.findAll();

		// if (!CollectionUtils.isEmpty(listaArbitros)) {
		// for (ArbitroConfirmado arbitroConfirmado : listaArbitros) {
		// String strDate = dateFormat.format(equipo.getFechaAlta());
		// equipo.setFechaAltaFormateada(strDate);
		// }
		// }

		return listaArbitros;
	}

	public ArbitroActualizado guardarArbitro(ArbitroActualizado arbitroActualizado) {

		if (arbitroActualizado.getId() != null) {

			arbitroActualizado = arbitroActualizadoDAO.save(arbitroActualizado);
			return arbitroActualizado;
		} else {

			arbitroActualizado = arbitroActualizadoDAO.save(arbitroActualizado);
			return arbitroActualizado;
		}
	}

	public ArbitroActualizado getArbitroById(Long id) { // Devuelve arbitro a través del ID

		ArbitroActualizado arbitro = arbitroActualizadoDAO.findOne(id);

		return arbitro;
	}

	public void deleteArbitro(Long id) {
		arbitroActualizadoDAO.delete(id);
	}
}
