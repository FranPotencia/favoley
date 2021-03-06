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


	<div class="container">
		<h1>Solicitud de alta arbitral</h1>
	</div>

	<div class="container">
		<div class="row form-group">
			<div class="col-xs-3">
				<label for="nombre">Nombre</label> <input type="text"
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
					<option></option>
					<option value="Huelva">Huelva</option>
					<option value="Sevilla">Sevilla</option>
					<option value="Almer�a">Almer�a</option>
					<option value="C�rdoba">C�rdoba</option>
					<option value="Granada">Granada</option>
					<option value="C�diz">C�diz</option>
					<option value="Ja�n">Ja�n</option>
					<option value="M�laga">M�laga</option>
				</select>
			</div>
			<div class="col-xs-3">
				<label for="email">Correo electr�nico</label> <input type="text"
					class="form-control input-sm" id="email"
					placeholder="tucorreo@gmail.com">
			</div>
			<div class="col-xs-3">
				<label for="movil">M�vil</label> <input type="text"
					class="form-control input-sm" id="movil"
					placeholder="Introduzca el n� de m�vil">
			</div>
		</div>
		<div class="row form-group">

			<div class="col-xs-3">
				<label for="delegacion">Delegaci�n</label> <select id="delegacion"
					class="form-control input-sm">
					<option></option>
					<option value="Huelva">Huelva</option>
					<option value="Sevilla">Sevilla</option>
					<option value="Almer�a">Almer�a</option>
					<option value="C�rdoba">C�rdoba</option>
					<option value="Granada">Granada</option>
					<option value="C�diz">C�diz</option>
					<option value="Ja�n">Ja�n</option>
					<option value="M�laga">M�laga</option>
				</select>
			</div>
			<div class="col-xs-3">
				<label for="licencia">Licencia</label> <select id="licencia"
					class="form-control input-sm">
					<option></option>
					<option value="Anotador">Anotador</option>
					<option value="TerritorialB">Territorial B</option>
					<option value="TerritorialA">Territorial A</option>
					<option value="Nacional">Nacional</option>
				</select>
			</div>
			<div class="col-xs-3">
				<label for="numeroLicencia">N�mero de licencia</label> <input type="text"
					class="form-control input-sm" id="numeroLicencia"
					placeholder="N� de licencia">
			</div>
		</div>
		<div class="row form-group ">
			<div class="col-xs-12">
				<button type="button" onclick="postArbitro()" style="float: right;"
					class="btn btn-primary btn-sm">Enviar</button>
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

	
	function postArbitro() {

		
			if($('#nombre').val()==''){
				alert('Falta nombre');
				return ;
			}
			if($('#apellidos').val()==''){
				alert('Falta apellidos');
				return ;
			}
			if($('#dni').val()==''){
				alert('Falta dni');
				return ;
			}
			if($('#fechaNacimiento').val()==''){
				alert('Falta fecha de nacimiento');
				return ;
			}
			if($('#localidad').val()==''){
				alert('Falta localidad');
				return ;
			}
			if($('#provincia').val()==''){
				alert('Falta provincia');
				return ;
			}
			if($('#email').val()==''){
				alert('Falta email');
				return ;
			}
			if($('#movil').val()==''){
				alert('Falta movil');
				return ;
			}
			if($('#delegacion').val()==''){
				alert('Falta delegacion');
				return ;
			}
			if($('#licencia').val()==''){
				alert('Falta licencia');
				return ;
			}
			if($('#numeroLicencia').val()==''){
				alert('Falta numeroLicencia');
				return ;
			}
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
			url : '/guardarArbitro',
			method : 'POST',
			data : JSON.stringify(arbitro),
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				location.href = "/";
			},
			error : function() {
				alert('No se ha podido guardar el �rbitro');
			}
		});

	}
	

</script>
