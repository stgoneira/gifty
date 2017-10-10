<%-- 
    Document   : index
    Created on : Oct 9, 2017, 4:33:39 PM
    Author     : Santiago Neira <sant.neira@profesor.duoc.cl>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Creación y Edición de Productos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous" />
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <div class="row">
                <div class="col col-lg-6">
                    <h1>Cree y Edite Productos</h1>
                    <form>
                        <div class="form-group">
                            <label for="id">ID</label>
                            <input type="number" class="form-control" id="id" name="id" readonly="readonly" aria-describedby="id-help">
                            <small id="id-help" class="form-text text-muted">El ID del producto se autogenera, solo se cargará cuando se edita un producto de manera informativa</small>
                        </div>
                        <div class="form-group">
                            <label for="producto">Producto</label>
                            <input type="text" class="form-control" id="producto" name="producto" placeholder="Ingrese el nombre de su producto" aria-describedby="producto-help">
                            <small id="producto-help" class="form-text text-muted">Ejemplo: Tazón personalizable</small>
                        </div>
                        <div class="form-group">
                            <label for="precio">Precio</label>
                            <div class="input-group">
                                <span class="input-group-addon">$</span>
                                <input type="number" class="form-control" id="precio" name="precio" placeholder="Ingrese el precio del producto" aria-describedby="precio-help">
                            </div><!-- end input-group-->
                            <small id="precio-help" class="form-text text-muted">Ejemplo: Ingresa el precio del producto con sólo números, no comas, puntos ni otro tipo de caracteres.</small>
                        </div>
                        <div class="form-group">
                            <label for="producto">Imagen</label>
                            <div class="input-group">
                                <span class="input-group-addon">http://</span>
                                <input type="text" class="form-control" id="imagen" name="imagen" placeholder="Ingrese la URL de la imagen" aria-describedby="imagen-help">
                            </div><!-- end input-group-->
                            <small id="imagen-help" class="form-text text-muted">Ejemplo: http://www.site.com/a/imagen.jpg</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div><!-- end col-->
        </div><!-- end row-->

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
