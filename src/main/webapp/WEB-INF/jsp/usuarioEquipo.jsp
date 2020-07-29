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
		<h1>Datos de usuario</h1>
	</div>

	<div class="container">
		<div class="row form-group">
			<div class="col-xs-3">
				<label for="nombreEquipo">Usuario</label> <input
					type="text" class="form-control input-sm" id="username"
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
			"password" : $('#password').val()
		};

		$.ajax({
			url : '/admin/cambiarPassword?token='+$('#token').val(),
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
				url : '/admin/usuario?token='+$('#token').val(),
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

	getUsuario();
</script>
