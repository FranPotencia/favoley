package com.mkyong.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.Arbitro;
import com.mkyong.model.ArbitroConfirmado;
import com.mkyong.model.Equipo;
import com.mkyong.model.Jugador;
import com.mkyong.repository.ArbitroDAO;

@Service
public class ArbitroService {

	@Autowired
	private ArbitroDAO arbitroDAO;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void guardarArbitro(Arbitro arbitro) {

				arbitroDAO.save(arbitro);
	}
	
	
	public List<Arbitro> getAllArbitros() {

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<Arbitro> listaArbitros = new ArrayList<Arbitro>();

		listaArbitros = arbitroDAO.findAll();

		if (!CollectionUtils.isEmpty(listaArbitros)) {
			for (Arbitro arbitro : listaArbitros) {
				// String strDate = dateFormat.format(equipo.getFechaAlta());
				// equipo.setFechaAltaFormateada(strDate);
			}
		}
		
		return listaArbitros;
	}
	
	
	public Arbitro getArbitroById(Long id) { //Devuelve arbitro a través del ID
		
		Arbitro arbitro=arbitroDAO.findOne(id);
		
		return arbitro;
	}
	
	public void deleteArbitro(Long id) {
		arbitroDAO.delete(id);
	}

}
