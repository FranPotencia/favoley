<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />

</head>
<body>
	<jsp:include page="complementos/header.jsp"></jsp:include>
	<input type="hidden" id="token" value="${token}">
	<input type="hidden" id="idArbitro" value="${idArbitro}">

	<div class="container">
		<h2>Partidos asignados</h2>
		<div class="row form-group">
			<div class="col-xs-12">
				<table id="tablaPartidos" class="display">
					<thead>
						<tr>
							<th>Nº partido</th>
							<th>Local</th>
							<th>Visitante</th>
							<th>Pabellón</th>
							<th>Set 1</th>
							<th>Set 2</th>
							<th>Set 3</th>
							<th>Set 4</th>
							<th>Set 5</th>
							<th>Resultado</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
			</div>

		</div>


		<h2>Datos árbitro</h2>
		<div class="row form-group">
			<div class="col-xs-3">
				<label for="nombre">Nombre:</label> <input type="text"
					class="form-control input-sm" id="nombre"
					placeholder="Introduzca su nombre">
			</div>
			<div class="col-xs-3">
				<label for="apellidos">Apellidos</label> <input type="text"
					class="form-control input-sm" id="apellidos"
					placeholder="Introduzca sus apellidos">
			</div>
			<div class="col-xs-3">
				<label for="dni">DNI</label> <input type="text"
					class="form-control input-sm" id="dni" placeholder="00000000W">
			</div>
			<div class="col-xs-3">
				<label for="fechaNacimiento">Fecha de Nacimiento</label> <input
					id="fechaNacimiento" class="form-control input-sm" type="date">
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
			<div class="col-xs-3">
				<label for="email">Correo electrónico</label> <input type="text"
					class="form-control input-sm" id="email"
					placeholder="tucorreo@gmail.com">
			</div>
			<div class="col-xs-3">
				<label for="movil">Móvil</label> <input type="text"
					class="form-control input-sm" id="movil"
					placeholder="Introduzca el nº de móvil">
			</div>
		</div>
		<div class="row form-group">

			<div class="col-xs-3">
				<label for="delegacion">Delegación</label> <select id="delegacion"
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
			<div class="col-xs-3">
				<label for="licencia">Licencia</label> <select id="licencia"
					class="form-control input-sm">
					<option>Elija una opción...</option>
					<option value="Anotador">Anotador</option>
					<option value="TerritorialB">Territorial B</option>
					<option value="TerritorialA">Territorial A</option>
					<option value="Nacional">Nacional</option>
				</select>
			</div>
			<div class="col-xs-3">
				<label for="movil">Número de licencia</label> <input type="text"
					disabled="disabled" class="form-control input-sm"
					id="numeroLicencia" placeholder="Nº de licencia">
			</div>

		</div>
		<div class="row form-group ">
			<div class="col-xs-12">
				<button type="button" onclick="postActualizar()"
					style="float: right;" class="btn btn-primary btn-sm">Actualizar</button>
			</div>
		</div>

		<h2>Cambiar contraseña</h2>

		<div class="row form-group">
			<div class="col-xs-3">
				<label for="nombreEquipo">Usuario</label> <input type="text"
					disabled="disabled" class="form-control input-sm" id="username"
					placeholder="Usuario">
			</div>
			<div class="col-xs-3">
				<label for="direccion">Contraseña</label> <input type="password"
					class="form-control input-sm" id="password"
					placeholder="Contraseña">
			</div>
		</div>
		<div class="row form-group ">
			<div class="col-xs-12">
				<button type="button" onclick="postUsuario()" style="float: right;"
					class="btn btn-primary btn-sm">Guardar</button>
			</div>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="exampleModalLabel">Datos partido</h3>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<input type="hidden" id="numeroPartidoIngresar">
						<div class="row form-group ">
							<div class="col-xs-12">
								<table id="tablaPartido" class="table">
									<thead>
										<tr align="center">
											<th style="text-align:center" >Set</th>
											<th id="equipoLocalIngresar" style="text-align:center"></th>
											<th id="equipoVisitanteIngresar" style="text-align:center"></th>
										</tr>
									</thead>
									<tbody>
										<tr align="center"><td>1º</td><td><input id="set1Local" type="number" style="width:30%;"
					class="form-control input-sm" ></td><td><input id="set1Visitante" type="number" style="width:30%;"
					class="form-control input-sm" ></td></tr>
										<tr align="center"><td>2º</td><td><input id="set2Local" type="number" style="width:30%;"
					class="form-control input-sm" ></td><td><input id="set2Visitante" type="number" style="width:30%;"
					class="form-control input-sm" ></td></tr>
										<tr align="center"><td>3º</td><td><input id="set3Local" type="number" style="width:30%;"
					class="form-control input-sm" ></td><td><input id="set3Visitante" type="number" style="width:30%;"
					class="form-control input-sm" ></td></tr>
										<tr align="center"><td>4º</td><td><input id="set4Local" type="number" style="width:30%;"
					class="form-control input-sm" ></td><td><input id="set4Visitante" type="number" style="width:30%;"
					class="form-control input-sm" ></td></tr>
										<tr align="center"><td>5º</td><td><input id="set5Local" type="number" style="width:30%;"
					class="form-control input-sm" ></td><td><input id="set5Visitante" type="number" style="width:30%;"
					class="form-control input-sm" ></td></tr>
										<tr align="center"><td>Resultado</td><td><input id="resultadoLocal" type="number" max="3" min="0" style="width:30%;"
					class="form-control input-sm" ></td><td><input id="resultadoVisitante" type="number" max="3" min="0" style="width:30%;"
					class="form-control input-sm" ></td></tr>
									</tbody>

								</table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cerrar</button>
						<button id="botonGuardarPartido" type="button" class="btn btn-primary">Guardar</button>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>

	<script
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<!-- <script -->
<!-- 	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script type="text/javascript"
	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>
<script type="text/javascript">
	$('#listaNavegadorArbitro').addClass("activeVerde"); //Para que en el navegador aparezca activo esta sección

	$('#botonGuardarPartido').on('click', function(){
		var partidoDTO={
			"numeroPartido":$('#numeroPartidoIngresar').val(),
			"set1Local":$('#set1Local').val(),
			"set1Visitante":$('#set1Visitante').val(),
			"set2Local":$('#set2Local').val(),
			"set2Visitante":$('#set2Visitante').val(),
			"set3Local":$('#set3Local').val(),
			"set3Visitante":$('#set3Visitante').val(),
			"set4Local":$('#set4Local').val(),
			"set4Visitante":$('#set4Visitante').val(),
			"set5Local":$('#set5Local').val(),
			"set5Visitante":$('#set5Visitante').val(),
			"resultadoLocal":$('#resultadoLocal').val(),
			"resultadoVisitante":$('#resultadoVisitante').val()
		}
		$.ajax({
			url : '/admin/guardarPartido?token='
					+ $('#token').val(),
			method : 'POST',
			data : JSON.stringify(partidoDTO),
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				alert('Resultados ingresados correctamente');
				location.reload();
			},
			error : function(response) {
				alert(response.responseJSON.error);
			}
		});
	})
	var tablaPartidos = $('#tablaPartidos').DataTable({"paging" : true, "responsive" : true,"pageLength" : 3,
		"lengthChange": false,"info": false,"columns": [
		    { "orderable": true },
		    { "orderable": false },
		    { "orderable": false },
		    { "orderable": false },
		    { "orderable": false },
		    { "orderable": false },
		    { "orderable": false },
		    { "orderable": false },
		    { "orderable": false },
		    { "orderable": false },
		    { "orderable": false }
		  ],"language": {
	            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
	        }});
	function getPartidosArbitro() {
		$
				.ajax({
					url : '/admin/getPartidosArbitro/?token='
							+ $('#token').val(),
					method : 'GET',
					success : function(response) {
						if (response != null && response.length > 0) {
							for (var i = 0; i < response.length; i++) {
								var set1 = "";
								var set2 = "";
								var set3 = "";
								var set4 = "";
								var set5 = "";
								var resultadoFinal = "";
								if (response[i].set1 != null) {
									set1 = response[i].set1;
								}
								if (response[i].set2 != null) {
									set2 = response[i].set2;
								}
								if (response[i].set3 != null) {
									set3 = response[i].set3;
								}
								if (response[i].set4 != null) {
									set4 = response[i].set4;
								}
								if (response[i].set5 != null) {
									set5 = response[i].set5;
								}
								if (response[i].resultado != null) {
									resultadoFinal = response[i].resultado;
								}
								if (response[i].resultado != null) {
									var rowNode = tablaPartidos
								    .row.add( [ response[i].numeroPartido, response[i].equipoLocal, response[i].equipoVisitante,
								    	response[i].pabellon,set1,set2,set3,set4,set5,resultadoFinal,'<label>Partido finalizado</label>'] )
								    .draw()
								    .node();
// 									$('#tablaPartidos tbody')
// 											.append(
// 													'<tr id="filaPartido'+i+'">'
// 															+ '<td>'
// 															+ response[i].numeroPartido
// 															+ '</td>'
// 															+ '<td>'
// 															+ response[i].equipoLocal
// 															+ '</td>'
// 															+ '<td>'
// 															+ response[i].equipoVisitante
// 															+ '</td>'
// 															+ '<td>'
// 															+ response[i].pabellon
// 															+ '</td>'
// 															+ '<td>'
// 															+ set1
// 															+ '</td>'
// 															+ '<td>'
// 															+ set2
// 															+ '</td>'
// 															+ '<td>'
// 															+ set3
// 															+ '</td>'
// 															+ '<td>'
// 															+ set4
// 															+ '</td>'
// 															+ '<td>'
// 															+ set5
// 															+ '</td>'
// 															+ '<td>'
// 															+ resultadoFinal
// 															+ '</td>'
// 															+ '<td><label>Partido finalizado</label></td>'
// 															+ '</tr>');
								} else {
									
									var rowNode = tablaPartidos
								    .row.add( [ response[i].numeroPartido, response[i].equipoLocal, response[i].equipoVisitante,
								    	response[i].pabellon,set1,set2,set3,set4,set5,resultadoFinal,'<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#exampleModal" id="ingresarResultado'+i+'" >Ingresar resultados</button>'] )
								    .draw()
								    .node();
									
// 									$('#tablaPartidos tbody')
// 											.append(
// 													'<tr id="filaPartido'+i+'">'
// 															+ '<td>'
// 															+ response[i].numeroPartido
// 															+ '</td>'
// 															+ '<td>'
// 															+ response[i].equipoLocal
// 															+ '</td>'
// 															+ '<td>'
// 															+ response[i].equipoVisitante
// 															+ '</td>'
// 															+ '<td>'
// 															+ response[i].pabellon
// 															+ '</td>'
// 															+ '<td>'
// 															+ set1
// 															+ '</td>'
// 															+ '<td>'
// 															+ set2
// 															+ '</td>'
// 															+ '<td>'
// 															+ set3
// 															+ '</td>'
// 															+ '<td>'
// 															+ set4
// 															+ '</td>'
// 															+ '<td>'
// 															+ set5
// 															+ '</td>'
// 															+ '<td>'
// 															+ resultadoFinal
// 															+ '</td>'
// 															+ '<td><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#exampleModal" id="ingresarResultado'+i+'" >Ingresar resultados</button></td>'
// 															+ '</tr>');
									$('#ingresarResultado' + i)
											.on(
													'click',
													function() {
														$('#numeroPartidoIngresar').val($(this)
																				.parent()
																				.parent()
																				.children()[0].innerHTML);
														$('#equipoLocalIngresar').html($(this)
																.parent()
																.parent()
																.children()[1].innerHTML);
														$('#equipoVisitanteIngresar').html($(this)
																.parent()
																.parent()
																.children()[2].innerHTML);
														
														

													})
								}
							}

						}
					},
					error : function() {
						alert('No se ha podido guardar el árbitro');
					}
				});
	}

	function postActualizar() {

		var arbitro = {
			"nombre" : $('#nombre').val(),
			"apellidos" : $('#apellidos').val(),
			"dni" : $('#dni').val(),
			"fechaNacimiento" : $('#fechaNacimiento').val(),
			"localidad" : $('#localidad').val(),
			"provincia" : $('#provincia').val(),
			"email" : $('#email').val(),
			"movil" : $('#movil').val(),
			"delegacion" : $('#delegacion').val(),
			"licencia" : $('#licencia').val(),
			"numeroLicencia" : $('#numeroLicencia').val()
		}

		$.ajax({
			url : '/admin/peticionActualizarArbitro/?token='
					+ $('#token').val(),
			method : 'POST',
			data : JSON.stringify(arbitro),
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				alert('Solicitud de cambio enviada');
			},
			error : function() {
				alert('No se ha podido guardar el árbitro');
			}
		});

	}

	function getArbitro() {
		if ($('#idArbitro').val() != '') {
			$.ajax({
				url : '/admin/getArbitroConfirmado/' + $('#idArbitro').val()
						+ '?token=' + $('#token').val(),
				method : 'GET',
				success : function(response) {
					$('#nombre').val(response.nombre);
					$('#apellidos').val(response.apellidos);
					$('#dni').val(response.dni);
					$('#localidad').val(response.localidad);
					$('#provincia').val(response.provincia);
					$('#licencia').val(response.licencia);
					$('#fechaNacimiento').val(response.fechaNacimientoFormateada);
					$('#delegacion').val(response.delegacion);
					$('#email').val(response.email);
					$('#movil').val(response.movil);
					$('#numeroLicencia').val(response.numeroLicencia);

				},
				error : function(response) {
					alert('Error inesperado');
				}
			});
		}
	}

	function postUsuario() {

		var usuario = {
			"username" : $('#username').val(),
			"password" : $('#password').val()
		};

		$.ajax({
			url : '/admin/cambiarPassword?token=' + $('#token').val(),
			method : 'POST',
			data : JSON.stringify(usuario),
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				$('#username').val(response.username);
				$('#password').val(response.password);
				alert('Contraseña actualizada');
			},
			error : function() {
				alert('No se ha podido guardar el usuario');
			}
		});

	}

	function getUsuario() {
		$.ajax({
			url : '/admin/usuario?token=' + $('#token').val(),
			method : 'GET',
			success : function(response) {
				$('#username').val(response.username);
				$('#password').val(response.password);
			},
			error : function() {
				alert('Error inesperado');
			}
		});
	}

	getArbitro();
	getUsuario();
	getPartidosArbitro();
	
	

</script>
