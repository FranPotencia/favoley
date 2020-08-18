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
				<a href="/admin/listaArbitros?token=${token}">Tiene �rbitros por
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
					�rbitros por actualizar!</a>
			</div>
		</c:if>
		<h2>Gesti�n jornadas</h2>
		<button class="btn btn-primary btn-sm" id="generar">Genera
			jornadas</button>

		<div class="row form-group">
			<div class="col-xs-9" style="width: 66.3%;"></div>
			<div class="col-xs-2" style="width: 8.666667%;">
				<label style="margin-top: 5px;">N� jornada</label>
			</div>
			<div class="col-xs-1">
				<select class="form-control input-sm" id="listaJornadas">
				</select>
			</div>
			<div class="col-xs-2">
				<input type="date" class="form-control input-sm" id="fechaJornada">
			</div>
			<div id="divTablaJornada" class="col-xs-12"></div>
		</div>

		<h2>Gesti�n de usuarios</h2>

		<div class="row form-group">
			<div class="col-xs-3">
				<label for="nombreEquipo">Usuario</label> <input type="text"
					class="form-control input-sm" id="username" placeholder="Usuario">
			</div>
			<div class="col-xs-3">
				<label for="direccion">Contrase�a</label> <input type="password"
					class="form-control input-sm" id="password"
					placeholder="Contrase�a">
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

	</div>
</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
<script type="text/javascript">
	$('#listaNavegadorAdministrador').addClass("activeVerde"); //Para que en el navegador aparezca activo esta secci�n
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
		$('#tablaUsuarios tbody')
				.append(
						'<tr>'
								+ '<td>'
								+ response.username
								+ '</td>'
								+ '<td>'
								+ response.tipo
								+ '</td>'
								+ '<td>'
								+ response.idAcceso
								+ '</td>'
								+ '<td><button type="button" onclick="deleteUsuario(\''
								+ response.username
								+ '\',this)" '
								+ ' class="btn btn-danger btn-sm">Eliminar</button></td>'
								+ '</tr>')
	}

	function deleteUsuario(id, botonCelda) {
		$.ajax({
			url : '/admin/deleteUsuario/' + id + '?token=' + $('#token').val(),
			method : 'DELETE',
			success : function(response) {
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

	var optionsArbitros='<option value=""></option>';
	function getArbitros() {
		$.ajax({
			url : '/admin/getArbitrosConfirmados?token='+$("#token").val(),
			method : 'GET',
			success : function(response) {
				if(response!=null){
					for (var i = 0; i < response.length; i++) {
						optionsArbitros=optionsArbitros+'<option value="'+response[i].id+'">'+response[i].nombre+' '+response[i].apellidos+'</option>';
					}
				}
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
							$('#divTablaJornada').append(
									'<table id="tablaPartidosJornada'+response[0].id+'" class="table classHidden">'
											+ '<thead>' + '<tr>'
											+ '<th>N� partido</th>'
											+ '<th>Equipo local</th>'
											+ '<th>Equipo visitante</th>'
											+ '<th>�rbitro</th>' + '</tr>'
											+ '</thead>' + '<tbody>'
											+ '</tbody>' + '</table>');
							for (var j = 0; j < response[0].partidos.length; j++) {
								var resultado;
								if (response[0].partidos[j].equipoLocal == 'Descansa'
										|| response[0].partidos[j].equipoVisitante == 'Descansa'
										|| response[0].partidos[j].resultado == null) {
									resultado = '-'
								} else {
									resultado = '<select class="form-control input-sm" style="width:50%;" id="arbitros">'+optionsArbitros+'</select>';
								}

								$(
										'#tablaPartidosJornada'
												+ response[0].id + ' tbody')
										.append(
												'<tr><td>'
														+ response[0].partidos[j].numeroPartido
														+ '</td>'
														+ '<td>'
														+ response[0].partidos[j].equipoLocal
														+ '</td><td>'
														+ response[0].partidos[j].equipoVisitante
														+ '</td><td>'
														+ resultado
														+ '</td></tr>');
							}
						}
						for (var i = 1; i < response.length; i++) {
							$('#listaJornadas').append(
									'<option value="'+response[i].id+'">'
											+ response[i].numeroJornada
											+ '</option>');
							$('#divTablaJornada')
									.append(
											'<table hidden="true" id="tablaPartidosJornada'+response[i].id+'" class="table classHidden">'
													+ '<thead>'
													+ '<tr>'
													+ '<th>N� partido</th>'
													+ '<th>Equipo local</th>'
													+ '<th>Equipo visitante</th>'
													+ '<th>�rbitro</th>'
													+ '</tr>'
													+ '</thead>'
													+ '<tbody>'
													+ '</tbody>'
													+ '</table>');
							for (var j = 0; j < response[i].partidos.length; j++) {
								var resultado;
								if (response[i].partidos[j].equipoLocal == 'Descansa'
										|| response[i].partidos[j].equipoVisitante == 'Descansa') {
									resultado = '-'
								} else {
									resultado = '<select class="form-control input-sm" id="arbitros">'+optionsArbitros+'</select>';
								}

								$(
										'#tablaPartidosJornada'
												+ response[i].id + ' tbody')
										.append(
												'<tr><td>'
														+ response[i].partidos[j].numeroPartido
														+ '</td>'
														+ '<td>'
														+ response[i].partidos[j].equipoLocal
														+ '</td><td>'
														+ response[i].partidos[j].equipoVisitante
														+ '</td><td>'
														+ resultado
														+ '</td></tr>');
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
		$('#tablaPartidosJornada' + $(this).val()).show();
	});
	getArbitros();
	getJornadas();
	getUsuarios();
</script>
