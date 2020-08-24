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
import com.mkyong.model.Jornada;
import com.mkyong.model.JornadaDTO;
import com.mkyong.model.Jugador;
import com.mkyong.model.Partido;
import com.mkyong.model.PartidoArbitroDTO;
import com.mkyong.model.PartidoDTO;
import com.mkyong.repository.ArbitroDAO;
import com.mkyong.repository.JornadaDAO;
import com.mkyong.repository.PartidoDAO;

@Service
public class PartidoService {

	@Autowired
	private PartidoDAO partidoDAO;

	@Autowired
	private QueryComponent queryComponent;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void guardarPartidos(List<Partido> partidos) {
		if (!CollectionUtils.isEmpty(partidos)) {
			Jornada jornada = partidos.get(0).getJornada();
			partidoDAO.deleteByJornada(jornada);
			partidoDAO.save(partidos);
		}

	}

	public Partido guardarPartidoDTO(PartidoDTO partidoDTO) throws Exception {
		Partido partido = partidoDAO.findByNumeroPartido(partidoDTO.getNumeroPartido());
		Integer setsLocal = 0;
		Integer setsVisitante = 0;
		if (partidoDTO.getSet1Local() != null && partidoDTO.getSet1Visitante() != null) {
			if (partidoDTO.getSet1Local() > partidoDTO.getSet1Visitante()) {
				if (partidoDTO.getSet1Local().equals(25) && partidoDTO.getSet1Visitante() <= 23) {

				} else if (partidoDTO.getSet1Local() > 25
						&& (partidoDTO.getSet1Local() - partidoDTO.getSet1Visitante()) == 2) {

				} else {
					throw new Exception("Introduzca correctamente los datos del set 1º");
				}
				setsLocal++;
			} else if (partidoDTO.getSet1Visitante() > partidoDTO.getSet1Local()) {
				if (partidoDTO.getSet1Visitante().equals(25) && partidoDTO.getSet1Local() <= 23) {

				} else if (partidoDTO.getSet1Visitante() > 25
						&& (partidoDTO.getSet1Visitante() - partidoDTO.getSet1Local()) == 2) {

				} else {
					throw new Exception("Introduzca correctamente los datos del set 1º");
				}
				setsVisitante++;
			} else {
				throw new Exception("Introduzca correctamente los datos del set 1º");
			}
			partido.setSet1(partidoDTO.getSet1Local() + "/" + partidoDTO.getSet1Visitante());
		} else {
			throw new Exception("Introduzca los datos del set 1º");
		}
		if (partidoDTO.getSet2Local() != null && partidoDTO.getSet2Visitante() != null) {
			if (partidoDTO.getSet2Local() > partidoDTO.getSet2Visitante()) {
				if (partidoDTO.getSet2Local().equals(25) && partidoDTO.getSet2Visitante() <= 23) {

				} else if (partidoDTO.getSet2Local() > 25
						&& (partidoDTO.getSet2Local() - partidoDTO.getSet2Visitante()) == 2) {

				} else {
					throw new Exception("Introduzca correctamente los datos del set 2º");
				}
				setsLocal++;
			} else if (partidoDTO.getSet2Visitante() > partidoDTO.getSet2Local()) {
				if (partidoDTO.getSet2Visitante().equals(25) && partidoDTO.getSet2Local() <= 23) {

				} else if (partidoDTO.getSet2Visitante() > 25
						&& (partidoDTO.getSet2Visitante() - partidoDTO.getSet2Local()) == 2) {

				} else {
					throw new Exception("Introduzca correctamente los datos del set 2º");
				}
				setsVisitante++;
			} else {
				throw new Exception("Introduzca correctamente los datos del set 2º");
			}
			partido.setSet2(partidoDTO.getSet2Local() + "/" + partidoDTO.getSet2Visitante());
		} else {
			throw new Exception("Introduzca los datos del set 2º");
		}
		if (partidoDTO.getSet3Local() != null && partidoDTO.getSet3Visitante() != null) {
			if (partidoDTO.getSet3Local() > partidoDTO.getSet3Visitante()) {
				if (partidoDTO.getSet3Local().equals(25) && partidoDTO.getSet3Visitante() <= 23) {

				} else if (partidoDTO.getSet3Local() > 25
						&& (partidoDTO.getSet3Local() - partidoDTO.getSet3Visitante()) == 2) {

				} else {
					throw new Exception("Introduzca correctamente los datos del set 3º");
				}
				setsLocal++;
			} else if (partidoDTO.getSet3Visitante() > partidoDTO.getSet3Local()) {
				if (partidoDTO.getSet3Visitante().equals(25) && partidoDTO.getSet3Local() <= 23) {

				} else if (partidoDTO.getSet3Visitante() > 25
						&& (partidoDTO.getSet3Visitante() - partidoDTO.getSet3Local()) == 2) {

				} else {
					throw new Exception("Introduzca correctamente los datos del set 3º");
				}
				setsVisitante++;
			} else {
				throw new Exception("Introduzca correctamente los datos del set 3º");
			}
			partido.setSet3(partidoDTO.getSet3Local() + "/" + partidoDTO.getSet3Visitante());
		} else {
			throw new Exception("Introduzca los datos del set 3º");
		}

		if (setsLocal < 3 && setsVisitante < 3) {
			if (partidoDTO.getSet4Local() != null && partidoDTO.getSet4Visitante() != null) {
				if (partidoDTO.getSet4Local() > partidoDTO.getSet4Visitante()) {
					if (partidoDTO.getSet4Local().equals(25) && partidoDTO.getSet4Visitante() <= 23) {

					} else if (partidoDTO.getSet4Local() > 25
							&& (partidoDTO.getSet4Local() - partidoDTO.getSet4Visitante()) == 2) {

					} else {
						throw new Exception("Introduzca correctamente los datos del set 4º");
					}
					setsLocal++;
				} else if (partidoDTO.getSet4Visitante() > partidoDTO.getSet4Local()) {
					if (partidoDTO.getSet4Visitante().equals(25) && partidoDTO.getSet4Local() <= 23) {

					} else if (partidoDTO.getSet4Visitante() > 25
							&& (partidoDTO.getSet4Visitante() - partidoDTO.getSet4Local()) == 2) {

					} else {
						throw new Exception("Introduzca correctamente los datos del set 4º");
					}
					setsVisitante++;
				} else {
					throw new Exception("Introduzca correctamente los datos del set 4º");
				}
				partido.setSet4(partidoDTO.getSet4Local() + "/" + partidoDTO.getSet4Visitante());
			} else {
				throw new Exception("Introduzca los datos del set 4º");
			}
		}
		if (setsLocal < 3 && setsVisitante < 3) {
			if (partidoDTO.getSet5Local() != null && partidoDTO.getSet5Visitante() != null) {
				if (partidoDTO.getSet5Local() > partidoDTO.getSet5Visitante()) {
					if (partidoDTO.getSet5Local().equals(15) && partidoDTO.getSet5Visitante() <= 13) {

					} else if (partidoDTO.getSet5Local() > 15
							&& (partidoDTO.getSet5Local() - partidoDTO.getSet5Visitante()) == 2) {

					} else {
						throw new Exception("Introduzca correctamente los datos del set 5º");
					}
					setsLocal++;
				} else if (partidoDTO.getSet5Visitante() > partidoDTO.getSet5Local()) {
					if (partidoDTO.getSet5Visitante().equals(15) && partidoDTO.getSet5Local() <= 13) {

					} else if (partidoDTO.getSet5Visitante() > 15
							&& (partidoDTO.getSet5Visitante() - partidoDTO.getSet5Local()) == 2) {

					} else {
						throw new Exception("Introduzca correctamente los datos del set 5º");
					}
					setsVisitante++;
				} else {
					throw new Exception("Introduzca correctamente los datos del set 5º");
				}
				partido.setSet5(partidoDTO.getSet5Local() + "/" + partidoDTO.getSet5Visitante());
			} else {
				throw new Exception("Introduzca los datos del set 5º");
			}
		}

		if (partidoDTO.getResultadoLocal() != null && partidoDTO.getResultadoVisitante() != null) {
			if (partidoDTO.getResultadoLocal() == setsLocal && partidoDTO.getResultadoVisitante() == setsVisitante) {

			} else {
				throw new Exception("No corresponde el resultado final con los datos introducidos");
			}
		} else {
			throw new Exception("Introduzca el resultado final");
		}

		partido.setResultado(partidoDTO.getResultadoLocal() + "-" + partidoDTO.getResultadoVisitante());

		return partidoDAO.save(partido);

	}

	public void guardarPartidosArbitros(JornadaDTO jornadaDTO) {
		if (!CollectionUtils.isEmpty(jornadaDTO.getListaPartidoDTO())) {
			for (PartidoDTO partidoDTO : jornadaDTO.getListaPartidoDTO()) {
				Partido partido = partidoDAO.findByNumeroPartido(partidoDTO.getNumeroPartido());
				if (partidoDTO.getIdArbitro() != null) {
					partido.setIdArbitro(partidoDTO.getIdArbitro());
				} else {
					partido.setIdArbitro(0L);
				}
				partidoDAO.save(partido);
			}
		}
	}

	public void deleteAll() {
		partidoDAO.deleteAll();
	}

	// Me interesa hacer un GetAllPartidosByID ***** **** ****

	// Devuelve todos los partidos
	public List<Partido> getAllPartidos() {

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<Partido> listaPartidos = new ArrayList<Partido>();

		listaPartidos = partidoDAO.findAll();

		if (!CollectionUtils.isEmpty(listaPartidos)) {
			for (Partido partido : listaPartidos) {
				// String strDate = dateFormat.format(equipo.getFechaAlta());
				// equipo.setFechaAltaFormateada(strDate);
			}
		}

		return listaPartidos;
	}

	public List<PartidoArbitroDTO> getListaPartidoArbitroDTOByIdArbitro(Long idArbitro) {

		List<PartidoArbitroDTO> result = new ArrayList<PartidoArbitroDTO>();

		List<Partido> listaPartido = partidoDAO.findByIdArbitro(idArbitro);

		if (!CollectionUtils.isEmpty(listaPartido)) {
			for (Partido partido : listaPartido) {
				PartidoArbitroDTO partidoArbitroDTO = new PartidoArbitroDTO();
				partidoArbitroDTO.setEquipoLocal(partido.getEquipoLocal());
				partidoArbitroDTO.setEquipoVisitante(partido.getEquipoVisitante());
				partidoArbitroDTO.setNumeroPartido(partido.getNumeroPartido());
				partidoArbitroDTO.setPabellon(queryComponent.getPabellonByNombreEquipo(partido.getEquipoLocal()));
				partidoArbitroDTO.setResultado(partido.getResultado());
				partidoArbitroDTO.setSet1(partido.getSet1());
				partidoArbitroDTO.setSet2(partido.getSet2());
				partidoArbitroDTO.setSet3(partido.getSet3());
				partidoArbitroDTO.setSet4(partido.getSet4());
				partidoArbitroDTO.setSet5(partido.getSet5());
				result.add(partidoArbitroDTO);
			}
		}

		return result;
	}

	public Partido getPartidoById(Long id) { // Devuelve partido a través del ID

		Partido partido = partidoDAO.findOne(id);

		return partido;
	}

	public void deleteJornada(Long id) {
		partidoDAO.delete(id);
	}

}
