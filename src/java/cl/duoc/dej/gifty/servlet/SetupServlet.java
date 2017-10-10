package cl.duoc.dej.gifty.servlet;

import cl.duoc.dej.gifty.service.SetupService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
@WebServlet(name = "SetupServlet", urlPatterns = {"/SetupServlet"})
public class SetupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parametros = request.getParameterMap();
        // revisa que existan parametros para procesar
        if( !parametros.isEmpty() && parametros.containsKey("operacion") ) {
            String operacion = parametros.get("operacion")[0];
            switch(operacion) {
                case "instalar":
                    instalar(request, response);
                    break;
                case "desinstalar":
                    break;
                case "cargar":
                    break;
            }
        }
        request.getRequestDispatcher("/setup.jsp").forward(request, response);
    }

    private void instalar(HttpServletRequest request, HttpServletResponse response) {
        SetupService setupService = new SetupService();
        boolean resultado = setupService.instalar();
        String[] mensajes = resultado?new String[]{"Se instaló correctamento la aplicación"}:null;
        String[] errores = !resultado?new String[]{"Hubo problemas al instalar"}:null;
        request.setAttribute("mensajes", mensajes);
        request.setAttribute("errores", errores);
    }
    
}
