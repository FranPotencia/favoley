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
		<h2>Detalles equipo</h2>
	</div>

	<div class="container">
			<div class="row form-group">
			<div class="col-xs-6">
				<label for="nombreEquipo">Nombre del Equipo</label> <input
					type="text" class="form-control input-sm" id="nombreEquipo" 
					placeholder="Introduzca el nombre del equipo">
			</div>
			<div class="col-xs-6">
				<label for="direccion">Dirección</label> <input type="text" 
					class="form-control input-sm" id="direccion" placeholder="Dirección">
			</div>
		</div>
		<div class="row form-group">
			<div class="col-xs-3">
				<label for="localidad">Localidad</label> <input type="text" 
					class="form-control input-sm" id="localidad" placeholder="Localidad">
			</div>
			<div class="col-xs-3">
				<label for="provincia">Provincia</label> <select id="provincia" 
					class="form-control input-sm">
					<option>Elija una opción...</option>
					<option value="Huelva">Huelva</option>
					<option value="Sevilla">Sevilla</option>
					<option value="Almería">Almería</option>
					<option value="Córdoba">Córdoba</option>
					<option value="Granada">Granada</option>
					<option value="Cádiz">Cádiz</option>
					<option value="Jaén">Jaén</option>
					<option value="Málaga">Málaga</option>
				</select>
			</div>
			<div class="col-xs-6">
				<label for="pabellon">Pabellón</label> <input type="text"
					class="form-control input-sm" id="pabellon"
					placeholder="Nombre del Pabellón">
			</div>
		</div>
		<div class="row form-group">
			<div class="col-xs-6">
				<label for="email">Email</label> <input type="email" 
					class="form-control input-sm" id="email" placeholder="Introduzca el email">
			</div>
			<div class="col-xs-4">
				<label for="movil">Móvil</label> <input type="text"
					class="form-control input-sm" id="movil"
					placeholder="Introduzca el nº de móvil">
			</div>
		</div>

		<div class="row form-group">
			<div class="col-xs-6">
				<label for="primerEntrenador">Primer Entrenador</label> <input 
					type="text" class="form-control input-sm" id="primerEntrenador"
					placeholder="Introduzca el nombre del 1º Entrenador">
			</div>
			<div class="col-xs-6">
				<label for="escudo">Escudo</label> <input
					type="file" class="form-control input-sm" id="escudo"
					placeholder="Introduzca la foto del escudo">
			</div>
		</div>
		<br>
		<h2>Jugadores</h2>
		<div class="row form-group ">
			<div class="col-xs-12">
				<table id="tablaJugadores" class="table">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>Apellidos</th>
							<th>DNI</th>
							<th>Fecha de Nacimiento</th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
			</div>
		</div>
		<div class="row form-group ">
			<div class="col-xs-1">
				<button type="button" onclick="deleteEquipo('${idEquipo}')" class="btn btn-danger btn-sm">Rechazar</button>
			</div>
			<div class="col-xs-11">
				<button type="button" onclick="postEquipo()" style="float: right;"
					class="btn btn-success btn-sm">Aceptar</button>
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
   
	
	function postEquipo() {

		$.ajax({
			url : '/admin/aceptarEquipo/'+$('#idEquipo').val()+'?token='+$('#token').val(),
			method : 'POST',
			//data : JSON.stringify(equipo), No se lo mando, ya que el equipo ya se encuentra en la base de datos, sólo necesita el id que le mando por la url
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				location.href = "/admin/equipos?token="+$('#token').val();
			},
			error : function() {
				alert('No se ha podido guardar el equipo');
			}
		});

	}
	
	//Jugadores ya existentes
	function addJugadorReal(response) {
		$('#tablaJugadores tbody').append(
						'<tr>'
						+ '<td>'+response.nombre+'</td>'
						+ '<td>'+response.apellidos+'</td>'
						+ '<td>'+response.dni+'</td>'
						+ '<td>'+response.fechaNacimientoFormateada+'</td>'
						+ '</tr>')
	}
	

	
	function getEquipo(){
		if($('#idEquipo').val()!=''){
			$.ajax({
				url : '/admin/getEquipo/'+$('#idEquipo').val()+'?token='+$('#token').val(),
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
	
	function deleteEquipo(id){
		$.ajax({
			url : '/admin/deleteEquipo/'+id+'?token='+$('#token').val(),
			method:'DELETE',
			success : function(response) {
				location.href = "/admin/equipos?token="+$('#token').val();
			},
			error: function(response) {
				alert('Ha ocurrido un error')
			}
		});
	}
	
	getEquipo();
</script>
