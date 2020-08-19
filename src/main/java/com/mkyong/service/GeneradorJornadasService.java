package com.mkyong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.Jornada;
import com.mkyong.model.Partido;
import com.mkyong.repository.JornadaDAO;

@Service
public class GeneradorJornadasService {

	@Autowired
	private EquipoConfirmadoService equipoConfirmadoService;
	
	@Autowired
	private JornadaService jornadaService;
	
	@Autowired
	private JornadaDAO jornadaDAO;

	
	private int[] equipos;
	private String[][] matriz1,matriz2,jornadas,jornadas2;
	
	//Num de jornadas = (N-1)*2, con N = num equipos. (N-1) es una vuelta.
	
	public void generador() {

		jornadaService.deleteAll();

		List<EquipoConfirmado> equipos = equipoConfirmadoService.getAllEquipos();
		int N=equipos.size();
		if(N%2!=0) {
			EquipoConfirmado equipoDescansa=new EquipoConfirmado();
			equipoDescansa.setNombreEquipo("Descansa");
			equipos.add(equipoDescansa);
			N++;
		}
		matriz1 = new String[N-1][N/2];
		matriz2 = new String[N-1][N/2];
		jornadas = new String[N-1][N/2]; //primera vuelta
		jornadas2 = new String[N-1][N/2]; //segunda vuelta
		
		//Relleno las matrices
		/*   Matriz 1    	 Matriz 2			 
			1   2   3		6   5   4
			4   5   1		6   3   2
			2   3   4		6   1   5
			5   1   2		6   4   3
			3   4   5		6   2   1
			
			Resultado:
			
			J1	6vs1	2vs5	3vs4
			J2	4vs6	5vs3	1vs2
			J3	6vs2	3vs1	4vs5
			J4	5vs6	1vs4	2vs3
			J5	6vs3	4vs2	5vs1
		 */
		
		int cont = 0;
		int cont2 = N-2;
		
		for(int i=0;i<N-1;i++){
			for(int j=0;j<N/2;j++){
				//matriz1
				matriz1[i][j] = String.valueOf(equipos.get(cont).getNombreEquipo());
				cont++;
				if(cont==(N-1)) cont=0;
				
				//matriz2
				if(j==0) matriz2[i][j] = String.valueOf(equipos.get(N-1).getNombreEquipo());
				else {
					matriz2[i][j] = String.valueOf(equipos.get(cont2).getNombreEquipo());
					cont2--;
					if(cont2==-1) cont2 = N-2;
				}
				
				//Elaboro la matriz final de enfrentamientos por jornada (primera vuelta)
				if(j==0){
					if(i%2==0) jornadas[i][j] = matriz2[i][j] + "-" + matriz1[i][j];
					else jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j];
				}
				else jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j];
				
				
				//segunda vuelta - al reves que la primera
				if(j==0){
					if(i%2==0) jornadas2[i][j] = matriz1[i][j] + "-" + matriz2[i][j];
					else jornadas2[i][j] = matriz2[i][j] + "-" + matriz1[i][j];
				}
				else jornadas2[i][j] = matriz2[i][j] + "-" + matriz1[i][j];
				
			}
		}
		
		//Solo para mostrarlo por consola

		List<Jornada> listaJornadas=new ArrayList<Jornada>();
		Long numeroPartido=1L;
		int jorn = 1;
		for(int i=0;i<N-1;i++){
			Jornada jornada=new Jornada();
			List<Partido> listaPartidos=new ArrayList<Partido>();
			for(int j=0;j<N/2;j++){
				jornada.setNumeroJornada(jorn);
				String[] nombreEquipos=jornadas[i][j].split("-");
				Partido partido=new Partido();
				partido.setEquipoLocal(nombreEquipos[0]);
				partido.setEquipoVisitante(nombreEquipos[1]);
				partido.setNumeroPartido(numeroPartido);
				numeroPartido++;
				listaPartidos.add(partido);
//				partido.setJornada(jornada);
			}
			jornada.setPartidos(listaPartidos);
			listaJornadas.add(jornada);
			jorn++;
		}
		
		jorn = N;
		for(int i=0;i<N-1;i++){
			Jornada jornada=new Jornada();
			List<Partido> listaPartidos=new ArrayList<Partido>();
			for(int j=0;j<N/2;j++){
				jornada.setNumeroJornada(jorn);
				String[] nombreEquipos=jornadas2[i][j].split("-");
				Partido partido=new Partido();
				partido.setEquipoLocal(nombreEquipos[0]);
				partido.setEquipoVisitante(nombreEquipos[1]);
				partido.setNumeroPartido(numeroPartido);
				numeroPartido++;
				listaPartidos.add(partido);
//				partido.setJornada(jornada);
			}
			jornada.setPartidos(listaPartidos);
			listaJornadas.add(jornada);
			jorn++;
		}
		
		for (Jornada jornada : listaJornadas) {
			jornadaService.guardarJornada(jornada);
		}
		
	}

	public List<Jornada> getAllJorndas(){
		return jornadaDAO.findAll();
	}
	
}
