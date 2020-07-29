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
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12">
								<h3>Acceso de usuarios</h3>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Contraseña">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="button" name="login-submit" id="login-submit" tabindex="4" onclick="login()" class="form-control btn btn-info" value="Validar">
											</div>
										</div>
									</div>
							</div>
						</div>
					</div>
				</div>
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
	
	$('#listaNavegadorAcceso').addClass("activeVerde"); //Para que en el navegador aparezca activo esta sección

	function login(){
		if($('#username').val()!='' && $('#password').val()!=''){
			var usuario={
					"username":$('#username').val(),
					"password":$('#password').val()
			}
			$.ajax({
				url : "/login",
				data : JSON.stringify(usuario),
				"headers" : {
					"Content-Type" : "application/json"
				},
				method : 'POST',
				async : false,
				success : function(data) {
					if(data!=null && data!=''){
						window.location.href = '/admin?token='+data;
					} else{
						alert('Usuario o contraseña inválidos')
					}
					
				},
				error: function (){
					alert('Usuario o contraseña inválidos')
				}
			});
		} else{
			alert('Introduzca los datos necesarios')
		}
	}
	
</script>
