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
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<!-- <script -->
<!-- 	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script type="text/javascript"
	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>

<script type="text/javascript">
	$('#listaNavegadorAdministrador').addClass("activeVerde")
	var tablaArbitros = $('#tablaArbitros').DataTable({"paging" : true, "responsive" : true,"pageLength" : 5,
		"lengthChange": false,"info": false,"ordering":false,"columns": [
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
	function getArbitros() {
		$.ajax({
			url : '/admin/getArbitrosActualizar?token='+$('#token').val(),
			method : 'GET',
			success : function(response) { //response manda una tabla con equipos

				for (var i = 0; i < response.length; i++) {
					var rowNode = tablaArbitros
				    .row.add( [ response[i].nombre, response[i].apellidos, 
				    	response[i].delegacion,response[i].licencia,response[i].numeroLicencia,response[i].email,
				    	'<button type="button" onclick="editarArbitro(\''+response[i].id+'\')" '
						+'  class="btn btn-info btn-sm">Ver detalles</button>'] )
				    .draw()
				    .node();
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

