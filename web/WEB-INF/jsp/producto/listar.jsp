<%-- 
    Document   : listar
    Created on : Oct 9, 2017, 4:51:32 PM
    Author     : Santiago Neira <sant.neira@profesor.duoc.cl>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Productos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous" />
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <!-- formulario de búsqueda -->
            <form>
                <div class="form-row align-items-center">
                    <div class="col-6">
                        <label class="sr-only" for="producto">Producto</label>
                        <input type="text" class="form-control form-control-lg mb-2 mb-sm-0" id="producto" placeholder="Ingrese el nombre del producto a buscar">
                    </div>
                    <div class="col-auto">
                        <label class="sr-only" for="categoria">Categoría</label>
                        <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="categoria">
                            <option selected>Escoja una categoría</option>
                            <option value="1">Niños</option>
                            <option value="2">Niñas</option>
                            <option value="3">Hombres</option>
                            <option value="4">Mujeres</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-warning">Buscar</button>
                    </div>
                </div>
            </form>
            <!-- END formulario de búsqueda -->


            <!-- tabla con productos -->
            <table class="table table-striped">
                <thead class="thead-inverse">
                    <tr>
                        <th>ID</th>
                        <th>Imagen</th>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Categoría</th>
                        <th>Fecha Creación</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>1</th>
                        <td>
                            <img src="http://elpoderdelconsumidor.org/wp-content/uploads/2014/12/JuguetesTren-3.jpg" alt="juguete" style="height: 100px; width: auto;" />
                        </td>
                        <td>Tren de madera</td>
                        <td>$15.000.-</td>
                        <td>Niños</td>
                        <td>2017-10-09</td>
                        <td>
                            <form method="get" action="EliminarServlet">
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th>2</th>
                        <td>
                            <img src="https://d243u7pon29hni.cloudfront.net/images/products/juguete-electronico-silverlit-digidi-1308459-5_l.jpg" alt="juguete" style="height: 100px; width: auto;" />
                        </td>
                        <td>Dinosaurio</td>
                        <td>$8.000.-</td>
                        <td>Niños</td>
                        <td>2017-10-09</td>
                        <td>
                            <form method="get" action="EliminarServlet">
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th>3</th>
                        <td>
                            <img src="https://www.tractordejuguete.com/upload_productos/1_img_1062bruder-2750-camion-remolcador.jpg" alt="juguete" style="height: 100px; width: auto;" />
                        </td>
                        <td>Camión grua</td>
                        <td>$19.500.-</td>
                        <td>Niños</td>
                        <td>2017-10-09</td>
                        <td>
                            <form method="get" action="EliminarServlet">
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
