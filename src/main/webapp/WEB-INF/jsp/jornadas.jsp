<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

</head>
<body>
	<jsp:include page="complementos/header.jsp"></jsp:include>
	 <!-- id equipo nos hará falta posteriormente -->
	<input type="hidden" id="idEquipo" value="${idEquipo}" >
	<input type="hidden" id="token" value="${token}" >
	<div class="container">
		<h1>Primera División Andaluza</h1>
		<hr>
	</div>

		
		<br>
	<div class="container">
		<h2>Jornada 1</h2>
		<div class="row form-group ">
			<div class="col-xs-12">
				<table id="tablaJornada" class="table" >
					<thead>
						<tr>
							<th>Nº Partido</th>
							<th>Local</th>
							<th>Visitante</th>
							<th>Resultado</th>
							<th>1º Set</th>
							<th>2º Set</th>
							<th>3º Set</th>
							<th>4º Set</th>
							<th>5º Set</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						    <td>1</td>
						    <td>Aguas de Huelva</td>
						    <td>C.V.Utrera</td>
						    <td>3-0</td>
						    <td>25/20</td>
						    <td>25/23</td>
						    <td>25/17</td>
						    <td></td>
						    <td></td>
					   </tr>
					   <tr>
						    <td>2</td>
						    <td>C.V.Local</td>
						    <td>Visitante Volley</td>
						    <td>3-0</td>
						    <td>25/20</td>
						    <td>25/23</td>
						    <td>25/17</td>
						    <td></td>
						    <td></td>
					   </tr>
					    <tr>
						    <td>3</td>
						    <td>Adesa 80</td>
						    <td>Rochelambert</td>
						    <td>3-0</td>
						    <td>25/20</td>
						    <td>25/23</td>
						    <td>25/17</td>
						    <td></td>
						    <td></td>
					   </tr>
					    <tr>
						    <td>4</td>
						    <td>Mairena Vóley</td>
						    <td>Cajasol</td>
						    <td>3-0</td>
						    <td>25/20</td>
						    <td>25/23</td>
						    <td>25/17</td>
						    <td></td>
						    <td></td>
					   </tr>
					</tbody>

				</table>
			</div>
		</div>
		<div class="row form-group ">
		<h2>Clasificación</h2>
			<div class="col-xs-12">
				<table id="tablaJornada" class="table">
					<thead>
						<tr>
							<th>Puesto</th>
							<th>Equipo</th>
							<th>PJ</th>
							<th>PG</th>
							<th>PP</th>
							<th>NP</th>
							<th>SF</th>
							<th>SC</th>
							<th>TF</th>
							<th>TC</th>
							<th>G3</th>
							<th>G2</th>
							<th>P1</th>
							<th>P0</th>
							<th>Puntos</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						    <td>1</td>
						    <td>C.V.Local</td>
						    <th>4</th>
							<th>3</th>
							<th>1</th>
							<th>4</th>
							<th>2</th>
							<th>2</th>
							<th>1</th>
							<th>1</th>
							<th>3</th>
							<th>4</th>
							<th>1</th>
							<th>0</th>
							<th>6</th>
					   </tr>
					   <tr>
						    <td>2</td>
						    <td>Aguas de Huelva</td>
						    <th>4</th>
							<th>2</th>
							<th>2</th>
							<th>1</th>
							<th>5</th>
							<th>3</th>
							<th>2</th>
							<th>2</th>
							<th>1</th>
							<th>4</th>
							<th>1</th>
							<th>4</th>
							<th>4</th>
					   </tr>
					</tbody>

				</table>
			</div>
		</div>
	</div>
 
 
</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
<script type="text/javascript">
	
	$('#listaNavegadorAdministrador').addClass("activeVerde"); //Para que en el navegador aparezca activo esta sección
   
	
	function getEquipo(){
		if($('#idEquipo').val()!=''){
			$.ajax({
				url : '/admin/getEquipoConfirmado/'+$('#idEquipo').val()+'?token='+$('#token').val(),
				method : 'GET',
				success : function(response) {
					$('#nombreEquipo').val(response.nombreEquipo);
					$('#localidad').val(response.localidad);
					$('#provincia').val(response.provincia);
					$('#direccion').val(response.direccion);
					$('#pabellon').val(response.pabellon);
					$('#email').val(response.email);
					$('#movil').val(response.movil);
					$('#primerEntrenador').val(response.primerEntrenador);
					$('#escudo').val(response.escudo);
					
					//Cuando muestro un equipo, añado a la vista todos los jugadores que pertenecen a un equipo, llamando a la función addJugadorReal
					for (var i = 0; i < response.jugadores.length; i++) {
						addJugadorReal(response.jugadores[i]);
					}
				},
				error : function() {
					alert('Error inesperado');
				}
			});
		}
		
	}
	

	
	
</script>
