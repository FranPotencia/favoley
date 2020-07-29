<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
 integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<link rel="stylesheet" type="text/css"
	href="/css/main.css" />

</head>
<body>
	<jsp:include page="complementos/header.jsp"></jsp:include>
	<input type="hidden" id="token" value="${token}">
	<div class="container">
		<h1>Solicitudes arbitrales para actualizar</h1>
	</div>

	<div class="container">
		<div class="row form-group">
			<div class="col-xs-12">
		<table id="tablaArbitros" class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>Delegación</th>
					<th>Licencia</th>
					<th>Número licencia</th>
					<th>Email</th>
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
	$('#listaNavegadorAdministrador').addClass("activeVerde")

	function getArbitros() {
		$.ajax({
			url : '/admin/getArbitrosActualizar?token='+$('#token').val(),
			method : 'GET',
			success : function(response) { //response manda una tabla con equipos

				for (var i = 0; i < response.length; i++) {
					$('#tablaArbitros tbody').append(
							'<tr id="arbitro'+response[i].id+'">' + '<td>'
									+ response[i].nombre + '</td>'
									+ '<td>' + response[i].apellidos + '</td>'
									+ '<td>' + response[i].delegacion + '</td>'
									+ '<td>' + response[i].licencia + '</td>'
									+ '<td>' + response[i].numeroLicencia + '</td>'
									+ '<td>' + response[i].email + '</td>'
									+ '<td><button type="button" onclick="editarArbitro(\''+response[i].id+'\')" '
									+'  class="btn btn-info btn-sm">Ver detalles</button></td>'
									+ '</tr>')
				}
// 
			},
			error : function() {
				alert('Error inesperado');
			}
		});
	}
	function editarArbitro(id){
		location.href='/admin/actualizarArbitro/'+id+'?token='+$("#token").val();
	}
	getArbitros();
</script>

