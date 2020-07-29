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
	<button id="generar">genera jornadas</button>
	</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>

<script type="text/javascript">
	$('#listaNavegadorInicio').addClass("activeVerde");
	$('#generar').on('click',function(){
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
</script>
