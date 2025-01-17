package Servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import GeneralDAO.CriticaDAO;

@WebServlet(name="EliminarCriticaServlet", urlPatterns="/EliminarCriticaServlet")

public class EliminarCriticaServlet extends HttpServlet{

	private static final long serialVersionUID = 4348751409631585512L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");
		
		CriticaDAO critDAO = new CriticaDAO(urlBD, userBD, passBD);
		
		String ID = request.getParameter("idCritica");
		int id = Integer.parseInt(ID);
		critDAO.EliminarCritica(id);
		
		String ruta = request.getContextPath() + "/P3/Vistas/MenuCriticas/TodasCriticasVista.jsp";
		critDAO.desCriticaDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Cr�tica eliminada correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");	
	}
}
