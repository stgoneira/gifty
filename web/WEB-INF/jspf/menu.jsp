<%@page contentType="text/html" pageEncoding="UTF-8"%>
<a href="index.jsp">
    <img src="img/logo.png" alt="logotipo" style="width: 200px; height: auto;" />
</a>
<br /><br />
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/index.jsp'?'active ':''}nav-link" href="index.jsp">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/setup.jsp'?'active ':''}nav-link" href="setup.jsp">Instalación</a>
            </li>
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/WEB-INF/jsp/producto/crear.jsp'?'active ':''}nav-link" href="crear">Crear Producto</a>
            </li>
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/WEB-INF/jsp/producto/listar.jsp'?'active ':''}nav-link" href="listar">Listar Productos</a>
            </li>
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/WEB-INF/jsp/categoria/crear.jsp'?'active ':''}nav-link" href="crear-categoria">Crear Categoría</a>
            </li>
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/WEB-INF/jsp/categoria/listar.jsp'?'active ':''}nav-link" href="listar-categorias">Listar Categorías</a>
            </li>
        </ul>
            
            
        <form action="listar" method="get" class="form-inline my-2 my-lg-0">
            <input name="producto" class="form-control mr-sm-2" type="text" placeholder="Buscar" aria-label="Buscar">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
        </form>
    </div>
</nav>
<br />