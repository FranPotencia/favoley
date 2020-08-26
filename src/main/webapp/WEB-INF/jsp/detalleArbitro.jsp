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
	<input type="hidden" id="idArbitro" value="${idArbitro}" >
	<input type="hidden" id="token" value="${token}" >
	<div class="container">
		<h2>Detalles árbitro</h2>
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
				<label for="numeroLicencia">Número de licencia</label> <input type="text"
					class="form-control input-sm" id="numeroLicencia"
					placeholder="Nº de licencia">
			</div>
		</div>
		<div class="row form-group ">
			<div class="col-xs-1">
				<button type="button" onclick="deleteArbitro('${idArbitro}')" class="btn btn-danger btn-sm">Rechazar</button>
			</div>
			<div class="col-xs-11">
				<button type="button" onclick="postArbitro()" style="float: right;"
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
   
	
	function postArbitro() {

		$.ajax({
			url : '/admin/aceptarArbitro/'+$('#idArbitro').val()+'?token='+$('#token').val(),
			method : 'POST',
			//data : JSON.stringify(equipo), No se lo mando, ya que el equipo ya se encuentra en la base de datos, sólo necesita el id que le mando por la url
			"headers" : {
				"Content-Type" : "application/json"
			},
			success : function(response) {
				location.href = '/admin/listaArbitros?token='+$('#token').val();
			},
			error : function() {
				alert('No se ha podido guardar el equipo');
			}
		});

	}
	
	

	
	function getArbitro(){
		if($('#idArbitro').val()!=''){
			$.ajax({
				url : '/admin/getArbitro/'+$('#idArbitro').val()+'?token='+$('#token').val(),
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
	
	function deleteArbitro(id){
		$.ajax({
			url : '/admin/deleteArbitro/'+id+'?token='+$('#token').val(),
			method:'DELETE',
			success : function(response) {
				location.href = '/admin/getArbitros?token='+$('#token').val();
			},
			error: function(response) {
				alert('Ha ocurrido un error')
			}
		});
	}
	
	getArbitro();
</script>
