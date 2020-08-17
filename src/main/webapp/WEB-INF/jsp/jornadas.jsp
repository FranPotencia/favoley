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
	<input type="hidden" id="idEquipo" value="${idEquipo}">
	<input type="hidden" id="token" value="${token}">
	<div class="container">
		<h1>Primera División Andaluza</h1>
		<hr>
		<div class="row form-group">
			<div class="col-xs-9" style="width: 83%;"></div>
			<div class="col-xs-2" style="width: 8.666667%;">
				<label style="margin-top: 5px;">Nº jornada</label>
			</div>
			<div class="col-xs-1">
				<select class="form-control" id="listaJornadas">
				</select>
			</div>
			<div id="divTablaJornada" class="col-xs-12"></div>
			<div id="divTablaClasificacion" class="col-xs-12">
				<table id="tablaClasificacion" class="table">
					<thead>
						<tr>
							<th>Posicion</th>
							<th>Equipo</th>
							<th>PJ</th>
							<th>PG</th>
							<th>PP</th>
							<th>TF</th>
							<th>TC</th>
							<th>Puntos</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>

			</div>
		</div>

		<div id="accordion"></div>
	</div>




</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
<script type="text/javascript">
	$('#listaNavegadorAdministrador').addClass("activeVerde"); //Para que en el navegador aparezca activo esta sección

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
									'<table id="tablaPartidosJornada'+response[0].id+'" class="table">'
											+ '<thead>' + '<tr>'
											+ '<th>Nº partido</th>'
											+ '<th>Equipo local</th>'
											+ '<th>Equipo visitante</th>'
											+ '<th>Resultado</th>' + '</tr>'
											+ '</thead>' + '<tbody>'
											+ '</tbody>' + '</table>');
							for (var j = 0; j < response[0].partidos.length; j++) {
								var resultado;
								if (response[0].partidos[j].equipoLocal == 'Descansa'
										|| response[0].partidos[j].equipoVisitante == 'Descansa'
										|| response[0].partidos[j].resultado == null) {
									resultado = '-';
								} else {
									resultado = response[0].partidos[j].resultado;
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
											'<table hidden="true" id="tablaPartidosJornada'+response[i].id+'" class="table">'
													+ '<thead>'
													+ '<tr>'
													+ '<th>Nº partido</th>'
													+ '<th>Equipo local</th>'
													+ '<th>Equipo visitante</th>'
													+ '<th>Resultado</th>'
													+ '</tr>'
													+ '</thead>'
													+ '<tbody>'
													+ '</tbody>'
													+ '</table>');
							for (var j = 0; j < response[i].partidos.length; j++) {
								var resultado;
								if (response[i].partidos[j].equipoLocal == 'Descansa'
										|| response[i].partidos[j].equipoVisitante == 'Descansa'
										|| response[i].partidos[j].resultado == null) {
									resultado = '-';
								} else {
									resultado = response[i].partidos[j].resultado;
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
		$('table').hide();
		$('#tablaPartidosJornada' + $(this).val()).show();
	});
	getJornadas();
</script>
