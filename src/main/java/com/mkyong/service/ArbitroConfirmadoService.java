package com.mkyong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.Arbitro;
import com.mkyong.model.ArbitroConfirmado;
import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.JugadorConfirmado;
import com.mkyong.repository.ArbitroConfirmadoDAO;

@Service
public class ArbitroConfirmadoService {

	@Autowired
	private ArbitroConfirmadoDAO arbitroConfirmadoDAO;

	// private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public List<ArbitroConfirmado> getAllArbitros() {

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<ArbitroConfirmado> listaArbitros = new ArrayList<ArbitroConfirmado>();

		listaArbitros = arbitroConfirmadoDAO.findAll();

		//if (!CollectionUtils.isEmpty(listaArbitros)) {
			//for (ArbitroConfirmado arbitroConfirmado : listaArbitros) {
				// String strDate = dateFormat.format(equipo.getFechaAlta());
				// equipo.setFechaAltaFormateada(strDate);
			//}
		//}

		return listaArbitros;
	}

	public ArbitroConfirmado guardarArbitro(ArbitroConfirmado arbitro) { // EXPLICAR ESTE MÉTODO

		if (arbitro.getId() != null) { // si el equipo ya ha sido guardado anteriormente (actualizar)

			arbitro = arbitroConfirmadoDAO.save(arbitro);
			return arbitro;
		} else { // si el equipo no ha sido guardado previamente

			arbitro = arbitroConfirmadoDAO.save(arbitro);
			return arbitro;
		}
	}

    public ArbitroConfirmado getArbitroById(Long id) { //Devuelve arbitro a través del ID
		
		ArbitroConfirmado arbitro=arbitroConfirmadoDAO.findOne(id);
		
		return arbitro;
	}
	
	public void deleteArbitro(Long id) {
		arbitroConfirmadoDAO.delete(id);
	}
}
