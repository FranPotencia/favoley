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
	<!-- esto habrá que echarle un ojo por si vale o no -->
	<input type="hidden" id="idEquipo" value="${idEquipo}">

	<div class="container">
		<h1>Creación de Jornadas</h1>
	</div>

	<div class="container">
		<div class="row form-group ">
			<div class="col-xs-8">
				<table id="tablaJornadas" class="table">
					<thead>
						<tr>
							<th class="col-xs-3">Nº de Jornada</th>
							<th class="col-xs-3">Fecha (Sábado)</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
			</div>
		</div>
		<div class="row form-group ">
			<div class="col-xs-1">
				<button type="button" onclick="addJornada()"
					class="btn btn-link btn-sm">Añadir Jornada</button>
			</div>
			<div class="col-xs-11">
				<button type="button" onclick="postJornada()" style="float: right;"
					class="btn btn-primary btn-sm">Guardar</button>
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
	$('#listaNavegadorEquipo').addClass("activeVerde"); //Para que en el navegador aparezca activo esta sección

	var idJornada = 0;

	//Jugador nuevo, inexistente en la base de datos
	function addJornada() {
		idJornada++;
		$('#tablaJornadas tbody')
				.append(
						'<tr class="filasNuevas" id="jornada'+idJornada+'">'
						        + '<td><input id="numeroJornada'+idJornada+'" class="form-control input-sm" type="text" value="'+idJornada+'"></td>'
								+ '<td><input id="fecha'+idJornada+'" class="form-control input-sm" type="date"></td>'
								+ '<td><a href="#victorModal" role="button" class="btn btn-danger" data-toggle="modal">Eliminar</a>'
								+ '<div id="victorModal" class="modal fade">'
								+ '<div class="modal-dialog"> '
								+ '<div class="modal-content">'
								+ '<div class="modal-header">'
								+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
								+ '<h4 class="modal-title">¡ATENCIÓN!</h4>'
								+ '</div>'
								+ '<div class="modal-body">'
								+ ' <p>¿Seguro que quieres borrar este jugador</p>'
								+ '<p class="text-warning"><small>Si lo borras, no podrás recuperarlo.</small></p>'
								+ '</div>'
								+ ' <div class="modal-footer">'
								+ ' <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>'
								+ ' <button type="button" class="btn btn-danger"  onclick="deleteJornada(\''
								+ idJornada + '\')" >Eliminar</button>'
								+ '</div>' + '</div>' + '</div>'
								+ '</div></td>'+ '</tr>')
	}

	function deleteJornada(id) {
		$('#jornada' + id).remove();
	}

	function postJornada() {

		//Jornadas que ya se encuentran en la base de datos, y ya tienen un ID real.
		var filasReales = $('.filasReal'); // .filasReal hace llamada a todos los elementos que pertenecen a esa clase
		var jornadas = []; //Creo una tabla en la que voy a introducir tanto las jornadas antiguas como las nuevas(en este caso las antiguas)
		for (var i = 0; i < filasReales.length; i++) {
			var item = filasReales[i].id.split('jornadaReal')[1]; //Extraigo el id de su base de datos, para que se actualicen los posibles cambios "Francisco Barbosa Marín" .split(" ") ["Francisco","Barbosa","Marín"]
			var jornada = {
				"id" : item,
				"numeroJornada" : $('#numeroJornada' + item).val(),
				"fecha" : $('#fecha' + item).val(),
				
			}
			jornadas.push(jornada); //Guardo en la tabla las jornadas antiguas
		}

		//Jornadas nuevas que no se encontraban en la base de datos
		var filasNuevas = $('.filasNuevas'); //#id .class 
		var listJornadas = []; // creo que esta no se usa y se puede eliminar
		for (var i = 0; i < filasNuevas.length; i++) {

			// 			var idFila=filasNuevas[i].id;// jugador1
			// 			var array=idFila.split('jugador'); // ["","1"]
			// 			var item=array[1];// 1		
			//			Esto es igual que la línea siguiente:

			var item = filasNuevas[i].id.split('jornada')[1];
			var jornada = {
				"id" : null,
				"numeroJornada" : $('#numeroJornada' + item).val(),
				"fecha" : $('#fecha' + item).val(),
			}
			jornadas.push(jornada); //añado estas jornadas a la lista general
		}


		$.ajax({
			url : '/guardarJornadas',
			method : 'POST',
			data : JSON.stringify(jornadas), //Aquí se pasa una tabla de jornadas
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				location.href = "/";
			},
			error : function() {
				alert('No se ha podido guardar las jornadas');
			}
		});

	}

	//Jugadores ya existentes
	function addJornadaReal(response) {
		$('#tablaJugadores tbody')
				.append(
						'<tr class="filasReal" id="jugadorReal'+response.id+'">'
						   + '<td><input id="numeroJornada'+response.id+'" class="form-control input-sm" type="text" value="'+response.numeroJornada+'"></td>'
							+ '<td><input id="fecha'+response.id+'" class="form-control input-sm" type="date"></td>'
							+ '<td><a href="#victorModal" role="button" class="btn btn-danger" data-toggle="modal">Eliminar</a>'
							+ '<div id="victorModal" class="modal fade">'
							+ '<div class="modal-dialog"> '
							+ '<div class="modal-content">'
							+ '<div class="modal-header">'
							+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
							+ '<h4 class="modal-title">¡ATENCIÓN!</h4>'
							+ '</div>'
							+ '<div class="modal-body">'
							+ ' <p>¿Seguro que quieres borrar este jugador</p>'
							+ '<p class="text-warning"><small>Si lo borras, no podrás recuperarlo.</small></p>'
							+ '</div>'
							+ ' <div class="modal-footer">'
							+ ' <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>'
							+ ' <button type="button" class="btn btn-danger"  onclick="deleteJornada(\''
							+ idJornada + '\')" >Eliminar</button>'
							+ '</div>' + '</div>' + '</div>'
							+ '</div></td>'+ '</tr>')
	}

	function deleteJugadorReal(id) {
		$.ajax({
			url : '/deleteJornada/' + id,
			method : 'DELETE',
			success : function(response) {
				$('#jornadaReal' + id).remove();
			},
			error : function(response) {
				alert('No se ha podido borrar la jornada')
			}
		});

	}

	function getJornadas() {
		if ($('#idJornada').val() != '') {
			$.ajax({
				url : '/getJornadas/' + $('#idJornada').val(),
				method : 'GET',
				success : function(response) {

					//Cuando muestro un equipo, añado a la vista todos los jugadores que pertenecen a un equipo, llamando a la función addJugadorReal
					for (var i = 0; i < response.jornadas.length; i++) {
						addJornadaReal(response.jornadas[i]);
						$('#numeroJornada'+i).val(response.numeroJornada);
						$('#fecha'+i).val(response.fecha);
					}
				},
				error : function() {
					alert('Error inesperado');
				}
			});
		}
	}

	getJornadas();
</script>
