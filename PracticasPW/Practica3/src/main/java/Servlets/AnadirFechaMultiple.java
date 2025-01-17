package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import GeneralDTO.FechaDTO;
import javax.servlet.*;
import GeneralDAO.EspectaculoDAO;

@WebServlet(name="AnadirFechaMultiple", urlPatterns="/AnadirFechaMultiple")

public class AnadirFechaMultiple extends HttpServlet{

	private static final long serialVersionUID = 895843397081386258L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO multDAO = new EspectaculoDAO(urlBD,userBD,passBD);		
		int idEsp = Integer.parseInt(request.getParameter("idesp"));
		
		int idFecha = multDAO.GenerarIdFechas();
		String fechaStr = request.getParameter("fecha");
		fechaStr = fechaStr.replace('T', ' ');
		fechaStr = fechaStr + ":00.0";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime Fecha = LocalDateTime.parse(fechaStr, formatter);
		
		FechaDTO newFecha = new FechaDTO(idFecha, Fecha);
		multDAO.CrearFecha(newFecha, idEsp);
		
		String ruta = request.getContextPath() + "/GetMultiplesTemporadaServlet";
		multDAO.desEspectaculoDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Sesi�n a�adida correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");
		
	}
}
