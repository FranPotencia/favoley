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
			<a href="/admin/listaArbitros?token=${token}">Tiene árbitros por
					confirmar!</a>
			</div>
		</c:if>
		<c:if test="${hayEquiposActualizado}">
			<div class="alert alert-warning" role="alert">
			<a href="/admin/equiposParaActualizar?token=${token}">Tiene equipos por
					actualizar!</a>
			</div>
		</c:if>
		<c:if test="${hayArbitrosActualizado}">
			<div class="alert alert-warning" role="alert">
			<a href="/admin/arbitrosParaActualizar?token=${token}">Tiene árbitros por
					actualizar!</a>
			</div>
		</c:if>
		<h1>Gestión de usuarios</h1>

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
		<br>
		<h2>Usuarios</h2>
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
	$('#listaNavegadorAdministrador').addClass("activeVerde"); //Para que en el navegador aparezca activo esta sección

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

	getUsuarios();
</script>
