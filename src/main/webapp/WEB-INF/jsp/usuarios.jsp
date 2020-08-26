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
	<div class="container">
		<c:if test="${hayEquipos}">
			<div class="alert alert-warning" role="alert">
				<a href="/admin/equipos?token=${token}">Tiene equipos por
					confirmar!</a>
			</div>
		</c:if>
		<c:if test="${hayArbitros}">
			<div class="alert alert-warning" role="alert">
				<a href="/admin/listaArbitros?token=${token}">Tiene árbitros por
					confirmar!</a>
			</div>
		</c:if>
		<c:if test="${hayEquiposActualizado}">
			<div class="alert alert-warning" role="alert">
				<a href="/admin/equiposParaActualizar?token=${token}">Tiene
					equipos por actualizar!</a>
			</div>
		</c:if>
		<c:if test="${hayArbitrosActualizado}">
			<div class="alert alert-warning" role="alert">
				<a href="/admin/arbitrosParaActualizar?token=${token}">Tiene
					árbitros por actualizar!</a>
			</div>
		</c:if>
		<h2>Gestión jornadas</h2>

		<div class="row form-group">
			<div class="col-xs-9" style="width: 66.3%;"></div>
			<div class="col-xs-2" style="width: 8.666667%;">
				<label style="margin-top: 5px;">Nº jornada</label>
			</div>
			<div class="col-xs-1">
				<select class="form-control input-sm" id="listaJornadas">
				</select>
			</div>
			<div id="divFechaJornadas" class="col-xs-2">
				
			</div>
		</div>
		<div class="row form-group">
			<div id="divTablaJornada" class="col-xs-12"></div>
		</div>
		<div class="row form-group">
			<div class="col-xs-1">
				<button class="btn btn-primary btn-sm" id="generar">Genera
					jornadas</button>
			</div>
			<div class="col-xs-11">
				<button type="button" onclick="guardarJornada()"
					style="float: right;" class="btn btn-primary btn-sm">Guardar Jornada</button>
			</div>
		</div>
		<div class="row form-group"></div>
		<h2>Gestión de usuarios</h2>
		
		<div class="row form-group ">
			<div class="col-xs-12">
				<table id="tablaUsuarios" class="table">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>Tipo</th>
							<th>Acceso</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
			</div>
		</div>
		
		<div class="row form-group">
			<div class="col-xs-3">
				<label for="nombreEquipo">Usuario</label> <input type="text"
					class="form-control input-sm" id="username" placeholder="Usuario">
			</div>
			<div class="col-xs-3">
				<label for="direccion">Contraseña</label> <input type="password"
					class="form-control input-sm" id="password"
					placeholder="Contraseña">
			</div>
			<div class="col-xs-2">
				<label>Tipo</label> <select id="tipo" class="form-control input-sm">
					<option value="ALL">ALL</option>
					<option value="ARB">ARBITRO</option>
					<option value="EQU">EQUIPO</option>
				</select>
			</div>
			<div class="col-xs-2">
				<label for="localidad">Id acceso</label> <input type="number"
					class="form-control input-sm" id="idAcceso" placeholder="idAcceso">
			</div>
		</div>
		
		<div class="row form-group ">
			<div class="col-xs-12">
				<button type="button" onclick="postUsuario()" style="float: right;"
					class="btn btn-primary btn-sm">Guardar</button>
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
	$('#listaNavegadorAdministrador').addClass("activeVerde"); //Para que en el navegador aparezca activo esta sección
	$('#generar').on('click', function() {
		$.ajax({
			url : '/generaJornadas',
			method : 'POST',
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				alert('Jornadas generadas')
			},
			error : function() {
				alert('Ha ocurrido un error');
			}
		});
	})

	var tablaUsuarios=$('#tablaUsuarios').DataTable({"paging" : true, "responsive" : true,"pageLength" : 5,
								"lengthChange": false,"info": false,"searching":true,"ordering": true,"columns": [
								    { "orderable": true },
								    { "orderable": false },
								    { "orderable": false },
								    { "orderable": false }
								   
								  ],"language": {
							            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
							        }});
	
	function guardarJornada() {

		if($('#divTablaJornada table:visible tbody tr')!==undefined){
			var filas=$('#divTablaJornada table:visible tbody tr');
			var partidos=[];
			for (var i = 0; i < filas.length; i++) {
				var partido;
				if(filas[i].children[1].innerHTML!='Descansa' && filas[i].children[2].innerHTML!='Descansa'){
					partido={
							"numeroPartido":filas[i].children[0].innerHTML,
							"idArbitro":filas[i].children[3].children[0].value
					};
					partidos.push(partido);
				}
			}
			var jornada={
					"idJornada":$('#listaJornadas').val(),
					"fechaJornada":$('#fechaJornada'+$('#listaJornadas').val()).val(),
					"listaPartidoDTO":partidos
			}
			
			$.ajax({
				url : '/admin/guardarJornada?token=' + $('#token').val(),
				method : 'POST',
				data : JSON.stringify(jornada),
				"headers" : {
					"Content-Type" : "application/json"
				},
				success : function(response) {
					alert('Jornada guardada')
				},
				error : function() {
					alert('No se ha podido guardar la jornada');
				}
			});
			
		} else{
			alert('No hay jornada para guardar')
		}
		
		
	}

	function postUsuario() {

		var usuario = {
			"username" : $('#username').val(),
			"password" : $('#password').val(),
			"tipo" : $('#tipo').val(),
			"idAcceso" : $('#idAcceso').val()
		};

		$.ajax({
			url : '/admin/crearUsuario?token=' + $('#token').val(),
			method : 'POST',
			data : JSON.stringify(usuario),
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				addUsuario(response);
				$('#username').val('');
				$('#password').val('');
				$('#tipo').val('');
				$('#idAcceso').val('');
			},
			error : function() {
				alert('No se ha podido guardar el usuario');
			}
		});

	}

	function addUsuario(response) {
		var rowNode = tablaUsuarios
	    .row.add( [ response.username, response.tipo, 
	    	response.idAcceso,'<button type="button" onclick="deleteUsuario(\''
				+ response.username + '\',this)" class="btn btn-danger btn-sm">Eliminar</button>'] )
	    .draw()
	    .node();
	}

	function deleteUsuario(id, botonCelda) {
		$.ajax({
			url : '/admin/deleteUsuario/?id=' + id + '&token=' + $('#token').val(),
			method : 'DELETE',
			success : function(response) {
				alert('Usuario eliminado')
				$(botonCelda).parent().parent().remove();
			},
			error : function(response) {
				alert('No se ha podido borrar el usuario')
			}
		});

	}

	function getUsuarios() {
		$.ajax({
			url : '/admin/usuarios?token=' + $('#token').val(),
			method : 'GET',
			success : function(response) {
				for (var i = 0; i < response.length; i++) {
					addUsuario(response[i]);
				}
			},
			error : function() {
				alert('Error inesperado');
			}
		});
	}

	var optionsArbitros = '<option value=""></option>';
	function getArbitros() {
		$.ajax({
			url : '/admin/getArbitrosConfirmados?token=' + $("#token").val(),
			method : 'GET',
			success : function(response) {
				if (response != null) {
					for (var i = 0; i < response.length; i++) {
						optionsArbitros = optionsArbitros
								+ '<option value="'+response[i].id+'">'
								+ response[i].nombre + ' '
								+ response[i].apellidos + '</option>';
					}
				}
				getJornadas();
			},
			error : function() {
			}
		});
	}

	function getJornadas() {
		$
				.ajax({
					url : '/getJornadas',
					method : 'GET',
					success : function(response) {

						if (response != null && response.length > 0) {
							$('#listaJornadas').append(
									'<option onchange=traeJornada("'
											+ response[0].id + '") value="'
											+ response[0].id + '">'
											+ response[0].numeroJornada
											+ '</option>');
							$('#divFechaJornadas').append('<input type="date" class="form-control input-sm classHidden" id="fechaJornada'+response[0].id+'">');
							$('#fechaJornada'+response[0].id).val(response[0].fechaJornadaFormateada);
							$('#divTablaJornada').append(
									'<table id="tablaPartidosJornada'+response[0].id+'" class="table classHidden">'
											+ '<thead>' + '<tr>'
											+ '<th>Nº partido</th>'
											+ '<th>Equipo local</th>'
											+ '<th>Equipo visitante</th>'
											+ '<th>Árbitro</th>' + '</tr>'
											+ '</thead>' + '<tbody>'
											+ '</tbody>' + '</table>');
							var tablaJornada=$('#tablaPartidosJornada'+response[0].id).DataTable({"paging" : false, "responsive" : true,
								"lengthChange": false,"info": false,"searching":false,"ordering": false,"columns": [
								    { "orderable": false },
								    { "orderable": false },
								    { "orderable": false },
								    { "orderable": false }
								   
								  ],"language": {
							            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
							        }});
							for (var j = 0; j < response[0].partidos.length; j++) {
								var resultado;
								if (response[0].partidos[j].equipoLocal == 'Descansa'
										|| response[0].partidos[j].equipoVisitante == 'Descansa') {
									resultado = '-'
								} else {
									resultado = '<select class="form-control input-sm" id="arbitro'+response[0].partidos[j].numeroPartido+'">'
											+ optionsArbitros + '</select>';
								}
								var rowNode = tablaJornada
							    .row.add( [ response[0].partidos[j].numeroPartido, response[0].partidos[j].equipoLocal, 
							    	response[0].partidos[j].equipoVisitante,resultado] )
							    .draw()
							    .node();
								if(resultado!='-'){
									$('#arbitro'+response[0].partidos[j].numeroPartido).val(response[0].partidos[j].idArbitro);
								}
							}
						}
						for (var i = 1; i < response.length; i++) {
							$('#listaJornadas').append(
									'<option value="'+response[i].id+'">'
											+ response[i].numeroJornada
											+ '</option>');
							$('#divFechaJornadas').append('<input type="date" class="form-control input-sm classHidden" style="display:none;" id="fechaJornada'+response[i].id+'">');
							$('#fechaJornada'+response[i].id).val(response[i].fechaJornadaFormateada);
							$('#divTablaJornada')
									.append(
											'<table hidden="true" id="tablaPartidosJornada'+response[i].id+'" class="table classHidden">'
													+ '<thead>'
													+ '<tr>'
													+ '<th>Nº partido</th>'
													+ '<th>Equipo local</th>'
													+ '<th>Equipo visitante</th>'
													+ '<th>Árbitro</th>'
													+ '</tr>'
													+ '</thead>'
													+ '<tbody>'
													+ '</tbody>'
													+ '</table>');
							var tablaJornada=$('#tablaPartidosJornada'+response[i].id).DataTable({"paging" : false, "responsive" : true,
								"lengthChange": false,"info": false,"searching":false,"ordering": false,"columns": [
								    { "orderable": false },
								    { "orderable": false },
								    { "orderable": false },
								    { "orderable": false }
								   
								  ],"language": {
							            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
							        }});
							for (var j = 0; j < response[i].partidos.length; j++) {
								var resultado;
								if (response[i].partidos[j].equipoLocal == 'Descansa'
										|| response[i].partidos[j].equipoVisitante == 'Descansa') {
									resultado = '-'
								} else {
									resultado = '<select class="form-control input-sm" id="arbitro'+response[i].partidos[j].numeroPartido+'">'
											+ optionsArbitros + '</select>';
								}
								var rowNode = tablaJornada
							    .row.add( [ response[i].partidos[j].numeroPartido, response[i].partidos[j].equipoLocal, 
							    	response[i].partidos[j].equipoVisitante,resultado] )
							    .draw()
							    .node();
// 								$(
// 										'#tablaPartidosJornada'
// 												+ response[i].id + ' tbody')
// 										.append(
// 												'<tr><td>'
// 														+ response[i].partidos[j].numeroPartido
// 														+ '</td>'
// 														+ '<td>'
// 														+ response[i].partidos[j].equipoLocal
// 														+ '</td><td>'
// 														+ response[i].partidos[j].equipoVisitante
// 														+ '</td><td>'
// 														+ resultado
// 														+ '</td></tr>');
								if(resultado!='-'){
									$('#arbitro'+response[i].partidos[j].numeroPartido).val(response[i].partidos[j].idArbitro);
								}
								
							}
						}
					},
					error : function() {
						alert('Error inesperado');
					}
				});

	}
	$('#listaJornadas').on('change', function() {
		$('.classHidden').hide();
		$('#fechaJornada' + $(this).val()).show();
		$('#tablaPartidosJornada' + $(this).val()).show();
	});
	getArbitros();
	
	getUsuarios();
</script>
