<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
 integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<link rel="stylesheet" type="text/css"
	href="/css/main.css" />
	
<link rel="icon" href="/images/logo.png" type="image/png" sizes="16x16">

</head>
<body>

		<div class="container">
			<img src="/images/cabecera.png" alt="Federacion Andaluza de Voleibol">
		</div>
				<nav class="navbar navbar-inverse verde">
				<div class="container">
				<a class="navbar-brand fontWhite" href="#">FEDERACIÓN</a>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li id="listaNavegadorInicio"><a href="/${tokenPublic}" class="fontWhite">Inicio</a></li>
					<li class="dropdown" id="listaNavegadorEquipo"><a href="#"
						class="dropdown-toggle fontWhite" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">Equipo <span
							class="caret"></span></a>
							<ul class="dropdown-menu">
							<li><a href="/equiposConfirmados${tokenPublic}">Lista Equipos</a></li>
							<li><a href="/crearEquipo${tokenPublic}">Nuevo Equipo</a></li>
							</ul>
					</li>
					<li class="dropdown" id="listaNavegadorArbitro"><a href="#"
						class="dropdown-toggle fontWhite" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">Árbitro <span
							class="caret"></span></a>
							<ul class="dropdown-menu">
							<li><a href="/altaArbitro${tokenPublic}">Dar de alta</a></li>
							</ul>
					</li>
					<!-- administrador se lo pasamos desde el model del controlador --> 
					<c:if test="${administrador}">
						<li class="dropdown" id="listaNavegadorAdministrador"><a href="#"
							class="dropdown-toggle fontWhite" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">Administrador<span
								class="caret"></span></a>
								<ul class="dropdown-menu">
								<li><a href="/admin/equipos?token=${token}">Equipos por confirmar</a></li>
								<li><a href="/admin?token=${token}">Gestión usuarios</a></li>
								<li><a href="/admin/listaArbitros?token=${token}">Arbitros por confirmar</a></li>
								<li><a href="/admin/arbitrosConfirmados?token=${token}">Arbitros confirmados</a></li>
								</ul>
						</li>
					</c:if>
					<c:if test="${equipo}">
						<li class="dropdown" id="listaNavegadorAdministrador"><a href="#"
							class="dropdown-toggle fontWhite" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">Gestión<span
								class="caret"></span></a>
								<ul class="dropdown-menu">
								<li><a href="/admin/equipoConfirmado/${idEquipo}?token=${token}">Editar mi equipo</a></li>
								</ul>
						</li>
					</c:if>
					<c:if test="${arbitro}">
						<li class="dropdown" id="listaNavegadorAdministrador"><a href="#"
							class="dropdown-toggle fontWhite" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">Gestión<span
								class="caret"></span></a>
								<ul class="dropdown-menu">
 								<li><a href="/admin/arbitroConfirmado/${idArbitro}?token=${token}">Mis datos</a></li> 
								<li><a href="/admin/arbitrosConfirmados?token=${token}">Lista de árbitros</a></li>
								</ul>
						</li>
					</c:if>
					<li id="listaNavegadorCompeticion"><a href="/jornadas" class="fontWhite">Competición</a></li>
					<li id="listaNavegadorAcceso"><a href="/acceso" class="fontWhite">Acceso</a></li>
				 </ul>
			</div>
		</div>	
	</nav>
</body>
</html>