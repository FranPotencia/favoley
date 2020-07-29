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

	<div class="container">
		<h1>Lista de Equipos</h1>
	</div>

	<div class="container">
		<table id="tablaEquipos" class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Localidad</th>
					<th>Provincia</th>
					<th>Dirección</th>
				</tr>
			</thead>
			<tbody>

			</tbody>

		</table>
		
	</div>

	<!-- /.container -->

 

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
<script type="text/javascript">
	$('#listaNavegadorEquipo').addClass("activeVerde")

		function getEquiposConfirmados() {
		$.ajax({
			url : '/getEquiposConfirmados',
			method : 'GET',
			success : function(response) { //response manda una tabla con equipos

				for (var i = 0; i < response.length; i++) {
					$('#tablaEquipos tbody').append(
							'<tr id="equipo'+response[i].id+'">' + '<td>'
									+ response[i].nombreEquipo + '</td>'
									+ '<td>' + response[i].localidad + '</td>'
									+ '<td>' + response[i].provincia + '</td>'
									+ '<td>' + response[i].direccion + '</td>'
									+ '</tr>')
				}
// 
			},
			error : function() {
				alert('Error inesperado');
			}
		});
	}
	
	getEquiposConfirmados();
</script>