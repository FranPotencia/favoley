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
	<input type="hidden" id="idEquipo" value="${idEquipo}">
	<input type="hidden" id="token" value="${token}">
	<div class="container">
		<h1>Datos equipo</h1>
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
					class="form-control input-sm" id="direccion"
					placeholder="Dirección">
			</div>
		</div>
		<div class="row form-group">
			<div class="col-xs-3">
				<label for="localidad">Localidad</label> <input type="text"
					class="form-control input-sm" id="localidad"
					placeholder="Localidad">
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
					class="form-control input-sm" id="email"
					placeholder="Introduzca el email">
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
<!-- 			<div class="col-xs-6"> -->
<!-- 				<label for="escudo">Escudo</label> <input type="file" -->
<!-- 					class="form-control input-sm" accept="image/*" id="escudo" -->
<!-- 					placeholder="Introduzca la foto del escudo"> -->
<!-- 			</div> -->
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
				<button type="button" onclick="addJugador()"
					class="btn btn-link btn-sm">Añadir jugador</button>
			</div>
			<div class="col-xs-11">
				<button type="button" onclick="postEquipo()" style="float: right;"
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

	var idJugador = 0;

	function getBase64(file) {
// 		var reader = new FileReader();
// 		reader.readAsDataURL(file);
// 		reader.onload = function() {
// 			postEquipoParam(reader.result)
// 		};
// 		reader.onerror = function(error) {
			postEquipoParam('');
// 		};
	}
	function postEquipo() {
// 		var file = $('#escudo')[0].files[0];
// 		getBase64(file); // prints the base64 string
		getBase64(''); 
	}

	//Jugador nuevo, inexistente en la base de datos
	function addJugador() {
		idJugador++;
		$('#tablaJugadores tbody')
				.append(
						'<tr class="filasNuevas" id="jugador'+idJugador+'">'
								+ '<td><input id="nombre'+idJugador+'" class="form-control input-sm"  type="text"></td>'
								+ '<td><input id="apellidos'+idJugador+'" class="form-control input-sm" type="text"></td>'
								+ '<td><input id="dni'+idJugador+'" class="form-control input-sm" type="text"></td>'
								+ '<td><input id="fecha'+idJugador+'" class="form-control input-sm" type="date"></td>'
								+ '<td> <button type="button" class="btn btn-danger btn-sm"  onclick="deleteJugador(\''
								+ idJugador + '\')" >Eliminar</button>'
								+ '</div>' + '</div>' + '</div>'
								+ '</div></td>' + '</tr>')
	}

	function deleteJugador(id) {
		$('#jugador' + id).remove();
	}

	function postEquipoParam(foto) {

		//Jugadores que ya se encuentran en la base de datos, y ya tienen un ID real.
		var filasReales = $('.filasReal'); // .filasReal hace llamada a todos los elementos que pertenecen a esa clase
		var jugadores = []; //Creo una tabla en la que voy a introducir tanto los jugadores antiguos como los nuevos(en este caso los antiguos)
		for (var i = 0; i < filasReales.length; i++) {
			var item = filasReales[i].id.split('jugadorReal')[1]; //Extraigo el id de su base de datos, para que se actualicen los posibles cambios "Francisco Barbosa Marín" .split(" ") ["Francisco","Barbosa","MArín"]
			
			//Justo aqui vever cada campo
			var jugador = {
				"id" : item,
				"nombre" : $('#nombreReal' + item).val(),
				"apellidos" : $('#apellidosReal' + item).val(),
				"dni" : $('#dniReal' + item).val(),
				"fechaNacimiento" : $('#fechaReal' + item).val()
			}
			jugadores.push(jugador); //Guardo en la tabla los jugadores antiguos
		}

		//Jugadores nuevos que no se encontraban en la base de datos
		var filasNuevas = $('.filasNuevas'); //#id .class 
		var listJugadores = []; // creo que esta no se usa y se puede eliminar
		for (var i = 0; i < filasNuevas.length; i++) {

			// 			var idFila=filasNuevas[i].id;// jugador1
			// 			var array=idFila.split('jugador'); // ["","1"]
			// 			var item=array[1];// 1		
			//			Esto es igual que la línea siguiente:

			var item = filasNuevas[i].id.split('jugador')[1];
			
			// Aqui igual
			var jugador = {
				"id" : null,
				"nombre" : $('#nombre' + item).val(),
				"apellidos" : $('#apellidos' + item).val(),
				"dni" : $('#dni' + item).val(),
				"fechaNacimiento" : $('#fecha' + item).val()
			}
			jugadores.push(jugador); //añado estos jugadores a la lista general
		}

		var idEquipo = null;
		if ($('#idEquipo').val() != '') { //Si el equipo no ha sido creado, se le creará un ID cuando sea introducido en la base de datos, si el equipo es actualizado, pillaremos el ID del principio, para que su modificación sea correcta.
			idEquipo = $('#idEquipo').val();
		}
		// comprobar todos los campos de equipo menos idEquipo
		var equipo = {
			"id" : idEquipo,
			"nombreEquipo" : $('#nombreEquipo').val(),
			"localidad" : $('#localidad').val(),
			"provincia" : $('#provincia').val(),
			"direccion" : $('#direccion').val(),
			"pabellon" : $('#pabellon').val(),
			"email" : $('#email').val(),
			"movil" : $('#movil').val(),
			"primerEntrenador" : $('#primerEntrenador').val(),
			"photoString" : foto,
			"jugadores" : jugadores
		};
		if ($('#idEquipo').val() != '') { //Si el equipo no ha sido creado, se le creará un ID cuando sea introducido en la base de datos, si el equipo es actualizado, pillaremos el ID del principio, para que su modificación sea correcta.
			$.ajax({
				url : '/admin/peticionActualizarEquipo?token='+$("#token").val(),
				method : 'POST',
				data : JSON.stringify(equipo),
				"headers" : {
					"Content-Type" : "application/json"
				},
				success : function(response) {
// 					if (foto == '') {
// 						alert('Equipo guardado sin escudo');
// 					}
					alert('Equipo guardado');
				},
				error : function() {
					alert('No se ha podido guardar el equipo');
				}
			});
		} else{
			$.ajax({
				url : '/guardarEquipo',
				method : 'POST',
				data : JSON.stringify(equipo),
				"headers" : {
					"Content-Type" : "application/json"
				},
				success : function(response) {
// 					if (foto == '') {
// 						alert('Equipo creado sin escudo');
// 					}
					location.href = "/";
				},
				error : function() {
					alert('No se ha podido guardar el equipo');
				}
			});
		}
		
	}

	//Jugadores ya existentes
	function addJugadorReal(response) {
		$('#tablaJugadores tbody')
				.append(
						'<tr class="filasReal" id="jugadorReal'+response.id+'">'
								+ '<td><input id="nombreReal'+response.id+'" class="form-control input-sm"  type="text" value="'+response.nombre+'"></td>'
								+ '<td><input id="apellidosReal'+response.id+'" class="form-control input-sm" type="text" value="'+response.apellidos+'"></td>'
								+ '<td><input id="dniReal'+response.id+'" class="form-control input-sm" type="text" value="'+response.dni+'"></td>'
								+ '<td><input id="fechaReal'+response.id+'" class="form-control input-sm" type="date" value="'+response.fechaNacimientoFormateada+'" ></td>'
								+ '<td><button type="button" onclick="deleteJugadorReal(\''
								+ response.id
								+ '\')" '
								+ ' class="btn btn-danger btn-sm">Eliminar</button></td>'
								+ '</tr>')
	}

	function deleteJugadorReal(id) {
		$('#jugadorReal' + id).remove();
	}

	function getEquipo() {
		if ($('#idEquipo').val() != '') {
			$.ajax({
				url : '/admin/getEquipoConfirmado/' + $('#idEquipo').val()+'?token='+$("#token").val(),
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

	getEquipo();
</script>
