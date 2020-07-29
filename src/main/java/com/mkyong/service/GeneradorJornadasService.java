package com.mkyong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.Jornada;
import com.mkyong.model.Partido;

@Service
public class GeneradorJornadasService {

	@Autowired
	private EquipoConfirmadoService equipoConfirmadoService;
	
	@Autowired
	private JornadaService jornadaService;

	public void generador() {

		jornadaService.deleteAll();
		List<EquipoConfirmado> list = equipoConfirmadoService.getAllEquipos();

		List<Jornada> listaJornadas = new ArrayList<Jornada>();
		Integer numeroJornadas = (list.size() - 1) * 2;
		Integer numeroEquipos = list.size() / 2;
		for (int i = 0; i < numeroJornadas; i++) {
			Jornada jornada = new Jornada();
			jornada.setNumeroJornada(i + 1);
			List<Partido> listaPartidos = new ArrayList<Partido>();
			for (int j = 0; j < numeroEquipos; j++) {
				Partido partido = new Partido();

				String equipoLocal = list.get((i + j) % (list.size() - 1)).getNombreEquipo();
				String equipoVisitante = list.get((list.size() - 1 - j + i) % (list.size() - 1)).getNombreEquipo();

				// Last team stays in the same place while the others rotate around it.
				if (i == 0) {
					equipoVisitante = list.get(list.size() - 1).getNombreEquipo();
				}

				// from rounds half interchange the position of teams in rounds, to get both
				// home and away matches
				String mixedRounds;
				if (i < (list.size() - 1)) {
					partido.setEquipoLocal(equipoLocal);
					partido.setEquipoVisitante(equipoVisitante);
					// mixedRounds = ( game.teamHome + " vs " + game.teamAway );
				} else {
					partido.setEquipoLocal(equipoVisitante);
					partido.setEquipoVisitante(equipoLocal);
					// mixedRounds = (game.teamAway + " vs " + game.teamHome);
				}

				listaPartidos.add(partido);
			}
			jornada.setPartidos(listaPartidos);
			listaJornadas.add(jornada);
		}

		for (Jornada jornada2 : listaJornadas) {
			jornadaService.guardarJornada(jornada2);
		}
		
//		this.rounds = new String[(list.size() - 1) * 2][(list.size() / 2)];
//
//		for (int round = 0; round < (list.size() - 1) * 2; round++) {
//			for (int match = 0; match < (list.size() / 2); match++) {
//				Game game = new Game();
//
//				game.teamHome = list.get((round + match) % (list.size() - 1));
//				game.teamAway = list.get((list.size() - 1 - match + round) % (list.size() - 1));
//
//				// Last team stays in the same place while the others rotate around it.
//				if (match == 0) {
//					game.teamAway = list.get(list.size() - 1);
//				}
//
//				// from rounds half interchange the position of teams in rounds, to get both
//				// home and away matches
//				String mixedRounds;
//				if (round < (list.size() - 1)) {
//					mixedRounds = (game.teamHome + " vs " + game.teamAway);
//				} else
//				// interchange the place of teams from half ((teamList.size() - 1)
//				{
//					mixedRounds = (game.teamAway + " vs " + game.teamHome);
//				}
//
//				rounds[round][match] = mixedRounds;
//			}
//		}
//		return rounds;
	}

}
