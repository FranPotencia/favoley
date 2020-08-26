package com.mkyong.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public List<ArbitroActualizado> getAllArbitros() {

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<ArbitroActualizado> listaArbitros = new ArrayList<ArbitroActualizado>();

		listaArbitros = arbitroActualizadoDAO.findAll();

		if (!CollectionUtils.isEmpty(listaArbitros)) {
			for (ArbitroActualizado arbitroActualizado : listaArbitros) {
//				arbitroActualizado.setFechaNacimientoFormateada(dateFormat.format(arbitroActualizado.getFechaNacimiento()));
			}
		}

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

		arbitro.setFechaNacimientoFormateada(dateFormat.format(arbitro.getFechaNacimiento()));
		
		return arbitro;
	}

	public void deleteArbitro(Long id) {
		arbitroActualizadoDAO.delete(id);
	}
}
