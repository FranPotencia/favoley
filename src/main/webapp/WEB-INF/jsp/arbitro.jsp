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
	<input type="hidden" id="idArbitro" value="${idArbitro}" >

<div class="container">
		<h1>Datos árbitro</h1>
	</div>

	<div class="container">
		<div class="row form-group">
			<div class="col-xs-3">
				<label for="nombre">Nombre:</label> <input
					type="text" class="form-control input-sm" id="nombre" 
					placeholder="Introduzca su nombre">
			</div>
			<div class="col-xs-3">
				<label for="apellidos">Apellidos</label> <input type="text" 
					class="form-control input-sm" id="apellidos"
					placeholder="Introduzca sus apellidos">
			</div>
			<div class="col-xs-3">
				<label for="dni">DNI</label> <input type="text" 
					class="form-control input-sm" id="dni"
					placeholder="00000000W">
			</div>
			<div class="col-xs-3">
				<label for="fechaNacimiento">Fecha de Nacimiento</label> <input id="fechaNacimiento" 
				 class="form-control input-sm" type="date">
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
				<label for="movil">Número de licencia</label> <input type="text" disabled="disabled"
					class="form-control input-sm" id="numeroLicencia"
					placeholder="Nº de licencia">
			</div>

		</div>
		<div class="row form-group ">
			<div class="col-xs-12">
				<button type="button" onclick="postActualizar()" style="float: right;"
					class="btn btn-primary btn-sm">Actualizar</button>
			</div>
		</div>

</div>

<div class="container">
		<h1>Cambiar contraseña</h1>
	</div>
	<div class="container">
		<div class="row form-group">
			<div class="col-xs-3">
				<label for="nombreEquipo">Usuario</label> <input type="text" disabled="disabled"
					class="form-control input-sm" id="username" placeholder="Usuario">
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




</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
<script type="text/javascript">
	$('#listaNavegadorArbitro').addClass("activeVerde"); //Para que en el navegador aparezca activo esta sección

	
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
			url : '/admin/peticionActualizarArbitro/?token='+$('#token').val(),
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
	
	
	function getArbitro(){
		if($('#idArbitro').val()!=''){
			$.ajax({
				url : '/admin/getArbitroConfirmado/'+$('#idArbitro').val()+'?token='+$('#token').val(),
				method : 'GET',
				success : function(response) {
					$('#nombre').val(response.nombre);
					$('#apellidos').val(response.apellidos);
					$('#dni').val(response.dni);
					$('#localidad').val(response.localidad);
					$('#provincia').val(response.provincia);
					$('#licencia').val(response.licencia);
					$('#fechaNacimiento').val(response.fechaNacimiento);
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
</script>
